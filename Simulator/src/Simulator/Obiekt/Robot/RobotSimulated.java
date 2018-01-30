/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Robot.Czujnik.Sensor;

/**
 *
 * @author Skrzatt
 */
public class RobotSimulated extends RobotAbstract{

    private Sensor lidar;
    
    public RobotSimulated(double x, double y, long programRate) {
        super(x, y, programRate);           
        super.setName("RobotSimulated");        
    }
    
    

    @Override
    public void robotInitProcedure(long currTime) 
    {
        lidar = super.getSensor("LIDAR");
        lidar.start();
    }
    
    @Override
    public void robotProcedure(long currTime)
    {        
        if( lidar.isDone())
        {
            System.out.println( "Robot.RobotProcedure() get mesurements: " + lidar.read());
            lidar.start();
        }
    }

    @Override
    public void robotEndProcedure(long currTime) 
    {
        
    }
}
