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
public class ServerThread extends ConnectionThread{

    public ServerThread(Connection connection) {
        super(connection);
    }
    
    @Override
    public void run()
    {
        System.out.println("peertopeerjavafx.Tools.ServerThread.run()");
        
        Connection tmp = super.getConnection();
        
        tmp.setConnected(false);
        tmp.setFail(true);
        //tmp.connected();
    }
}
