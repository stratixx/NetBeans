/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Robot.Czujnik.Sensor;
import Simulator.Tools.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Skrzatt
 */
public class RobotSimulated extends RobotAbstract{
    
    public RobotSimulated(double x, double y, long programRate) {
        super(x, y, programRate);           
        super.setName("RobotSimulated");        
    }
    
    private Sensor lidar;
    
    private List<Point> distances;
    
    private double measurementMaxDensity;
    private long measurementLifeTime; 

    @Override
    public void robotInitProcedure(long currTime) 
    {
        measurementMaxDensity = 2.0;        
        measurementLifeTime = 1600; 
        
        lidar = super.getSensor("LIDAR");
        
        distances = new ArrayList<Point>() {
            @Override
            public boolean contains(Object o) {
                Point p = (Point)o;
                List<Boolean> list = new ArrayList<>();
                forEach((point) -> {
                    if( point.subtract(p).magnitude()<measurementMaxDensity )
                        list.add(Boolean.TRUE);
                });
                if(list.size()>0)
                    return true;
                else
                    return false;
            }
        };
        
        lidar.start();
    }
    
    @Override
    public void robotProcedure(long currTime)
    {       
        distances.removeIf((element) -> {
            return ( currTime-element.getCreationTime()>=measurementLifeTime );
        });
        
        if( lidar.isDone())
        {
            lidar.read().forEach((element) -> {
                if( !distances.contains(element) )
                    distances.add(element);
            });
            System.out.println( "Robot.RobotProcedure() get mesurements: " + distances);
            lidar.start();
        }
    }

    @Override
    public void robotEndProcedure(long currTime) 
    {
        distances.clear();
    }
}
