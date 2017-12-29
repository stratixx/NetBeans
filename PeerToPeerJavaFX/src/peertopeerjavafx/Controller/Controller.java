package peertopeerjavafx.Controller;


import java.util.Observable;
import java.util.Observer;
import peertopeerjavafx.Model.*;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.View.*;



public class Controller implements ControllerModelInterface,
                                   ControllerViewInterface,
                                   Observer{
    /**
     * Referencja do obiektu Model 
     * @see Model
     */
    private final ModelInterface model;
    
    /**
     * Referencja do obiektu View
     * @see View
     */
    private final ViewInterface view;
    
    /**
     * Konstruktor parametryzowany obiektami {@link View} i {@link Model}
     * @param view obiekt widoku
     * @param model obiekt sterowania aplikacji
     */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
    }
    
    
    @Override
    public void update(Observable obs, Object obj)
    {
        Connection connect = (Connection)obs;
        
        // Sprawdzenie statusu połączenia
        if( isConnectionEnd() )
            view.showConnectionEnd(); // Połączenie zotało zerwane
        else if( isConnectionFail() ) 
            view.showConnectionFAIL(); // Nie udało się nawiązać połączenia
        else if( isConnectionOK() ) 
            view.showConnectionOK(); // Połączenie nawiązane
        else if( isConnectionDefault() )
            view.showConnectionDefault(); // Brak połączenia/nawiązywanie połączenia  
        else
            System.out.println("peertopeerjavafx.Controller.Controller.update() fatal error");
    }
    
    
    
    @Override
    public ViewInterface getView()
    {
        return view;
    }
    
    
    //Metody interfesju ControllerViewInterface
    @Override
    public Boolean isConnectionDefault()
    {
        return model.getConnection().isConnectionDefault();
    }
    
    @Override
    public Boolean isConnectionFail()
    {
        return model.getConnection().isConnectionFail();
    }
    
    @Override
    public Boolean isConnectionOK()
    {
        return model.getConnection().isConnectionOK();
    }
    
    @Override
    public Boolean isConnectionEnd()
    {
        return model.getConnection().isConnectionEnd();
    }
    
    /**
     * Start próby nawiązania połączenia
     * @param connection 
     */
    @Override
    public void startConnection( Connection connection )
    {
        model.setConnection(connection);
        model.getConnection().addObserver(this);
        model.startConnection();
    }
    
    /**
     * Zakończenie połączenia 
     */
    @Override
    public void stopConnection(  )
    {        
        model.stopConnection();
    }
    
    //Metody interfejsu ControllerModelInterface
    
    
    /**
     * Połączenie nawiązane
     *//*
    @Override
    public void connectionOK()
    {
        model.getConnection().setConnected(true);
        view.showConnectionOK();       
    }*/
    
    /**
     * Próba połączenia nie powiodła się
     *//*
    @Override
    public void connectionFAIL()
    {
        model.getConnection().setFail(true);
        view.showConnectionFAIL();
    }
    */
}
