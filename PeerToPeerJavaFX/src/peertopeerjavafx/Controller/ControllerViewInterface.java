package peertopeerjavafx.Controller;

import peertopeerjavafx.Tools.Connection;

/**
 * funkcje komunikujące View z Controller
 * @author Skrzatt
 */
public interface ControllerViewInterface {
    
    //deklaracje funkcji change something
    
    public Boolean isConnectionDefault();
    public Boolean isConnectionFail();
    public Boolean isConnectionOK();
    public Boolean isConnectionEnd();
    public void startConnection( Connection connection );
    public void stopConnection(  );
    public ViewInterface getView();
}
