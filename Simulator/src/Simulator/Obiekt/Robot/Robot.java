/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt.Robot;

import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Robot.Czujnik.Czujnik;
import Simulator.Obiekt.Robot.Czujnik.LIDAR;
import Simulator.Tools.Drawer;
import Simulator.Tools.Figura.Kolo;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Prostokat;
import Simulator.Tools.Figura.Trojkat;
import Simulator.Tools.Figura.Wielokat;
import Simulator.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Robot extends Obiekt{
    
    //private List<Przeszkoda> elementCalc; // lista przeliczonych punktów robota
    private List<Czujnik> czujnik;
    
    
    public Robot( double x, double y ) 
    {        
        super( new Point2D(x, y), true, false, "Robot");
        
        czujnik = new ArrayList<>();        
        
        czujnik.add( new LIDAR( this ) );
        
        List<Figura> element = super.getElementList();        
        double x0 = -25;
        double y0 = -50;
        double width = 50;
        double height = 100;                
        
        element.add( new Wielokat(new ArrayList<Point2D>()
        {{
            add( new Point2D(x0, y0) );
            add( new Point2D(x0+width/2, y0-height/4) );
            add( new Point2D(x0+width, y0) );
            add( new Point2D(x0+width,y0+height));
            add( new Point2D(x0,y0+height));
        }}));
        //element.add( new Prostokat(x0, y0, width, height) );
        //element.add( new Trojkat(new Point2D(x0, y0), new Point2D(x0+width, y0), new Point2D(x0+width/2, y0-height/4) ));
        element.add( new Kolo(0, -55, 10));        
        element.add( new Kolo(-width/2, -30, 10));
        element.add( new Kolo(-width/2, 30, 10));
        element.add( new Kolo(width/2, -30, 10));
        element.add( new Kolo(width/2, 30, 10));
        
        super.setElementList(element);
    }
    
    public Czujnik getSensor(int n)
    {
        return czujnik.get(n);
    }
    
    
    /**
     * Próba przemieszczenia obiektu
     * oryginalna pozycja w celu minimalizacji błędów numerycznych 
     * jest przesuwana ale nie jest obracana o kąt 
     * wynik obliczeń uwzględniający wszystkie poprzednie transformacje
     * zawarty jest w zmiennej positionCalc
     * @param deltaX przemieszczenie obiektu wzdłóż osi X
     * @param deltaY przemieszczenie obiektu wzdłóż osi Y
     * @param deltaTheta obrócenie obiektu wokół osi obrotu o kąt Theta
     * @return
     */    
    @Override    
    public Boolean move( double deltaT )
    {
        czujnik.forEach((sensor) -> {
            sensor.tick( deltaT );
        });
        return super.move(deltaT);
    }
    
    @Override
    public void draw( Drawer drawer )
    {
        super.draw(drawer);
        czujnik.forEach((sensor) -> {
            sensor.draw(drawer);
        });
    }
    
}
