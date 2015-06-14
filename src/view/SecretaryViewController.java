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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Course;
import model.Professor;
import model.Room;
import model.Subject;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 //        name.setCellValueFactory(new PropertyValueFactory("name"));

    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    
    // ***************************** PROFESSORES *****************************   
    @FXML
    private Pane InsertProfessor;
    @FXML
    private Pane EditProfessor;
    @FXML
    private Pane ListProfessor;
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
    @FXML
    private TableColumn name;
    
    @FXML
    public void showInsertProfessor(ActionEvent event) {
        hideAllPanes();
        InsertProfessor.setVisible(true);
    }
    
    @FXML
    public void showEditProfessor(ActionEvent event) throws SQLException {
        hideAllPanes();
        
        //Pane dentro de editProfessor, responsavel pela exibicao do listar
       
        ArrayList<Professor> profs_list = SecretaryController.listProf();
        
        
        professorsTable.setEditable(false);
        
       //professorsTable.getColumns().setAll(profs);
        professorsTable.setEditable(true);
              
        TableColumn nameCol = new TableColumn("Nome");
       nameCol.setCellValueFactory(
                        new PropertyValueFactory<Professor,String>("name")
        );
        TableColumn telephoneCol = new TableColumn("Telefone");
        telephoneCol.setCellValueFactory(
                         new PropertyValueFactory<Professor,String>("telephone")
        );

       TableColumn lattesCol = new TableColumn("Lattes");
       lattesCol.setCellValueFactory(
                         new PropertyValueFactory<Professor,String>("lattes")
        );

        TableColumn registerCol = new TableColumn("Registro");
        registerCol.setCellValueFactory(
                         new PropertyValueFactory<Professor,String>("register")
        );
                
        TableColumn roomCol = new TableColumn("Sala");
                roomCol.setCellValueFactory(
                        new PropertyValueFactory<Professor,String>("room")
        );
                
      
                
                if(profs_list != null){
                    ObservableList<Professor> profs = FXCollections.observableArrayList(profs_list);
                    profs.addAll(profs_list);

                    professorsTable.setItems(profs);
                }


                professorsTable.getColumns().addAll(nameCol, telephoneCol, lattesCol, registerCol, roomCol);
 
       // professorsTable.setItems(profs);
        //professorsTable.getItems().setAll(profs);
        ListProfessor.setVisible(true); 
        EditProfessor.setVisible(true); 
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
    
    // ***************************** CURSOS ******************************
    @FXML
    private Pane InsertCourse;
    @FXML
    private TextField CourseName;
    @FXML
    private TextField CourseDescription;
    @FXML
    private TextField CourseCode;
    @FXML
    private Pane EditCourse;
    @FXML
    private Pane ListCourse;
    @FXML 
    private TableView coursesTable;
    
    
    @FXML
    public void showInsertCourse(ActionEvent event) {
        hideAllPanes();
        InsertCourse.setVisible(true);
    }
    
     @FXML
    public void showEditCourses(ActionEvent event) throws SQLException {
         ArrayList<Course> course_list = SecretaryController.listCourse();
        
        
        coursesTable.setEditable(true);
              
        TableColumn nameCol = new TableColumn("Nome");
        nameCol.setCellValueFactory(
                        new PropertyValueFactory<Course,String>("name")
        );
        TableColumn codeCol = new TableColumn("Código");
        codeCol.setCellValueFactory(
                         new PropertyValueFactory<Course,String>("code")
        );

       TableColumn descriptionCol = new TableColumn("Descrição");
       descriptionCol.setCellValueFactory(
                         new PropertyValueFactory<Course,String>("description")
        );

                
                if(course_list != null){
                    ObservableList<Course> courses = FXCollections.observableArrayList(course_list);
                    courses.addAll(course_list);

                    coursesTable.setItems(courses);
                }


                coursesTable.getColumns().addAll(nameCol, codeCol, descriptionCol);
 
       // professorsTable.setItems(profs);
        //professorsTable.getItems().setAll(profs);
        ListCourse.setVisible(true); 
        EditCourse.setVisible(true); 
    }
    
     @FXML
    private void handleInsertCourseButton(ActionEvent event) throws SQLException{
        System.out.println("cliquei em inserir curso");
        
        String description = CourseDescription.getText() ;
        String name = CourseName.getText() ;
        String code = CourseCode.getText() ;
        SecretaryController.insertCourse(name, code, description);
       
    }
    
    //***************************** DISCIPLINAS *****************************
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
    @FXML
     private TableView subjectTable;
    @FXML
     private Pane EditSubject;
    @FXML
     private Pane ListSubject;
    
    
    @FXML
    public void showInsertSubject(ActionEvent event) throws SQLException {
        hideAllPanes();
        
        SubjectCourse.getItems().clear();

        ArrayList<Course> c_list = SecretaryController.listCourses();
        for (Course c : c_list) {  
           SubjectCourse.getItems().add(c.getName());
        }   
        
        InsertSubject.setVisible(true);
    }
     @FXML
    private void handleInsertSubjectButton(ActionEvent event) throws SQLException{
         
         String name = SubjectName.getText();
         String code = SubjectCode.getText();
         String description = SubjectDescription.getText();
         String credits = SubjectCredits.getText();
         String courseName = SubjectCourse.getSelectionModel().selectedItemProperty().getValue().toString();
         
          
         SecretaryController.insertSubject(code, description, name, credits, courseName);
    }
    
     @FXML
    public void showEditSubject(ActionEvent event) throws SQLException {
         ArrayList<Subject> sub_list = SecretaryController.listSubject();
        
        
        subjectTable.setEditable(true);
              //name, description, course, code, credits
        TableColumn nameCol = new TableColumn("Nome");
        nameCol.setCellValueFactory(
                        new PropertyValueFactory<Subject,String>("name")
        );
        TableColumn codeCol = new TableColumn("Código");
        codeCol.setCellValueFactory(
                         new PropertyValueFactory<Subject,String>("code")
        );

       TableColumn descriptionCol = new TableColumn("Descrição");
       descriptionCol.setCellValueFactory(
                         new PropertyValueFactory<Subject,String>("description")
        );
       TableColumn courseCol = new TableColumn("Curso");
        courseCol.setCellValueFactory(
                         new PropertyValueFactory<Subject,String>("course")
        );

       TableColumn creditsCol = new TableColumn("Créditos");
       creditsCol.setCellValueFactory(
                         new PropertyValueFactory<Subject,String>("credits")
        );

                
                if(sub_list != null){
                    ObservableList<Subject> subs = FXCollections.observableArrayList(sub_list);
                    subs.addAll(sub_list);

                    subjectTable.setItems(subs);
                }


                subjectTable.getColumns().addAll(nameCol, codeCol, descriptionCol,courseCol, creditsCol);
 
       // professorsTable.setItems(profs);
        //professorsTable.getItems().setAll(profs);
        ListSubject.setVisible(true); 
        EditSubject.setVisible(true); 
    }
    
    // ***************************** TURMAS *****************************
    @FXML
    private Pane InsertClass;
    @FXML
    private TextField RoomName;
    @FXML
    private TextField RoomDescription;
    @FXML
     private ComboBox ClassProfessor;
    @FXML
     private ComboBox ClassSubject;
    @FXML
     private ComboBox ClassRoom;
    
    @FXML
    public void showInsertClass(ActionEvent event) throws SQLException {
        hideAllPanes();
        //Professor
        ClassProfessor.getItems().clear();
       
        ArrayList<Professor> p_list = SecretaryController.listProf();
        for (Professor p : p_list) {  
           ClassProfessor.getItems().add(p.getName());
        } 
        //Sala
        ClassRoom.getItems().clear();
       
        ArrayList<Room> r_list = SecretaryController.listRooms();
        for (Room r : r_list) {  
           ClassRoom.getItems().add(r.getNumber());
        } 
        
        //Disciplina
        ClassSubject.getItems().clear();
       
        ArrayList<Subject> s_list = SecretaryController.listSubjects();
        
        for (Subject s : s_list) {  
          ClassSubject.getItems().add(s.getName());
        } 
        
        
        InsertClass.setVisible(true);
        
    }
    
    //***************************** SALAS *****************************
    @FXML
    private Pane InsertRoom;
    @FXML
    private TextField RoomCapacity;
    @FXML
    private TextField RoomNumber;
    
     @FXML
    public void showInsertRoom(ActionEvent event) {
        hideAllPanes();
        InsertRoom.setVisible(true);
    }
    
      @FXML
    private void handleInsertRoomButton(ActionEvent event) throws SQLException{
         String number = RoomNumber.getText();
         String capacity = RoomCapacity.getText();
          
         SecretaryController.insertRoom(number, capacity);
    }
    
    // ***************************** 
    
    public void hideAllPanes() {
        InsertProfessor.setVisible(false);
        InsertCourse.setVisible(false);
        EditProfessor.setVisible(false);
        EditCourse.setVisible(false);
        ListProfessor.setVisible(false);
        ListCourse.setVisible(false);
        InsertCourse.setVisible(false);
        InsertSubject.setVisible(false);
        InsertClass.setVisible(false);
        InsertRoom.setVisible(false);
        EditSubject.setVisible(false);
        ListSubject.setVisible(false);
        
    }
    
 
    
    }

   

    

