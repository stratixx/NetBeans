/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot.Czujnik;

import Simulator.Tools.Drawer;

/**
 *
 * @author Skrzatt
 */
abstract public class Sensor {
    
    abstract public void scan();
    
    /**
     * symulacja jednej chwili czasu
     */
    public void tick( double deltaT )
    {
        
    }
    
    public void draw( Drawer drawer )
    {
        
    }
    
    public void drawMeasurement( Drawer drawer )
    {
        
    }
    
    abstract public String getName();
    
}
