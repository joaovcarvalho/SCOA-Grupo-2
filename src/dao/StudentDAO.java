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
}
