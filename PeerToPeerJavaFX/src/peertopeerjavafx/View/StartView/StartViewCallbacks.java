/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.StartView;

import peertopeerjavafx.Tools.Connection;

/**
 * Callbacki widoku początkowego
 * @author Konrad Winnicki
 */
public interface StartViewCallbacks {
    
    /**
     * Reakcja na przycisk TX/Klient
     * @param connect nowy obiekt połączenia zawierający adres i port
     */
    public void buttonTXClicked( Connection connect );

    /**
     * Reakcja na przycisk RX/Serwer
     * @param connect nowy obiekt połączenia zawierający adres i port
     */
    public void buttonRXClicked( Connection connect );
}
