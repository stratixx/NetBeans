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
import Simulator.Tools.Promien;
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
    public void checkCross( List<Promien> promien, Point2D figuraOffset )
    {
        Point2D prevPoint = point.get(point.size()-1).add(figuraOffset);
        
        for (Point2D _point : point) {
            _point = _point.add(figuraOffset);
            double xB = _point.getX();
            double yB = _point.getY();
            double xA = prevPoint.getX();
            double yA = prevPoint.getY();
            double dyBA = yB-yA;
            double dxBA = xB-xA;
            
            promien.forEach((_promien) -> {
            double xD = _promien.getDirection().getX();
            double yD = _promien.getDirection().getY();
            double xS = _promien.getStart().getX();
            double yS = _promien.getStart().getY();
            double dyDS = yD-yS;
            double dxDS = xD-xS;
                
            double A1, B1, C1;
            double A2, B2, C2;
            
            double x;
            double y;
            
            if( Math.abs(dyBA)<Math.abs(dxBA) )
            {
                A1 = -dyBA/dxBA;
                B1 = 1.0;
                C1 = dyBA/dxBA*xB-yB;
                
                //if( !Double.isFinite(A1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error A1: "+dyBA+"/"+dxBA);
                //if( !Double.isFinite(B1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error B1: "+B1);
                //if( !Double.isFinite(C1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error C1: "+dyBA+"/"+dxBA+"*"+xB+"-"+yB);
                
                if( Math.abs(dyDS)<Math.abs(dxDS) ) // wariant 2-4
                {
                    A2 = -dyDS/dxDS;
                    B2 = 1.0;
                    C2 = dyDS/dxDS*xD-yD;
                    x = (C1-C2)/(A2-A1);
                    y = -C1-A1*x;
                //if( Double.isNaN(x) || Double.isNaN(y) )  
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() Not a Number 2-4 "+" A1: "+A1+" B1: "+B1+" C1: "+C1+" A2: "+A2+" B2: "+B2+" C2: "+C2);
                   /* 
                // WARIANT 2-4
                    x = -yB+yD+dyBA*xB/dxBA-dyDS*xD/dxDS;
                    x /= dyBA/dxBA - dyDS/dxDS;

                    // wyznaczone z x oraz z równania 2
                    y = yD -dyDS*xD/dxDS + dyDS*x/dxDS;            
                     */   
                }
                else // wariant 1-4
                {
                    A2 = 1.0;
                    B2 = -dxDS/dyDS;
                    C2 = dxDS/dyDS*yD-xD;
                    y = -(C1-A1*C2)/(1-A1*B2);
                    x = -C2-B2*y;  
                //if( Double.isNaN(x) || Double.isNaN(y) )  
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() Not a Number 1-4 "+" A1: "+A1+" B1: "+B1+" C1: "+C1+" A2: "+A2+" B2: "+B2+" C2: "+C2);                      
                }
            }
            else
            {
                A1 = 1.0;
                B1 = -dxBA/dyBA;
                C1 = dxBA/dyBA*yB-xB;
                
                //if( !Double.isFinite(A1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error A1: "+A1);
                //if( !Double.isFinite(B1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error B1: "+dxBA+"/"+dyBA);
                //if( !Double.isFinite(C1))
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() error C1: "+dxBA+"/"+dyBA+"*"+yB+"-"+xB);
                
                if( Math.abs(dyDS)<Math.abs(dxDS) ) // wariant 2-3
                {
                    A2 = -dyDS/dxDS;
                    B2 = 1.0;
                    C2 = dyDS/dxDS*xD-yD;
                    y = -(C2-A2*C1)/(1-A2*B1);
                    x = -C1-B1*y;
                //if( Double.isNaN(x) || Double.isNaN(y) )  
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() Not a Number 2-3 "+" A1: "+A1+" B1: "+B1+" C1: "+C1+" A2: "+A2+" B2: "+B2+" C2: "+C2);
                }
                else // wariant 1-3
                {
                    A2 = 1.0;
                    B2 = -dxDS/dyDS;
                    C2 = dxDS/dyDS*yD-xD;
                    y = (C1-C2)/(B2-B1);
                    x = -C2-B2*y;
                //if( Double.isNaN(x) || Double.isNaN(y) )  
                //    System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() Not a Number 1-3 "+" A1: "+A1+" B1: "+B1+" C1: "+C1+" A2: "+A2+" B2: "+B2+" C2: "+C2);
                }
            }
            
                if( Double.isFinite(x) && Double.isFinite(y) ) {
                    if( !((x > Double.max(xA,xB)) || (x < Double.min(xA,xB)) || (y > Double.max(yA,yB)) || (y < Double.min(yA,yB))) )
                    {
                        Point2D newPoint = new Point2D(x, y);
                        //System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() point "+newPoint);
                        _promien.addCross(newPoint);
                    }
                } //else System.out.println("Simulator.Tools.Figura.Wielokat.checkCross() NaN x: "+x+" y: "+y);        
            });
                    
            prevPoint = _point;
        }
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
