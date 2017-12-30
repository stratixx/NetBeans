package peertopeerjavafx.Model;

import java.util.Observable;
import java.util.Observer;
import peertopeerjavafx.Controller.ControllerModelInterface;
import peertopeerjavafx.Controller.ModelInterface;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.Tools.ConnectionType;

/**
 * 
 * @author Skrzatt
 */
public class Model implements ModelInterface, Observer {
    /** Referencja do obiektu kontrolera z którym model ma się komunikować */
    private ControllerModelInterface controller;
    
    private Connection connection;
    
    public Model()
    {
        connection = new Connection(ConnectionType.NONE, "", 0);
        connection.addObserver(this);
    }
    
    @Override
    public void update(Observable obs, Object obj)
    {
        //Connection connect = (Connection)obs;
        
        // Sprawdzenie statusu połączenia
        if( connection.isConnectionEnd() )
            controller.showConnectionEnd(); // Połączenie zotało zerwane
        else if( connection.isConnectionFail() ) 
            controller.showConnectionFAIL(); // Nie udało się nawiązać połączenia
        else if( connection.isConnectionOK() ) 
            controller.showConnectionOK(); // Połączenie nawiązane
        else if( connection.isConnectionDefault() )
            controller.showConnectionDefault(); // Brak połączenia/nawiązywanie połączenia  
        else
            System.out.println("peertopeerjavafx.Model.Model.update()");
        
    }
    
    /**
     * 
     * @param connection 
     */
    @Override
    public void setConnection( Connection connection )
    {
        this.connection.setConnectionType( connection.getconnectionType() );
        this.connection.setAdress( connection.getAdress() );
        this.connection.setPort( connection.getPort() );        
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
                connection.startClientConnection();
                break;
            case SERVER:
                System.out.println("START AS SERVER");
                System.out.printf("port: %d", connection.getPort());
                connection.startServerConnection();
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
        connection.close();
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
