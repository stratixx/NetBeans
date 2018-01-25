/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.View.SimulatorView;

import Simulator.View.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 * widok symulacji
 * @author Skrzatt
 */
public class SimulatorView extends ViewController implements Initializable {

    @FXML
    public Canvas canvas;
    @FXML
    private Pane pane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Simulator.View.SimulatorView.SimulatorView.initialize()");
        
        super.setViewName("Symulator");
        super.setGraphicsContext( canvas.getGraphicsContext2D() );        
        
        pane.setStyle("-fx-background-color: black");        
        getGraphicsContext().setFill(Color.WHITESMOKE);
        getGraphicsContext().fillRect(0, 0, 800, 600);
        
    }    

    
    
    public Pane getPane()
    {
        return this.pane;
    }    
    
    public GraphicsContext getGC()
    {
        return canvas.getGraphicsContext2D();
    }
    
    
    public void addNode( Image newImg )
    {
        this.canvas.getGraphicsContext2D().drawImage(newImg, 0, 0);
    }

    @FXML
    private void paneOnMouseExited(MouseEvent event) {
        System.out.println("SimulatorView.paneOnMouseExited()");
    }

    @FXML
    private void paneOnMouseEntered(MouseEvent event) {
        System.out.println("SimulatorView.paneOnMouseEntered()");
    }

    @FXML
    private void paneOnMouseMoved(MouseEvent event) {
        //System.out.println("Simulator.View.SimulatorView.SimulatorView.paneOnMouseMoved()");
    }

    @FXML
    private void canvasOnMouseExited(MouseEvent event) {
        System.out.println("SimulatorView.canvasOnMouseExited()");
    }

    @FXML
    private void canvasOnMouseEntered(MouseEvent event) {
        System.out.println("SimulatorView.canvasOnMouseEntered()");
    }

            
}
