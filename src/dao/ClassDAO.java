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
        String query = "SELECT * FROM classes LEFT JOIN professors ON professors.id = classes.id_professor"
                + " WHERE professors.id = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        ArrayList<Class> classes = new ArrayList<>();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
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
    
    
}
