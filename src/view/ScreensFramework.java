package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amanda Medeiros
 */
public class ScreensFramework extends Application {
    
    public static String screen1ID = "login";
    public static String screen1File = "Login.fxml";
    
    public static String screen2ID = "secretary";
    public static String screen2File = "Secretary.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.setScreen(ScreensFramework.screen1ID);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
     
      
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
