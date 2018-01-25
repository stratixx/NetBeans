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
public class Point extends Point2D {
    
    private double creationTime;
    
    public Point(double x, double y) {
        super(x, y);
        creationTime = 0;
    }
    
    public Point(Point2D point, double time) {
        super(point.getX(), point.getY());
        creationTime = time;
    }
    
    public Point(Point2D point) {
        super(point.getX(), point.getY());
        creationTime = 0;
    }
    
    public Point(double x, double y, double time) {
        super(x, y);
        creationTime = time;
    }
    
    public void setCreationTime( double time )
    {
        creationTime = time;
    }
    
    public double getCreationTime()
    {//this.
        return creationTime;
    }
    
    //static public Point2D projection( Point2D projected)
}
