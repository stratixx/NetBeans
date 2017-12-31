/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
    private PrintWriter output;
    private BufferedReader externalInput;
    private List internalInput;
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
        this.output = null;
        this.externalInput = null;
        this.internalInput = null;
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
    
    public void startTalkerThread()
    {
        Connection conn = this;
        
        Thread thread = new Thread(() -> {
            System.out.println("peertopeerjavafx.Tools.Connection.startTalkerThread() new thread");
            while( (connectionThread!=null)&&(connectionThread.isAlive()) );
            connectionThread = new TalkerThread( conn );
            connectionThread.setName("TalkerThread");
            connectionThread.start();
        });
        thread.setName("StarterForTalkerThread");
        thread.start();
    }
    
    /**
     *
     */
    synchronized public void close()
    {
        System.out.println("peertopeerjavafx.Tools.Connection.close()");
        //setConnected(false);
        try
        {
            //this.deleteObservers();
            if( serverSocket!=null ) serverSocket.close();
            if( socket!=null ) socket.close();
            if( externalInput!=null ) externalInput.close();
            //if( internalInput!=null ) internalInput.;
            if( output!=null ) output.close();
            if( connectionThread!=null ) connectionThread.interrupt();
            
            
        }
        catch( Exception e )
        {
            System.out.println("peertopeerjavafx.Tools.Connection.close() EXCEPTION");
            e.printStackTrace();
        }
        
        connectionThread = null;
        externalInput = null;
        internalInput = null;
        output = null;
        serverSocket = null;
        socket = null;
        //setConnected(false);
        informObservers();
    }
    
    synchronized public void informObservers()
    {
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();             
        }); 
        
    }
    
    /**
     *  Wątek nawiązujący połączenie zakończył pracę
     */
    public void connected()
    {        
        this.startTalkerThread();
        // wywołanie zadania dla javafx, obserwatorem jest model
        informObservers();
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
    
    
    public void setInternalInput( List newInput )
    {
        this.internalInput = newInput;
    }
    
    public List getInternalInput()
    {
        return this.internalInput;
    }
    
    
    public void setExternalInput( BufferedReader newInput )
    {
        this.externalInput = newInput;
    }
    
    public BufferedReader getExternalInput()
    {
        return this.externalInput;
    }
    
    
    public void setOutput( PrintWriter newOutput )
    {
        this.output = newOutput;
    }
    
    public PrintWriter getOutput()
    {
        return this.output;
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
