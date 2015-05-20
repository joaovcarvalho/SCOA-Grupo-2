/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.SecretaryController;
import dao.UserDAO;
import controller.UserController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Professor;
import model.User;
/**
 *
 * @author Amanda
 */
public class SecretaryViewController implements Initializable, ControlledScreen {
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    // PROFESSORES    
    @FXML
    private Pane InsertProfessor;
    @FXML
    private Pane EditProfessor;
    @FXML
    private Pane ListProfessor;
        
    @FXML
    private Hyperlink InsertProf;
    @FXML
    private Label labelTesteInsertProf;
        
    @FXML
    private Label nameInsertProf;
    @FXML
    private Label telInsertProf;
    @FXML
    private Label regInsertProf;
    @FXML
    private Label lattesInsertProf;
    @FXML
    private Label cpfInsertProf;
    @FXML
    private Label roomInsertProf;
    @FXML
    private Label passwordInsertProf;
    @FXML
    private Label confirmInsertProf;
    
    
    @FXML
    private TextField ProfName;
    @FXML
    private TextField ProfReg;
    @FXML
    private TextField ProfCPF;
    @FXML
    private TextField ProfPassword;
    @FXML
    private TextField ProfTel;
    @FXML
    private TextField ProfLattes;
    @FXML
    private TextField ProfRoom;
    @FXML
    private TextField ProfConfirm;
    @FXML
    private Label statusPassword;
    @FXML
    private Label statusEmpty;
    @FXML
    private Label statusSuccess;
    @FXML
    private TableView professorsTable;
    
    
    // CURSOS
    @FXML
    private Pane InsertCourse;
    @FXML
    private TextField CourseName;
    @FXML
    private TextField CourseDescription;
    @FXML
    private TextField CourseCode;
    
    //DISCIPLINAS
    @FXML
    private Pane InsertSubject;
    @FXML
    private TextField SubjectName;
    @FXML
    private TextField SubjectDescription;
    @FXML
    private TextField SubjectCode;
    @FXML
     private TextField SubjectCredits;
    @FXML
     private ComboBox SubjectCourse;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void showEditProfessor(ActionEvent event) throws SQLException {
        hideAllPanes();
        EditProfessor.setVisible(true); 
        //Pane dentro de editProfessor, responsavel pela exibicao do listar
        ListProfessor.setVisible(true); 
        
        ArrayList<Professor> profs_list = SecretaryController.listProf();
        ObservableList<Professor> profs = FXCollections.observableArrayList(profs_list);
        
        professorsTable.setEditable(false);
        System.out.println("oi");
        professorsTable.getItems().setAll(profs);
        
        
        
    }
    
     @FXML
    public void showInsertSubject(ActionEvent event) {
        hideAllPanes();
        InsertSubject.setVisible(true);
    }
    
    @FXML
    public void showInsertProfessor(ActionEvent event) {
        hideAllPanes();
        InsertProfessor.setVisible(true);
    }
    
     @FXML
    public void showInsertCourse(ActionEvent event) {
        hideAllPanes();
        InsertCourse.setVisible(true);
    }
    
    public void hideAllPanes() {
        InsertProfessor.setVisible(false);
        InsertCourse.setVisible(false);
        EditProfessor.setVisible(false);
        InsertCourse.setVisible(false);
        InsertSubject.setVisible(false);
    }
    
     
    @FXML
    private void handleInsertSubjectButton(ActionEvent event) throws SQLException{
         System.out.println("cliquei em inserir disciplina");
    }
    
    @FXML
    private void handleInsertCourseButton(ActionEvent event) throws SQLException{
        System.out.println("cliquei em inserir curso");
        
        String description = CourseDescription.getText() ;
        String name = CourseName.getText() ;
        String code = CourseCode.getText() ;
        SecretaryController.insertCourse(name, code, description);
        
        System.out.println("foi");
    }
    
    
    @FXML
    private void handleInsertProfButton(ActionEvent event) throws SQLException{
      
         statusEmpty.setVisible(false);
         statusPassword.setVisible(false);
         statusSuccess.setVisible(false);
         String name = ProfName.getText();
         String cpf = ProfCPF.getText() ;
         String reg = ProfReg.getText() ;
         String password = ProfPassword.getText() ;
         String tel = ProfTel.getText() ;
         String lattes = ProfLattes.getText() ;
         String room = ProfRoom.getText() ;
         String confirm = ProfConfirm.getText() ;
         int result = SecretaryController.insertProf(cpf,password,confirm, reg, room, tel, lattes, name);
         
         
         if(result == 1){
             statusSuccess.setVisible(true);
         }else if(result == 2){
           statusEmpty.setVisible(true);
         }else if(result ==3){
             statusPassword.setVisible(true); 
         }
    }
    
    
    
    }

   

    

