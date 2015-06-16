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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;


/**
 *
 * @author JoãoVitor
 */
public class FeedbackDAO extends DataAccessObject {
   
    
    public static void insertFeedback(Feedback  feedback){
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO feedbacks (description,type) VALUES (?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, feedback.getDescription());
            statement.setString(2, feedback.getType());
            
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }
    
   
}
