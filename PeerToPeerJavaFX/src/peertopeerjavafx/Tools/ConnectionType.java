/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

/**
 *  Enum zawierający typy połączenia
 * @author Skrzatt
 */
    public enum ConnectionType{
        NONE,   // Not used
        CLIENT, // Połączenie jako klient - podłączam się do instancji serwerowej
        SERVER  // Połączenie jako serwer - oczekuję na instancję kliencką
    }
