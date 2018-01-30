/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot.Czujnik;

import Simulator.Obiekt.Obiekt;
import Simulator.Tools.Drawer;
import Simulator.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class LIDAR extends Sensor {
    
    private String name;
    final private Obiekt robot;
    
    final private List<Point> distances; // Odległości wyznaczone w danym pomiarze
    final private int beamsNumber; // ilość jednocześnie wyznaczanych punktów
    private Boolean started;
    private Boolean done; // czy pomiar zakończono
    private Boolean readed; // czy pomiar odczytano
    
    final private long delay; // Czas trwania pomiaru [ms]
    private long prevTime;    
    private long startTime; // Czas rozpoczęcia pomiaru [ms]
        
    public LIDAR( Obiekt newRobot )
    {
        name = "LIDAR";
        robot = newRobot;        
        beamsNumber = 6;
        started = false;
        done = false;
        readed = false;
        prevTime = 0;
        startTime = 0;
        delay = 500;
               
        distances = new ArrayList<Point>();
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
                
                Point point = new Point(new Point2D(startTime/10, 0), startTime);
                
                distances.add( point );                
        
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
    public void drawMeasurement( Drawer drawer )
    {/*
        Point2D offset = drawer.getOffset();
        drawer.setoffset(new Point2D(0, 0));
        drawer.setFill(Color.GREY);
        List<Point2D> tmp = new ArrayList<>(distances);
        tmp.forEach((point) -> {
            drawer.fillOval(point.getX()-measurementMaxDensity/2, point.getY()-measurementMaxDensity/2, measurementMaxDensity*2/2, measurementMaxDensity*2/2);
            
        });
        drawer.setoffset(offset);*/
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
