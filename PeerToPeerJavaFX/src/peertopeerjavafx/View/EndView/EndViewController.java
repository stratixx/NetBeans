/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.EndView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Klasa okna zakończenia połączenia
 * @author Konrad Winnicki
 */
public class EndViewController extends Stage {

    // Obiekt przyscisku OK
    private final Button buttonOK;
    // Obiekt napisu informującego
    private final Label infoLabel;
    // Obiekt zawierający wygląd okna, ładowany z pliku endView.fxml
    private final Parent content;
    
    /**
     * Konstruktor okna 
     * @param talkViewStage Referencja do okna rozmowy
     * @param callbacks Callbacki dla przycisków
     * @throws IOException
     */
    public EndViewController( Stage talkViewStage, EndViewCallbacks callbacks ) throws IOException
    {
        //super();
        // Ustawienie jako okno nadrzędne
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(talkViewStage.getScene().getWindow());
        // Załadowanie zawartości
        content = FXMLLoader.load(getClass().getResource("endView_.fxml"));
        // Inicjacja obiektów
        buttonOK = (Button)content.lookup("#ButtonOK");
        infoLabel = (Label)content.lookup("#infoLabelOK");
        
        // Ustawienie reakcji na przycisk
        buttonOK.setOnMouseClicked((MouseEvent event) -> {
            callbacks.confirmAction();
            event.consume();
        });
        // Ustawienie reakcji na żądanie zamknięcia okna
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.confirmAction();
            event.consume();
        });
           
        // Ustawienie ikony i tytułu okna
        this.getIcons().add(new Image(getClass().getResourceAsStream("/peertopeerjavafx/View/icon.jpg")));
        this.setTitle("Komunikator PeerToPeer");
        this.setResizable(false);
        this.setScene(new Scene(content));
    }
    
    
    /**
     * Ukrycie i ustawienie stanu początkowego okna
     */
    @Override
    public void hide()
    {
        super.hide();
        setStatusDefault();
    }
    
    /**
     * Ustawienie stanu początkowego i pokazanie okna
     * @param x pozycja x na ekranie
     * @param y pozycja y na ekranie
     */
    public void showView( double x, double y)
    {
        this.setX(x+(300-275)/2);
        this.setY(y+(400-150)/2);
        //setStatusDefault();
        super.show();
    }
        
    /**
     * Ustawienie stanu początkowego
     */
    public void setStatusDefault()
    {      
        infoLabel.setText("Zakończono rozmowę");
    }
    
    /**
     * Ustawinie stanu po zakończeniu połączenia
     */
    public void setStatusEnd()
    {        
        infoLabel.setText("Połączenie zerwane");
    }
}
