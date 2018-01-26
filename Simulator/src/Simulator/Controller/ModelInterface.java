package Simulator.Controller;

import Simulator.Tools.Drawer;


public interface ModelInterface 
{    
    public void drawObjects( Drawer drawer );
    public void drawRobot( Drawer drawer );
    public void startThread();
    public void stopThread();
    public void pauseThread();
}
