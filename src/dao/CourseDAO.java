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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Professor;
import model.User;

/**
 *
 * @author Amanda
 */
public class CourseDAO extends DataAccessObject{
    
     
    
    public void insertCourse(String code, String description, String name){
        
        
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO courses (name, code, description) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.execute();
           
           /* int id = -1;
            ResultSet rs = statement.getGeneratedKeys();
                if(rs.next())
                {
                    id = rs.getInt(1);
                    
                }
*/            closeConnection();
           
        }
            
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        closeConnection();
      
    }
}

