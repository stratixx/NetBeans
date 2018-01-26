package Simulator.Model;

import Simulator.Controller.ControllerModelInterface;
import Simulator.Controller.ModelInterface;
import Simulator.Obiekt.Collision;
import Simulator.Obiekt.Latarnia.Latarnia;
import Simulator.Obiekt.Obiekt;
import Simulator.Obiekt.Przeszkoda.*;
import Simulator.Obiekt.Robot.Czujnik.LIDAR;
import Simulator.Obiekt.Robot.Robot;
import Simulator.Tools.Drawer;
import Simulator.Tools.RefreshThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class Model implements ModelInterface {
    
    Collision collision;
    
    List<Obiekt> obiekt;
    
    // robot porusza się po trasie ograniczonej barierą
    private Robot robot;    
    // przeszkody na trasie robota
    private List<Przeszkoda> przeszkoda;
    // bariera ogranicza pole ruchu robota
    private List<Przeszkoda> bariera;
    // punkty odniesienia robota, 4 latarnie w skrajnych punktach
    private List<Latarnia> latarnia;
    // cel robota
    private Przeszkoda cel;
    
    private RefreshThread refreshThread;
    
    /**
     * Obiekt kontrolera
     */
    private ControllerModelInterface controller;
    
    
    /**
     * Pusty konstruktor modelu
     */
    public Model()
    {
        final double screenWidth = 800;
        final double screenHeight = 600;
        
        collision = new Collision();
        
        obiekt = new ArrayList<>();
        
        
        obiekt = new ArrayList<Obiekt>() {            
            @Override
            public boolean add(Obiekt element) {
                element.setID( this.size() );
                return super.add(element);
            }

            @Override
            public boolean addAll(Collection<? extends Obiekt> collect) {
                collect.forEach((element) -> {
                    add(element);
                });
                return true;
            }
        };
        
        robot = new Robot( 100, 80 );
        robot.setTheta(90);
        robot.setVelocity(new Point2D(100, 0));
        robot.setRotationSpeed(1);
        robot.addSensor( new LIDAR( robot ) );
        
        przeszkoda = new ArrayList<>();
        przeszkoda.add( new FourDots(new Point2D(400, 420), 60, 50, 10));
        //przeszkoda.add( new FourDots(new Point2D(180, 200), 60, 50, 10));
        przeszkoda.add( new Skala(new Point2D(620,275)));
        przeszkoda.add( new FourDots(new Point2D(250, 450), 60, 50, 10));
        przeszkoda.add( new PieciokatForemny(new Point2D(130,315)));
        //przeszkoda.add( new Prostokat(50, 300, 50, 50) );
        
        //bariera = new ArrayList<>();
        
        cel = new Cel( new Point2D(700, 500));
        
        latarnia = new ArrayList<>();
        latarnia.add( new Latarnia(0,0) );
        latarnia.add( new Latarnia(screenWidth,0) );
        latarnia.add( new Latarnia(screenWidth,screenHeight) );
        latarnia.add( new Latarnia(0,screenHeight) );
        
        obiekt.add( robot );
        obiekt.addAll( przeszkoda );
        //obiekt.addAll(bariera);
        obiekt.add(cel);
        obiekt.addAll( latarnia );
        
        // dodanie listy obiektów i ustawienie nadzorcy kolizji obiektów
        collision.addObjectList( obiekt );
        obiekt.forEach((element) -> {
            element.setVetoableChangeListener(collision);
        });
        
        obiekt.forEach((element) -> {
            element.move(0); // odświeżenie elementów
            System.out.println( element.toString() );
        });
        
        refreshThread = new RefreshThread(100.0) {
            @Override
            public void threadProcedure( double deltaT) {
                moveObjects( deltaT );
            }
        };
        refreshThread.setName("ModelRefreshThread");
    }
    @Override
    public void drawObjects( Drawer drawer )
    {       
        drawer.getGC().setFill(Color.WHITESMOKE);
        drawer.getGC().fillRect(0, 0, 800, 600);      
        obiekt.forEach((element) -> 
        { 
            //System.out.println(element.toString());
            element.draw( drawer ); 
        });
    }
    
    @Override
    public void startThread()
    {        
        refreshThread.startThread();
    }
    
    @Override
    public void stopThread()
    {
        refreshThread.stopThread();
    }
    
    @Override
    public void pauseThread()
    {
        refreshThread.pauseThread();
    }
    
    public void moveObjects( double deltaT )
    {            
            
                
                robot.tick(deltaT);
                                
                przeszkoda.forEach((element) -> 
                {
                    if(element.getVelocity().magnitude()<=0.001 && element.getRotationSpeed()<=1) 
                    {
                        //element.setVelocity(new Point2D(0.25*20, 0*0.25*-1));
                        element.setRotationSpeed(element.getID()*3);
                    }
                    if( !element.move( deltaT ))
                    {
                        element.setVelocity( element.getVelocity().multiply(-1) );
                        element.setRotationSpeed( -1.2*element.getRotationSpeed() );
                    }
                });            
    }
    
    /**
     * Ustawienie referencji na kontroler
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerModelInterface controller){
        this.controller = controller;
    }

    @Override
    public void drawRobot(Drawer drawer) {
        drawer.getGC().setFill(Color.WHITESMOKE);
        drawer.getGC().fillRect(0, 0, 800, 600); 
        robot.draw(drawer);
        robot.getSensor().forEach((sensor) -> {
            sensor.draw(drawer);
        });
    }
    
}
