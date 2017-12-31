package peertopeerjavafx.Controller;


import peertopeerjavafx.Model.*;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.View.*;



public class Controller implements ControllerModelInterface,
                                   ControllerViewInterface                                   
                                   {
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
        //model.getConnection().addObserver(this);
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
    
    
    @Override
    public void sendText( String inputText )
    {
        model.getConnection().getOutput().println(inputText);
    }
    //Metody interfejsu ControllerModelInterface
    
    /**
     * 
     */
    @Override
    public void showConnectionEnd()
    {
        System.out.println("peertopeerjavafx.Controller.Controller.showConnectionEnd()");
        view.getEndView().setStatusEnd();
        view.getWaitView().setStatusEnd();
        if( view.getTalkView().isShowing() )
            view.getEndView().show();
    }
    
    /**
     * 
     */
    @Override
    public void showConnectionOK()
    {
        view.getWaitView().setStatusOK();
    }
    
    /**
     * 
     */
    @Override
    public void showConnectionFAIL()
    {
        view.getWaitView().setStatusFAIL();
    }
    
    /**
     * 
     */
    @Override
    public void showConnectionDefault()
    {
        view.getWaitView().setStatusDefault();
        view.getEndView().setStatusDefault();
    }
    
    @Override
    public void showText( String text )
    {
        view.getTalkView().setOutputText(text);
    }
    
    public void clearOutput()
    {
        view.getTalkView().clearOutputText();
    }
}
