/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Robot.Czujnik.LIDAR;
import Simulator.Obiekt.Robot.Czujnik.Sensor;
import Simulator.Tools.Drawer;
import Simulator.Tools.Figura.Kolo;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Wielokat;
import Simulator.Tools.Promien;
import Simulator.Tools.RefreshThread;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
abstract public class RobotAbstract extends Obiekt{
    
    //private List<Przeszkoda> elementCalc; // lista przeliczonych punktów robota
    private final List<Sensor> sensor;
    private long prevTime;
    private final RefreshThread robotThread;
    
    public RobotAbstract( double x, double y, long programRate ) 
    {        
        super( new Point2D(x, y), true, false, "RobotAbstract");
                
        sensor = new ArrayList<>();        
        prevTime = 0;
        robotThread = new RefreshThread(programRate) {
            @Override
            public void threadInitProcedure(long currTime) {
                robotInitProcedure( currTime );
            }
            
            @Override
            public void threadProcedure(long currTime) {
                robotProcedure( currTime );
            }

            @Override
            public void threadEndProcedure(long currTime) {
                robotEndProcedure( currTime );
            }
        };
        robotThread.setName("RobotProgramThread");
        
        super.setVelocity(new Point2D(90*0.5, 60*0.5));
        super.setRotationSpeed(0);
        addSensor( new LIDAR( this ) );
        
        
        List<Figura> element = super.getElementList();        
        double x0 = -25;
        double y0 = -50;
        double width = 50;
        double height = 100;                
        
        element.add( new Wielokat(new ArrayList<Point2D>()
        {{
            add( new Point2D(x0, y0) );
            add( new Point2D(x0+width/2, y0-height/4) );
            add( new Point2D(x0+width, y0) );
            add( new Point2D(x0+width,y0+height));
            add( new Point2D(x0,y0+height));
        }}));

        element.add( new Kolo(0, -height/2-10/2, width/5));        
        element.add( new Kolo(-width/2, -30, width/5));
        element.add( new Kolo(-width/2, 30, width/5));
        element.add( new Kolo(width/2, -30, width/5));
        element.add( new Kolo(width/2, 30, width/5));
        
        super.setElementList(element);
    }
    
    @Override
    public void draw( Drawer drawer )
    {
        super.draw(drawer);
        
        sensor.forEach((element) -> {
            element.draw(drawer);
        });
    }
    
    public Sensor getSensor(String sensorName)
    {
        for (Sensor czujnik1 : sensor) {
            if( czujnik1.getName().equals(sensorName))
                return czujnik1;
        }
        return null;
    }
    
    public Boolean addSensor( Sensor newSensor )
    {
        return sensor.add(newSensor);
    }
    
    public List<Sensor> getSensor()
    {       
        return sensor;
    }
    
    
    public void tick( long currTime )
    {        
        double deltaT = 0;
        
        if( prevTime!=0 )            
            deltaT = currTime - prevTime; 
        prevTime = currTime;
        
        if( !this.move( deltaT/1000.0 ))
        {
            this.setVelocity( this.getVelocity().multiply(-1.0) );
            this.setRotationSpeed( -1.0*this.getRotationSpeed() );
        }
        
        sensor.forEach((sensor) -> {
            sensor.tick( currTime );
        });
    }
    
    public void startProgram()
    {
        robotThread.startThread();
    }
    
    public void pauseProgram()
    {
        robotThread.pauseThread();
    }
    
    public void stopProgram()
    {
        robotThread.stopThread();
    }
    
    abstract public void robotInitProcedure(long currTime);    
    abstract public void robotProcedure(long currTime);
    abstract public void robotEndProcedure(long currTime);
    
}
