/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.WaitView;

/**
 * Callbacki widoku oczekiwania
 * @author Konrad Winnicki
 */
public interface WaitViewCallbacks {
    
    /**
     * Rekacja na przycisk potwierdzenia
     */
    public void buttonClicked();

    /**
     * Reakcja na żadanie zamknięcia okna
     */
    public void onClose();
}
