/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator.View;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;

/**
 * klasa abstrakcyjna rozszerzana przez konkretne widoki, 
 * usostępnia metodę loadera widoku z pliku .fxml
 * @author Skrzatt
 */
abstract public class ViewController {
    
    private View view;
    private Parent content;
    private GraphicsContext graphic;    
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
    
    public void setGraphicsContext( GraphicsContext newGraphic )
    {
        this.graphic = newGraphic;
    }
     
    public GraphicsContext getGraphicsContext()
    {
        return this.graphic;
    }
    
    public void setViewName( String newViewName )
    {
        this.viewName = newViewName;
    }
     
    public String getViewName()
    {
        return this.viewName;
    }
}
