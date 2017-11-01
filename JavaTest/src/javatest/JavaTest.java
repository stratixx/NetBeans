/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

import java.net.*;
import javatest.MyRun;
import javatest.PeerServer;
import javatest.PeerClient;

/**
 *
 * @author Skrzatt
 */

import java.awt.EventQueue;

public class JavaTest {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        // start okienka
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Display mainDisplay = new Display();
                
                // start modułu serwera peerowego
                PeerServer peer_server = new PeerServer(21, mainDisplay);
                peer_server.start();
            }
        });
        
        
        System.out.println("tutaj main sie konczy");
    }
    
}
