package peertopeerjavafx.Controller;

import peertopeerjavafx.Tools.Connection;

/**
 * deklaracje funkcji modelu
 * @author Konrad Winnicki
 */
public interface ModelInterface 
{    
    /**
     * Inicjacja pól obiektu Connection
     * @param connection Obiekt Connection zawierający dane połączenia
     */
    public void setConnection( Connection connection );
    
    /**
     * Pobranie referencji na obiekt Connection
     * @return referencja na obiekt Connection
     */
    public Connection getConnection(  );
    
    /**
     * Start próby nawiązania połączenia
     */
    public void startConnection();
    
    /**
     * Żądanie zakończenia połączenia
     */
    public void stopConnection();
}
