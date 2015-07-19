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
    
    public static String screen3ID = "professor";
    public static String screen3File = "Professor.fxml";
    
    public static String screen4ID = "student";
    public static String screen4File = "Student2.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.fullScreenProperty();
        ScreensController mainContainer = new ScreensController();
        
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
      
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(750);
        primaryStage.show();
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
