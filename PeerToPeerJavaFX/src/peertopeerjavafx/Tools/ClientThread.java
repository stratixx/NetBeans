/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

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
        
        tmp.setConnected(true);
        tmp.setFail(false);
        tmp.connected();
    }
    
}
