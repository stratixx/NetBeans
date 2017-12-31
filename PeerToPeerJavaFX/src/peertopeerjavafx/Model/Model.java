package peertopeerjavafx.Model;

import java.util.Iterator;
import java.util.List;
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
        System.out.println("peertopeerjavafx.Model.Model.update() start");
        Connection connect = (Connection)obs;
        System.out.print("peertopeerjavafx.Model.Model.update() ");
        System.out.print("conn="+connect.isConnected()+" ");
        System.out.println("fail="+connect.isFail());
        // Sprawdzenie statusu połączenia
        if( connect.isConnectionEnd() )
            controller.showConnectionEnd(); // Połączenie zotało zerwane
        else if( connect.isConnectionFail() ) 
            controller.showConnectionFAIL(); // Nie udało się nawiązać połączenia
        else if( connect.isConnectionOK() ) 
            controller.showConnectionOK(); // Połączenie nawiązane
        else if( connect.isConnectionDefault() )
            controller.showConnectionDefault(); // Brak połączenia/nawiązywanie połączenia  
        else
            System.out.println("peertopeerjavafx.Model.Model.update()");
        
        // sprawdzenie czy są dane do wyświetlenia
        if(connect.isConnectionOK() && (connect.getInternalInput()!=null) ) 
        {
            List list = connect.getInternalInput();
            Iterator<String> iter = list.iterator();
            String text;
            
            while(iter.hasNext())
            {
                text = iter.next();
                System.out.println( text );                
                iter.remove();
                
                controller.showText(text);
            }
           // connection.getInternalInput().listIterator().
        }
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
