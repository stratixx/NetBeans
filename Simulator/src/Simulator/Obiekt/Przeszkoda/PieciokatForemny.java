/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Tools.Figura.Wielokat;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class PieciokatForemny extends Przeszkoda{
    
    public PieciokatForemny(Point2D newPosition) {
        super(newPosition);
        double mul=3.0;
        
        Wielokat ksztalt = new Wielokat( new ArrayList<Point2D>()
        {{
            add(new Point2D(0.0,20.0).multiply(mul));
            add(new Point2D(-19.0211303259031,	6.18033988749895).multiply(mul));
            add(new Point2D(-11.7557050458495,	-16.1803398874989).multiply(mul));
            add(new Point2D(11.7557050458495,	-16.1803398874990).multiply(mul));
            add(new Point2D(19.0211303259031,	6.18033988749895).multiply(mul));
        }});
        
        
        
        ksztalt.setAxisOnMassCenter();
        super.getElementList().add(ksztalt);
        
    }
}
