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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Professor;
import model.Registration;

/**
 *
 * @author JoãoVitor
 */
public class RegistrationDAO extends DataAccessObject {
    
    public static Registration getRegistrationById(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM registrations WHERE id = ? ";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                int id_class = rs.getInt("id_class");
                int id_student = rs.getInt("id_student");
                
                return new Registration(id, id_student, id_class);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
    
    public static Registration getRegistrationByStudentIdAndClassId(int studentId, int classId) throws SQLException{
        initConnection();
        
        System.out.println(studentId);
        System.out.println(classId);
        Connection connection = getConnection();
        String query = "SELECT * FROM registrations WHERE id_student = ? AND id_class = ? ";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, studentId);
        st.setInt(2, classId);
        
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_class = rs.getInt("id_class");
                int id_student = rs.getInt("id_student");
                
                return new Registration(id, id_student, id_class);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
}
