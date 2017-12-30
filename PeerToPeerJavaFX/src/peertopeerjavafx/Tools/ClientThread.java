/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Skrzatt
 */
public class ClientThread extends ConnectionThread {

    public ClientThread(Connection connection) {
        super(connection);
    }
    
    @Override
    public void run()
    {        
        System.out.println("peertopeerjavafx.Tools.ClientThread.run()");
        
        Connection tmp = super.getConnection();
        Socket socket;
        
        try
        {        
            socket = new Socket(tmp.getAdress(), tmp.getPort());    
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            
            tmp.setOutput( out );
            tmp.setInput( in );
            tmp.setSocket( socket );
            tmp.setConnected(true);
            tmp.setFail(false);
            System.out.println("peertopeerjavafx.Tools.Connection.startClientConnection() OK");
        }
        catch( Exception e )
        {
            //e.printStackTrace();
            tmp.setConnected(false);
            tmp.setFail(true);
            System.out.println("peertopeerjavafx.Tools.Connection.startClientConnection() FAIL");
        }
        
        tmp.connected();
    }
    
}
