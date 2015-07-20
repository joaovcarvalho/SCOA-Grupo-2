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
import model.Subject;

import model.Class;
import model.Professor;
import model.Room;

/**
 *
 * @author Amanda
 */
public class ClassDAO extends DataAccessObject {
    
    
    public static ArrayList<Class> getClassByProfessorId(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM classes JOIN professors ON professors.id = classes.id_professor"
                + " WHERE professors.id = ?";
        
        PreparedStatement st = connection.prepareStatement(query) ;
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        ArrayList<Class> classes = new ArrayList<>();
        try{
            while (rs.next()) {
                int semester = rs.getInt("semester");
                Professor p = ProfessorDAO.getProfessorById( rs.getInt("id_professor") );
                Room r = RoomDAO.getRoomById( rs.getInt("id_room") );
                Subject s = SubjectDAO.getSubjectById( rs.getInt("id_subject") );
                
                Class c = new Class(id, p, r, s, semester);
 
                classes.add(c);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return classes;
    }
    
    public static Class getClassById(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM classes WHERE id = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                int semester = rs.getInt("semester");
                Professor p = ProfessorDAO.getProfessorById( rs.getInt("id_professor") );
                Room r = RoomDAO.getRoomById( rs.getInt("id_room") );
                Subject s = SubjectDAO.getSubjectById( rs.getInt("id_subject") );
                
                Class c = new Class(id, p, r, s, semester);
                return c;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();  
        return null;
    }
    
    
    
     public static ArrayList<model.Class> listClasses() throws SQLException{ 
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM classes";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<model.Class> classes = new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_professor = rs.getInt("id_professor");
                int id_room = rs.getInt("id_room");
                int id_subject = rs.getInt("id_subject");
                int semester = rs.getInt("semester");
     
                Professor p = ProfessorDAO.getProfessorById(id_professor);
                Room r = RoomDAO.getRoomById(id_room);
                Subject s = SubjectDAO.getSubjectById(id_subject);
                 
                Class c = new Class(id, p, r, s, semester);
               
    
                classes.add(c);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return classes;
    }
     
       public static void insertClass(int profId, int roomId, int subjectId, String semester) throws SQLException{
        initConnection();
        Connection connection = getConnection();
        
        
        String query = "INSERT INTO classes (id_professor, id_room, id_subject, semester) VALUES (?,?,?,?)";
        
        
        
         try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, profId);
            statement.setInt(2, roomId);
            statement.setInt(3, subjectId);
            statement.setString(4, semester);
            statement.execute();
           
            closeConnection();
            
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        closeConnection();  
     }
    
    
}
