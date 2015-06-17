/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClassDAO;
import dao.CourseDAO;
import dao.ProfessorDAO;
import dao.RoomDAO;
import dao.SubjectDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Course;
import model.Professor;
import model.Room;
import model.Subject;
import tools.Validator;

/**
 *
 * @author Amanda
 */
public class SecretaryController {
    
    static ProfessorDAO p = new ProfessorDAO();
    static CourseDAO c = new CourseDAO();
    static SubjectDAO s = new SubjectDAO();
    static RoomDAO r = new RoomDAO();
    
    /******************************INSERIR****************************************/

    public static void insertCourse(String name, String code, String description) throws MissingFieldException{
        
        if(     !Validator.validateRequired(name) || 
                !Validator.validateRequired(description) ||
                !Validator.validateRequired(code))
            throw new MissingFieldException();
      
       
           c.insertCourse(code, description, name);
       }
       
       public static int insertProf(String cpf, String password,String confirm, String register, String room, String telephone, String lattes, String name){
           // Validar os campos também, nesse if
          if(password.equals(confirm) && !cpf.isEmpty()&& !password.isEmpty()&& !confirm.isEmpty()&& !register.isEmpty()
                  && !room.isEmpty()&& !telephone.isEmpty()&& !lattes.isEmpty()&& !name.isEmpty()){
            
              p.insertProfessor(cpf, password, register, room, telephone, lattes, name);
            
             return 1;
          }else if(!password.equals(confirm)){
              return 2;
          }
          else{
              return 3;
          }
    }
       
    
     public static void insertRoom(String number, String capacity) throws SQLException, MissingFieldException, InvalidFieldException{
          if(   !Validator.validateRequired(number) || 
                !Validator.validateRequired(capacity) )
            throw new MissingFieldException();
          
            if(!Validator.validateRequired(capacity)|| !Validator.validateNumber(number))
               throw new InvalidFieldException("Todos os campos devem ser preenchidos com números.");
            
        r.insertRoom(number, capacity);     
     }
     
     public static void insertSubject(String code, String description, String name, String credits, String courseName) throws SQLException, MissingFieldException, InvalidFieldException{
         if(     !Validator.validateRequired(code) || 
                 !Validator.validateRequired(description) ||
                 !Validator.validateRequired(name) ||
                 !Validator.validateRequired(credits) ||
                 !Validator.validateRequired(courseName))
            throw new MissingFieldException();
         
         if (!Validator.validateRequired(credits)) {
             throw new InvalidFieldException("Todos os campos devem ser preenchidos com números.");
         }
         
         
         
         s.insertSubject(code, description, name, credits, courseName);
     }
     
     
     
     /******************************LISTAR****************************************/
     
     public static ArrayList<Course>listCourses() throws SQLException{
        return CourseDAO.selectAllCourses();
    }
     
     public static ArrayList<Room>listRooms() throws SQLException{
         return RoomDAO.listRooms();
     }
       
     public static ArrayList<Subject>listSubjects() throws SQLException{
         return SubjectDAO.listSubjects();
     }
      public static ArrayList<Professor>listProf() throws SQLException{
        return ProfessorDAO.listProfessors();
    }
     
     public static ArrayList<Course>listCourse() throws SQLException{
         return CourseDAO.listCourses();
     }
     
     public static ArrayList<Subject>listSubject() throws SQLException{
         return SubjectDAO.listSubjects();
     }
      public static ArrayList<model.Class>listClass() throws SQLException{
         return ClassDAO.listClasses();
     }
     
     public static ArrayList<Room>listRoom() throws SQLException{
         return RoomDAO.listRooms();
     }
     
  }  
     
  

