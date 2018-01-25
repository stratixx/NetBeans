package Simulator.View;

import Simulator.Controller.ControllerViewInterface;
import Simulator.Controller.ViewInterface;
import Simulator.Tools.Drawer;
import Simulator.View.MainView.MainView;
import Simulator.View.RobotView.RobotView;
import Simulator.View.SimulatorView.SimulatorView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class View implements ViewInterface
{
    // referencja na obiekt kontrolera
    private ControllerViewInterface controller; 
    
    private final String resourcePath;
    
    private RobotView robot;
    private final String robotResource;
    
    private SimulatorView simulator;
    private final String simulatorResource;
    
    private MainView mainView;
    private final String mainResource;
        
    public View()
    {
        resourcePath = "/Simulator/View";
        robotResource = resourcePath + "/RobotView/RobotView.fxml";
        simulatorResource = resourcePath + "/SimulatorView/SimulatorView.fxml";
        mainResource = resourcePath + "/MainView/MainView.fxml";
    }
    
    /**
     * Ustawienie referencji na kontroler
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerViewInterface controller){
        this.controller = controller;        
    }
    
    public void refreshLoop()
    {        
            Platform.runLater(() -> 
            {
                refreshLoop();
            });
            controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
    }

    @Override
    public void launch( Stage PrimaryStage ) throws IOException{ 
        
        robot     = (RobotView)     ViewController.loadView(this, getClass().getResource(robotResource)    );
        simulator = (SimulatorView) ViewController.loadView(this, getClass().getResource(simulatorResource));
        mainView  = (MainView)      ViewController.loadView(this, getClass().getResource(mainResource)     );
            
        mainView.addNodeAsNewTab( robot.getPane()    , robot.getViewName()    );
        mainView.addNodeAsNewTab( simulator.getPane(), simulator.getViewName()); 
        
        controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
    
        
            Thread thread = new Thread(() -> {
                int f=30;
                int n=10;
                controller.startMoving();
                while( true )
                {
                    if( !mainView.enableButton.isSelected() )
                        break;
                    
                    Platform.runLater(() -> {
                        controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
                    });
                    try {
                        Thread.sleep(1000/f);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
                
                controller.stopMoving();
                
            });
            thread.setDaemon(true);
            thread.setName("ViewRefreshThreadHz");
            
        mainView.enableButton.setOnMouseClicked((event) -> 
        {
            //controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
            if( mainView.enableButton.isSelected() )
                thread.start();
            
        });
        
        simulator.canvas.setOnMouseMoved((event) -> 
        {
            mainView.mouseLabel.setText(event.getX()+":"+event.getY());
            //controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
        });
        
        PrimaryStage.setScene(new Scene(mainView.getContent()));
        PrimaryStage.show();
    }
    
    public GraphicsContext getRobotGraphicsContext()
    {
        return robot.getGraphicsContext();
    }
    
    public GraphicsContext getSimulatorGraphicsContext()
    {
        return simulator.getGraphicsContext();
    }
    
}