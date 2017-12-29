/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

/**
 * wątek służący do nieblokującego nawiązania połączenia
 * @author Skrzatt
 */
public class ConnectionThread extends Thread{
    
    private Connection connection;
    
    public ConnectionThread( Connection connection )
    {
        this.connection = connection;
        
    }
    
    /**
     * 
     * @param connection 
     */
    public void setConnection( Connection connection )
    {
        this.connection = connection;
    }
    
    /**
     * 
     * @return 
     */
    public Connection getConnection(  )
    {
        return this.connection;
    }
    
    
    @Override
    public void run()
    {
        System.out.println("peertopeerjavafx.Tools.ConnectionThread.run()");
    }
}
