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
    
    final private List<Point> distances; // Odległości wyznaczone
    final private long measurementLifeTime; // Czas życia punktu [ms]
    final private double measurementMaxDensity; // max gęstość punktów
    final private int beamsNumber; // ilość jednocześnie wyznaczanych punktów
    final private long delay; // Czas trwania pomiaru [ms]
    private Boolean done; // czy pomiar zakończono
    private Boolean readed; // czy pomiar odczytano
    
    private long prevTime;
    
    private class Measurement
    { 
        private long startTime; // Czas rozpoczęcia pomiaru [ms]
        private List<Point> newDistances; // nowe wyznaczone odległości
        
        public Measurement( long startTime )
        {
            this.startTime = startTime;
            newDistances = new ArrayList<>();
            setDone(false);
            setReaded(false);
        }
        
        public void tick( long currTime )
        {
            if( (currTime-this.startTime)>=delay )
            {
                
                newDistances.add(new Point(new Point2D(startTime/10, 0), startTime));
                setDone(true);
            }
        }
        
        public List<Point> getMeasurements()
        {
            setReaded(true);
            return this.newDistances;
        }
        
    }
    
    private Measurement currMeasurement;
        
    public LIDAR( Obiekt newRobot )
    {
        name = "LIDAR";
        robot = newRobot;        
        beamsNumber = 6;
        measurementMaxDensity = 3.5;
        measurementLifeTime = 1000; 
        prevTime = 0;
        delay = 500;
        done = false;
        readed = false;
        currMeasurement = null;
               
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
    }
    
    @Override
    public void start() 
    {
        //System.out.println("Simulator.Obiekt.Robot.Sensor.LIDAR.scan()");
        currMeasurement = new Measurement(prevTime);
        /*
        for( int n=0; n<360; n=n+(360/beamsNumber))
        {
            //beams.add( new SensorBeam( this, robot.getOffset(), MyMath.rotate(vectorOrginal, n), robot.getID()));    
        }
            //vectorOrginal = MyMath.rotate(vectorOrginal, -8);
            
        
        Point p = new Point(new Point2D(90, 90), prevTime );
        if( !distances.contains( p ) )
            distances.add( p );*/
    }
    
    @Override
    public List<Point> read()
    {
        synchronized( distances )
        {
        synchronized( currMeasurement )
        {
            List<Point> list = currMeasurement.getMeasurements();
            list.forEach((element) -> {
                if( !distances.contains(element))
                    distances.add(element);
            });
            currMeasurement = null;
            return list;
        }
        }
    }
    
    
    
    @Override
    public void tick( long currTime )
    {     
        long deltaT = 0;
        
        if( prevTime!=0 )            
            deltaT = currTime - prevTime; 
        prevTime = currTime;
        
        if( currMeasurement!=null )        
            synchronized( currMeasurement )
            {
                currMeasurement.tick(currTime);            
            }        
        
        synchronized( distances )
        {
            distances.removeIf((point) -> {
                return ( (point.getCreationTime()+measurementLifeTime)<currTime );
            });
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
        return ( currMeasurement!=null );
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
