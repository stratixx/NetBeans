package peertopeerjavafx.Controller;

import peertopeerjavafx.Tools.Connection;

/**
 * Metody komunikujące View z Controller
 * @author Konrad Winnicki
 */
public interface ControllerViewInterface {
        
    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nie nawiązano połączenia
     */
    public Boolean isConnectionDefault();

    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nie udało się nawiązać połączenia
     */
    public Boolean isConnectionFail();

    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli nawiązano połączenie poprawnie
     */
    public Boolean isConnectionOK();

    /**
     * Sprawdzenie stanu połączenia
     * @return true jeśli połączenie zostało zakończone/zerwane
     */
    public Boolean isConnectionEnd();

    /**
     * Start próby nawiązania połączenia
     * @param connection Obiekt Connection zawierający dane połączenia
     */
    public void startConnection( Connection connection );

    /**
     * Żądanie zakończenia połączenia 
     */
    public void stopConnection(  );

    /**
     * Przesłanie danych do rozmówcy
     * @param inputText dane do przesłania
     */
    public void sendText( String inputText );
}
