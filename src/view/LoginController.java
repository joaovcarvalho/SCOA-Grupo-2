package view;

import dao.UserDAO;
import controller.UserController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Professor;
import model.Secretary;
import model.User;


public class LoginController implements Initializable, ControlledScreen {

    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea UserCPF;
    
    @FXML
    private PasswordField PasswordField;
    
    @FXML
    private Label Status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException{
       System.out.println("Iniciando Login...");
       String CPF = UserCPF.getText() ;
       String password = PasswordField.getText() ;
       
       User user = UserController.login(CPF, password);
       if(user != null){
           
           if(user.getType() instanceof Secretary){
               myController.setScreen(ScreensFramework.screen2ID);
           }
           
           if(user.getType() instanceof Professor){
               myController.setScreen(ScreensFramework.screen3ID);
           }
          
          
       }else{
           System.out.println("Usuário ou Senha Incorretos");
           Status.setVisible(true);
       }
     //  myController.setCurrentUser(user);
       
             
    }

}
