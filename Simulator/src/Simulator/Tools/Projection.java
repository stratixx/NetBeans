/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.Tools;


import javafx.geometry.Point2D;

/**
 *
 * @author Skrzatt
 */
public class Projection {
    
    //private List<Point2D> projected;
    private Point2D start;
    private Point2D end;
    
    public Projection()
    {
        //projected = new ArrayList<>();
        start = null;
        end = null;
    }
    
    public Boolean IsOverlap( Projection projection )
    {
        Point2D projStart = projection.getStart();
        Point2D projEnd = projection.getEnd();
        double eps = 0.001;
        
        //System.out.println("Projection.isOverlap() (start: "+start+"| end: "+end+"); " );
        //System.out.println("Projection.isOverlap() (projstart: "+projStart+"| projend: "+projEnd+"); " );
        
        double start_end = start.distance(end);
        double start_projStart = start.distance(projStart);
        double end_projStart = end.distance(projStart);
        
        if( Math.abs(start_projStart+end_projStart-start_end)<eps )
            return true;
        
        double end_projEnd = end.distance(projEnd);
        double start_projEnd = start.distance(projEnd);
        if( Math.abs(start_projEnd+end_projEnd-start_end)<eps )
            return true;
        
        double proi_start_proi_end = projStart.distance(projEnd);
        if( Math.abs(start_projStart+start_projEnd-proi_start_proi_end)<eps )
            return true;
        
        if( Math.abs(end_projEnd+end_projStart-proi_start_proi_end)<eps )
            return true;
        
        return false;
    }
    /*
    public List<Point2D> getProjectedPoints()
    {
        return projected;
    }*/
    
    public void addProjectedPoint( Point2D point )
    {
        
        if( start==null && end==null ) // pierwszy punkt
        {
            //System.out.println("Projection.addProjectedPoint()if_1 (start: "+point+"| end: "+point+"); " );
            start = end = point;
        }
        else
        {        
            double start_point = start.distance(point);
            double start_end = start.distance(end);
            double end_point = end.distance(point);

            if( start_point>end_point )
            {
                if( start_point>start_end )
                    end = point;
            }
            else
            {
                if( end_point>start_end )
                    start = point;
            }
            //System.out.println("Projection.addProjectedPoint()else (start: "+start+"| end: "+end+"); " );
            //System.out.println("Projection.addProjectedPoint() distance: "+start.distance(end));
        }        
        
        //return true;//projected.add(point);
    }
    /*
    public void setStart( Point2D newStart )
    {
        start = newStart;
    }*/
    
    public Point2D getStart()
    {
        return start;
    }
    /*
    public void setEnd( Point2D newEnd )
    {
        end = newEnd;
    }*/
    
    public Point2D getEnd()
    {
        return end;
    }
    
    public double getLength()
    {
        return start.distance(end);
    }
}
