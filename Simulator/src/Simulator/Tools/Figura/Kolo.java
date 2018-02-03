/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools.Figura;

import Simulator.Tools.Axis;
import Simulator.Tools.Drawer;
import Simulator.Tools.MyMath;
import Simulator.Tools.Point;
import Simulator.Tools.Projection;
import Simulator.Tools.Promien;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Przeszkoda okrągła
 * @author Skrzatt
 */
public class Kolo extends Figura {
    
    private Point2D centerOriginal; // oryginalny środek okręgu
    private Point2D center; // środek okręgu po obliczeniach
    private double radius; // promień okręgu
    
    
    public Kolo( double x0, double y0, double newRadius, Boolean newVisible, Boolean newTransparent )
    {
        super(newVisible, newTransparent, "Kolo");
        centerOriginal = new Point2D(x0, y0);
        center = centerOriginal;        
        radius = newRadius;
    }
    
    public Kolo( double x0, double y0, double newRadius )
    {
        super(true, false, "Kolo");
        centerOriginal = new Point2D(x0, y0);
        center = centerOriginal;        
        radius = newRadius;
    }
    
    
    @Override
    public Boolean move( Point2D position, double angle  )
    {
        //System.out.println("Kolo.move()");
        super.move(position, angle);
        center = centerOriginal.add(position);
        center = MyMath.rotate(center, angle);
                
        return true;
    }  
    
    @Override
    public void draw( Drawer drawer )
    {
        
        double x = center.getX();
        double y = center.getY();
        double maxX = getMaxX() ;
        double minX = getMinX() ;
        double maxY = getMaxY() ;
        double minY = getMinY() ;
        
        //if( super.isVisible() )
        {
            //System.out.println("Figura.Kolo.draw()");
            drawer.setFill(Color.YELLOW);
            drawer.setStroke(Color.RED);
        if( !super.isTransparent() )
            drawer.strokeOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
        if( super.isVisible() )
            drawer.fillOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
        }
    }
    
    @Override
    public double getMaxX()
    {
        return center.getX()+radius;
    }
    
    @Override
    public double getMinX()
    {
        return center.getX()-radius;
    }
    
    @Override
    public double getMaxY()
    {
        return center.getY()+radius;
    }
    
    @Override
    public double getMinY()
    {
        return center.getY()-radius;
    }
    
    
    @Override
    public List<Axis> getAxis()
    {
        return null;
    }
    
    @Override
    public Projection projectOnAxis( Axis axis, Point2D offset )
    {
        //System.out.println("Wielokat.projectOnAxis()");
        Projection projection = new Projection();
        Point2D versor = axis.getVersor();
        Point2D center = this.center.add(offset);
        double dp;
        // projekcja dwóch skrajnych punktów okregu na oś
                
        dp = center.subtract(versor.multiply(radius)).dotProduct(versor);
        projection.addProjectedPoint( new Point2D(dp*versor.getX(), dp*versor.getY()) );   
        
        dp = center.add(versor.multiply(radius)).dotProduct(versor);
        projection.addProjectedPoint( new Point2D(dp*versor.getX(), dp*versor.getY()) );  
        
        return projection;
    }
    
    @Override
    public void checkCross( List<Promien> promien, Point2D figuraOffset)
    {
        Point2D _center = this.center.add(figuraOffset);
        //System.out.println("Simulator.Tools.Figura.Kolo.checkCross() center: "+center);
        double xC = _center.getX();
        double yC = _center.getY();
            
        promien.forEach((_promien) -> 
        {
            double xD = _promien.getDirection().getX();
            double yD = _promien.getDirection().getY();
            double xS = _promien.getStart().getX();
            double yS = _promien.getStart().getY();
            double dyDS = yD-yS;
            double dxDS = xD-xS;
            double dySC = yS-yC;
            double dxSC = xS-xC;

            double a, b, c;

            double xA, xB;
            double yA, yB;
            double tA, tB;
            
            
            a = dxDS*dxDS + dyDS*dyDS;
            b = 2*( dxDS*dxSC + dyDS*dySC );
            c = dxSC*dxSC + dySC*dySC - radius*radius;
            //System.out.println("Simulator.Tools.Figura.Kolo.checkCross() "+a+" | "+b+" | "+c);
            if( MyMath.delta(a, b, c)>=0.0  )
            {
            //System.out.println("Simulator.Tools.Figura.Kolo.checkCross() "+a+" | "+b+" | "+c);
                //System.out.println("Simulator.Tools.Figura.Kolo.checkCross() delta>=0 : "+MyMath.delta(a, b, c));
                tA = MyMath.getRootMinus(a, b, c);
                xA = xS+dxDS*tA;
                yA = yS+dyDS*tA;
                
                tB = MyMath.getRootPlus(a, b, c);
                xB = xS+dxDS*tB;
                yB = yS+dyDS*tB;
                    
               // System.out.println("Simulator.Tools.Figura.Kolo.checkCross() tA: "+tA);
                //System.out.println("Simulator.Tools.Figura.Kolo.checkCross() tB: "+tB);
                
                    //System.out.println("Figura.Kolo.checkCross() pre pointA: "+new Point2D(xA,yA));
                    //System.out.println("Figura.Kolo.checkCross() pre pointB: "+new Point2D(xB,yB));
                //if( Double.isFinite(tA) )                
                    if( !((xA > Double.max(xD,xS)) || (xA < Double.min(xD,xS)) || (yA > Double.max(yD,yS)) || (yA < Double.min(yD,yS))) )
                {
                    //System.out.println("Figura.Kolo.checkCross() tA point: "+new Point2D(xA,yA));
                    _promien.addCross(new Point2D(xA, yA)); 
                }
                //if( Double.isFinite(tB) )
                    if( !((xB > Double.max(xD,xS)) || (xB < Double.min(xD,xS)) || (yB > Double.max(yD,yS)) || (yB < Double.min(yD,yS))) )
                {
                    //System.out.println("Figura.Kolo.checkCross() tB point: "+new Point2D(xB,yB));
                    _promien.addCross(new Point2D(xB, yB)); 
                }
            }
            
            
        });
        //promien.get(0).addCross(center);
    }
    
    @Override
    public String toString()
    {
        String x = Double.toString(center.getX());
        String y = Double.toString(center.getY());
        String promien = Double.toString(radius);
        
        return "Type: Kolo; Point: ("+x+","+y+"); Promien:"+promien+"; ";
    }
    
    ////////// Settery i Gettery //////////////////
    
    public void setRadius( double newRadius )
    {
        radius = newRadius;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public void setCenter( Point2D newCenter )
    {
        center = newCenter;
    }
    
    public Point2D getCenter()
    {
        return center;
    }

    @Override
    public Point2D setAxisOnMassCenter() {
        super.setMassCenter(center);
        return center;    
    }
}
