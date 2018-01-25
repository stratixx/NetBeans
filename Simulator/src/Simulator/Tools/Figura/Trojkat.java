/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools.Figura;

import Simulator.Tools.Drawer;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 * Przeszkoda trójkątna
 * figura zbudowana na wierzchołkach znajdujących się w punktach ABC
 * @author Skrzatt
 */
public class Trojkat extends Wielokat{
    
    private Point2D pointA;
    private Point2D pointB;
    private Point2D pointC;   

    public Trojkat( Point2D a, Point2D b, Point2D c, double xAxis, double yAxis )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(a);
                add(b);
                add(c);
            }} );
        
        setPointA(a);
        setPointB(b);
        setPointC(c);
        super.setName("Trojkat");
    }

    public Trojkat( Point2D a, Point2D b, Point2D c, Boolean newVisible, Boolean newTransparent )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(a);
                add(b);
                add(c);
            }}, newVisible, newTransparent );
        
        setPointA(a);
        setPointB(b);
        setPointC(c);
        super.setName("Trojkat");
    }

    public Trojkat( Point2D a, Point2D b, Point2D c )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(a);
                add(b);
                add(c);
            }} );
        
        setPointA(a);
        setPointB(b);
        setPointC(c);
        super.setName("Trojkat");
    }
    
    @Override
    public String toString()
    {
        return "Type: Trojkat; ";
    }
    
    //////////// Settery i Gettery /////////////
    
    public void setPointA( Point2D newPoint )
    {
        pointA = new Point2D( newPoint.getX(), newPoint.getY() );
    }
    
    public Point2D getPointA()
    {
        return pointA;
    }
    
    public void setPointB( Point2D newPoint )
    {
        pointB = new Point2D( newPoint.getX(), newPoint.getY() );
    }
    
    public Point2D getPointB()
    {
        return pointB;
    }
    
    public void setPointC( Point2D newPoint )
    {
        pointC = new Point2D( newPoint.getX(), newPoint.getY() );
    }
    
    public Point2D getPointC()
    {
        return pointC;
    }
}
