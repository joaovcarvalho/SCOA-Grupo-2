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
import exceptions.MissingFieldException;

/**
 * FXML Controller class
 * @author Dóglas
 */
public class StudentViewController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    
    private final String ERROR_TITLE_EXAM = "Erro - Avaliação";
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
        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Sugestão",
                "Reclamação"
            );
        cb.getItems().addAll(options);
    }
    
    @FXML
    private void handleSendFeedbackButton(ActionEvent event) throws SQLException{
        try{
            StudentController.insertFeedback((String) feedbackTypeComboBox.getValue(),feedbackTextArea.getText());
        }catch (SQLException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (ParseException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (MissingFieldException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InvalidFieldException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
