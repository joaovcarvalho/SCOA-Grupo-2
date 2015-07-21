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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exam;
import model.Professor;
import model.Registration;
import model.StudentGrade;

/**
 *
 * @author JoãoVitor
 */
public class ExamDAO extends DataAccessObject {
    public static ArrayList<Exam> getExamsByRegistrationId(int registrationId) throws SQLException{
        initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT * FROM exams WHERE id_registration = ? ";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, registrationId);
        
        ResultSet rs = st.executeQuery();
        ArrayList<Exam> exams = null;
        try{
            
            while (rs.next()) {
                if(exams == null)
                    exams = new ArrayList<Exam>();
                            
                String grade = rs.getString("grade");
                String description = rs.getString("description");
                Date delivery_date = rs.getDate("delivery_date");
                int id = rs.getInt("id");
                Registration registration = RegistrationDAO.getRegistrationById(registrationId);
                Exam exam = new Exam(id, description, grade, delivery_date, registration);
                
                exams.add(exam);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return exams;
    }
    
    public static void insertExam(Exam exam){
        initConnection();
        Connection connection = getConnection();
        String query = "INSERT INTO exams (grade, description, delivery_date, id_registration) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exam.getGrade());
            statement.setString(2, exam.getDescription());
            statement.setDate(3, exam.getDelivery_date());
            statement.setInt(4, exam.getRegistration().getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
    
    public static void editExam(Exam exam){
        initConnection();
        Connection connection = getConnection();
        String query = "UPDATE exams SET grade = ?, description = ? , delivery_date = ?, id_registration = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exam.getGrade());
            statement.setString(2, exam.getDescription());
            statement.setDate(3, exam.getDelivery_date());
            statement.setInt(4, exam.getRegistration().getId());
            statement.setInt(5, exam.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
      
    }

    public static void deleteExam(Exam exam) {
        initConnection();
        Connection connection = getConnection();
        String query = "DELETE FROM exams WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, exam.getId());
            statement.execute();
           
            closeConnection();
        }
            
         catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        closeConnection();
    }
    
    public static ArrayList<StudentGrade> getStudentGradeById(int student_id) throws SQLException{
         initConnection();
        
        Connection connection = getConnection();
        String query = "SELECT name,grade FROM (SELECT subjects.name , classes.id as class_id FROM subjects JOIN classes ON subjects.id = classes.id_subject) as nome "
                + "JOIN (SELECT id_class , AVG(grade) as grade from exams JOIN registrations ON registrations.id = exams.id_registration WHERE id_student = ? GROUP BY id_class) "
                + "as nota ON nome.class_id = nota.id_class";
        
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, student_id);
        ResultSet rs = st.executeQuery();
        ArrayList<StudentGrade> student_grade = new ArrayList<StudentGrade>();
        try{
            while (rs.next()) {
                String name = rs.getString("name");
                int grade = rs.getInt("grade");
                
                StudentGrade s = new StudentGrade(name, grade);
 
                student_grade.add(s);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        closeConnection();     
        return student_grade;
    }

}
