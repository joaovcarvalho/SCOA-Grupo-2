/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfessorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Professor;

/**
 *
 * @author Amanda
 */
public class SecretaryController {
    
    static ProfessorDAO p = new ProfessorDAO();
       
    
       public static void insertProf(String cpf, String password,String confirm, String register, String room, String telephone, String lattes, String name){
           // Validar os campos
           // Conferir de password e confirm batem
          
           p.insertProfessor(cpf, password, register, room, telephone, lattes, name);
       
          System.out.println("foi");
    }
  
}
