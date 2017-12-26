/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Skrzatt
 */
public class PeerToPeerJavaFX extends Application {
    //public Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startView.fxml"));
        Scene scene = new Scene(root);        
        primaryStage.setScene(scene);
        primaryStage.show();        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("END OF: peertopeerjavafx.PeerToPeerJavaFX.main()");
    }
    
}
