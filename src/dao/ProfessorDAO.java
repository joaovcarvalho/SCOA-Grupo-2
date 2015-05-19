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
public class ProfessorDAO extends DataAccessObject{
    
    
    
      public static ArrayList<Professor> listProfessors() throws SQLException{ //throws SQLException {
        initConnection();
        Connection connection = getConnection();
        String query = "SELECT * FROM Professors";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Professor> profs = new ArrayList<>();
        try{
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("register");
                String register = rs.getString("room");
                String room = rs.getString("telephone");
                String telephone = rs.getString("lattes");
                String lattes = rs.getString("name");
    
                Professor prof = new Professor(id, register, room, telephone,  lattes,  name);
                
    
                profs.add(prof);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return profs;
    }
    
    
    
    
    
    public void insertUser(String cpf, String password, String type, int type_id){
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
    
    
    public void insertProfessor(String cpf, String password, String register, String room, String telephone, String lattes, String name){
        
        
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO professors (register, room, telephone, lattes, name) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, register);
            statement.setString(2, room);
            statement.setString(3, telephone);
            statement.setString(4, lattes);
            statement.setString(5, name);
            statement.execute();
           
            int id = -1;
            ResultSet rs = statement.getGeneratedKeys();
                if(rs.next())
                {
                    id = rs.getInt(1);
                    
                }
            closeConnection();
           
            insertUser(cpf, password, "professor", id);

             
             //return new Professor(id, register, room, telephone, lattes, name);
        }
            
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }
}

