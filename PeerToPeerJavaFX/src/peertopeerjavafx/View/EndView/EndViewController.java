/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.EndView;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class EndViewController extends Stage {

    @FXML
    private Button buttonOK;
    @FXML
    private Label infoLabel;
   

    Parent content;
    
    public EndViewController( Stage endViewStage, EndViewCallbacks callbacks ) throws IOException
    {
        super();
        
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(endViewStage.getScene().getWindow());
        content = FXMLLoader.load(getClass().getResource("endView_.fxml"));
        
        infoLabel = (Label)content.lookup("#infoLabel");
        buttonOK = (Button)content.lookup("#ButtonOK");
        
        this.setScene(new Scene(content));
        //this.show();
        
        buttonOK.setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonOKClicked();
            event.consume();
        });
        
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
            event.consume();
        });
    }
    
    public void setStatusDefault()
    {        
        infoLabel.setText("Zakończono rozmowę");
    }
    
    public void setStatusEnd()
    {        
        infoLabel.setText("Połączenie zerwane");
    }
}
