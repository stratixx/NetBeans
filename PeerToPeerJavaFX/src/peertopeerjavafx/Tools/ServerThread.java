/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.net.ServerSocket;

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
        
        try
        {
            tmp.setServerSocket( new ServerSocket(tmp.getPort()) );
            tmp.setSocket( tmp.getServerSocket().accept() );
            tmp.getServerSocket().close();
            
            tmp.setConnected(true);
            tmp.setFail(false);
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() OK");
        }
        catch( Exception e )
        {
            //e.printStackTrace();
            tmp.setConnected(false);
            tmp.setFail(true);
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() FAIL");
        }  
        
        tmp.connected();
    }
}
