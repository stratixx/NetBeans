/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import javafx.application.Platform;

/**
 * Klasa obsługująca połączenie PeerToPeer
 * @author Konrad Winnicki
 */
public class Connection extends Observable{
    
    // Typ połączenia: "SERVER" lub "CLIENT"
    private ConnectionType connectionType;
    // Wątek obsługi nawiązywania połączenia oraz przesyłania danych 
    private ConnectionThread connectionThread;
    // Element instancji serwerowej
    private ServerSocket serverSocket;
    // Socket połączenia
    private Socket socket;
    // Adres hosta
    private String adress;
    // Numer portu połączenia
    private int port;
    // Wyjście danych
    private PrintWriter output;
    // Wejście danych z połączenia(Socket-a)
    private BufferedReader externalInput;
    // Wejście danych obsługiwane(wyświetlane) przez program
    private List internalInput;
    // Flagi stanu
    private Boolean connected;
    private Boolean fail;
    // Nazwa użytkownika z którym nawiązano połączenie(Not supported)
    private String friendName;
    
    /**
     * Konstruktor obiektu połączenia
     * @param connectionType Typ połączenia
     * @param adress Adres hosta
     * @param port numer portu
     */
    public Connection( ConnectionType connectionType, String adress, int port)
    {
        this.connectionType = connectionType;
        this.connectionThread = null;
        this.serverSocket = null;
        this.socket = null;
        this.adress = adress;
        this.port = port;
        this.output = null;
        this.externalInput = null;
        this.internalInput = null;
        this.connected = false;
        this.fail = false;
        this.friendName = "Andrzej";
    }
    
    /**
     * Start próby nawiązania połączenia klienckiego w osobnym wątku
     */
    public void startClientConnection()
    {
        connectionThread = new ClientThread( this );
        connectionThread.setName("ClientConnectionThread");
        connectionThread.start();        
    }
    
    /**
     * Start próby nawiązania połączenia serwerowego w osobnym wątku
     */
    public void startServerConnection()
    {
        connectionThread = new ServerThread( this );
        connectionThread.setName("ServerConnectionThread");
        connectionThread.start();             
    }
    
    /**
     * Inicjacja wątku obsługi nawiązanego połączenia,
     * Metoda wywoływana przez wątek nawiązywania połączenia po pomyślnej próbie, 
     * Metoda oczekuje na koniec pracy wątku nawiązywania połączenia i 
     * uruchamia na jego miejscu nowy wątek - 
     * wątek obsługi nawiązanego połączenia.
     */
    public void startTalkerThread()
    {
        Connection conn = this;
        
        Thread thread = new Thread(() -> {
            System.out.println("peertopeerjavafx.Tools.Connection.startTalkerThread() starting new TalkerThread");
            // Zaczekaj na zakończenie pracy wątku nawiązywania połączenia
            while( (connectionThread!=null)&&(connectionThread.isAlive()) );
            // Start wątku obsługi połączenia
            connectionThread = new TalkerThread( conn );
            connectionThread.setName("TalkerThread");
            connectionThread.start();
        });
        thread.setName("StarterForTalkerThread");
        thread.start();
    }
    
    /**
     * Funkcja zamykająca połączenie, 
     * "czyści" obiekt połączenia, 
     * informuje obserwatorów o zmianie obiektu
     */
    synchronized public void close()
    {
        System.out.println("peertopeerjavafx.Tools.Connection.close()");
        
        try
        {
            if( serverSocket!=null ) serverSocket.close();
            if( socket!=null ) socket.close();
            if( externalInput!=null ) externalInput.close();
            if( output!=null ) output.close();
            if( connectionThread!=null ) connectionThread.interrupt();            
        }
        catch( Exception e )
        {
            System.out.println("peertopeerjavafx.Tools.Connection.close() EXCEPTION");
            //e.printStackTrace();
        }
        
        connectionThread = null;
        externalInput = null;
        internalInput = null;
        output = null;
        serverSocket = null;
        socket = null;
        
        informObservers();
    }
    
