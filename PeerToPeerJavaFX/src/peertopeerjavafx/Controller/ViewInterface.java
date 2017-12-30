package peertopeerjavafx.Controller;

import java.io.IOException;
import javafx.stage.Stage;
import peertopeerjavafx.View.EndView.EndViewController;
import peertopeerjavafx.View.StartView.StartViewController;
import peertopeerjavafx.View.TalkView.TalkViewController;
import peertopeerjavafx.View.WaitView.WaitViewController;


/**
 * deklaracje funkcji widoku
 * @author Skrzatt
 */
public interface ViewInterface {
    // deklaracje funkcji
    
    /**
     * Inicjacja głównego widoku
     * @param primaryStage
     * @throws java.io.IOException
     */
    public void launch( Stage primaryStage ) throws IOException;
    
    public WaitViewController getWaitView();
    public EndViewController getEndView();
    public TalkViewController getTalkView();
    public StartViewController getStartView();
}
