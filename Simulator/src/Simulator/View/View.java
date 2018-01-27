package Simulator.View;

import Simulator.Tools.RefreshThread;
import Simulator.Controller.ControllerViewInterface;
import Simulator.Controller.ViewInterface;
import Simulator.View.MainView.MainView;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class View implements ViewInterface
{
    // referencja na obiekt kontrolera
    private ControllerViewInterface controller; 
    
    private final String resourcePath;
    
    private ViewController robot;
    private final String robotResource;
    
    private ViewController simulator;
    private final String simulatorResource;
    
    private MainView mainView;
    private final String mainResource;
     
    private RefreshThread viewRefreshThread;
    
    public View()
    {
        resourcePath = "/Simulator/View";
        robotResource = resourcePath + "/RobotView/RobotView.fxml";
        simulatorResource = resourcePath + "/SimulatorView/SimulatorView.fxml";
        mainResource = resourcePath + "/MainView/MainView.fxml";
        viewRefreshThread = new RefreshThread(30) {
            @Override
            public void threadProcedure( long currTime ) {
                Platform.runLater(() -> {
                    refreshRobotViewRequest();
                });
                Platform.runLater(() -> {
                    refreshSimulatorViewRequest();
                });
            }
        };
        viewRefreshThread.setName("ViewRefreshThread");
    }
    
    public void setController(ControllerViewInterface controller){
        this.controller = controller;        
    }
    
    public ControllerViewInterface getController(){
        return controller;        
    }

    @Override
    public void launch( Stage PrimaryStage ) throws IOException{ 
        
        robot     = ViewController.loadView(this, getClass().getResource(robotResource)    );
        simulator = ViewController.loadView(this, getClass().getResource(simulatorResource));
        mainView  = (MainView)      ViewController.loadView(this, getClass().getResource(mainResource)     );
            
        mainView.addNodeAsNewTab( robot.getPane()    , robot.getViewName()    );
        mainView.addNodeAsNewTab( simulator.getPane(), simulator.getViewName()); 
        
        
    
        /*
        simulator.canvas.setOnMouseMoved((event) -> 
        {
            mainView.mouseLabel.setText(event.getX()+":"+event.getY());
            //controller.simulatorRefreshRequest( new Drawer(simulator.getGC()) );
        });*/
        
        refreshRobotViewRequest();
        refreshSimulatorViewRequest();
        
        PrimaryStage.setScene(new Scene(mainView.getContent()));
        PrimaryStage.show();
    }
    
    public void buttonStartAction( ActionEvent event )
    {
        controller.startSimulation();        
        viewRefreshThread.startThread();
    }
    
    public void buttonPauseAction( ActionEvent event )
    {
        controller.pauseSimulation();
        viewRefreshThread.pauseThread();
    }
    
    public void buttonStopAction( ActionEvent event )
    {
        controller.stopSimulation();
        viewRefreshThread.stopThread();
    }
    
    public void refreshRobotViewRequest()
    {
        controller.refreshRobotView( robot.getDrawer() );        
    }
    
    public void refreshSimulatorViewRequest()
    {
        controller.refreshSimulatorView( simulator.getDrawer() );
    }
    
}