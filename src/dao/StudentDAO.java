/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DataAccessObject.closeConnection;
import static dao.DataAccessObject.getConnection;
import static dao.DataAccessObject.initConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Professor;
import model.Room;
import model.Student;
import model.Subject;
import model.User;

/**
 *
 * @author JoãoVitor
 */
public class StudentDAO extends DataAccessObject {
    public static ArrayList<Student> getStudentsByClassId(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM students JOIN registrations ON students.id = registrations.id_student"
                + " WHERE registrations.id_class = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String register = rs.getString("register");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birth_date = rs.getString("birth_date");
                int semester = rs.getInt("semester");
                
                Student s = new Student(id, name, telephone, register, address, email, birth_date, semester);
 
                students.add(s);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return students;
    }
    
    public static Student getStudentByEmail(String e) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM students WHERE email = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, e);
        ResultSet rs = st.executeQuery();
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String register = rs.getString("register");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birth_date = rs.getString("birth_date");
                int semester = rs.getInt("semester");
                
                Student s = new Student(id, name, telephone, register, address, email, birth_date, semester);
                User user = UserDAO.getUserByType(s);
                s.setUser(user);
                
                return s;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
    
    public static ArrayList<Student> all() throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM students";
        
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String telephone = rs.getString("telephone");
                String register = rs.getString("register");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birth_date = rs.getString("birth_date");
                int semester = rs.getInt("semester");
                
                Student s = new Student(id, name, telephone, register, address, email, birth_date, semester);
 
                User u = UserDAO.getUserByType(s);
                s.setUser(u);
                
                students.add(s);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return students;
    }
    
    public static void insertStudent(Student s){
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO students (register, telephone, address, email, birth_date, semester, name) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, s.getRegister());
            statement.setString(2, s.getTelephone());
            statement.setString(3, s.getAddress());
            statement.setString(4, s.getEmail());
            statement.setDate(5, s.getBirthDate());
            statement.setInt(6, s.getSemester());
            statement.setString(7, s.getName());
            statement.execute();
           
            int id = -1;
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);

            }
            closeConnection();
           
            User u = s.getUser();
            UserDAO.insertUser(u.getCpf(), u.getPassword(), "student", id);

             //return new Professor(id, register, room, telephone, lattes, name);
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
    
    public static void updateStudent(Student s){
        initConnection();
        Connection connection = getConnection();
        String query = "UPDATE students SET register = ?, telephone = ?, "
                + "address = ?, email = ?, birth_date = ?, semester = ?, name = ? "
                + "WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, s.getRegister());
            statement.setString(2, s.getTelephone());
            statement.setString(3, s.getAddress());
            statement.setString(4, s.getEmail());
            statement.setDate(5, s.getBirthDate());
            statement.setInt(6, s.getSemester());
            statement.setString(7, s.getName());
            statement.setInt(8, s.getId());
            statement.execute();
           
            int id = -1;
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);

            }
            closeConnection();
           
            User u = s.getUser();
            UserDAO.updateUser(u);

             //return new Professor(id, register, room, telephone, lattes, name);
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
    
    public static void deleteStudent(Student s) {
        initConnection();
        Connection connection = getConnection();
        String query = "DELETE FROM students WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, s.getId());
            statement.execute();
           
            closeConnection();
            
            UserDAO.deleteUser(s.getUser());
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
}
