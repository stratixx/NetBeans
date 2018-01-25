/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Przeszkoda;

import Simulator.Tools.Figura.Prostokat;
import Simulator.Tools.Figura.Wielokat;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Skala extends Przeszkoda {
    
    public Skala(Point2D newPosition) {
        super(newPosition);
        double mul=5.0;
        /*
        Wielokat ksztalt = new Wielokat( new ArrayList<Point2D>()
        {{
            add(new Point2D(0.0,20.0).multiply(mul));
            add(new Point2D(-19.0211303259031,	6.18033988749895).multiply(mul));
            add(new Point2D(-11.7557050458495,	-16.1803398874989).multiply(mul));
            add(new Point2D(11.7557050458495,	-16.1803398874990).multiply(mul));
            add(new Point2D(19.0211303259031,	6.18033988749895).multiply(mul));
        }});*/
        
        
        Wielokat ksztalt = new Wielokat( new ArrayList<Point2D>()
        {{
            add(new Point2D(-40,-80 ).multiply(2));
            add(new Point2D(-20,-59 ).multiply(2));
            add(new Point2D(-10,-30 ).multiply(2));
            add(new Point2D(-10, 10 ).multiply(2));
            //add(new Point2D(-30,-20 ).multiply(2));
            //add(new Point2D(-40,-15 ).multiply(2));
            add(new Point2D(-50,-60 ).multiply(2));
        }});
        //Wielokat ksztalt = new Prostokat(0, 0, 80, 80);
        
        ksztalt.setAxisOnMassCenter();
        super.getElementList().add(ksztalt);
        
    }
    
}
