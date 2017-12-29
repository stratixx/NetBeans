/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.StartView;


import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.Tools.ConnectionType;
/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class StartViewController extends Stage{

    @FXML
    private TextField adressIP;
    @FXML
    private TextField adressPort;
    @FXML
    private Button buttonTX;
    @FXML
    private Button buttonRX;
    @FXML
    private Label labelStatus;
    
    Parent content;
    
    
    public StartViewController( StartViewCallbacks calbacks ) throws IOException
    {
        super();
        
        content = FXMLLoader.load(getClass().getResource("startView.fxml"));
        this.setScene(new Scene(content));
        
        
        adressIP = (TextField)content.lookup("#adressIP");
        adressPort = (TextField)content.lookup("#adressPort");
        buttonTX = (Button)content.lookup("#buttonTX");
        buttonRX = (Button)content.lookup("#buttonRX");
        labelStatus = (Label)content.lookup("#labelStatus");
        
        buttonTX.setOnMouseClicked((MouseEvent event) -> {
            Connection connect =
                    new Connection(ConnectionType.CLIENT, adressIP.getText(), Integer.parseInt(adressPort.getText()));
            calbacks.buttonTXClicked(connect);
            event.consume();
        });
        
        buttonRX.setOnMouseClicked((MouseEvent event) -> {
            Connection connect =
                    new Connection(ConnectionType.SERVER, adressIP.getText(), Integer.parseInt(adressPort.getText()));
            calbacks.buttonRXClicked(connect);
            event.consume();
        });
    }
    
    
      
      

    
}
