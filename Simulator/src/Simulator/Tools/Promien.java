/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Promien {
    
    private final Point2D start;      // początek promienia
    private final Point2D direction;  // wektor kierunku promienia
    private final double angle;
    private final List<Point2D> crossDistance;  // punkt przecięcia z obiektami
    
    public Promien( Point2D newStart, double newAngle, double newRange )
    {
        start = newStart;
        angle = newAngle;
        direction = MyMath.rotate( new Point2D(0.0, -1.0).multiply(newRange), angle ).add(start);
        crossDistance = new ArrayList<>();
        //crossDistance.add( direction );
    }
    
    public Point2D getStart()
    {
        return start;
    }
    
    public Point2D getDirection()
    {
        return direction;
    }
    
    public List<Point2D> getCrossList()
    {
        return crossDistance;
    }
    
    public void addCross( Point2D newCross )
    {
        crossDistance.add(newCross);
    }
    
    public Point2D getMinCrossPoint()
    {        
        Double result = Double.MAX_VALUE;
        Double tmp;
        Point2D resultPoint = null;
        //System.out.println("Tools.Promien.getMinCrossPoint() start: "+start+" direction: "+direction);
        //System.out.println("Tools.Promien.getMinCrossPoint() "+crossDistance);
        for (Point2D cross : crossDistance) {
            tmp=cross.distance(start);
            if( tmp < result )
            {
                result = tmp;
                resultPoint = cross;
            }
        }
        return resultPoint;
    }
    
    @Override
    public String toString()
    {
        return "Promien: start: "+start+" direction: "+direction+" cross: "+crossDistance.toString()+"; ";
    }
}
