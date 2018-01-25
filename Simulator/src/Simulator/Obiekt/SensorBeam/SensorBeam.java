/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.SensorBeam;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Robot.Czujnik.LIDAR;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Kolo;
import Simulator.Tools.Figura.Prostokat;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class SensorBeam extends Obiekt {
    
    private Figura ksztalt;
    private LIDAR lidar;
    private Point2D startPosition;
    private Point2D endPosition;
    
    private Boolean active;
    
    
    public SensorBeam( LIDAR lidar, Point2D start, Point2D newVelocity, int newID ) {
        super(start, false, false, "SensorBeam");
        
        this.lidar = lidar;
        startPosition = start;
        endPosition = start; 
        active = true;
        super.setID(newID);
        super.setVetoableChangeListener(lidar.getChangeListener());
        super.setVelocity(newVelocity);
        
        super.getElementList().add( new Kolo(0, 0, 4, false, true) );
        //super.getElementList().add( new Prostokat(0, 0, 1, 1) );
    }
    
    public void tick( double deltaT )
    {
        // wystąiła kolizja z obiektem
        if( !super.move( deltaT ) )
            if( !super.move( deltaT/4 ) )
        {
            active = false;
            endPosition = super.getOffset();
            super.getVetoableChangeSupport().removeVetoableChangeListener(lidar.getChangeListener());
        }
    }
    
    public Boolean isActive()
    {
        return active;
    }
    
    public Point2D getEndPosition()
    {
        return endPosition;
    }
    
    public Point2D getStartPosition()
    {
        return startPosition;
    }
}
