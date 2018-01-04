package peertopeerjavafx.Controller;


/**
 * Interfejs komunikujący model z kontrolerem
 * @author Konrad Winnicki
 */
public interface ControllerModelInterface {
    
    
    /**
     * Aktualizacja widoku w przypadku zakończenia połączenia
     */
    public void showConnectionEnd();
    
    /**
     * Aktualizacja widoku w przypadku poprawnego nawiązania połączenia
     */
    public void showConnectionOK();
    
    /**
     * Aktualizacja widoku w przypadku niepowodzenia nawiązania połączenia
     */
    public void showConnectionFAIL();
    
    /**
     * Aktualizacja widoku w przypadku braku połączenia(stan początkowy)
     */
    public void showConnectionDefault();
    
    /**
     * Wyświetlenie odebranych danych
     * @param text dane odebrane do wyświetlenia
     */
    public void showText( String text );
    
    /**
     * Wyczyszczenie elementu wyświetlającego dane
     */
    public void clearOutput();
}
