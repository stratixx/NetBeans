package Simulator.Controller;

import java.io.IOException;
import javafx.stage.Stage;


public interface ViewInterface {    
    
    /**
     * Inicjacja głównego widoku
     * @param primaryStage Głowne okno
     * @throws java.io.IOException
     */
    public void launch( Stage primaryStage ) throws IOException;
    
}
