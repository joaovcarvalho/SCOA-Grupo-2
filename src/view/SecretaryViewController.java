/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UserDAO;
import controller.UserController;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
    
    
    @FXML
    private Pane InsertProfessor;
    @FXML
    private Pane EditProfessor;
    @FXML
    private Pane DeleteProfessor;
    @FXML
    private Hyperlink InsertProf;
    @FXML
    private Label labelTesteInsertProf;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    public void showInsertProfessor(ActionEvent event) {
        hideAllPanes();
        InsertProfessor.setVisible(true);
        labelTesteInsertProf.setVisible(true);
        System.out.println("Exibindo Painel de Inserir professor...");
    }
    
    public void hideAllPanes() {
        InsertProfessor.setVisible(false);
        EditProfessor.setVisible(false);
        DeleteProfessor.setVisible(false);
    }

   

    
}
