package Simulator.Controller;

import Simulator.Tools.Drawer;


public interface ControllerViewInterface {
       
    public void simulatorRefreshRequest( Drawer gc );
    public void startMoving();
    public void stopMoving();
}
