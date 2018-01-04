/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

/**
 * Abstrakcyjna klasa tworząca wątek nawiązujący połączenie
 * @author Konrad Winnicki
 */
public abstract class ConnectionThread extends Thread{
    
    /**
     * Abstrakcyjny setter obiektu połączenia
     * @param connection nowy obiekt połączenia
     */
    public abstract void setConnection( Connection connection );
    
    /**
     * abstrakcyjny getter połączenia
     * @return obiekt połączenia
     */
    public abstract Connection getConnection(  );
    
    
    /**
     * Metoda uruchamiająca wątek
     */
    @Override
    public abstract void run();
}
