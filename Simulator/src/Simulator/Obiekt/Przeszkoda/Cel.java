/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Kolo;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Cel extends Przeszkoda{
    
    public Cel(Point2D newPosition) {
        super(newPosition);
         
        super.getElementList().add( new Kolo(0, 0, 15, isVisible(), isTransparent()));
        
        super.setName("Cel");
    }
    
}
