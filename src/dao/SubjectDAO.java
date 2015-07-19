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
import model.Course;
import model.Subject;

/**
 *
 * @author Amanda
 */
public class SubjectDAO extends DataAccessObject{
    
     public static void insertSubject(String code, String description, String name, String credits, String courseName) throws SQLException{
        initConnection();
        Connection connection = getConnection();
        Course c = CourseDAO.getCourseByName(courseName);
        
        String query = "INSERT INTO subjects (name, code, description, credits, course_id) VALUES (?,?,?,?,?)";
        
        
        
         try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.setString(4, credits);
            statement.setInt(5, c.getId());
            statement.execute();
           
            closeConnection();
            
        }
            
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        closeConnection();  
     }
     
     
     public static ArrayList<Subject> listSubjects() throws SQLException{ 
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM subjects";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Subject> subs = new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int course_id = rs.getInt("course_id");
                String code = rs.getString("code");
                String credits = rs.getString("credits");
     
                Subject sub = new Subject(id, name, description, code, credits, course_id);
                sub.setCourse(CourseDAO.getCourseById(course_id));
    
                subs.add(sub);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return subs;
    }
     
    public static Subject getSubjectById(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM subjects WHERE id = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Course course = CourseDAO.getCourseById( rs.getInt("course_id"));
                String code = rs.getString("code");
                String credits = rs.getString("credits");
                 
                Subject sub = new Subject(id, name, description, code, credits, -1);
               
                return sub;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
    }
    
    public static ArrayList<Subject> getSubjectByCourseId(int id) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM subjects WHERE course_id = ?";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, id);
        
        ResultSet rs = st.executeQuery();
        ArrayList<Subject> subjects = new ArrayList<>();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Course course = CourseDAO.getCourseById( rs.getInt("course_id"));
                String code = rs.getString("code");
                String credits = rs.getString("credits");
                 
                Subject sub = new Subject(id, name, description, code, credits, -1);
               
                subjects.add( sub);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();
        return subjects;
    }
    
     
     
     
    
    
}
