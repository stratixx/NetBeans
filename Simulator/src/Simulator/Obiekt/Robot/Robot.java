/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Robot.Czujnik.Sensor;
import Simulator.Tools.Figura.Kolo;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Wielokat;
import Simulator.Tools.RefreshThread;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Robot extends Obiekt{
    
    //private List<Przeszkoda> elementCalc; // lista przeliczonych punktów robota
    private List<Sensor> sensor;
    private long prevTime;
    private RefreshThread robotThread;
    
    public Robot( double x, double y, long programRate ) 
    {        
        super( new Point2D(x, y), true, false, "Robot");
                
        sensor = new ArrayList<>();        
        prevTime = 0;
        robotThread = new RefreshThread(programRate) {
            @Override
            public void threadProcedure(long currTime) {
                robotProcedure(currTime);
            }
        };
        robotThread.setName("RobotProgramThread");
        
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
        long deltaT = 0;
        
        if( prevTime!=0 )            
            deltaT = currTime - prevTime; 
        prevTime = currTime;
        
        if( !this.move( deltaT/1000 ))
        {
            this.setVelocity( this.getVelocity().multiply(-1.1) );
            this.setRotationSpeed( -1.25*this.getRotationSpeed() );
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
    
    private void robotProcedure(long currTime)
    {/*
        Sensor lidar = getSensor("LIDAR");
        
            if( lidar.currMeasurement.isDone() && !currMeasurement.isReaded() )
            {
                currMeasurement.getMeasurements();
            }
        lidar.startMeasurement();*/
    }
    
}
