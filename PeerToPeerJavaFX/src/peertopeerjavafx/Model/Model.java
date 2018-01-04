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
 * @author Konrad Winnicki
 */
public class Model implements ModelInterface, Observer {
    
    /**
     * Obiekt kontrolera
     */
    private ControllerModelInterface controller;
    
    /**
     * Obiekt połączenia
     */
    private Connection connection;
    
    /**
     * Pusty konstruktor modelu
     */
    public Model()
    {
        connection = null;
    }
        
    /**
     * Ustawienie referencji na kontroler
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerModelInterface controller){
        this.controller = controller;
    }
    
    /**
     * Zmiana w obiekcie obserwowanym
     * @param obs obiekt połączenia w którym nastąpiła zmiana
     * @param obj Object
     */    
    @Override
    public void update(Observable obs, Object obj)
    {
        Connection connect = (Connection)obs;
        
        System.out.println("peertopeerjavafx.Model.Model.update() start");
        System.out.print("peertopeerjavafx.Model.Model.update() ");
        System.out.print("conn="+connect.isConnected()+" ");
        System.out.println("fail="+connect.isFail());
        
        // Aktualizacja stanu widoku
        if( connect.isConnectionEnd() )
            controller.showConnectionEnd(); // Połączenie zotało zerwane
        else if( connect.isConnectionFail() ) 
            controller.showConnectionFAIL(); // Nie udało się nawiązać połączenia
        else if( connect.isConnectionOK() ) 
            controller.showConnectionOK(); // Połączenie nawiązane
        else if( connect.isConnectionDefault() )
            controller.showConnectionDefault(); // Brak połączenia/nawiązywanie połączenia  
        else
            System.out.println("peertopeerjavafx.Model.Model.update() ERROR");
        
        // sprawdzenie czy są dane do wyświetlenia
        if(connect.isConnectionOK() && (connect.getInternalInput()!=null) ) 
        {
            List list = connect.getInternalInput();
            Iterator<String> iter = list.iterator();
            String text;
            
            while(iter.hasNext())
            {
                text = iter.next();
                System.out.println( "Input text: " + text );                   
                controller.showText(text);             
                iter.remove();
            }
        }        
    }
    
    /**
     * Inicjacja pól obiektu Connection
     * @param connection Obiekt Connection zawierający dane połączenia
     */
    @Override
    public void setConnection( Connection connection )
    {
        if( this.connection!=null )
        {
            this.connection.close();
            this.connection.deleteObservers();
        }
        this.connection = connection;
        this.connection.addObserver(this);
        //this.connection.setConnectionType( connection.getconnectionType() );
        //this.connection.setAdress( connection.getAdress() );
        //this.connection.setPort( connection.getPort() );        
    }
    
    /**
     * Pobranie referencji na obiekt Connection
     * @return referencja na obiekt Connection
     */
    @Override
    public Connection getConnection(  )
    {
        return this.connection;
    }
    
    /**
     * Start próby nawiązania połączenia
     */
    @Override
    public void startConnection()
    {
        System.out.println("peertopeerjavafx.Model.Model.startConnection()");
        switch( connection.getconnectionType() )
        {
            case CLIENT:
                System.out.print("START AS CLIENT: ");
                System.out.printf("Adress: %s | port: %d", connection.getAdress(), connection.getPort());
                System.out.println(" ");
                connection.startClientConnection();
                break;
            case SERVER:
                System.out.print("START AS SERVER: ");
                System.out.printf("port: %d", connection.getPort());
                System.out.println(" ");
                connection.startServerConnection();
                break;
            default:                    
        }        
    }
    
    /**
     * Żądanie zakończenia połączenia
     */
    @Override
    public void stopConnection()
    {
        //System.out.println("peertopeerjavafx.Model.Model.stopConnection()");
        connection.close();
        connection = null;
    }
}
