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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        
        labelStatus = (Label)content.lookup("#labelStatus");
        buttonSend = (Button)content.lookup("#buttonSend");
        textOutput = (TextFlow)content.lookup("#textOutput");
        textInput = (TextArea)content.lookup("#textInput");
        scroller = (ScrollBar)content.lookup("#scroller");
        //this.show();
        
        //textOutput.setTextAlignment(TextAlignment.LEFT);
        
        buttonSend.setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonSendClicked();
            event.consume();
        });
        
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
            event.consume();
        });
    }
    
    @Override
    public void hide()
    {
        clearOutputText();
        super.hide();
    }
    
    public String getInputText()
    {
        String inputText = textInput.getText();
        if(!inputText.equals(""))
        {
            Text text = new Text("You: "+inputText+"\n");
            text.setTextAlignment(TextAlignment.RIGHT);
            textOutput.getChildren().add(text);
        }
        textOutput.requestLayout();
        textInput.setText("");
        return inputText;
    }
    
    public void setOutputText( String outputText )
    {
        if(!outputText.equals(""))
        {
            Text text = new Text("John: "+outputText+"\n");
            text.setTextAlignment(TextAlignment.LEFT);
            textOutput.getChildren().add(text);
        }
        textOutput.requestLayout();        
    }
    
    public void clearOutputText()
    {
        textOutput.getChildren().clear();
    }
}
