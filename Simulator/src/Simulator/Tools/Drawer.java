/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 *
 * @author Skrzatt
 */
public class Drawer{
    
    private GraphicsContext gc;
    private Point2D offset;
    
    public Drawer( GraphicsContext newgc )
    {
        gc = newgc;
        offset = new Point2D(0, 0);
    }
    
    public void setFill( Paint paint )
    {
        gc.setFill(paint);
    }
    
    public void setStroke( Paint paint )
    {
        gc.setStroke(paint);
    }
    
    public void strokeLine( double x0, double y0, double x1, double y1 )
    {
        gc.strokeLine(x0+offset.getX(), y0+offset.getY(), x1+offset.getX(), y1+offset.getY());
    }
    
    public void strokePoint( double x0, double y0 )
    {
        final double radius = 2;
        gc.strokeOval(x0+offset.getX()-radius/2, y0+offset.getY()-radius/2, radius*2, radius*2);
    }
    
    public void fillPoint( double x0, double y0 )
    {
        gc.fillOval(x0+offset.getX(), y0+offset.getY(), 1.0, 1.0);
    }
    
    public void strokeOval( double x0, double y0, double width, double height)
    {
        gc.strokeOval(x0+offset.getX(), y0+offset.getY(), width, height);
    }
    
    public void fillOval( double x0, double y0, double width, double height)
    {
        //System.out.println("Drawer.fillOval() "+ Double.toString(x0+offset.getX()) + " | " + Double.toString(y0+offset.getY()) + " | " + Double.toString(width) + " | " + Double.toString(height));
        gc.fillOval(x0+offset.getX(), y0+offset.getY(), width, height);
    }
    
    public void strokeRect( double x0, double y0, double width, double height)
    {
        gc.strokeRect(x0+offset.getX(), y0+offset.getY(), width, height);
    }
    
    public void fillRect( double x0, double y0, double width, double height)
    {     
        //System.out.println("Drawer.fillRect()");
        //System.out.println("Drawer.fillRect() "+ Double.toString(x0+offset.getX()) + " | " + Double.toString(y0+offset.getY()) + " | " + Double.toString(width) + " | " + Double.toString(height));
        gc.fillRect(x0+offset.getX(), y0+offset.getY(), width, height);
    }
    
    public void strokePolygon( List<Point2D> points)
    {
        double xPoints[] = new double[points.size()];
        double yPoints[] = new double[points.size()];
        
        Iterator<Point2D> iter = points.iterator();
        Point2D point;
        for( int n=0; iter.hasNext(); n++ )
        {
            point = iter.next();
            xPoints[n] = point.getX() + offset.getX();
            yPoints[n] = point.getY() + offset.getY();
        }
        
        gc.strokePolygon(xPoints, yPoints, points.size());
    }
    
    public void fillPolygon( List<Point2D> points)
    {
        //System.out.println("Drawer.fillPolygon()");
        double xPoints[] = new double[points.size()];
        double yPoints[] = new double[points.size()];
        
        Iterator<Point2D> iter = points.iterator();
        Point2D point;
        for( int n=0; iter.hasNext(); n++ )
        {
            point = iter.next();
            xPoints[n] = point.getX() + offset.getX();
            yPoints[n] = point.getY() + offset.getY();
        }
        
        gc.fillPolygon(xPoints, yPoints, points.size());
    }
    
    public void setGC( GraphicsContext newGC )
    {
        gc = newGC;
    }
    
    public GraphicsContext getGC()
    {
        return gc;
    }
    
    public void setoffset( Point2D newOffset )
    {
        offset = newOffset;
    }
    
    public Point2D getOffset()
    {
        return offset;
    }
}
