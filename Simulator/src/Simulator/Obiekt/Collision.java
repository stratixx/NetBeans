/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Obiekt;

import Simulator.Tools.Axis;
import Simulator.Tools.Figura.Figura;
import Simulator.Tools.Figura.Kolo;
import Simulator.Tools.Figura.Wielokat;
import Simulator.Tools.Projection;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Point2D;

/**
 * nadzorca kolizji obiektów
 * @author Skrzatt
 */
public class Collision implements VetoableChangeListener{
    
    List<Obiekt> obiekt;
    
    public Collision()
    {
        
    }
    
    public static Boolean isCollision( Obiekt obiekt )
    {
        return false;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        
        Obiekt objTest = (Obiekt)evt.getNewValue();
        
        Point2D point = objTest.getOffset();
        
        double x = point.getX();
        double y = point.getY();
        double maxX = getMaxX(objTest.getElementList()) + x;
        double minX = getMinX(objTest.getElementList()) + x;
        double maxY = getMaxY(objTest.getElementList()) + y;
        double minY = getMinY(objTest.getElementList()) + y;
        
        Boolean isCollison = false;
        
        // sprawdzenie kolizji z granicami widoku
        if( minX<=0 || maxX>=800 || minY<=0 || maxY>=600 )        
            isCollison = true;
        // sprawdzenie kolizji AABB
        else if( true )
        {    
            
                // dla każdego obiektu potencjalnie kolidującego z obiektem testowanym
                // sprawdź czy ich składowe kolidują ze sobą
                // wykorzystanie metody AABB dla figur
                /*
                nie usuwaj obiektu kolizji jeśli
                którakolwiek figura obiektu koliduje z
                którąkolwiek figurą obiektu testowanego kolizji
                */
            // Sprawdzenie czy AABB obiektów kolidują ze sobą
            ArrayList<Obiekt> collisions = new ArrayList<>();
            ArrayList<Boolean> figCollided = new ArrayList<>();
            
            this.obiekt.forEach((obj) -> {
                if( (objTest.getID()!=obj.getID()) )
                    if( checkObjectAABBCollision( objTest, obj ) )
                    {  
                        figCollided.clear();
                        obj.getElementList().forEach((figObiekt) -> 
                        {
                            objTest.getElementList().forEach((figTest) -> 
                            {
                                //if( checkFigureAABBCollision( figTest, figObiekt, objTest, obj) )
                                {
                                    //System.out.println("Collision.vetoableChange() "+figTest.getName()+"|"+figColision.getName());
                                    if ( checkFiguresCollision( figTest, figObiekt, objTest, obj) )
                                        figCollided.add(true);
                                }
                            });
                        });
                        
                        if(figCollided.size()>0)
                            collisions.add(obj);
                    }
            });
            
            if(collisions.size()>0)    
                isCollison = true;
            collisions.clear();
        }
        
        // Wystąpiła kolizja obiektów
        if( isCollison==true )
        {
            //System.out.println("Simulator.Obiekt.Collision.vetoableChange() collision");
            //if( ((Obiekt)evt.getOldValue()).hasCollision() )
                //System.out.println("Collision.vetoableChange() "+evt.getPropertyName()+" stuck");
            //System.out.println("Collision.vetoableChange() COLLISION, send veto to "+evt.getPropertyName());
            throw new PropertyVetoException("VETO angle", evt);            
        }
    }
    
    public Boolean checkObjectAABBCollision( Obiekt obiekt, Obiekt element)
    {        
        Point2D point = obiekt.getOffset();
        
        double x = point.getX();
        double y = point.getY();
        double maxX = getMaxX(obiekt.getElementList()) + x;
        double minX = getMinX(obiekt.getElementList()) + x;
        double maxY = getMaxY(obiekt.getElementList()) + y;
        double minY = getMinY(obiekt.getElementList()) + y;
        
        point = element.getOffset();
        
        double x_el = point.getX();
        double y_el = point.getY();
        double maxX_el = getMaxX(element.getElementList()) + x_el;
        double minX_el = getMinX(element.getElementList()) + x_el;
        double maxY_el = getMaxY(element.getElementList()) + y_el;
        double minY_el = getMinY(element.getElementList()) + y_el;
        
        if( (minX > maxX_el) || (maxX < minX_el)
         || (minY > maxY_el) || (maxY < minY_el) ) {
            //System.out.println("Simulator.Obiekt.Collision.checkObjectsCollision() colision false");
            return false;
        }
        //System.out.println("Simulator.Obiekt.Collision.checkObjectsCollision() collision true");
        return true;
    }
        
