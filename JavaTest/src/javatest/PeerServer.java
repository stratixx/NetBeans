/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JWindow;

/**
 *
 * @author Skrzatt
 */
public class PeerServer extends java.lang.Thread
{
    
    private int port;
    private ServerSocket server_socket;
    private Display mainDisplay;
 
    public PeerServer(int port, Display mainDisplay) 
    {
        this.port = port;
        this.mainDisplay = mainDisplay;
        
        try
        {
            this.server_socket = new ServerSocket(port);
        }
        catch( Exception except )
        {
            System.err.println( except.getMessage() );
        }
        
    }
    
    @Override
    public void run()
    {
        Socket socket;
        ArrayList<ConnectionService> thread_list = new ArrayList<>();
        
        JButton but = new JButton("|");
        mainDisplay.add(but);
        mainDisplay.setVisible(true);
        JTabbedPane panelZakladek = new JTabbedPane(1);
        //panelZakladek.setTabPlacement(JTabbedPane.TOP);
        
        
        int number = 0;
        try
        {
            while( true )
            {
                socket = server_socket.accept();
                System.out.println("Zglosil sie klient");
                number++;
                
                JButton witajButton = new JButton();
                witajButton.setText("Witaj!");
                JButton zegnajButton = new JButton("Żegnaj!");
                //witajButton.addActionListener(this);
                //zegnajButton.addActionListener(this);
                JPanel jp = new JPanel();
                jp.setLayout(new GridLayout(1,2));
                jp.add(witajButton);
                jp.add(zegnajButton);
                JPanel talkWindow = new JPanel();
                talkWindow.setLayout(new GridLayout(3,1));
                // tworzymy pole tekstowe z domyślnym tekstem
                JTextArea textF = new JTextArea("Wpisz swe imię przybyszu!");
                // tworzymy pole na którym będzie wypisany tekst
                JLabel rezultatJL = new JLabel();
                rezultatJL.setSize(400,20);
                talkWindow.add(textF);
                talkWindow.add(rezultatJL);
                talkWindow.add(jp);
                panelZakladek.addTab("talk: "+number, null, talkWindow, "tt");
                panelZakladek.setVisible(true);
                mainDisplay.setVisible(true);
                
                ConnectionService new_thread = new ConnectionService(socket, number, talkWindow );
                new_thread.start();
                
                thread_list.add( new_thread );                
                Iterator<ConnectionService> thread_iter = thread_list.iterator();
                
                but.setText("");
                but.setMinimumSize(new Dimension(200, 300));
                mainDisplay.repaint();
                while( thread_iter.hasNext() )
                {
                    ConnectionService temp = thread_iter.next();
                    if( temp.isAlive() )
                        but.setText( but.getText() + temp.number + '|' );
                    else
                        thread_iter.remove(); // watek umarl, czysc
                }
            }
        }
        catch (Exception e) 
        {
            System.err.println( e.getMessage() );
        }

    }
}
