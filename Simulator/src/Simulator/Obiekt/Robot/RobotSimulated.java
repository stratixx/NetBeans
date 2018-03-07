/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Robot.Czujnik.Sensor;
import Simulator.Tools.Drawer;
import Simulator.Tools.MyMath;
import Simulator.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

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
    
    private Point2D position;
    private List<Point2D> distances;
    
    private double measurementMaxDensity;
    private long measurementLifeTime; 
    
    
    @Override
    public void draw( Drawer drawer )
    {
        super.draw(drawer);                
        if( !super.isInitialized() ) return;
            
        if( distances.size()>0 )
        synchronized(distances)
        {
            //drawer.setFill(Color.RED);
            //drawer.setStroke(Color.RED);
            //drawer.strokeOval(-range, -range, range*2, range*2);
            
            Point2D offset = drawer.getOffset();
            drawer.setoffset(new Point2D(0, 0));
            drawer.setFill(Color.BLUE);
            drawer.setStroke(Color.BLUE);
            //List<Point2D> tmp = new ArrayList<>(distances);
            distances.forEach((point) -> {
                //drawer.strokeLine(super.getOffset().getX(), super.getOffset().getY(), point.getX(), point.getY());
                drawer.strokePoint( point.getX(), point.getY());
            });
            drawer.setoffset(offset);
        }
        
    }
    
    @Override
    public void robotInitProcedure(long currTime) 
    {
        measurementMaxDensity = 2.0;        
        measurementLifeTime = 1600; 
        
        lidar = super.getSensor("LIDAR");
        
        
        distances = new ArrayList<Point2D>() {
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
        
        super.setInitialized(true);
    }
    
    @Override
    public void robotProcedure(long currTime)
    {   /*    
        distances.removeIf((element) -> {
            return ( currTime-element.getCreationTime()>=measurementLifeTime );
        });*/
        position = super.getOffset();
        
        if( lidar.isDone())
        {
            synchronized(distances)
            {
                Point2D velocityDelta = Point2D.ZERO;
                
                //System.out.println("RobotSimulated.robotProcedure() LIDAR done");
                distances.clear();
                lidar.read().forEach((element) -> {
                    //if( !distances.contains(element) )
                    //System.out.println(element);
                        distances.add(element.add(position));
                });
                int count = 1;
                for (Point2D _distance : distances) {
                    if( _distance.subtract(position).magnitude()<150.0)
                    {
                        velocityDelta = velocityDelta.add( _distance.subtract(position).multiply(-1.0) );
                        if( _distance.subtract(position).magnitude()<70.0)
                            velocityDelta = velocityDelta.add( _distance.subtract(position).multiply(-5.0) );
                        count++;
                    }
                }
                velocityDelta = velocityDelta.multiply(1.0/count/2.0);
                
                Point2D target = new Point2D(700, 500);
                
                super.setVelocity( velocityDelta.add( target.subtract(position).multiply(0.25) ) );
        super.setRotationSpeed( (-super.getTheta() + MyMath.angle(new Point2D(0, 1), Point2D.ZERO, super.getVelocity())-180.0)*0.55 );
                //System.out.println("Simulator.Obiekt.Robot.RobotSimulated.robotProcedure() "+super.getRotationSpeed());
                //System.out.println( "Robot.RobotProcedure() get mesurements: " + distances);
                lidar.start();
            }
        }
    }

    @Override
    public void robotEndProcedure(long currTime) 
    {
        distances.clear();
    }
}
