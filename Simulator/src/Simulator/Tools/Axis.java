/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools;

import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Axis  {
    
    private Point2D versor;
    
    public Axis( Point2D start, Point2D end)
    {
        Point2D vector = start.subtract(end).normalize();
        //if( vector.getY()<0 )
        //    versor = new Point2D(-vector.getY(), vector.getX()); // right hand normal
        //else
            versor = new Point2D(vector.getY(), -vector.getX()); // left hand normal
        
    } 
    
    public Point2D getVersor()
    {
        return versor;
    }
    
    public String toString()
    {
        return versor.toString();
    }
}
