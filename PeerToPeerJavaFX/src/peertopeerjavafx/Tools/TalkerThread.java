/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa wątku obsługującego nawiązane połączenie
 * Odbiera dane od rozmówcy, 
 * wysyłanie zrealizowane w metodzie kontrolera sendText()
 * @author Konrad Winnicki
 */
public class TalkerThread extends ConnectionThread{
    
    // Obiekt połączenia
    private Connection connect;
    // Wejście danych z połączenia(Socket-a)
    private final BufferedReader externalInput;
    // Wejście danych obsługiwane(wyświetlane) przez program
    private final List<String> internalInput;
    
    /**
     * Konstruktor klasy
     * Tworzy wewnętrzny bufor danych wejściowych
     * @param connection obiekt połączenia
     */
    public TalkerThread(Connection connection) {
        this.connect = connection;
        externalInput = connection.getExternalInput();
        internalInput = new ArrayList<>();
        connection.setInternalInput( internalInput );
    }
    
    /**
     * Procedura obsługi połączenia
     */
    @Override
    public void run()
    {                
        String userInput;
        
        try 
        {
            while( connect.isConnectionOK() )
            {
                int c;
                /*
                Warunek ten zapewnia sprawdzanie czy połączenie jest
                ciągle dostępne.
                Metoda wywoływana w warunku zwraca -1 gdy połączenie jest niedostępne,
                blokuje przebieg procedury gdy nie ma danych wejściowych,
                zwraca jeden znak odebrany gdy odebrano dane.
                Znak ten jest zapamiętywany i doklejany do danych wejściowych
                */
                if( (c=connect.getSocket().getInputStream().read()) ==(-1) )
                {
                    System.out.println("peertopeerjavafx.Tools.TalkerThread.run() connection closed externaly");
                    connect.setFail(true);
                    connect.close();
                    break;
                }  
                else
                {
                    while ((userInput = externalInput.readLine()) != null)
                    {
                        if( !connect.isConnectionOK())
                            break;
                        
                        userInput = (char)c + userInput; 
                        c = 0;
                        // wpisanie danych do wewnętrznego bufora danych
                        internalInput.add( connect.getFriendName() + ": " + userInput);                        
                        System.out.println("peertopeerjavafx.Tools.TalkerThread.run() input text"); 
                        // poinformowanie obserwatorów co spowoduje odświeżenie widoku                        
                        connect.informObservers();
                    }
                }
            }
        } catch (IOException ex) 
        {
            System.out.println("peertopeerjavafx.Tools.TalkerThread.run() conection closed localy");
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
