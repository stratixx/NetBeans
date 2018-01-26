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
    
    /**
     * Obliczenie kąta pomiędzy punktem A, a B  wierzchołkiem vertex
     * kąt narasta przeciwnie do kierunku zegara
     * zakres <0 ; 360) stopni
     * @param a punkt początkowy
     * @param vertex wierzchołek
     * @param b punkt końcowy
     * @return kąt pomiędzy A i B
     */
    public static double angle( Point2D a, Point2D vertex, Point2D b  )
    {        
        double result;
        a = a.subtract(vertex);
        b = b.subtract(vertex);
        result = Math.atan2( b.getY(), b.getX() );
        result -= Math.atan2( a.getY(), a.getX() );
        result = Math.toDegrees(result);
        if(result<0.0)
            result += 360.0;
        
        return result;
    }
    
}
