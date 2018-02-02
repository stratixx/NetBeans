/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot.Czujnik;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Robot.RobotAbstract;
import Simulator.Tools.Drawer;
import Simulator.Tools.Point;
import Simulator.Tools.Promien;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Skrzatt
 */
public class LIDAR extends Sensor {
    
    private final String name;
    final private RobotAbstract robot;
    
    final private List<Point> distances; // Odległości wyznaczone w danym pomiarze
    final private int beamsNumber; // ilość jednocześnie wyznaczanych punktów
    private Boolean started;
    private Boolean done; // czy pomiar zakończono
    private Boolean readed; // czy pomiar odczytano
    
    private double orientation;
    private double range;
    
    final private long delay; // Czas trwania pomiaru [ms]
    private long prevTime;    
    private long startTime; // Czas rozpoczęcia pomiaru [ms]
        
    public LIDAR( RobotAbstract newRobot )
    {
        name = "LIDAR";
        robot = newRobot;        
        beamsNumber = 10; // old 36
        started = false;
        done = false;
        readed = false;
        orientation = robot.getTheta();
        range = 5000;
        prevTime = 0;
        startTime = 0;
        delay = 100;
               
        distances = new ArrayList<>();
    }
    
    @Override
    public void start() 
    {
        setStarted(true);
        setDone(false);
        setReaded(false);
        startTime = prevTime;        
    }
    
    @Override
    public List<Point> read()
    {
        synchronized( distances )
        {
            setReaded(true);
            return distances;
        }
    }
    
    
    private List<Promien> promien = new ArrayList<>();
    
    @Override
    public void tick( long currTime )
    {     
        long deltaT = 0;
        
        if( prevTime!=0 )            
            deltaT = currTime - prevTime; 
        prevTime = currTime;
                     
        synchronized( distances )
        {
            if( isStarted() && (currTime-this.startTime)>=delay )
            {
                distances.clear();
                
                promien.clear();
                
                for( int n=0; n<beamsNumber; n++)
                {             
                    orientation += 360/beamsNumber;
                    Promien newPromien = new Promien(robot.getOffset(), orientation, range);
                    promien.add( newPromien );
                    
                    //distances.add( new Point( newPromien.getDirection()) );  
                }
                //orientation += 5.0;                   
          
                robot.getModel().getObjectsList().forEach((element) -> 
                {
                    if( element.getID()!=robot.getID() )
                        element.getElementList().forEach((figura) -> 
                        {
                            figura.checkCross( promien, element.getOffset() );
                        }); 
                });    
               // System.out.println("Simulator.Obiekt.Robot.Czujnik.LIDAR.tick() primien.size() "+promien.size());
                promien.forEach((element) -> {
                    if( element.getCrossList().size()>0 )
                    {
                        //System.out.println("Simulator.Obiekt.Robot.Czujnik.LIDAR.tick() element.size() "+element.getCrossList().size());
                        Point2D point = element.getMinCrossPoint();
                        //if( point.distance(robot.getOffset())<=range )
                            distances.add( new Point(point, currTime) );
                    }
                });
                
                
        
                /*
                for( int n=0; n<360; n=n+(360/beamsNumber))
                {
                    //beams.add( new SensorBeam( this, robot.getOffset(), MyMath.rotate(vectorOrginal, n), robot.getID()));    
                }
                    //vectorOrginal = MyMath.rotate(vectorOrginal, -8);


                Point p = new Point(new Point2D(90, 90), prevTime );
                if( !distances.contains( p ) )
                    distances.add( p );*/
                
                setStarted(false);
                setDone(true);
            }            
        }
    }
    
    @Override
    public void draw( Drawer drawer )
    {
        synchronized(distances)
        {
            Point2D offset = drawer.getOffset();
            drawer.setoffset(new Point2D(0, 0));
            drawer.setFill(Color.BLUE);
            drawer.setStroke(Color.BLUE);
            //List<Point2D> tmp = new ArrayList<>(distances);
            distances.forEach((point) -> {
                drawer.strokeLine(robot.getOffset().getX(), robot.getOffset().getY(), point.getX(), point.getY());
                drawer.strokePoint( point.getX(), point.getY());
            });
            drawer.setoffset(offset);
        }
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public Boolean isStarted()
    {
        return started;
    }
        
    public void setStarted(Boolean newStarted)
    {
        started = newStarted;
    }
        
    @Override    
    public Boolean isDone()
    {
        return done;
    }
        
    public void setDone(Boolean newDone)
    {
        done = newDone;
    }
        
    @Override    
    public Boolean isReaded()
    {
        return readed;
    }
        
    public void setReaded(Boolean newReaded)
    {
        readed = newReaded;
    }
}
