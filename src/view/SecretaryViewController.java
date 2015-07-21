/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.SecretaryController;
import dao.UserDAO;
import controller.UserController;
import dao.ClassDAO;
import dao.CourseDAO;
import dao.ProfessorDAO;
import dao.RoomDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.JOptionPane;
import model.Course;
import model.Class;
import model.Professor;
import model.Room;
import model.Student;
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
    private Pane inputProfessor;
    @FXML
    private TextField nameProfTA;
    @FXML
    private TextField regProfTA;
    @FXML
    private TextField cpfProfTA;
    @FXML
    private TextField telProfTA;
    @FXML
    private TextField lattesProfTA;
    @FXML
    private TextField roomProfTA;
    
    
    Professor selectedProf;
    Course selectedCourse;
    Room selectedRoom;
    Subject selectedSubject;
    Student selectedStudent;
    Class selectedClass;
    
    @FXML
    public void handleEditProfButton(){
        Professor prof = (Professor) professorsTable.getSelectionModel().getSelectedItem();
        
        hideAllPanes();
        selectedProf = prof;
        nameProfTA.setText(prof.getName());
   
        regProfTA.setText(prof.getName());
        User u = UserDAO.getUserByType(prof);
        cpfProfTA.setText(u.getCpf());
        telProfTA.setText(prof.getTelephone());
        lattesProfTA.setText(prof.getLattes());
        roomProfTA.setText(prof.getRoom());
        
        EditProfessor.setVisible(true);
        inputProfessor.setVisible(true);
    } 
    
    @FXML
    public void showInsertProfessor(ActionEvent event) {
        hideAllPanes();
        InsertProfessor.setVisible(true);
    }
    
    @FXML
    public void showEditProfessor(ActionEvent event) throws SQLException {
        hideAllPanes();
        professorsTable.getColumns().clear();
        //Pane dentro de editProfessor, responsavel pela exibicao do listar
       
        ArrayList<Professor> profs_list = SecretaryController.listProf();
        
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
            ObservableList<Professor> profs = FXCollections.observableArrayList();
            profs.addAll(profs_list);
            professorsTable.getItems().clear();
            professorsTable.setItems(profs);
        }

        
        professorsTable.getColumns().addAll(nameCol, telephoneCol, lattesCol, registerCol, roomCol);
 
       // professorsTable.setItems(profs);
        //professorsTable.getItems().setAll(profs);
        ListProfessor.setVisible(true); 
        EditProfessor.setVisible(true); 
    }
    
    @FXML
    public TextField ProfessorEditPassword;
    
    @FXML
    public TextField ProfessorConfirmPasswordEdit;
    
    @FXML
    private void handleUpdateProfButton(){
         String name = nameProfTA.getText();
         String cpf = cpfProfTA.getText() ;
         String reg = regProfTA.getText() ;
         String password = ProfessorEditPassword.getText() ;
         String tel = telProfTA.getText() ;
         String lattes = lattesProfTA.getText() ;
         String room = roomProfTA.getText() ;
         String confirm = ProfessorConfirmPasswordEdit.getText() ;
         
         selectedProf.setName(name);
         selectedProf.setLattes(lattes);
         selectedProf.setRegister(reg);
         selectedProf.setTelephone(tel);
         selectedProf.setRoom(room);
         
         User u = UserDAO.getUserByType(selectedProf);
        
         u.setCpf(cpf);
         if(!password.equals(confirm)){
             infoBox("Senha e confirmação não batem.", "Editar Professor - Error");
             return;
         }
         
         u.setPassword(password);
         UserDAO.updateUser(u);
         ProfessorDAO.updateProfessor(selectedProf);
         
         infoBox("Dados alterados com sucesso", "Atualizando professor");
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
             infoBox("Professor inserido com sucesso", "Professor");
         }else if(result == 2){
            infoBox("Os campos senha e confirmação de senha precisam ser iguais.", "Senha");
         }else if(result ==3){
             infoBox("Todos os campos são de preenchimento obrigatório.", "Preenchimento obrigatório");
         }
    }
    
       @FXML
    public void handleDeleteProfessorButton(ActionEvent event) throws SQLException{
        Professor p = (Professor) professorsTable.getSelectionModel().getSelectedItem();
        ProfessorDAO.deleteProfessor(p);
        showEditProfessor(event);
        infoBox("Professor deletado com sucesso", "Professor");
     
        
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
    private Pane inputCourse;
    @FXML 
    private TableView coursesTable;
    @FXML
    private TextField nameCourseTA;
    @FXML
    private TextField descriptionCourseTA;
    @FXML
    private TextField codeCourseTA;
    
    
    @FXML
    public void showInsertCourse(ActionEvent event) {
        hideAllPanes();
        InsertCourse.setVisible(true);
    }
    
     @FXML
    public void showEditCourses(ActionEvent event) throws SQLException {
        hideAllPanes();
        coursesTable.getColumns().clear();
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
                    ObservableList<Course> courses = FXCollections.observableArrayList();
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
    public void handleEditCourseButton(){
        Course course = (Course) coursesTable.getSelectionModel().getSelectedItem();
        
        hideAllPanes();
        selectedCourse = course;
        nameCourseTA.setText(course.getName());
   
        codeCourseTA.setText(course.getCode());
        descriptionCourseTA.setText(course.getDescription());
        
        EditCourse.setVisible(true);
        inputCourse.setVisible(true);
    } 
    
     @FXML
    private void handleUpdateCourseButton(){
         String name = nameCourseTA.getText();
         String code = codeCourseTA.getText() ;
         String description = descriptionCourseTA.getText() ;
        
         selectedCourse.setName(name);
         selectedCourse.setCode(code);
         selectedCourse.setDescription(description);
         
           
    
         CourseDAO.updateCourse(selectedCourse);
         infoBox("Dados alterados com sucesso", "Curso");
    }
    
    
    
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
     @FXML
    private void handleInsertCourseButton(ActionEvent event) throws SQLException{
       
        
        String description = CourseDescription.getText() ;
        String name = CourseName.getText() ;
        String code = CourseCode.getText() ;
        try{
            SecretaryController.insertCourse(name, code, description);
            infoBox("Curso inserido com sucesso", "Curso");
        } catch (exceptions.MissingFieldException ex) {
           infoBox("Todos campos são de preenchimento obrigatório", "Erro de validação");
        }
        
       
    }
    
      @FXML
    public void handleDeleteCourseButton(ActionEvent event) throws SQLException{
        Course c = (Course) coursesTable.getSelectionModel().getSelectedItem();
        CourseDAO.deleteCourse(c);
        showEditCourses(event);
        infoBox("Curso deletado com sucesso", "Curso");
     
        
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
     private Pane inputSubject;
    @FXML
     private TextField descriptionSubjectTA;
    @FXML
     private TextField nameSubjectTA;
    @FXML
     private TextField codeSubjectTA;
    @FXML
     private TextField creditsSubjectTA;
   
    
 

    
    
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
         
           
        try{
            SecretaryController.insertSubject(code, description, name, credits, courseName);
             infoBox("Disciplina inserida com sucesso", "Disciplina");
        } catch (exceptions.MissingFieldException ex) {
           infoBox("Todos os campos são de preechimento obrigatório.", "Erro de validação");
        }catch(exceptions.InvalidFieldException ex){
            infoBox(ex.getMessage(), "Erro de validação");
        }
        
        
         
    }
    
     @FXML
    public void showEditSubject(ActionEvent event) throws SQLException {
        hideAllPanes();
        subjectTable.getColumns().clear();
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
                         new PropertyValueFactory<Subject,String>("courseName")
        );

       TableColumn creditsCol = new TableColumn("Créditos");
       creditsCol.setCellValueFactory(
                         new PropertyValueFactory<Subject,String>("credits")
        );

                
                if(sub_list != null){
                    ObservableList<Subject> subs = FXCollections.observableArrayList();
                    subs.addAll(sub_list);

                    subjectTable.getItems().clear();
                    subjectTable.setItems(subs);
                }

                subjectTable.getColumns().clear();
                subjectTable.getColumns().addAll(nameCol, codeCol, descriptionCol,courseCol, creditsCol);
 
       // professorsTable.setItems(profs);
        //professorsTable.getItems().setAll(profs);
        ListSubject.setVisible(true); 
        EditSubject.setVisible(true); 
    }
    
    private ArrayList<Course> tmpListCourses; 
    private ArrayList<Subject> tmpListSubjects; 
    private ArrayList<Room> tmpListRooms; 
    private ArrayList<Professor> tmpListProfs; 
     
     
    private void populateCoursesComboBox(ComboBox cb){
        try {
            ArrayList<Course> courses = CourseDAO.listCourses();
            cb.getItems().clear();
            
            for(Course course : courses){
                cb.getItems().add(course.getName());
            }

            tmpListCourses = courses;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    ComboBox courseSubjectComboBox;
    
     @FXML
    public void handleEditSubjectButton(){
      
        Subject subject = (Subject) subjectTable.getSelectionModel().getSelectedItem();
        
        
        hideAllPanes();
        selectedSubject = subject;
        
        
        descriptionSubjectTA.setText(subject.getDescription());
        nameSubjectTA.setText(subject.getName());   
        codeSubjectTA.setText(subject.getCode());   
        creditsSubjectTA.setText(subject.getCredits());
        
 
        populateCoursesComboBox(courseSubjectComboBox);
        courseSubjectComboBox.getSelectionModel().select(subject.getCourse().getName());
       // courseSubjectComboBox
        
        
        EditSubject.setVisible(true);
        inputSubject.setVisible(true); 
    } 
    
     @FXML
    private void handleUpdateSubjectButton(){
        
         String name = nameSubjectTA.getText();
         String code = codeSubjectTA.getText() ;
         String description = descriptionSubjectTA.getText() ;
         String credits = creditsSubjectTA.getText() ;
         
        
         selectedSubject.setName(name);
         selectedSubject.setCode(code);
         selectedSubject.setDescription(description);
         
        for (Course next : tmpListCourses) {
            if(next.getName().equals( courseSubjectComboBox.getValue() )){
                selectedSubject.setCourse(next);
                break;
            }
        }
        
         //course !! 
         selectedSubject.setCredits(credits);
         
         SubjectDAO.updateSubject(selectedSubject);
         
        infoBox("Dados atualizados com sucesso", "Disciplina");
       
         
         
         
    }
    
    @FXML
    public void handleDeleteSubjectButton(ActionEvent event) throws SQLException{
        Subject subject = (Subject) subjectTable.getSelectionModel().getSelectedItem();
        SubjectDAO.deleteSubject(subject);
        showEditSubject(event);
        infoBox("Disciplina deletada com sucesso", "Disciplina");
     
        
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
     private Pane EditClass;
    @FXML
     private Pane ListClass;
    @FXML
     private Pane inputClass;
    @FXML
     private TableView classTable;
    @FXML
    private TextField classSemester;
    @FXML
    private ComboBox profClassComboBox;
    @FXML
    private ComboBox subjectClassComboBox;
    @FXML
    private ComboBox roomClassComboBox;
    @FXML
    private TextField semesterClassTA;
    
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
    
    @FXML
    public void handleInsertClassButton(ActionEvent event) throws SQLException {
         ArrayList<Professor> p_list = SecretaryController.listProf();
         ArrayList<Subject> s_list = SecretaryController.listSubjects();
         ArrayList<Room> r_list = SecretaryController.listRooms();         
        
        Professor selectedProf = null;
        for (Professor next : p_list) {
            if(next.getName().equals( ClassProfessor.getValue() )){
                selectedProf = next;
                break;
            }
        }
        
        Subject selectedSubject = null;
        for (Subject next : s_list) {
            if(next.getName().equals( ClassSubject.getValue() )){
                selectedSubject = next;
                break;
            }
        }
        
        Room selectedRoom = null;
        for (Room next : r_list) {
            if(next.getNumber().equals( ClassRoom.getValue() )){
                selectedRoom = next;
                break;
            }
        }
        
        String semester = classSemester.getText();
        
      
        ClassDAO.insertClass(selectedProf.getId(), selectedRoom.getId(), selectedSubject.getId(), semester);
        
       infoBox("Turma inserida com sucesso", "Turma");
      
        
    }
    
    @FXML
    public void showEditClass(ActionEvent event) throws SQLException {
         hideAllPanes();
         classTable.getColumns().clear();
         ArrayList<model.Class> class_list = SecretaryController.listClass();
      
        
        classTable.setEditable(true);
              
        TableColumn profCol = new TableColumn("Professor");
        profCol.setCellValueFactory(
                        new PropertyValueFactory<Class,String>("professorName")
        );
        
        TableColumn roomCol = new TableColumn("Sala");
        roomCol.setCellValueFactory(
                         new PropertyValueFactory<Class,String>("roomNumber")
        );

       TableColumn subjectCol = new TableColumn("Disciplina");
       subjectCol.setCellValueFactory(
                         new PropertyValueFactory<Class,String>("subjectName")
        );
        TableColumn semesterCol = new TableColumn("Semestre");
        semesterCol.setCellValueFactory(
                         new PropertyValueFactory<Class,String>("semester")
        );

       

                
                if(class_list != null){
                    ObservableList<model.Class> classes = FXCollections.observableArrayList();
                    classes.addAll(class_list);

                   classTable.setItems(classes);
                }


                classTable.getColumns().addAll(profCol);
                classTable.getColumns().addAll(roomCol);
                classTable.getColumns().addAll(subjectCol);
                classTable.getColumns().addAll(semesterCol);
                
        ListClass.setVisible(true); 
        EditClass.setVisible(true); 
    }
    
   
    
    private void populateProfsComboBox(ComboBox cb){
        try {
            ArrayList<Professor> profs = ProfessorDAO.listProfessors();
            cb.getItems().clear();
            
            for(Professor p : profs){
                cb.getItems().add(p.getName());
            }

            tmpListProfs = profs;
            
        } catch (SQLException ex) {
            Logger.getLogger(SecretaryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void populateSubjectsComboBox(ComboBox cb){
        try {
            ArrayList<Subject> subs = SubjectDAO.listSubjects();
            cb.getItems().clear();
            
            for(Subject s : subs){
                cb.getItems().add(s.getName());
            }

            tmpListSubjects = subs;
            
        } catch (SQLException ex) {
            Logger.getLogger(SecretaryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void populateRoomsComboBox(ComboBox cb){
        try {
            ArrayList<Room> rooms = RoomDAO.listRooms();
            cb.getItems().clear();
            
            for(Room r : rooms){
                cb.getItems().add(r.getNumber());
            }

            tmpListRooms = rooms;
            
        } catch (SQLException ex) {
            Logger.getLogger(SecretaryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    @FXML
    public void handleEditClassButton(){
  
        Class cl = (Class) classTable.getSelectionModel().getSelectedItem();

        hideAllPanes();
        selectedClass = cl;
         
         populateProfsComboBox(profClassComboBox);
         profClassComboBox.getSelectionModel().select(cl.getProfessorName());
         
         populateSubjectsComboBox(subjectClassComboBox);
         subjectClassComboBox.getSelectionModel().select(cl.getSubjectName());
         
         populateRoomsComboBox(roomClassComboBox);
         roomClassComboBox.getSelectionModel().select(cl.getRoomNumber());
      
         semesterClassTA.setText("" + cl.getSemester());
        
        EditClass.setVisible(true);
        inputClass.setVisible(true);
    } 
    
     @FXML
    private void handleUpdateClassButton(){
      
       
         
        int semester = Integer.parseInt(semesterClassTA.getText());
        selectedClass.setSemester(semester);
         
    
        for (Professor next : tmpListProfs) {
            if(next.getName().equals(profClassComboBox.getValue() )){
                selectedClass.setProfessor(next);
                break;
            }
        }
          for (Subject next : tmpListSubjects) {
            if(next.getName().equals( subjectClassComboBox.getValue() )){
                selectedClass.setSubject(next);
                break;
            }
        }
           for (Room next : tmpListRooms) {
            if(next.getNumber().equals( roomClassComboBox.getValue() )){
                selectedClass.setRoom(next);
                break;
            }
        }
         
         ClassDAO.updateClass(selectedClass);
         infoBox("Dados atualizados com sucesso", "Turma");
    }
   
     @FXML
    public void handleDeleteClassButton(ActionEvent event) throws SQLException{
        Class c = (Class) classTable.getSelectionModel().getSelectedItem();
        ClassDAO.deleteClass(c);
        showEditClass(event);
        infoBox("Turma deletada com sucesso", "Turma");
     
        
    }
    
    //***************************** SALAS *****************************
    @FXML
    private Pane InsertRoom;
    @FXML
    private TextField RoomCapacity;
    @FXML
    private TextField RoomNumber;
    @FXML
    private Pane EditRoom;
    @FXML
    private Pane ListRoom;
     @FXML
    private Pane inputRoom;
    @FXML
    private TableView roomTable;
    @FXML
    private TextField numberRoomTA;
    @FXML
    private TextField capacityRoomTA;
    
    @FXML
    public void showEditRoom(ActionEvent event) throws SQLException {
        hideAllPanes();
        roomTable.getColumns().clear();
         ArrayList<Room> room_list = SecretaryController.listRoom();
        
        
        roomTable.setEditable(true);
              
        TableColumn numberCol = new TableColumn("Número");
        numberCol.setCellValueFactory(
                        new PropertyValueFactory<Room,String>("number")
        );
        TableColumn capacityCol = new TableColumn("Capacidade");
        capacityCol.setCellValueFactory(
                         new PropertyValueFactory<Room,String>("capacity")
        );

   
        if(room_list != null){
            ObservableList<Room> rooms = FXCollections.observableArrayList();
            rooms.addAll(room_list);
            roomTable.setItems(rooms);
        }


        roomTable.getColumns().addAll(numberCol, capacityCol);
 
       
        ListRoom.setVisible(true); 
        EditRoom.setVisible(true); 
    }
    
    
     @FXML
    public void showInsertRoom(ActionEvent event) {
        hideAllPanes();
        InsertRoom.setVisible(true);
    }
    
      @FXML
    private void handleInsertRoomButton(ActionEvent event) throws SQLException{
         String number = RoomNumber.getText();
         String capacity = RoomCapacity.getText();
          
         
        try{
            SecretaryController.insertRoom(number, capacity);
            infoBox("Sala inserida com sucesso", "Sala");
        } catch (exceptions.MissingFieldException ex) {
           infoBox("Todos os campos são de preechimento obrigatório.", "Erro de validação");
        }catch(exceptions.InvalidFieldException ex){
            infoBox(ex.getMessage(), "Erro de validação");
        }
         
         
         
    }
    
     @FXML
    public void handleEditRoomButton(){
        Room room = (Room) roomTable.getSelectionModel().getSelectedItem();
        
        hideAllPanes();
        selectedRoom = room;
        numberRoomTA.setText(room.getNumber());
   
        capacityRoomTA.setText(room.getCapacity());
        
        EditRoom.setVisible(true);
        inputRoom.setVisible(true);
    } 
    
     @FXML
    private void handleUpdateRoomButton(){
        
         String number = numberRoomTA.getText();
         String capacity = capacityRoomTA.getText() ;
        
         selectedRoom.setNumber(number);
         selectedRoom.setCapacity(capacity);
         
           
    
         RoomDAO.updateRoom(selectedRoom);
         infoBox("Dados atualizados com sucesso", "Turma");
    }
    
      @FXML
    public void handleDeleteRoomButton(ActionEvent event) throws SQLException{
        Room r = (Room) roomTable.getSelectionModel().getSelectedItem();
        RoomDAO.deleteRoom(r);
        showEditRoom(event);
        infoBox("Sala deletada com sucesso", "Sala");
     
        
    }
    
    //***************************** ALUNOS *****************************
    @FXML public Pane StudentPane;
    
    @FXML public TableView studentsTable;
    
    @FXML public AnchorPane alunoEditPane;
    
    @FXML public TextField register;
    
    @FXML public TextField telephone;
    
    @FXML public TextField address;
    
    @FXML public TextField email;
    
    @FXML public TextField birth_date;

    @FXML public TextField semester;
    
    @FXML public AnchorPane studentsTablePane;
    
    
    private void makeTopPane(String title, Pane pane){
        VBox innerPane = new VBox(8);
        pane.getChildren().setAll(innerPane);
        
        innerPane.setPadding( new Insets(10));
        
        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.setFont(Font.font("system", FontWeight.BOLD, 15));
        innerPane.getChildren().add(titleLabel);
    }
    
    private void addElementToPane(Pane pane, Node n){
        VBox innerPane = (VBox) pane.getChildren().get(0);
        innerPane.getChildren().add(n);
    }
    
    public void createFormAluno(){
        makeTopPane("Formulário Aluno", alunoEditPane);
        
        register = createFormInput("Registro: ", alunoEditPane);
        telephone = createFormInput("Telefone: ", alunoEditPane);
        address = createFormInput("Endereço: ", alunoEditPane);
        email = createFormInput("Email: ", alunoEditPane);
        birth_date = createFormInput("Data de Nascimento: ", alunoEditPane);
        semester = createFormInput("Semestre: ", alunoEditPane);
    }
    
    private void createConfirmButton(EventHandler<ActionEvent> handler, Pane pane){
        Button confirmBtn = new Button("Confirmar");
        addElementToPane(pane, confirmBtn);
        
        confirmBtn.setOnAction(handler);
    }
    
    
    private TextField createFormInput(String name, Pane pane){
        Label l = new Label(name);
        addElementToPane(pane, l);
        
        TextField tf = new TextField();
        addElementToPane(pane, tf);
        return tf;
    }
    
    private void createTableViewStudents(Pane pane){
        studentsTable = new TableView();
        
        TableColumn idCol = new TableColumn("Id");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        
        TableColumn registerCol = new TableColumn("Registro");

        registerCol.setCellValueFactory(
                new PropertyValueFactory<>("register")
        );
        
        TableColumn emailCol = new TableColumn("Email");

        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        
        studentsTable.getColumns().addAll(idCol, registerCol , emailCol);
        try {
            studentsTable.getItems().addAll(StudentDAO.all());
        } catch (SQLException ex) {
            Logger.getLogger(SecretaryViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pane.getChildren().add(studentsTable);
    }
    
    @FXML public void handleListStudents(ActionEvent event){
        StudentPane.setVisible(true);
        createTableViewStudents(studentsTablePane);
        createFormAluno();
    }
    
    
    // ***************************** 
    
    public void hideAllPanes() {
        InsertProfessor.setVisible(false);
        InsertCourse.setVisible(false);
        InsertCourse.setVisible(false);
        InsertSubject.setVisible(false);
        InsertClass.setVisible(false);
        InsertRoom.setVisible(false);
        
        EditProfessor.setVisible(false);
        EditCourse.setVisible(false);
        EditSubject.setVisible(false);
        EditClass.setVisible(false);
        EditRoom.setVisible(false);
        
        ListProfessor.setVisible(false);
        ListCourse.setVisible(false);      
        ListSubject.setVisible(false);
        ListClass.setVisible(false);
        ListRoom.setVisible(false);
        
        inputProfessor.setVisible(false);
        inputCourse.setVisible(false);
        inputRoom.setVisible(false);
        inputClass.setVisible(false);
        inputSubject.setVisible(false);
        StudentPane.setVisible(false);
    }
    
    
 }

   

    

