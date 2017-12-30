/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import javafx.application.Platform;

/**
 *
 * @author Skrzatt
 */
public class Connection extends Observable{
    
    
    private ConnectionType connectionType; // "SERVER" or "CLIENT"
    private ConnectionThread connectionThread;
    private ServerSocket serverSocket;
    private Socket socket;
    private String adress;
    private int port;
    //private inputbuffer
    //private outputbuffer
    private Boolean connected;
    private Boolean fail;
    
    public Connection( ConnectionType connectionType, String adress, int port)
    {
        this.connectionType = connectionType;
        this.connectionThread = null;
        this.serverSocket = null;
        this.socket = null;
        this.adress = adress;
        this.port = port;
        this.connected = false;
        this.fail = false;
    }
    
    public void startClientConnection()
    {
        connectionThread = new ClientThread( this );
        connectionThread.setName("ClientConnectionThread");
        connectionThread.start();
        
        
    }
    
    public void startServerConnection()
    {
        connectionThread = new ServerThread( this );
        connectionThread.setName("ServerConnectionThread");
        connectionThread.start();
             
    }
    
    public void close()
    {
        try
        {
            //this.deleteObservers();
            if( serverSocket!=null ) serverSocket.close();
            if( connectionThread!=null ) connectionThread.interrupt();
            if( socket!=null ) socket.close();
            
        }
        catch( Exception e )
        {
            System.out.println("peertopeerjavafx.Tools.Connection.close() EXCEPTION");
            e.printStackTrace();
        }
        
        connectionThread = null;
        serverSocket = null;
        socket = null;
        //this.setChanged();
        //this.notifyObservers();
    }
    
    public void connected()
    {// wywołanie zadania dla javafx
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();             
        });       
    }
    
    public void setServerSocket( ServerSocket newServerSocket )
    {
        this.serverSocket = newServerSocket;
    }
    
    public ServerSocket getServerSocket()
    {
        return this.serverSocket;
    }
    
    public void setSocket( Socket newSocket )
    {
        this.socket = newSocket;
    }
    
    public Socket getSocket()
    {
        return this.socket;
    }
    
    public void setAdress( String newAdress )
    {
        this.adress = newAdress;
    }
    
    public String getAdress()
    {
        return adress;
    }
    
    public void setPort( int newPort )
    {
        this.port = newPort;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public void setConnectionType( ConnectionType newType )
    {
        connectionType = newType;
    }
    
    public ConnectionType getconnectionType()
    {
        return connectionType;
    }
    
    public void setConnected(Boolean newState)
    {
        this.connected = newState;
    }
    
    public Boolean isConnected()
    {        
        return this.connected;
    }
    
    public void setFail(Boolean newState)
    {
        this.fail = newState;
    }
    
    public Boolean isFail()
    {
        return this.fail;
    }
    
    public Boolean isConnectionDefault()
    {        
        return (!this.isConnected() && !this.isFail());
    }
    public Boolean isConnectionFail()
    {
        return (!this.isConnected() && this.isFail());
    }
    public Boolean isConnectionOK()
    {
        return (this.isConnected() && !this.isFail());
    }
    public Boolean isConnectionEnd()
    {
        return (this.isConnected() && this.isFail());
    }
}
