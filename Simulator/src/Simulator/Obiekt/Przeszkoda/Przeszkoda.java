/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Obiekt.Obiekt;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Promien;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
abstract public class Przeszkoda extends Obiekt{
        
    public Przeszkoda( Point2D newPosition, List<Figura> newElement )
    {
        super( newPosition, true, false, newElement, "Przeszkoda");        
    }
        
    public Przeszkoda( Point2D newPosition )
    {
        super( newPosition, true, false, "Przeszkoda");        
    }
     
    public Przeszkoda( Point2D newPosition, List<Figura> newElement, String name )
    {
        super( newPosition, true, false, newElement, name);        
    }
    //////////////// Settery i Gettery /////////////////
    
}