package peertopeerjavafx.Controller;


import peertopeerjavafx.Model.*;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.View.*;



public class Controller implements ControllerModelInterface,
                                   ControllerViewInterface {
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
    
    
    //Metody interfesju ControllerViewInterface
    @Override
    public Boolean isConnected()
    {
        return model.getConnection().isConnected();
    }
    
    /**
     * Start próby nawiązania połączenia
     * @param connection 
     */
    @Override
    public void startConnection( Connection connection )
    {
        model.setConnection(connection);
        model.startConnection();
    }
    
    /**
     * Zakończenie połączenia
     * @param connection 
     */
    @Override
    public void stopConnection(  )
    {
        //view.showEndView();
        model.stopConnection();
    }
    
    //Metody interfejsu ControllerModelInterface
    
    
    /**
     * Połączenie nawiązane
     */
    @Override
    public void connectionOK()
    {
        model.getConnection().setConnected(true);
        view.showConnectionOK();       
    }
    
    /**
     * Próba połączenia nie powiodła się
     */
    @Override
    public void connectionFAIL()
    {
        model.getConnection().setFail(true);
        view.showConnectionFAIL();
    }
    
}
