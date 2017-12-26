/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx;

import java.lang.String;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class StartViewController implements Initializable {

    @FXML
    private TextField adressIP;
    @FXML
    private TextField adressPort;
    @FXML
    private Button buttonTX;
    @FXML
    private Button buttonRX;
    
    private Socket socket;
    @FXML
    private Label labelStatus;
    
    
    
    void windowStart( Socket socket, String newView, String closeView)
    {
        try
        {            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource( newView ));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            //stage.setTitle("TalkView");
            stage.setScene(scene);
            if( closeView!="" )                
                stage.setOnCloseRequest(event -> { try{socket.close();} catch(Exception e){} windowStart( socket, closeView, "");});
            stage.show();
        }
        catch( Exception e )
        {
            
        }
        
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("peertopeerjavafx.StartViewController.initialize()");
    }    

    @FXML
    private void onActionButtonTX(ActionEvent event) {
        try
        {
            labelStatus.setText("Connecting to host...");
            socket = new Socket( adressIP.getText(), Integer.parseInt(adressPort.getText()) );
            // hide current view and start talkView
            buttonTX.getScene().getWindow().hide();
            windowStart(socket, "WaitWindowView.fxml", "");
            labelStatus.setText("Connecting to host... OK");
        }
        catch( Exception e )
        {
            labelStatus.setText("Connecting to host... FAILED");
        }      
        
    }

    @FXML
    private void onActionButtonRX(ActionEvent event) {
        try
        {
            //Iterator<String> 
            
            labelStatus.setText("Waiting for client...");
            System.out.println("try: peertopeerjavafx.StartViewController.onActionButtonRX()");
            
            ServerSocket serverSocket;
            serverSocket = new ServerSocket( Integer.parseInt( adressPort.getText()) );
            adressIP.setText( serverSocket.getInetAddress().getHostName() );
            //waitWindowStart(new Socket());
            socket = serverSocket.accept();
            serverSocket.close();
            System.out.println("accept: peertopeerjavafx.StartViewController.onActionButtonRX()");
            
            buttonRX.getScene().getWindow().hide();
            
            windowStart(socket, "WaitWindowView.fxml", "talkView.fxml");
            labelStatus.setText("Waiting for client... OK");
            System.out.println("done try: peertopeerjavafx.StartViewController.onActionButtonRX()");
        }
        catch (Exception ex) 
        {
            ex.printStackTrace(System.out);
        }
        System.out.println("end: peertopeerjavafx.StartViewController.onActionButtonRX()");
    }
    
}
