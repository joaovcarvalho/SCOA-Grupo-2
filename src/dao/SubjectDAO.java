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
import model.Course;
import model.Subject;

/**
 *
 * @author Amanda
 */
public class SubjectDAO extends DataAccessObject{
    
     public static void insertSubject(String code, String description, String name, String credits, String courseName) throws SQLException{
        initConnection();
        Connection connection = getConnection();
        Course c = CourseDAO.getCourseByName(courseName);
        
        String query = "INSERT INTO subjects (name, code, description, credits, course_id) VALUES (?,?,?,?,?)";
        
        
        
         try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.setString(4, credits);
            statement.setInt(5, c.getId());
            statement.execute();
           
            closeConnection();
            
        }
            
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        closeConnection();  
     }
    
    
}
