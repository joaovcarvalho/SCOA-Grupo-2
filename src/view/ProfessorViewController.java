/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProfessorController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 * @author JoãoVitor
 */
public class ProfessorViewController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Pane inserirPauta;
        
    @FXML
    private Pane editPauta;
    
    @FXML
    private Pane deletePauta;
    
    @FXML
    private Hyperlink inserirPautaLink;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideAllPanels();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private void hideAllPanels(){
        inserirPauta.setVisible(false);
        editPauta.setVisible(false);
        inserirPauta.setVisible(false);
    }
    
    @FXML
    private void handleInserirPautaButton(ActionEvent event) throws SQLException{
        hideAllPanels();
        inserirPauta.setVisible(true);
    }
    
    @FXML
    private void insertPauta(ActionEvent event){
        String turma = "";
        String data = "";
        String descricao = "";
        
        ProfessorController.insertAgenda(turma, data, descricao);
    }
}
