/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Skrzatt
 */
public class ServerThread extends ConnectionThread{

    public ServerThread(Connection connection) {
        super(connection);
    }
    
    @Override
    public void run()
    {
        System.out.println("peertopeerjavafx.Tools.ServerThread.run()");
        
        Connection tmp = super.getConnection();
        ServerSocket serverSocket;
        Socket socket;
        
        try
        {
            serverSocket = new ServerSocket(tmp.getPort());
            socket = serverSocket.accept();
            serverSocket.close();
                        
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            
            tmp.setOutput( out );
            tmp.setExternalInput( in );
            tmp.setSocket( socket );
            tmp.setConnected(true);
            tmp.setFail(false);
            tmp.startTalkerThread();
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() OK");
        }
        catch( Exception e )
        {
            //e.printStackTrace();
            tmp.setConnected(false);
            tmp.setFail(true);
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() FAIL");
        }  
        
        tmp.informObservers();
    }
}
