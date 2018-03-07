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
    
    final private List<Point2D> distances; // Odległości wyznaczone w danym pomiarze
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
        beamsNumber = 60; // old 36
        started = false;
        done = false;
        readed = false;
        orientation = 0.0; // przesunięcie kąta pomiaru w celu dokładnego pokrycia
        range = 1000;
        prevTime = 0;
        startTime = 0;
        delay = 10;
               
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
    public List<Point2D> read()
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
                    //orientation += 360/beamsNumber;
                    Promien newPromien = new Promien(robot.getOffset(), orientation+360*n/beamsNumber+robot.getTheta(), range);
                    promien.add( newPromien );
                    
                    //distances.add( new Point( newPromien.getDirection()) );  
                }
                orientation += 360.0/beamsNumber/10.0;   
                if( orientation>=360.0) orientation -= 360.0;                
                //System.out.println("Czujnik.LIDAR.tick() check crosses start");
                robot.getModel().getObjectsList().forEach((element) -> 
                {
                    //System.out.println("Czujnik.LIDAR.tick() figury");
                    if( element.getID()!=robot.getID() )
                        element.getElementList().forEach((figura) -> 
                        {
                            figura.checkCross( promien, element.getOffset() );
                            //System.out.println("Czujnik.LIDAR.tick() promien: "+promien);
                        }); 
                });    
                promien.forEach((element) -> {
                    if( element.getCrossList().size()>0 )
                    {
                        Point2D point = element.getMinCrossPoint();
                        if( point.distance(robot.getOffset())<=range )
                            distances.add( point.subtract(element.getStart()) );
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
        /*
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
                drawer.strokeLine(robot.getOffset().getX(), robot.getOffset().getY(), point.getX(), point.getY());
                drawer.strokePoint( point.getX(), point.getY());
            });
            drawer.setoffset(offset);
        }*/
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
