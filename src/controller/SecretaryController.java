/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import dao.ProfessorDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Professor;

/**
 *
 * @author Amanda
 */
public class SecretaryController {
    
    static ProfessorDAO p = new ProfessorDAO();
    static CourseDAO c = new CourseDAO();
       
       public static void insertCourse(String name, String code, String description){
           
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
       
     public static ArrayList<Professor>listProf() throws SQLException{
        return ProfessorDAO.listProfessors();
    }
  }  
     
  

