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
import model.Course;
import model.Room;

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
    
    
    
     public static ArrayList<Room> listRooms() throws SQLException{ //throws SQLException {
        initConnection();
        Connection connection = getConnection();
        String query = "SELECT * FROM Rooms";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String capacity = rs.getString("capacity");
                
    
                Room room = new Room(id, number, capacity);
               
    
                rooms.add(room);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return rooms;
    }
     
     public static Room getRoomById(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM rooms WHERE id = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                String capacity = rs.getString("capacity");
                String number = rs.getString("number");
                 
                Room r = new Room(id, number, capacity);
               
                return r;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
     
      public static void updateRoom(Room room){
        initConnection();
        Connection connection = getConnection();
        String query = "UPDATE rooms SET number  = ?, capacity = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, room.getNumber());
            statement.setString(2, room.getCapacity());
            statement.setInt(3, room.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }
    
    
    
    
    
}
