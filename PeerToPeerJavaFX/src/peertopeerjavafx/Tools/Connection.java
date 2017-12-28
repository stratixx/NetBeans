/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Skrzatt
 */
public class Connection {
    
    
    private ConnectionType connectionType; // "SERVER" or "CLIENT"
    private ConnectionThread connectionThread;
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
        this.socket = null;
        this.adress = adress;
        this.port = port;
        this.connected = false;
        this.fail = false;
    }
    
    public void close()
    {
        try
        {
            if( connectionThread!=null ) connectionThread.interrupt();
            //if( serverSocket!=null ) serverSocket.close();
            if( socket!=null ) socket.close();
        }
        catch( Exception e )
        {
            System.out.println("peertopeerjavafx.Tools.Connection.close() EXCEPTION");
            e.printStackTrace();
        }
        
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
        connected = newState;
    }
    
    public Boolean isConnected()
    {        
        return connected;
    }
    
    public void setFail(Boolean newState)
    {
        this.fail = newState;
    }
    
    public Boolean isFail()
    {
        return fail;
    }
}
