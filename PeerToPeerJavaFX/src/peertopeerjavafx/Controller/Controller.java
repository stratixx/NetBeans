package peertopeerjavafx.Controller;


import peertopeerjavafx.Model.*;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.View.*;

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

    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nie nawiązano połączenia
     */    
    @Override
    public Boolean isConnectionDefault()
    {
        return model.getConnection().isConnectionDefault();
    }
    
    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nie udało się nawiązać połączenia
     */
    @Override
    public Boolean isConnectionFail()
    {
        return model.getConnection().isConnectionFail();
    }
    
    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nawiązano połączenie poprawnie
     */
    @Override
    public Boolean isConnectionOK()
    {
        return model.getConnection().isConnectionOK();
    }
    
    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli połączenie zostało zakończone/zerwane
     */
    @Override
    public Boolean isConnectionEnd()
    {
        return model.getConnection().isConnectionEnd();
    }
    
    /**
     * Start próby nawiązania połączenia
     * @param connection Obiekt Connection zawierający dane połączenia
     */
    @Override
    public void startConnection( Connection connection )
    {
        model.setConnection(connection);
        //model.getConnection().addObserver(this);
        model.startConnection();
    }
    
    /**
     * Żądanie zakończenia połączenia 
     */
    @Override
    public void stopConnection(  )
    {        
        model.stopConnection();
    }
    
    /**
     * Przesłanie danych do rozmówcy
     * @param inputText dane do przesłania
     */
    @Override
    public void sendText( String inputText )
    {
        System.out.println("Controller.sendText() output text: "+inputText);
        model.getConnection().getOutput().println(inputText);
    }
    
    /////// Metody ControllerModelInterface /////////////////////
    
    /**
     * Aktualizacja widoku w przypadku zakończenia połączenia
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
     * Aktualizacja widoku w przypadku poprawnego nawiązania połączenia
     */
    @Override
    public void showConnectionOK()
    {
        view.getWaitView().setStatusOK();
    }
    
    /**
     * Aktualizacja widoku w przypadku niepowodzenia nawiązania połączenia
     */
    @Override
    public void showConnectionFAIL()
    {
        view.getWaitView().setStatusFAIL();
    }
    
    /**
     * Aktualizacja widoku w przypadku braku połączenia(stan początkowy)
     */
    @Override
    public void showConnectionDefault()
    {
        view.getWaitView().setStatusDefault();
        view.getEndView().setStatusDefault();
    }
    
    /**
     * Wyświetlenie odebranych danych
     * @param text dane odebrane do wyświetlenia
     */
    @Override
    public void showText( String text )
    {
        view.getTalkView().setOutputText(text);
    }
    
    /**
     * Wyczyszczenie elementu wyświetlającego dane
     */
    @Override
    public void clearOutput()
    {
        view.getTalkView().clearOutputText();
    }
}
