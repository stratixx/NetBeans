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
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class WaitWindowViewController implements Initializable {

    @FXML
    private Label infoLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private ProgressBar statusBar;
    @FXML
    private Button ButtonCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("peertopeerjavafx.WaitWindowViewController.initialize()");
    }    

    @FXML
    private void OnActionButtonCancel(ActionEvent event) {
        System.out.println("peertopeerjavafx.WaitWindowViewController.OnActionButtonCancel()");
    }
    
}