    public Boolean checkFigureAABBCollision( Figura figTest, Figura figColision, Obiekt objTest, Obiekt objColision)
    {
        Point2D point = objTest.getOffset();
        
        double x = point.getX();
        double y = point.getY();
        double maxX = figTest.getMaxX() + x;
        double minX = figTest.getMinX() + x;
        double maxY = figTest.getMaxY() + y;
        double minY = figTest.getMinY() + y;
        
        point = objColision.getOffset();
        
        double x_el = point.getX();
        double y_el = point.getY();
        double maxX_el = figColision.getMaxX() + x_el;
        double minX_el = figColision.getMinX() + x_el;
        double maxY_el = figColision.getMaxY() + y_el;
        double minY_el = figColision.getMinY() + y_el;
        
        if( (minX > maxX_el) || (maxX < minX_el)
         || (minY > maxY_el) || (maxY < minY_el) ) {
            //System.out.println("Simulator.Obiekt.Collision.checkObjectsCollision() colision false");
            return false;
        }
        //System.out.println("Simulator.Obiekt.Collision.checkObjectsCollision() collision true");
        return true;
    }
    
    /**
     * Sprawdzenie kolizji dwóch figur dowolnych
     * @param figTest
     * @param figColision
     * @param objTest
     * @param objColision
     * @return
     */
    public Boolean checkFiguresCollision( Figura figTest, Figura figColision, Obiekt objTest, Obiekt objColision)
    {          
        Point2D offsetTest = objTest.getOffset();
        Point2D offsetCollision = objColision.getOffset();
        Point2D offsetDelta = offsetCollision.subtract(offsetTest);
        
        if( figTest.getName().equals("Kolo") && figColision.getName().equals("Kolo") )
            return checkCircleCollision( (Kolo)figTest, (Kolo)figColision, offsetDelta);
        else if( figTest.getName().equals("Kolo") )
            return checkCircleConvexCollision( (Kolo)figTest, (Wielokat)figColision, offsetDelta );
        else if( figColision.getName().equals("Kolo") )
            return checkCircleConvexCollision( (Kolo)figColision, (Wielokat)figTest, offsetDelta );
        else 
            return checkConvexCollision( (Wielokat)figTest, (Wielokat)figColision, offsetDelta );
        
    }
    
    /**
     * sprawdzenie kolisji dwóch okręgów
     * metoda porównywania odległości od środków okręgów
     * @param figTest figura potencjalnie uderzająca
     * @param figCollision figura w którą potencjalnie uderza figura testowana
     * @param offsetDelta offset pomiędzy obiektami, dodawany do figCollision
     * @return czy zaszłą kolizja figur
     */
    public Boolean checkCircleCollision( Kolo figTest, Kolo figCollision, Point2D offsetDelta)
    {
        return figCollision.getCenter().add(offsetDelta).distance(figTest.getCenter())
                <
                (figCollision.getRadius()+figTest.getRadius());
    }
    
