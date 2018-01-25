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
public class MyMath {
        
    public static Point2D rotateVector( Point2D axis, Point2D end, double angle )
    {
        // uzyskanie wektora zaczepionego w (0,0)
        end = end.subtract(axis);
        // obrót wektora o kąt
        end = rotate(end, angle);
        // zwrócenie pozycji po transformacji z przesunięciem
        return end.add(axis);
    }
    
    public static Point2D rotate( Point2D point, double angle )
    {
        // przeskalowanie stopni na radiany
        angle = 2*Math.PI * angle / 360.0;
        // obrót wektora o kąt theta i przesunięcie spowrotem
        double x = point.getX()*Math.cos(angle) - point.getY()*Math.sin(angle);
        double y = point.getX()*Math.sin(angle) + point.getY()*Math.cos(angle);
        
        return new Point2D(x, y);
    }
    
}
