/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Prostokat;
import Simulator.Tools.Figura.Wielokat;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Bariera extends Przeszkoda {
    
    public Bariera( double width, double height, double thickness) {
        super( new Point2D(0, 0) );
        super.setName("Bariera");
        super.setVisible(true);
        super.setTransparent(false);
        
        Wielokat ksztalt ;
        
        // lewa krawędź
        ksztalt = new Prostokat(0, 0, -thickness, height+thickness);
        super.getElementList().add(ksztalt);
        //górna krawędź
        ksztalt = new Prostokat(width, 0, -width-thickness, -thickness);
        super.getElementList().add(ksztalt);
        // prawa krawędź
        ksztalt = new Prostokat(width, height, thickness, -height-thickness);
        super.getElementList().add(ksztalt);
        // dolna krawędź
        ksztalt = new Prostokat(0, height, width+thickness, thickness);
        super.getElementList().add(ksztalt);
        
        
        //ksztalt.setAxisOnMassCenter();
    }
    
}
