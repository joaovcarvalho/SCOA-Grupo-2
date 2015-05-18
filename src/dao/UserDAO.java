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


public class UserDAO extends DataAccessObject {
    
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
            System.out.println("1");
            if(rs.next()){
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String user_password = rs.getString("password");
                String type = rs.getString("type");
                int type_id = rs.getInt("type_id");
                String name = "";
                //Verifica qual o tipo , e pega as informacoes do tipo especifico
                //de usuario no banco
                if(type.equals("secretary")){
                   System.out.println("secretaria");
                   String q = "SELECT * FROM secretaries WHERE id = ?";
                    PreparedStatement s = connection.prepareStatement(q);
                    s.setInt(1, type_id);
                    ResultSet r = s.executeQuery();
                   
                    if(r.next()){
                        name = r.getString("name");
                    }
                    
                }/*else if(type.equals("student")){
                   return new User(id, cpf, user_password, new Secretary());
                }else if(type.equals("professor")){
                    return new User(id, cpf, user_password, new Professor());
                }*/
                    closeConnection();
                    return new User(id, cpf, user_password, new Secretary(name, type_id));                     
            }
            System.out.println("2");
            
        }
        
         catch (SQLException ex) {
               System.out.println("3");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        System.out.println("4");
        
        closeConnection();
        return null;
    }
    
    
    
}
