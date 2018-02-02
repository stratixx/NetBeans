/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools.Figura;

import Simulator.Tools.Axis;
import Simulator.Tools.Drawer;
import Simulator.Tools.Point;
import Simulator.Tools.Projection;
import Simulator.Tools.Promien;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
abstract public class Figura {
    
    private double theta; // kąt obrotu figury
    private Point2D massCenter;
    
    private Boolean visible;
    private Boolean transparent;
    
    private String name;
    
    
    public Figura(Boolean newVisible, Boolean newTransparent, String newName){
        this.name = newName;
        theta = 0.0;
        massCenter = null;
        visible = newVisible;
        transparent = newTransparent;
    }  
    
    public Figura( String newName ){
        this.name = newName;
        theta = 0.0;
        massCenter = null;
    }       
        
    public Boolean move( Point2D positionDelta, double newTheta  )
    {
        theta = newTheta;
        return true;
    }    
    
    abstract public void draw( Drawer drawer );    
    abstract public double getMaxX();    
    abstract public double getMinX();    
    abstract public double getMaxY();    
    abstract public double getMinY();    
    abstract public Point2D setAxisOnMassCenter();    
    abstract public List<Axis> getAxis();
    abstract public Projection projectOnAxis( Axis axis, Point2D offset );
    abstract public void checkCross( List<Promien> promien, Point2D figuraOffset);
    
    @Override
    public String toString()
    {
        return "Type: Figura; ";
    }
    
    public double getTheta()
    {
        return theta;
    }
    
    public void setTheta( double newTheta)
    {
        theta = newTheta;
    }
    
    public final void setVisible( Boolean newVisible )
    {
        visible = newVisible;
    }
    
    public Boolean isVisible()
    {
        return visible;
    }
    
    public final void setTransparent( Boolean newTransparent )
    {
        transparent = newTransparent;
    }
    
    public Boolean isTransparent()
    {
        return transparent;
    }  
    
    public void setName( String newName )
    {
        name = newName;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Point2D getMassCenter()
    {
        return massCenter;
    }
    
    protected void setMassCenter( Point2D newMassCenter)
    {
        massCenter = newMassCenter;
    }
}
