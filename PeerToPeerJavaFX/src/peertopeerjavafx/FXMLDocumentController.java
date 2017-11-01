/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Skrzatt
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button przycisk;
    @FXML
    private Tab talkWindow;
    @FXML
    private Label labelStatus;
    @FXML
    private TabPane talkTabs;
    
    private int number = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnActionPrzycisk(ActionEvent event) {
        System.out.println("peertopeerjavafx.FXMLDocumentController.OnActionPrzycisk()");
        number++;
        Tab newTab = new Tab("Tab #" + number);
        //Node node = talkWindow.getContent();
        GridPane grid = new GridPane();
        Button sendButton = new Button("X");
        
        grid.add(sendButton, 2, 2);
        newTab.setContent(grid);
        
        
        
        //talkWindow.setContent(node);
        //newTab.setContent(node);
        
        
        
        
        talkTabs.getTabs().add( newTab );
                
    }

    @FXML
    private void OnCloseTalkWindow(Event event) {
    }

    
}
