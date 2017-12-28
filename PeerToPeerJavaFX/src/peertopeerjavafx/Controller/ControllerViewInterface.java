package peertopeerjavafx.Controller;

import peertopeerjavafx.Tools.Connection;

/**
 * funkcje komunikujące View z Controller
 * @author Skrzatt
 */
public interface ControllerViewInterface {
    
    //deklaracje funkcji change something
    
    public Boolean isConnected();
    public void startConnection( Connection connection );
    public void stopConnection(  );
    
}
