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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;

/**
 *
 * @author Amanda
 */
public class RoomDAO extends DataAccessObject{
    
    public static void insertRoom(String number, String capacity) throws SQLException{
        initConnection();
        Connection connection = getConnection();
        
        String query = "INSERT INTO rooms (number, capacity) VALUES (?,?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            statement.setString(2, capacity);
            
            statement.execute();
           
            closeConnection();            
        }
            
         catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        closeConnection();  
     }
    
    
}
