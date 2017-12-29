/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.StartView;

import peertopeerjavafx.Tools.Connection;

/**
 *
 * @author Skrzatt
 */
public interface StartViewCallbacks {
    public void buttonTXClicked( Connection connect );
    public void buttonRXClicked( Connection connect );
}
