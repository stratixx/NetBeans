/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class TalkViewController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionPrzycisk(ActionEvent event) {
    }

    @FXML
    private void OnActionButtonSend(ActionEvent event) {
        
    }
    
    public void OnClose(){
        System.out.println("Stage is closing");
        // Save file
    }
    
}
