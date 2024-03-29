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
        String query = "SELECT * FROM professors";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Professor> profs = new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String register = rs.getString("register");
                String room = rs.getString("room");
                String telephone = rs.getString("telephone");
                String lattes = rs.getString("lattes");
    
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
    
        public static void updateProfessor(Professor prof){
        initConnection();
        Connection connection = getConnection();
        String query = "UPDATE professors SET register = ?, room = ? , telephone = ?, lattes = ?,  name = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, prof.getRegister());
            statement.setString(2, prof.getRoom());
            statement.setString(3, prof.getTelephone());
            statement.setString(4, prof.getLattes());
            statement.setString(5, prof.getName());
            statement.setInt(6, prof.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
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
           
            UserDAO.insertUser(cpf, password, "professor", id);

             
             //return new Professor(id, register, room, telephone, lattes, name);
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }
    
    public static Professor getProfessorById(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM professors WHERE id = ? ";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
                String register = rs.getString("register");
                String room = rs.getString("room");
                String telephone = rs.getString("telephone");
                String lattes = rs.getString("lattes");
                Professor p = new Professor(id, register, room, telephone, lattes, name);
               
                return p;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
    
    
    
     public static void deleteProfessor(Professor professor) {
        initConnection();
        Connection connection = getConnection();
        String query = "DELETE FROM professors WHERE id = ?";
        User u = UserDAO.getUserByType(professor);
        UserDAO.deleteUser(u);
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, professor.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
}

