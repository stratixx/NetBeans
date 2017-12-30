/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import peertopeerjavafx.Model.Model;
import peertopeerjavafx.View.View;
import peertopeerjavafx.Controller.Controller;

/**
 *
 * @author Skrzatt
 */
public class PeerToPeerJavaFX extends Application{
    
    static Model model;
    static View view;
    static Controller controller;
    
    
    public static void main(String[] args) 
    {
        model = new Model();
        view = new View();
        controller = new Controller(view, model);
        
        model.setController(controller);
        view.setController(controller);   
        
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception { 
        try
        {
            view.launch(primaryStage); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } 
}
