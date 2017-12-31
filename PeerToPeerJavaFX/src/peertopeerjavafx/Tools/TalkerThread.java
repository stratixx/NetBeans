/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Skrzatt
 */
public class TalkerThread extends ConnectionThread{
    
    private BufferedReader externalInput;
    private List<String> internalInput;
    
    public TalkerThread(Connection connection) {
        super(connection);
        externalInput = connection.getExternalInput();
        internalInput = new ArrayList<>();
        connection.setInternalInput( internalInput );
    }
    
    @Override
    public void run()
    {         
        Connection connect = super.getConnection();        
        String userInput;
        
        try 
        {
            while( connect.isConnectionOK() )
            {
                int c;
                if( (c=connect.getSocket().getInputStream().read()) ==(-1) )
                {
                    System.out.println("peertopeerjavafx.Tools.TalkerThread.run() connection end externaly");
                    connect.setFail(true);
                    connect.close();
                    break;
                }  
                else
                {
                    //while( !input.ready() )
                    while ((userInput = externalInput.readLine()) != null)
                    {
                        if( !connect.isConnectionOK())
                            break;
                        userInput = (char)c + userInput;
                        c=0;
                        char[] array = userInput.toCharArray();
                        for(int n=0; n<array.length; n++)
                            System.out.println((int)array[n]);
                        if(!userInput.equals(""))
                            internalInput.add(userInput);
                        
                        System.out.println("peertopeerjavafx.Tools.TalkerThread.run() input text");
                        Platform.runLater(TalkerThread.super.getConnection()::informObservers);                    
                    }
                }
            }
        } catch (IOException ex) 
        {
            System.out.println("peertopeerjavafx.Tools.TalkerThread.run() conection closed localy");
        }
        Platform.runLater(TalkerThread.super.getConnection()::informObservers);
        //connect.close();
    }
    
}
