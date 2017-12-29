package peertopeerjavafx.Controller;

import java.io.IOException;
import javafx.stage.Stage;
import peertopeerjavafx.Tools.Connection;


/**
 * deklaracje funkcji widoku
 * @author Skrzatt
 */
public interface ViewInterface {
    // deklaracje funkcji
    
    /**
     * Inicjacja głównego widoku
     */
    public void launch( Stage primaryStage ) throws IOException;
    
    /**
     * 
     */
    public void showConnectionEnd();
    
    /**
     * 
     */
    public void showConnectionOK();
    
    /**
     * 
     */
    public void showConnectionFAIL();
    
    /**
     * 
     */
    public void showConnectionDefault();
}
