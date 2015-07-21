/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.StudentController;
import dao.ClassDAO;
import dao.ExamDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import exceptions.InvalidFieldException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import model.Class;
import model.Exam;
import model.Registration;
import model.Student;
import controller.StudentController;
import dao.CourseDAO;
import dao.SubjectDAO;
import exceptions.MissingFieldException;
import model.Course;
import static view.ProfessorViewController.infoBox;

/**
 * FXML Controller class
 * @author Dóglas
 */
public class StudentViewController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    /**
     * Initializes the controller class. 
    */
    
    private final String ERROR_TITLE_FEEDBACK = "Erro - Feedback";
    private final String ERROR_DATE_FORMAT = "Data em formato inválido. Por favor use dd/MM/yyyy. Ex: 30/11/2015";
    private final String ERROR_MISSING_FIELD = "Por favor preencha todos os campos.";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideAllPanels();
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private void hideAllPanels(){
        insertFeedbackPane.setVisible(false);
        signUpPane.setVisible(false);

    }
    
    
    // *****************************  INSERIR FEEDBACK ***************************** 
    @FXML
    private Pane insertFeedbackPane;
    @FXML
    private ComboBox feedbackTypeComboBox; 
    @FXML
    private TextArea feedbackTextArea;
    @FXML
    private Button sendFeedbackButton;
    
    
    @FXML
    private void handleInsertFeedbackLink(ActionEvent event) throws SQLException{
        hideAllPanels();
        insertFeedbackPane.setVisible(true);
        populateFeedbackCombox(feedbackTypeComboBox);
        
    }
    
    private void populateFeedbackCombox(ComboBox cb){
        String s1 = "Feedback";
        String s2 =  "Reclamação";
        cb.getItems().add(s1);
        cb.getItems().add(s2);
    }
    
    @FXML
    private void handleSendFeedbackButton(ActionEvent event) throws SQLException{
        try{
            StudentController.insertFeedback((String) feedbackTypeComboBox.getValue(),feedbackTextArea.getText());
            infoBox("Feedback inserido com sucesso.", "Info - Feedback");
        }catch (SQLException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
            infoBox("Erro ao inserir feedback no banco.", ERROR_TITLE_FEEDBACK);
        }catch (ParseException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (MissingFieldException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
            infoBox(ERROR_MISSING_FIELD, ERROR_TITLE_FEEDBACK);
        }catch (InvalidFieldException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
            infoBox(ex.getMessage(), ERROR_TITLE_FEEDBACK);
        }
    }
    
    
    // *****************************  INSCREVER-SE NUMA TURMA ***************************** 
    @FXML
    private Pane signUpPane;
    
    @FXML
    private Button signUpButton;
    @FXML
    private ComboBox classComboBox; 
    @FXML
    private ComboBox courseComboBox; 
    @FXML
    private ComboBox subjectComboBox; 
    
    private ArrayList<model.Course> tmpListCourses;
    private ArrayList<model.Subject> tmpListSubjects;
    
    @FXML
    private void  handleSignUpLink(ActionEvent event){
        hideAllPanels();
        signUpPane.setVisible(true);
        populateCourseComboBox(courseComboBox);
        
        
    }
    
    @FXML
    private void  handleSignUpClassButton(ActionEvent event){
        
    }
    
    private void populateSubjectComboBoxByCourseId(ComboBox cb, int course_id){
        ArrayList<model.Subject> subjects;
        try {
            subjects = SubjectDAO.getSubjectByCourseId(course_id);
            
            cb.getItems().clear();
            subjects.stream().forEach((next) -> {
                cb.getItems().add(next.getName());
            });
            tmpListSubjects = subjects;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void populateCourseComboBox(ComboBox cb){
        ArrayList<model.Course> courses;
        try {
            courses = CourseDAO.selectAllCourses();
            
            cb.getItems().clear();
            courses.stream().forEach((next) -> {
                cb.getItems().add(next.getName());
            });
            
            tmpListCourses = courses;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    Course selectedCourse = null;
    public void handleCourseSelected(ActionEvent event){
        selectedCourse = null;
        for (Course next : tmpListCourses) {
            if(next.getName().equals( courseComboBox.getValue() )){
                selectedCourse = next;
                break;
            }
        }
        
        if(selectedCourse == null)
           return;
        
        populateSubjectComboBoxByCourseId(subjectComboBox, selectedCourse.getId());
    }
    
    @FXML Button logoutBtn;
    
    @FXML
    public void handleLogout(ActionEvent event){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
}
