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
import model.Professor;
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
                }/*else if(type.equals("student")){
                   return new User(id, cpf, user_password, new Secretary());
                }*/else if(type.equals("professor")){
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