    /**
     * sprawdzenie kolizji okręgu z wielokątem
     * metoda wykorzystująca Voronoi regions
     * @param figTest figura potencjalnie uderzająca
     * @param figCollision figura w którą potencjalnie uderza figura testowana
     * @param offsetDelta offset pomiędzy obiektami, dodawany do figCollision
     * @return czy zaszłą kolizja figur
     */
    public Boolean checkCircleConvexCollision( Kolo figTest, Wielokat figCollision, Point2D offsetDelta )
    {        
        Point2D start = null;
        Point2D end = null;
        Point2D convexCenter = figCollision.getMassCenter().add(offsetDelta);
        Point2D circleCenter = figTest.getCenter();        
        double angleStart =Double.MAX_VALUE;
        double angleEnd = Double.MAX_VALUE;
        
            //System.out.println("Collision.checkCircleConvexCollision() start");
         /*   
        figCollision.getPointsList().forEach((point) -> {
            double angle = convexCenter.angle(circleCenter, point);
            //System.out.println("Collision.checkCircleConvexCollision() angle: "+angle);
        });*/
        /*
        // wyszukanie dwóch najmniejszych kątów
        for (Point2D point : figCollision.getPointsList())
        {
            point = point.add(offsetDelta);            
            double tmp = convexCenter.angle(circleCenter, point);
            
            if( tmp<angleStart )
            {
                if(tmp<angleEnd  )
                {
                    angleStart = angleEnd;
                    start = end;
                    angleEnd = tmp;
                    end = point;
                }                
                else
                {
                    angleStart = tmp;
                    start = point;
                }
            }
        }  */
        
        List<Axis> axis = figCollision.getAxis();
        //axis.clear();
        axis.add(0, new Axis(convexCenter, circleCenter) );
        //axis.add( new Axis(start, circleCenter) );
        //axis.add( new Axis(end, circleCenter) );
        Projection projTest;
        Projection projCollision;           
        
        for (Axis axi : axis) 
        {
            projTest = figTest.projectOnAxis(axi, Point2D.ZERO);
            projCollision = figCollision.projectOnAxis(axi, offsetDelta);
            
            if( !projTest.IsOverlap(projCollision) )
                return false;        
        }
        
        //System.out.println("collision() projTest: "+projTest);      
        //System.out.println("collision() projCollision"+projCollision);
        
        //if( !projTest.IsOverlap(projCollision) )
           // return false;  
        
        
        return true;        
    }
    
    /**
     * sprawdzenie kolizji dwóch wielokątów wypukłych
     * metoda SAT
     * @param figTest figura potencjalnie uderzająca
     * @param figCollision figura w którą potencjalnie uderza figura testowana
     * @param offsetDelta offset pomiędzy obiektami
     * @return czy zaszłą kolizja figur, dodawany do figCollision
     */
    public Boolean checkConvexCollision( Wielokat figTest, Wielokat figCollision, Point2D offsetDelta )
    {           
        List<Axis> axis = figTest.getAxis();
        axis.addAll(figCollision.getAxis());
                
        //System.out.println("axis: "+axis);
        Projection projTest;
        Projection projCollision;
        
        for (Axis axi : axis) 
        {
            projTest = figTest.projectOnAxis(axi, Point2D.ZERO);
            projCollision = figCollision.projectOnAxis(axi, offsetDelta);
            
            if( !projTest.IsOverlap(projCollision) )
                return false;        
        }
        //System.out.println("Collision.checkConvexCollision() return true");
        return true;        
    }
    
    static public double getMaxX( List<Figura> element)
    {
        List<Double> value = new ArrayList<>();
        
        element.forEach( (figura) -> {
            value.add( figura.getMaxX() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MIN_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.max(result, valueArr[n]);            
        }
        
        return result;
    }
    
    static public double getMinX( List<Figura> element)
    {
        List<Double> value = new ArrayList<>();
        
        element.forEach( (figura) -> {
            value.add( figura.getMinX() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MAX_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.min(result, valueArr[n]);            
        }
        
        return result;
    }
    
    static public double getMaxY( List<Figura> element)
    {
        List<Double> value = new ArrayList<>();
        
        element.forEach( (figura) -> {
            value.add( figura.getMaxY() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MIN_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.max(result, valueArr[n]);            
        }
        
        return result;
    }
    
    static public double getMinY( List<Figura> element)
    {
        List<Double> value = new ArrayList<>();
        
        element.forEach( (figura) -> {
            value.add( figura.getMinY() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MAX_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.min(result, valueArr[n]);            
        }
        
        return result;
    }
    
    public void addObjectList( List<Obiekt> newObiekt )
    {
        obiekt = newObiekt;
    }
}
