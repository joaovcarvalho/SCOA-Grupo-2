/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoa;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joaocarvalho
 */
public abstract class View {
    
    protected String fxmlFile = null;
    
    public void render() throws Exception{
        if(fxmlFile == null){
            throw new Exception("Fxml file not set.");
        }
        
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root);
        SCOA.stage.setScene(scene);
        SCOA.stage.show();
    }
    
    
    
}
