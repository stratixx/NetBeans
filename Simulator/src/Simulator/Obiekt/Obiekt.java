/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt;

import Simulator.Tools.Axis;
import Simulator.Tools.Drawer;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Prostokat;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * Obiekt jest obserwowany przez nadzorcę kolizji obiektów
 * @author Skrzatt
 */
 public class Obiekt{
    
    VetoableChangeSupport changeSupport; // rozgłaszanie zmiany pozycji obiektu
             
    private Point2D offset; // pozycja obiektu i jego oś obrotu
    private double theta; // kąt zwrotu obiektu
     
    private Boolean visible; // Czy widzialny optycznie?
    private Boolean transparent; // Czy można "wjechać" w ten obiekt?
    private Boolean hasCollison; // Czy krok wcześniej wystąpiła kolizja?
    
    private Point2D velocity; // wektor prędkości obiektu
    private double rotationSpeed; // prędkość obrotowa/kątowa
    
    private List<Figura> element; // lista punktów robota
    
    private int ID;    
    private String name;
    
    public Boolean debugObwiedniaObiektu = true;
    public Boolean debugObiektOffset = false;
    public Boolean debugObiektID = false;
    
    public Obiekt( Point2D newOffset, Boolean newVisible, Boolean newTransparent, List<Figura> newElement, String newName )
    {        
        changeSupport = new VetoableChangeSupport(this);
        
        offset =  newOffset;
        theta = 0.0;
        visible = newVisible;
        transparent = newTransparent;
        hasCollison = false;
        velocity = new Point2D(0, 0);
        rotationSpeed=0;
        element = newElement;
        ID = Integer.MIN_VALUE;
        name = newName;
    }
    
    public Obiekt( Point2D newOffset, Boolean newVisible, Boolean newTransparent, String newName )
    {        
        changeSupport = new VetoableChangeSupport(this);
        
        offset =  newOffset;
        theta = 0.0;
        visible = newVisible;
        transparent = newTransparent;
        hasCollison = false;
        velocity = new Point2D(0, 0);
        rotationSpeed=0;
        element = new ArrayList<>();
        ID = Integer.MIN_VALUE;
        name = newName;
    }
    
    public Obiekt( Obiekt obiekt, Point2D newOffset, double newTheta, List<Figura> newElement )
    {
        changeSupport = obiekt.getVetoableChangeSupport();
        
        offset =  newOffset;
        theta = newTheta;
        visible = obiekt.isVisible();
        transparent = obiekt.isTransparent();
        hasCollison = obiekt.hasCollision();
        velocity = obiekt.getVelocity();
        rotationSpeed = obiekt.getRotationSpeed();
        element = newElement;
        ID = Integer.MIN_VALUE;
        name = obiekt.getName();
    }
    
    /**
     * Próba przemieszczenia obiektu
     * oryginalna pozycja w celu minimalizacji błędów numerycznych 
     * jest przesuwana ale nie jest obracana o kąt 
     * wynik obliczeń uwzględniający wszystkie poprzednie transformacje
     * zawarty jest w zmiennej positionCalc
     * @param deltaT chwila czasu
     * @param deltaX przemieszczenie obiektu wzdłóż osi X
     * @param deltaY przemieszczenie obiektu wzdłóż osi Y
     * @param deltaTheta obrócenie obiektu wokół osi obrotu o kąt Theta
     * @return
     */
    public Boolean move( double deltaT  )
    {
        double deltaX = velocity.getX()*deltaT;
        double deltaY = velocity.getY()*deltaT;
        double deltaTheta = rotationSpeed * deltaT;
        //System.out.println("Robot.move()");
        // kopia danych przed próbą zmiany
        Point2D oldOffset = getOffset();
        double oldTheta = getTheta();
        List<Figura> oldElement = new ArrayList<>();
        element.forEach((el) -> {
            oldElement.add(el);
        });
        
        setOffset( oldOffset.add(deltaX, deltaY) );
        setTheta( oldTheta + deltaTheta );
                
        element.forEach((t) -> { t.move(new Point2D(0,0), getTheta()); });
        
        try {
            getVetoableChangeSupport().fireVetoableChange(
                    getID()+"; Typ: "+this.name+";", 
                    new Obiekt( this, oldOffset, oldTheta, oldElement ),
                    this);
            // return true;
        } catch (PropertyVetoException ex) {
            setTheta(oldTheta);
            setOffset(oldOffset);
            element = oldElement;
            this.setCollision(true);
            return false;
        }
        this.setCollision(false);
        return true;
    }  
    
    public void draw( Drawer drawer )
    {
        Point2D point = this.getOffset();
        List<Figura> tmpList = new ArrayList<>(this.getElementList());
        double x = point.getX();
        double y = point.getY();
        double maxX = Collision.getMaxX(tmpList) ;
        double minX = Collision.getMinX(tmpList) ;
        double maxY = Collision.getMaxY(tmpList) ;
        double minY = Collision.getMinY(tmpList) ;
        
        drawer.setoffset(point);
        
        if( debugObwiedniaObiektu)
        {
            Prostokat obwiednia =  new Prostokat(minX, minY, maxX-minX, maxY-minY );
            obwiednia.setVisible(false);
            obwiednia.setTransparent(false);
            //obwiednia.draw(drawer);
        }
        
        tmpList.forEach((obiekt) -> 
        { 
            obiekt.draw(drawer);             
        });  
        
        if( debugObiektOffset )
        {
            drawer.getGC().setFill(Color.RED);
            drawer.strokeOval(-0.5, -0.5, 1, 1);
        }
        if( debugObiektID )
        {
            drawer.getGC().setFill(Color.GREEN);
            drawer.getGC().fillText(Integer.toString(ID), x, y);
        }
    }
    
    @Override
    public String toString()
    {
        String x = Double.toString(offset.getX());
        String y = Double.toString(offset.getY());
        String angle = Double.toString(theta);
        return "ID: "+ID+"; Name: "+name+"; Position: ("+x+","+y+"); Theta:"+angle+"; Elements: "+element.toString();                
    }
    
    public void setVetoableChangeListener( VetoableChangeListener listener )
    {
        this.changeSupport.addVetoableChangeListener(listener);
    }
    
    public VetoableChangeSupport getVetoableChangeSupport()
    {
        return this.changeSupport;
    }
    /////////// Settery i Gettery ///////////////////////////  
    
    public final void setOffset( Point2D newOffset )
    {
        offset = new Point2D( newOffset.getX(), newOffset.getY() );
    }
    
    public Point2D getOffset()
    {
        return offset;
    }
    
    public void setTheta( double newTheta )
    {
        theta = newTheta;
    }
    
    public double getTheta()
    {
        return theta;
    }
    
    public final void setVisible( Boolean newVisible )
    {
        visible = newVisible;
    }
    
    public Boolean isVisible()
    {
        return visible;
    }
    
    public final void setTransparent( Boolean newTransparent )
    {
        transparent = newTransparent;
    }
    
    public Boolean isTransparent()
    {
        return transparent;
    }  
    
    public final void setCollision( Boolean newCollision )
    {
        hasCollison = newCollision;
    }    
    
    public Boolean hasCollision()
    {
        return hasCollison;
    }
    
    public final void setVelocity( Point2D newVelocity )
    {
        velocity = newVelocity;
    }
    
    public Point2D getVelocity()
    {
        return velocity;
    }
    
    public void setRotationSpeed( double newRotationSpeed )
    {
        rotationSpeed = newRotationSpeed;
    }
    
    public double getRotationSpeed()
    {
        return rotationSpeed;
    }
    
    public void setElementList( List<Figura> element)
    {
        this.element = element;
    }
    
    public List<Figura> getElementList()
    {
        return element;
    }
    
    public void setID( int newID )
    {
        ID = newID;
    }
    
    public int getID()
    {
        return ID;
    }
    
    public void setName( String newName )
    {
        name = newName;
    }
    
    public String getName()
    {
        return name;
    }
}
