/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.WaitView;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class WaitViewController extends Stage{

    @FXML
    private Label infoLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private ProgressBar statusBar;
    @FXML
    private Button ButtonCancel;
    
    
    Parent content;
    
    public WaitViewController( Stage talkViewStage, WaitViewInterface callbacks ) throws IOException
    {
        super();
        
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(talkViewStage.getScene().getWindow());
        content = FXMLLoader.load(getClass().getResource("waitView_.fxml"));
        
        infoLabel = (Label)content.lookup("#infoLabel");
        statusLabel = (Label)content.lookup("#statusLabel");
        statusBar = (ProgressBar)content.lookup("#statusBar");
        ButtonCancel = (Button)content.lookup("#Button");
        this.setScene(new Scene(content));
        
        
        ButtonCancel.setOnMouseClicked(          
            new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {                    
                    callbacks.buttonClicked();
                    event.consume();
                };
            }
        );
        
        this.setOnCloseRequest(         
            new EventHandler<WindowEvent>() 
            {
                @Override
                public void handle(WindowEvent event) 
                {                    
                    callbacks.onClose(); 
                    System.out.println(".handle() on exit");
                    event.consume();
                };
            }
        );
    }
    
    @Override
    public void hide()
    {
        setStatusDefault();
        super.hide();
    }
    
    public void setStatusDefault()
    {
        statusLabel.setText("w trakcie...");
        ButtonCancel.setText("Anuluj");
    }
    
    public void setStatusOK()
    {
        statusLabel.setText("zakończone sukcesem!");
        ButtonCancel.setText("Rozpocznij rozmowę");
    }
    
    public void setStatusFAIL()
    {
        statusLabel.setText("zakończone niepowodzeniem!");
        ButtonCancel.setText("Powrót");
    }
    
    
    
    
}
