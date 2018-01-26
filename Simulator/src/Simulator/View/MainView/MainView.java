/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.View.MainView;

import Simulator.View.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 * Główny widok aplikacji
 * @author Skrzatt
 */
public class MainView extends ViewController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab TabStart;
    @FXML
    private AnchorPane startView;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStop;
    @FXML
    private Label labelMousePosition;
    @FXML
    private Button buttonPause;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Simulator.View.MainView.MainView.initialize()");        
        
        super.setViewName("Start");
        //super.setGraphicsContext( canvas.getGraphicsContext2D() );        
        
        pane.setStyle("-fx-background-color: white");
    }    


    @FXML
    private void paneOnMouseMoved(MouseEvent event) {
        labelMousePosition.setText( event.getX()+"|"+event.getY());
    }
    
    

    ////////////////////////////////////////////////////////////////
    
    public Pane getPane()
    {
        return this.pane;
    }
    
    public Button getButtonStart()
    {
        return buttonStart;
    }
    
    public Button getButtonPauza()
    {
        return buttonPause;
    }
    
    public Button getButtonStop()
    {
        return buttonStop;
    }
    
    public void addNodeAsNewTab( Node tabNode, String tabTitle )
    {      
        Tab newTab = new Tab(tabTitle);
        newTab.setContent(tabNode);
        tabPane.getTabs().add( newTab );
    }


    @FXML
    private void buttonStartOnAction(ActionEvent event) {
        super.getView().buttonStartAction( event );
    }

    @FXML
    private void buttonPauseOnAction(ActionEvent event) {
        super.getView().buttonPauseAction( event );
    }

    @FXML
    private void buttonStopOnAction(ActionEvent event) {
        super.getView().buttonStopAction( event );
    }

}
