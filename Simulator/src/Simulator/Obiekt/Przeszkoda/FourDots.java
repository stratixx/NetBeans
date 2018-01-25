/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Tools.Figura.Kolo;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class FourDots extends Przeszkoda{
    
    public FourDots(Point2D newPosition, double width, double height, double radius) {
        super(newPosition);
        
            //add( new Prostokat(+width/4, +height, width, height*2) );
        super.getElementList().add( new Kolo(-width/2+radius, height/2-radius, radius, isVisible(), isTransparent()) );
        super.getElementList().add( new Kolo(-width/2+radius, -height/2+radius, radius, isVisible(), isTransparent()) );
        super.getElementList().add( new Kolo(width/2-radius, -height/2+radius, radius, isVisible(), isTransparent()) );
        super.getElementList().add( new Kolo(width/2-radius, height/2-radius, radius, isVisible(), isTransparent()) );
        
        super.setName("FourDots");
    }
    
}
