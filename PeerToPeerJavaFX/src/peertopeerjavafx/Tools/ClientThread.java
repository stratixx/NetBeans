/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

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
        
        try
        {            
            tmp.setSocket( new Socket(tmp.getAdress(), tmp.getPort()) );
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
