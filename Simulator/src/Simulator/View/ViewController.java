/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.View;

import Simulator.Tools.Drawer;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * klasa abstrakcyjna rozszerzana przez konkretne widoki, 
 * usostępnia metodę loadera widoku z pliku .fxml
 * @author Skrzatt
 */
abstract public class ViewController {
    
    private View view;
    private Parent content;
    private Drawer drawer;   
    private String viewName;
    
    /**
     * loader widoku z pliku .fxml
     * @param view referencja na obiekt widoku
     * @param resource ścieżka do pliku .fxml
     * @return obiekt kontrolera nowego widoku
     */
    public static ViewController loadView( View view, URL resource )
    {        
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader( resource );
            Parent content = fxmlLoader.load();  
            ViewController viewController = fxmlLoader.getController();        
            viewController.setView( view );
            viewController.setContent( content );    

            return viewController;
        }
        catch( IOException e )
        {
            System.out.println("ViewController.loadView() IOException");
            return null;
        }
        
    }
    
    /////////////////////////// Settery i gettery ///////////////////////////
    
    public void setView( View newView )
    {
        this.view = newView;
    }
    
    public View getView()
    {
        return this.view;
    }
    
    public void setContent( Parent newContent )
    {
        this.content = newContent;
    }
    
    public Parent getContent()
    {
        return this.content;
    }
     
    public Drawer getDrawer()
    {
        return drawer;
    }
     
    public void setDrawer( Drawer newDrawer)
    {
        drawer = newDrawer;
    }
    
    public void setViewName( String newViewName )
    {
        this.viewName = newViewName;
    }
     
    public String getViewName()
    {
        return this.viewName;
    }  
    
    public Pane getPane()
    {
        return null;
    }  
}
