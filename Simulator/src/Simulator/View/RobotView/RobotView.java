/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.View.RobotView;

import Simulator.View.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 * widok robota
 * @author Skrzatt
 */
public class RobotView extends ViewController implements Initializable {
    
    private ImageView robotImageView;
    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Simulator.View.RobotView.RobotView.initialize()");
        
        super.setViewName("Robot");
        super.setGraphicsContext( canvas.getGraphicsContext2D() );  
        
        pane.setStyle("-fx-background-color: black");        
        getGraphicsContext().setFill(Color.WHITESMOKE);
        getGraphicsContext().fillRect(0, 0, 800, 600);
    }    
    
    public void echo()
    {
        System.out.println("Simulator.View.RobotView.RobotView.echo()");
    }

    
    
    
    /////////////////////////// Settery i gettery ///////////////////////////
    
    
    
    public Pane getPane()
    {
        return this.pane;
    }
    
    public Image getImage()
    {
        return robotImageView.getImage();
    }

    private void ImageOnMouseMoved(MouseEvent event) {
        System.out.println("Simulator.View.RobotView.RobotView.ImageOnMouseMoved()");
        //event.consume();
    }

    @FXML
    private void paneOnMouseExited(MouseEvent event) {
        System.out.println("Simulator.View.RobotView.RobotView.paneOnMouseExited()");
    }

    @FXML
    private void paneOnMouseEntered(MouseEvent event) {
        System.out.println("Simulator.View.RobotView.RobotView.paneOnMouseEntered()");
    }

    @FXML
    private void paneOnMouseMoved(MouseEvent event) {
        //System.out.println("Simulator.View.RobotView.RobotView.paneOnMouseMoved()");
        //event.consume();
    }
}
