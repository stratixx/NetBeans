/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Latarnia;

import Simulator.Obiekt.Obiekt;
import Simulator.Tools.Figura.Kolo;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Latarnia extends Obiekt{
    
    public Latarnia( double x0, double y0) {
        super(new Point2D(x0, y0), true, false, "Latarnia");
        
        super.getElementList().add( new Kolo(0, 0, 15, isVisible(), isTransparent()) );
    }
}
        