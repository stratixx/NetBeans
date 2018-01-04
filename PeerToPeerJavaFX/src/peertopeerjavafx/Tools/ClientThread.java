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
 * Klasa wątku próby nawiązania połączenia instancji klienckiej
 * @author Konrad Winnicki
 */
public class ClientThread extends ConnectionThread {

    // Obiekt połączenia zawierający adres i port
    private Connection connect;   
    // Socket połączenia
    private Socket socket;
    
    /**
     * Konstruktor klasy wątku
     * @param connection obiekt połączenia
     */
    public ClientThread(Connection connection) {
        this.connect = connection;
    }
    
    @Override
    public void run()
    {        
        System.out.println("peertopeerjavafx.Tools.ClientThread.run() start");        
        
        try
        {        
            // Próba stworzenia Socketa
            // Rzuca wyjątek w przypadku niepowodzenia
            socket = new Socket(connect.getAdress(), connect.getPort());    
            
            // Utworzenie bufora danych wyjściowych
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Utworzenie zewnętrznego bufora danych wejściowych
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            
            // Ustawienie pól obiektu połączenia
            connect.setOutput( out );
            connect.setExternalInput( in );
            connect.setSocket( socket );
            connect.setConnected(true);
            connect.setFail(false);
            // inicjacja wątku obsługi połączenia
            connect.startTalkerThread();
            System.out.println("peertopeerjavafx.Tools.Connection.startClientConnection() OK");
        }
        catch( Exception e )
        {
            //e.printStackTrace();
            connect.setConnected(false);
            connect.setFail(true);
            System.out.println("peertopeerjavafx.Tools.Connection.startClientConnection() FAIL");
        }
        
        connect.informObservers();
    }
    
    // Settery i Gettery
    
    /**
     * 
     * @param newConnection nowy obiekt połączenia
     */
    @Override
    public void setConnection( Connection newConnection)
    {
        this.connect = newConnection;
    }
    
    /**
     * 
     * @return obiekt połączenia
     */
    @Override
    public Connection getConnection()
    {
        return this.connect;
    }
}
