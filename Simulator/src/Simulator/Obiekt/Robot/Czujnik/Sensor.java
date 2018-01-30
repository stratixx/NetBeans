/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot.Czujnik;

import Simulator.Tools.Drawer;
import Simulator.Tools.Point;
import java.util.List;

/**
 *
 * @author Skrzatt
 */
abstract public class Sensor {
    
    
    abstract public void start();
    abstract public List<Point> read();
    abstract public Boolean isStarted();
    abstract public Boolean isDone();
    abstract public Boolean isReaded();
    
    
    
    /**
     * symulacja jednej chwili czasu
     */
    abstract public void tick( long currTime );
    
    public void draw( Drawer drawer )
    {
        
    }
    
    public void drawMeasurement( Drawer drawer )
    {
        
    }
    
    abstract public String getName();
    
}
