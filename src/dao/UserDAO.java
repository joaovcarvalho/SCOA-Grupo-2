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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Secretary;
import model.User;

/**
 *
 * @author JoãoVitor
 */
public class UserDAO extends DataAccessObject {
    
    public User getUserByCPFandPassword(String CPF, String password){
        initConnection();
        
        Connection connection = getConnection();
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

                closeConnection();
                return new User(id, cpf, user_password, new Secretary());
            }


        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        return null;
    }
    
    
    
}
