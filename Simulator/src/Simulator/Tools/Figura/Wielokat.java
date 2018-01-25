/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools.Figura;

import Simulator.Tools.Axis;
import Simulator.Tools.Drawer;
import Simulator.Tools.MyMath;
import Simulator.Tools.Projection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Skrzatt
 */
public class Wielokat extends Figura {
    
    private List<Point2D> pointOriginal; // punkty oryginalne
    private List<Point2D> point; // punkty po obliczeniach
    
    public Wielokat( List<Point2D> newPoint) {
        
        super( true, false, "Wielokat" );
        
        pointOriginal = newPoint;
        point = new ArrayList<>(pointOriginal);
    }      
    
    public Wielokat( List<Point2D> newPoint, Boolean newVisible, Boolean newTransparent) {
        
        super( newVisible, newTransparent, "Wielokat" );
        
        pointOriginal = newPoint;
        super.setName("Wielokat");
        point = new ArrayList<>(pointOriginal);
    }    
    
    @Override
    public Boolean move( Point2D pointDelta, double newAngle  )
    {        
        //System.out.println("Prostokat.move()");
        super.move(pointDelta, newAngle);
        List<Point2D> tmpPoints = new ArrayList<>();
        pointOriginal.forEach( (el) -> 
        { 
            el = el.add(pointDelta); 
            tmpPoints.add(MyMath.rotate(el, super.getTheta()));
        });        
        //point.clear();
        point = tmpPoints;
        return true;
    }
    
    @Override
    public void draw( Drawer drawer )
    {
        //System.out.println("Figura.Wielokat.draw()");
        List<Point2D> tmpPoints = new ArrayList<>(point);
        //point.forEach((element) -> {
        //    tmpPoints.add(element);
        //});
        drawer.setFill(Color.BLACK);
        drawer.setStroke(Color.BLACK);
        if( !super.isTransparent() )
            drawer.strokePolygon(tmpPoints);
        if( super.isVisible() )
            drawer.fillPolygon(tmpPoints); 
    }
    
    
    @Override
    public double getMaxX()
    {
        List<Double> value = new ArrayList<>();
        List<Point2D> tmp = new ArrayList<>(point);
        
        tmp.forEach( (punkt) -> {
            value.add( punkt.getX() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MIN_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.max(result, valueArr[n]);            
        }
        
        return result;
    }
    
    @Override
    public double getMinX()
    {
        List<Double> value = new ArrayList<>();
        List<Point2D> tmp = new ArrayList<>(point);
        
        tmp.forEach( (punkt) -> {
            value.add( punkt.getX() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MAX_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.min(result, valueArr[n]);            
        }
        
        return result;
    }
    
    @Override
    public double getMaxY()
    {
        List<Double> value = new ArrayList<>();
        List<Point2D> tmp = new ArrayList<>(point);
        
        tmp.forEach( (punkt) -> {
            value.add( punkt.getY() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MIN_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.max(result, valueArr[n]);            
        }
        
        return result;
    }
    
    @Override
    public double getMinY()
    {
        List<Double> value = new ArrayList<>();
        List<Point2D> tmp = new ArrayList<>(point);
        
        tmp.forEach( (punkt) -> {
            value.add( punkt.getY() );
        });
        
        Double valueArr[] = new Double[value.size()];
        valueArr = value.toArray(valueArr);
        
        double result = Double.MAX_VALUE;
        for ( int n=0; n<value.size(); n++ ) {
            result = Double.min(result, valueArr[n]);            
        }
        
        return result;
    }
    
    @Override
    public List<Axis> getAxis()
    {
        List<Axis> axis = new ArrayList<>();        
        Iterator<Point2D> iter = point.iterator();
        Point2D prevPoint = iter.next();
        
        while( iter.hasNext() )
            axis.add( new Axis(prevPoint, prevPoint = iter.next()) );  
        axis.add( new Axis(prevPoint, point.get(0)) );     
        
        return axis;
    }
    
    @Override
    public Projection projectOnAxis( Axis axis, Point2D offset )
    {
        //System.out.println("Wielokat.projectOnAxis()");
        Projection projection = new Projection();
        Point2D versor = axis.getVersor();
        
        point.forEach((p) -> {
            double dp = p.add(offset).dotProduct(versor);
            projection.addProjectedPoint( new Point2D(dp*versor.getX(), dp*versor.getY()) );
        });

        return projection;
    }
    
    
    
    @Override
    public String toString()
    {        
        return "Type: Wielokat; Point: "+point.toString()+"; Angle: "+Double.toString(super.getTheta())+"; ";
    }
    
    public List<Point2D> getPointsList()
    {
        return point;
    }
    
    public List<Point2D> getOriginalPointsList()
    {
        return pointOriginal;
    }
    
    public void setOriginalPointsList(List<Point2D> newOriginal)
    {
        pointOriginal = newOriginal;
        point = pointOriginal;
    }

    @Override
    public Point2D getMassCenter() {
        if( super.getMassCenter()==null )
        {
            Point2D result = new Point2D(0, 0);
            // wyznaczanie aktualnego środeka  masy
            for( Point2D key: pointOriginal )
            {
                result = result.add(key);
            }
            // aktualny środek masy
            
            final Point2D massCenter = result.multiply(1/(double)pointOriginal.size());
            super.setMassCenter(massCenter);
        }
        return super.getMassCenter();
    }

    @Override
    public Point2D setAxisOnMassCenter() {
            final Point2D massCenter = getMassCenter();

            List<Point2D> list = new ArrayList<>();
            // przesunięcie figury tak aby środek masy znalazł się w newOffset
            this.getOriginalPointsList().forEach((point) -> {
                list.add( point.subtract(massCenter) );
            });
            pointOriginal = list;
            point = pointOriginal;
        
        return super.getMassCenter();
    }
}
