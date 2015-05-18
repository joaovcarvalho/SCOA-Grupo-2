package view;

import dao.UserDAO;
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
import model.User;

/**
 * FXML Controller class
 *
 * @author Guilherme Herzog
 */
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
    private Label StatusLabel;
    
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
       
       User user = new UserDAO().getUserByCPFandPassword(CPF, password);
       System.out.println(user.getId());
    }

}