    /**
     * Dodanie zadania wątkowi JavaFX,
     * poinformowanie obserwatorów połączenia,
     * obserwatorem jest obiekt Modelu
     */
    synchronized public void informObservers()
    {
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();             
        });         
    }
    
    /**
     *  Metoda wywoływana automatycznie gdy wątek nawiązujący połączenie zakończył pracę z sukcesem
     */
    public void connected()
    {        
        this.startTalkerThread();
        // wywołanie zadania dla javafx, obserwatorem jest model
        informObservers();
    }
    
    // Settery i Gettery klasy
    
    /**
     *
     * @param newServerSocket nowy ServerSocket
     */
    public void setServerSocket( ServerSocket newServerSocket )
    {
        this.serverSocket = newServerSocket;
    }
    
    /**
     *
     * @return ServerSocket
     */
    public ServerSocket getServerSocket()
    {
        return this.serverSocket;
    }
    
    /**
     *
     * @param newSocket nowy Socket
     */
    public void setSocket( Socket newSocket )
    {
        this.socket = newSocket;
    }
    
    /**
     *
     * @return Socket
     */
    public Socket getSocket()
    {
        return this.socket;
    }
    
    /**
     *
     * @param newAdress nowy Adres hosta
     */
    public void setAdress( String newAdress )
    {
        this.adress = newAdress;
    }
    
    /**
     *
     * @return adres hosta
     */
    public String getAdress()
    {
        return adress;
    }
    
    /**
     *
     * @param newPort nowy numer portu
     */
    public void setPort( int newPort )
    {
        this.port = newPort;
    }
    
    /**
     *
     * @return numer portu
     */
    public int getPort()
    {
        return port;
    }  
    
    /**
     *
     * @param newInput nowy wewnętrzny bufor danych wejściowych
     */
    public void setInternalInput( List newInput )
    {
        this.internalInput = newInput;
    }
    
    /**
     *
     * @return wewnętrzny bufor danych wejściowych
     */
    public List getInternalInput()
    {
        return this.internalInput;
    }
        
    /**
     *
     * @param newInput nowy zewnętrzny bufor danych wejściowych
     */
    public void setExternalInput( BufferedReader newInput )
    {
        this.externalInput = newInput;
    }
    
    /**
     *
     * @return zewnętrzny bufor danych wejściowych
     */
    public BufferedReader getExternalInput()
    {
        return this.externalInput;
    }
        
    /**
     *
     * @param newOutput nowy bufor danych wyjściowych
     */
    public void setOutput( PrintWriter newOutput )
    {
        this.output = newOutput;
    }
    
    /**
     *
     * @return bufor danych wyjściowych
     */
    public PrintWriter getOutput()
    {
        return this.output;
    }
    
    /**
     *
     * @param newType nowy typ połączenia
     */
    public void setConnectionType( ConnectionType newType )
    {
        connectionType = newType;
    }
    
    /**
     *
     * @return typ połączenia
     */
    public ConnectionType getconnectionType()
    {
        return connectionType;
    }
    
    /**
     *
     * @param newState nowy stan flagi
     */
    public void setConnected(Boolean newState)
    {
        this.connected = newState;
    }
    
    /**
     *
     * @return stan flagi
     */
    public Boolean isConnected()
    {        
        return this.connected;
    }
    
    /**
     *
     * @param newState nowy stan flagi
     */
    public void setFail(Boolean newState)
    {
        this.fail = newState;
    }
    
    /**
     *
     * @return stan flagi
     */
    public Boolean isFail()
    {
        return this.fail;
    }
    
    /**
     *
     * @param newFriendName nowa nazwa rozmówcy
     */
    public void setFriendName( String newFriendName )
    {
        this.friendName = newFriendName;
    }
    
    /**
     *
     * @return nazwa rozmówcy
     */
    public String getFriendName()
    {
        return this.friendName;
    }
    
    /**
     * Sprawdza czy połączenie jest w stanie początkowym
     * @return stan połączenia
     */
    public Boolean isConnectionDefault()
    {        
        return (!this.isConnected() && !this.isFail());
    }

    /**
     * Sprawdza czy próba połączenia nie udała się 
     * @return stan połączenia
     */
    public Boolean isConnectionFail()
    {
        return (!this.isConnected() && this.isFail());
    }

    /**
     * Sprawdza czy próba połączenia powiodła się, czy połączony
     * @return stan połączenia
     */
    public Boolean isConnectionOK()
    {
        return (this.isConnected() && !this.isFail());
    }

    /**
     *  Sprawdza czy połączenie zostało zakończone/zerwane
     * @return stan połączenia
     */
    public Boolean isConnectionEnd()
    {
        return (this.isConnected() && this.isFail());
    }
}
