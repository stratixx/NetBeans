/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools.Figura;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 * Przeszkoda prostokątna
 * @author Skrzatt
 */
public class Prostokat extends Wielokat{
    
    private Point2D pointOriginal; // punkt oryginalny
    private Point2D point; // punkt po obliczeniach
    private double width;
    private double height;
    
    
    
    public Prostokat( double x0, double y0, double width, double height, double xAxis, double yAxis )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(new Point2D(x0, y0));
                add(new Point2D(x0+width,y0));
                add(new Point2D(x0+width,y0+height));
                add(new Point2D(x0,y0+height));
            }} );
                
        pointOriginal = new Point2D(x0, y0);
        point = pointOriginal;
        super.setName("Prostokat");
        this.width = width;
        this.height = height;
    }
    
    public Prostokat( double x0, double y0, double width, double height )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(new Point2D(x0, y0));
                add(new Point2D(x0+width,y0));
                add(new Point2D(x0+width,y0+height));
                add(new Point2D(x0,y0+height));
            }} );
        pointOriginal = new Point2D(x0, y0);
        point = pointOriginal;
        super.setName("Prostokat");
        this.width = width;
        this.height = height;
    }
    
    public Prostokat( double x0, double y0, double width, double height, Boolean newVisible, Boolean newTransparent )
    {
        super( new ArrayList<Point2D>(){
            {       
                add(new Point2D(x0, y0));
                add(new Point2D(x0+width,y0));
                add(new Point2D(x0+width,y0+height));
                add(new Point2D(x0,y0+height));
            }}, newVisible, newTransparent );
        pointOriginal = new Point2D(x0, y0);
        point = pointOriginal;
        super.setName("Prostokat");
        this.width = width;
        this.height = height;
    }
    
    @Override
    public String toString()
    {
        String x = Double.toString(point.getX());
        String y = Double.toString(point.getY());
        
        return "Type: Prostokat; Point: ("+x+","+y+"); Angle: "+Double.toString(super.getTheta())+"; ";
    }
    
    ////////// Settery i Gettery //////////////////
    
    public double getWidth()
    {
        return width;
    }
    
    public double getHeight()
    {
        return height;
    }
}
