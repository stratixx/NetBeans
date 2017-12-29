/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.TalkView;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class TalkViewController extends Stage{

    @FXML
    private Label labelStatus;
    @FXML
    private Button przycisk;
    @FXML
    private Button buttonSend;
    @FXML
    private TextFlow textOutput;
    @FXML
    private TextArea textInput;
    @FXML
    private ScrollBar scroller;
  
    
    
    Parent content;
    
    public TalkViewController( TalkViewCallbacks callbacks ) throws IOException
    {
        super();
        
        content = FXMLLoader.load(getClass().getResource("talkView.fxml"));
        this.setScene(new Scene(content));
        //this.show();
        
        content.lookup("#buttonSend").setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonSendClicked();
            event.consume();
        });
        
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
            event.consume();
        });
    }

    
    
}
