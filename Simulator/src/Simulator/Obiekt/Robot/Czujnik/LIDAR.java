/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot.Czujnik;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.SensorBeam.SensorBeam;
import Simulator.Tools.Drawer;
import Simulator.Tools.MyMath;
import Simulator.Tools.Point;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Skrzatt
 */
public class LIDAR extends Czujnik {
    
    List<Point> points;
    final private double pointsLifeTime;
    final private double pointsDensity;
    final private int beamsNumber;
    final private double fotonSpeed;
    List<SensorBeam> beams;
    Obiekt robot;
    double time;
    private Point2D vectorOrginal;
    private String name;
    
    public LIDAR( Obiekt newRobot )
    {
        name = "LIDAR";
        this.fotonSpeed = 500;
        this.beamsNumber = 6;
        this.pointsDensity = 3.5;
        this.pointsLifeTime = 4.5;
        points = new ArrayList<Point>() {
            @Override
            public boolean contains(Object o) {
                Point p = (Point)o;
                List<Boolean> list = new ArrayList<>();
                forEach((point) -> {
                    if( point.subtract(p).magnitude()<pointsDensity )
                        list.add(Boolean.TRUE);
                });
                if(list.size()>0)
                    return true;
                else
                    return false;
            }
        };
        
        
        beams = new ArrayList<>();
        robot = newRobot;
        time = 0.0;
        vectorOrginal = new Point2D(fotonSpeed, 0);
    }
    
    @Override
    public void scan() {
        //System.out.println("Simulator.Obiekt.Robot.Czujnik.LIDAR.scan()");
        for( int n=0; n<360; n=n+(360/beamsNumber))
        {
            beams.add( new SensorBeam( this, robot.getOffset(), MyMath.rotate(vectorOrginal, n), robot.getID()));    
        }
            vectorOrginal = MyMath.rotate(vectorOrginal, -8);
    }
    
    @Override
    public void tick( double deltaT )
    {
        time+=deltaT;
        
        beams.forEach((beam) -> {
            beam.tick( deltaT );
        });        
        
        beams.removeIf((beam) -> {
            if( ! beam.isActive() )
            {
                Point p = new Point(beam.getEndPosition(), time );
                if( !points.contains( p ) )
                    points.add( p );
                //else
                    //System.out.println("Simulator.Obiekt.Robot.Czujnik.LIDAR.tick() contains: "+points.size());
            }
                //System.out.println( "DISTANCE: " + beam.getOffset().distance(beam.getStartPosition()) + "; " );
            return !beam.isActive();
        });
        
        points.removeIf((point) -> {
            if( (point.getCreationTime()+pointsLifeTime)<time )
                {
                    //System.out.println("Simulator.Obiekt.Robot.Czujnik.LIDAR.tick() delete");
                    return true;
                }
            return false;
        });
    }
    
    @Override
    public void draw( Drawer drawer)
    {
        List<SensorBeam> tmp = new ArrayList<>(beams);
        drawMeasurement(drawer);
        tmp.forEach((beam) -> {
            beam.draw( drawer );
        });
    }
    
    @Override
    public void drawMeasurement( Drawer drawer )
    {
        Point2D offset = drawer.getOffset();
        drawer.setoffset(new Point2D(0, 0));
        drawer.setFill(Color.GREY);
        List<Point2D> tmp = new ArrayList<>(points);
        tmp.forEach((point) -> {
            drawer.fillOval(point.getX()-pointsDensity/2, point.getY()-pointsDensity/2, pointsDensity*2/2, pointsDensity*2/2);
            
        });
        drawer.setoffset(offset);
    }
    
    public VetoableChangeListener getChangeListener()
    {
        return robot.getVetoableChangeSupport().getVetoableChangeListeners()[0];
    }
    
    @Override
    public String getName()
    {
        return name;
    }
}
