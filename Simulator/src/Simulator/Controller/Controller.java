package Simulator.Controller;


import Simulator.Model.*;
import Simulator.Tools.Drawer;
import Simulator.View.*;

/**
 * Kontroler zapewniający komunikację pomiędzy widokiem a modelem aplikacji
 * @author Konrad Winnicki
 */
public class Controller implements ControllerModelInterface,
                                   ControllerViewInterface                                   
                                   {
    /**
     * Referencja do modelu
     */
    private final ModelInterface model;
    
    /**
     * Referencja do widoku
     */
    private final ViewInterface view;
    
    /**
     * Konstruktor kontrolera
     * @param view widok
     * @param model model aplikacji
     */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;        
    }    
    
    /**
     * Pobranie referencji na widok
     * @return widok
     */
    public ViewInterface getView()
    {
        return view;
    }    
    
    ///// Metody ControllerViewInterface ////////
    
    @Override
    public void refreshRobotView( Drawer drawer )
    {
        model.drawRobot( drawer );
    }
    
    @Override
    public void refreshSimulatorView( Drawer drawer)
    {
        model.drawObjects( drawer );
    }
    
    @Override
    public void startSimulation(  )
    {
        model.startThread();
        
    }
    
    @Override
    public void pauseSimulation(  )
    {
        model.pauseThread();
    }
    
    @Override
    public void stopSimulation(  )
    {
        model.stopThread();
        
    }
    
    /////// Metody ControllerModelInterface /////////////////////
    
}
