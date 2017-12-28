


package peertopeerjavafx.Controller;

import peertopeerjavafx.Tools.Connection;
/**
 * deklaracje funkcji modelu
 * @author Skrzatt
 */
public interface ModelInterface 
{
    
    /**
     * 
     * @param connection 
     */
    public void setConnection( Connection connection );
    
    /**
     * 
     * @return 
     */
    public Connection getConnection(  );
    
    /**
     * 
     */
    public void startConnection();
    
    /**
     * 
     */
    public void stopConnection();
}
