package Simulator.Controller;

import Simulator.Tools.Drawer;


public interface ControllerViewInterface {
       
    public void refreshSimulatorView( Drawer gc );
    public void refreshRobotView( Drawer gc );    
    
    public void startSimulation(  );
    
    public void pauseSimulation(  );
    
    public void stopSimulation(  );
}
