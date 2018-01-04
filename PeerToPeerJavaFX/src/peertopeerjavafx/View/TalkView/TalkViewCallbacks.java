/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.TalkView;

/**
 * Callbacki widoku rozmowy
 * @author Konrad Winnicki
 */
public interface TalkViewCallbacks {
    
    /**
     * Reakcja na przycisk wysyłania danych
     */
    public void buttonSendClicked();

    /**
     * Reakcja na żądanie zamknięcia okna
     */
    public void onClose();
}
