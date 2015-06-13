/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProfessorController;
import dao.ClassDAO;
import dao.ProfessorDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.Class;
import model.Professor;

/**
 * FXML Controller class
 * @author JoãoVitor
 */
public class ProfessorViewController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    

    // INSERIR AVALIACAO
    @FXML
    private Pane insertExamPane;
    
    // LISTAR AVALIACOES
    @FXML
    private Pane listExamsPane;
    
    @FXML
    private ComboBox classListComboBox;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideAllPanels();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private void hideAllPanels(){
        insertExamPane.setVisible(false);
        listExamsPane.setVisible(false);
    }
    
    @FXML
    private void handleInsertExamLink(ActionEvent event) throws SQLException{
        hideAllPanels();
        insertExamPane.setVisible(true);
    }
    
    private ArrayList<model.Class> tmpListClasses;
    
    @FXML 
    private void handleListExamLink(ActionEvent event){
        hideAllPanels();
        listExamsPane.setVisible(true);
        
        ArrayList<model.Class> classes;
        try {
            classes = ClassDAO.getClassByProfessorId(myController.getUser().getType().getId());
            
            classListComboBox.getItems().clear();
            for (Iterator<model.Class> iterator = classes.iterator(); iterator.hasNext();) {
                model.Class next = iterator.next();
                
                classListComboBox.getItems().add(next.getSubject().getName());
            }
            
            tmpListClasses = classes;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML 
    private void handleClassSelected(ActionEvent event){
        for (Iterator<Class> iterator = tmpListClasses.iterator(); iterator.hasNext();) {
            Class next = iterator.next();
            if(next.getSubject().getName().equals( classListComboBox.getValue() )){
                
            }
        }
    }
    
    @FXML
    private void insertPauta(ActionEvent event){
        String turma = "";
        String data = "";
        String descricao = "";
        
        ProfessorController.insertAgenda(turma, data, descricao);
    }
}
