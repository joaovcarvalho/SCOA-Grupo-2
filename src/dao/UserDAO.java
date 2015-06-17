/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Professor;
import model.Secretary;
import model.Student;
import model.Type;
import model.User;


public class UserDAO extends DataAccessObject {
    
    public static void insertUser(String cpf, String password, String type, int type_id){
        initConnection();
        
        Connection connection = getConnection();
        String query = "INSERT INTO users (cpf, password, type, type_id) VALUES (?, ?,?,?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cpf);
            statement.setString(2, password);
            statement.setString(3, type);
            statement.setInt(4, type_id);
            
            statement.execute();
            
           
                
             closeConnection();                    
        }
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        
    }
    
     public static void updateUser(User u){
        initConnection();
        Connection connection = getConnection();
        String query = "UPDATE users SET cpf = ?, password = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, u.getCpf());
            statement.setString(2, u.getPassword());
            statement.setInt(3, u.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }
    
    
    public static User getUserByType(Type t){
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM Users WHERE type_id = ? AND type = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, t.getId());
            if(t instanceof Professor)
               st.setString(2, "professor");
            else if(t instanceof Student)
                st.setString(2, "student");
       
        
          ResultSet rs = st.executeQuery();
        
            if (rs.next()) {
                String cpf = rs.getString("cpf");
                String password = rs.getString("password");
                User u = new User(t.getId(), cpf, password,t);
               
                return u;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
    
    public User getUserByCPFandPassword(String CPF, String password){
        initConnection();
        
        Connection connection = getConnection();
        //Pega o usuario do banco
        String query = "SELECT * FROM users WHERE cpf = ? AND password = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, CPF);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery(); 
            if(rs.next()){
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String user_password = rs.getString("password");
                String type = rs.getString("type");
                int type_id = rs.getInt("type_id");
                
                //Verifica qual o tipo , e pega as informacoes do tipo especifico
                //de usuario no banco
                if(type.equals("secretary")){
                   String q = "SELECT * FROM secretaries WHERE id = ?";
                    PreparedStatement s = connection.prepareStatement(q);
                    s.setInt(1, type_id);
                    ResultSet r = s.executeQuery();
                    
                    String name = "";
                    if(r.next()){
                        name = r.getString("name");
                    }
                    
                    closeConnection();
                    return new User(id, cpf, user_password, new Secretary(name, type_id));   
                }else if(type.equals("student")){
                    String queryType = "SELECT * FROM students WHERE id = ?";
                    PreparedStatement typeStatment = connection.prepareStatement(queryType);
                    typeStatment.setInt(1, type_id);
                    ResultSet r = typeStatment.executeQuery();
                    Student student = null;
                   
                    if(r.next()){
                        student = new Student();
                        student.setName(r.getString("name"));
                        student.setAddress(r.getString("address"));
                        student.setBirth_date(r.getString("birth_date"));
                        student.setEmail(r.getString("email"));
                        student.setRegister(r.getString("register"));
                        student.setSemester(r.getInt("semester"));
                        student.setTelephone(r.getString("telephone"));
                                               
                    }
                    return new User(id, cpf, user_password, student);
                }else if(type.equals("professor")){
                    String queryType = "SELECT * FROM professors WHERE id = ?";
                    PreparedStatement typeStatment = connection.prepareStatement(queryType);
                    typeStatment.setInt(1, type_id);
                    ResultSet r = typeStatment.executeQuery();
                    Professor professor = null;
                    
                    if(r.next()){
                        professor = new Professor();
                        professor.setName(r.getString("name"));
                        professor.setId(r.getInt("id"));
                        professor.setRegister(r.getString("register"));
                        professor.setRoom(r.getString("room"));
                        professor.setLattes(r.getString("lattes"));
                        professor.setTelephone(r.getString("telephone"));
                    }
                    
                    return new User(id, cpf, user_password, professor);
                };
                                      
            }
            
        }
        
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
        return null;
    }
    
    
    
}
