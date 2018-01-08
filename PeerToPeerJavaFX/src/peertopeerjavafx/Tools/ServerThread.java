/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa wątku nawiązywania połączenia instancji serwerowej
 * @author Konrad Winnicki
 */
public class ServerThread extends ConnectionThread{
    
    // Obiekt połączenia
    private Connection connect;
    // ServerSocket i tyle
    private ServerSocket serverSocket;
    // Socket połączenia
    private Socket socket;
    
    /**
     * Konstruktor
     * @param connection obiekt połączenia
     */
    public ServerThread(Connection connection) {
        this.connect = connection;
    }
    
    /**
     * Procedura próby nawiązania połączenia instancji serwerowej
     */
    @Override
    public void run()
    {
        System.out.println("peertopeerjavafx.Tools.ServerThread.run() START");
                
        try
        {
            // Utworzenie ServerSocket, wyjątek gdy port zajęty
            serverSocket = new ServerSocket(connect.getPort());
            // Ustawienie timeout
            serverSocket.setSoTimeout(5000);
            // Oczekiwanie na połączenie
            socket = serverSocket.accept();
            // Zamknięcie serverSocketa po nawiązaniu połączenia
            // Interesuje nas tylko obiekt Socket
            serverSocket.close();
                   
            // Bufor danych wyjściowych
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Zewnętrzny bufor danych wejściowych
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
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() OK");
        }
        catch( SocketTimeoutException e )
        {
            try { serverSocket.close(); } catch (IOException ex) {}     
            connect.setConnected(false);
            connect.setFail(true);  
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() Timeout");     
        }
        catch( Exception e )
        {
            //e.printStackTrace();
            connect.setConnected(false);
            connect.setFail(true);
            System.out.println("peertopeerjavafx.Tools.Connection.startServerConnection() FAIL");
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
