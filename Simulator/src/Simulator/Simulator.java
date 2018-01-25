package Simulator;

import javafx.application.Application;
import javafx.stage.Stage;
import Simulator.Model.Model;
import Simulator.View.View;
import Simulator.Controller.Controller;

public class Simulator extends Application{
    static Model model;
    static View view;
    static Controller controller;
    
    /**
     *  Stworzenie i zainicjowanie obiektów MVC
     * @param args Parametry wywołania aplikacji
     */
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
    public void start(Stage primaryStage) { 
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
