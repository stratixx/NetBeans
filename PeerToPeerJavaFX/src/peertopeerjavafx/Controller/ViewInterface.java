package peertopeerjavafx.Controller;

import java.io.IOException;
import javafx.stage.Stage;
import peertopeerjavafx.View.EndView.EndViewController;
import peertopeerjavafx.View.StartView.StartViewController;
import peertopeerjavafx.View.TalkView.TalkViewController;
import peertopeerjavafx.View.WaitView.WaitViewController;


/**
 * Deklaracje funkcji widoku
 * @author Konrad Winnicki
 */
public interface ViewInterface {    
    
    /**
     * Inicjacja głównego widoku
     * @param primaryStage Głowne okno
     * @throws java.io.IOException
     */
    public void launch( Stage primaryStage ) throws IOException;
    
    /**
     * Pobranie referencji na okno popup oczekiwania
     * @return referencja na okno popup oczekiwania
     */
    public WaitViewController getWaitView();

    /**
     * Pobranie referencji na okno popup zakończenia połączenia
     * @return Referencja na okno popup zakończenia połączenia
     */
    public EndViewController getEndView();

    /**
     * Pobranie referencji na okno rozmowy
     * @return Referencja na okno rozmowy
     */
    public TalkViewController getTalkView();

    /**
     * Pobranie referencji na okno startowe
     * @return Referencja na okno startowe
     */
    public StartViewController getStartView();
}
