/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;
import java.io.IOException;
import java.net.*;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
/**
 *
 * @author Skrzatt
 */
public class ConnectionService extends java.lang.Thread {
    
    private Socket socket;
    public int number;
    private JPanel talkWindow;

    public ConnectionService(Socket s, int n, JPanel talkWindow )
    {
        socket = s;
        number = n;
        this.talkWindow = talkWindow;
        
    }
    public void run() 
    {
        try
        {              
            DataOutputStream out;
            OutputStream out_sock;      
            out_sock = socket.getOutputStream();
            out = new DataOutputStream ( out_sock );
            
            int ticks = 0;
            while(true) 
            {
                System.out.println("Obsluga klienta nr: "+number);
                ticks++;
                //button.setText("ticks: "+ticks);
                try {
                    //usypiamy wątek na 500 milisekund
                    out.writeInt('0'+number);                
                    Thread.sleep(1000);
                } 
                catch (Exception e) 
                {
                    talkWindow.setVisible(false);
                    break;
                }
            }           
        }
        catch( IOException e ) 
        {
            System.err.println( e.getMessage() );
            e.printStackTrace();
        }
    }
}

