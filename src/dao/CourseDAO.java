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
import model.Professor;
import model.User;

/**
 *
 * @author Amanda
 */
public class CourseDAO extends DataAccessObject{
    
   public static ArrayList<Course> selectAllCourses() throws SQLException{
        initConnection();
        Connection connection = getConnection();
        String query = "SELECT * FROM Courses";
        Statement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Course> courses = new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String description = rs.getString("description");
                
    
                Course c = new Course(id, name, description, code);
                System.out.println("nome: " + c.getName() + "id: " + c.getId());
    
                courses.add(c);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return courses;
   }
    
   public static Course getCourseByName(String course_name) throws SQLException{
       
       initConnection();
        Connection connection = getConnection();
        String query = "SELECT * FROM courses WHERE name = '" + course_name + "'";
        PreparedStatement st = connection.prepareStatement(query);
        
        ResultSet rs = st.executeQuery(query);
        
        try{
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String description = rs.getString("description");
                
    
                Course c = new Course(id, name, description, code);
                return c;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return null;
       
   }
   
    public void insertCourse(String code, String description, String name){
        
        
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO courses (name, code, description) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, code);
            statement.setString(3, description);
            statement.execute();
           
        
            closeConnection();
           
        }
            
         catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        closeConnection();
      
    }
}

