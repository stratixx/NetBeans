package peertopeerjavafx.Model;

import peertopeerjavafx.Controller.ControllerModelInterface;
import peertopeerjavafx.Controller.ModelInterface;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.Tools.ConnectionType;

/**
 * 
 * @author Skrzatt
 */
public class Model implements ModelInterface{
    /** Referencja do obiektu kontrolera z którym model ma się komunikować */
    private ControllerModelInterface controller;
    
    private Connection connection;
    
    
    /**
     * 
     * @param connection 
     */
    @Override
    public void setConnection( Connection connection )
    {
        this.connection = connection;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Connection getConnection(  )
    {
        return this.connection;
    }
    
    /**
     * 
     */
    @Override
    public void startConnection()
    {
        System.out.println("peertopeerjavafx.Model.Model.startConnection()");
        switch( connection.getconnectionType() )
        {
            case CLIENT:
                System.out.println("START AS CLIENT");
                System.out.printf("Adress: %s | port: %d", connection.getAdress(), connection.getPort());
                controller.connectionOK();
                break;
            case SERVER:
                System.out.println("START AS SERVER");
                System.out.printf("port: %d", connection.getPort());
                controller.connectionFAIL();
                break;
            default:
                    
        }
        
        
            
        
    }
    
    /**
     * 
     */
    @Override
    public void stopConnection()
    {
        // cos tam coś tam
        if(connection!=null)
        {            
            connection.close();
        }
        connection = null;
    }
        
    /**
     * Ustaw referencje do obiektu kontrolera i inicjalizuje obiekt.
     * 
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerModelInterface controller){
        this.controller = controller;
        //initializeModel();
    }
}
