/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.WaitView;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Klasa widoku okna oczekiwania
 *
 * @author Konrad Winnicki
 */
public class WaitViewController extends Stage{

    // Napis 
    //private final Label infoLabel;
    // Napis informujący o stanie połączenia
    private final Label statusLabel;
    // Pasek postępu połączenia
    private final ProgressBar statusBar;
    // Przycisk potwierdzenia(wielofunkcyjny)
    private final Button ButtonCancel;
    // Zawartość okna, ładowany z pliku waitView.fxml
    private final Parent content;
    
    
    /**
     * Konstruktor okna
     * @param startViewStage Referencja do okna startowego
     * @param callbacks Callbacki do przycisku
     * @throws IOException
     */
    public WaitViewController( Stage startViewStage, WaitViewCallbacks callbacks ) throws IOException
    {
        //super();              
        // Ustawienie jako okno nadrzędne
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(startViewStage.getScene().getWindow());
        // Załadowanie zawartości 
        content = FXMLLoader.load(getClass().getResource("waitView_.fxml"));   
        // Inicjacja obiektów     
        //infoLabel = (Label)content.lookup("#infoLabel");
        statusLabel = (Label)content.lookup("#statusLabel");
        statusBar = (ProgressBar)content.lookup("#statusBar");
        ButtonCancel = (Button)content.lookup("#Button");       
        
        // Ustawienie reakcji na przycisk
        ButtonCancel.setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonClicked();
            event.consume();
        });
        
        // Ustawienie reakcji na żądanie zamknięcia okna
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
            System.out.println(".handle() on exit");
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
     */
    public void showView()
    {
        setStatusDefault();
        super.show();
    }
    
    /**
     * Połączenie zakończone przed rozpoczęciem rozmowy
     */
    public void setStatusEnd()
    {
        statusLabel.setText("połączenie zerwane!");
        ButtonCancel.setText("Powrót");
        statusBar.setProgress(0.66);
    }
    
    /**
     * Stan początkowy, nawiązywanie połączenia
     */
    public void setStatusDefault()
    {
        statusLabel.setText("w trakcie...");
        ButtonCancel.setText("Anuluj");
        statusBar.setProgress(-1.0);
    }
    
    /**
     * Połączenie nawiązane
     */
    public void setStatusOK()
    {
        statusLabel.setText("zakończone sukcesem!");
        ButtonCancel.setText("Rozpocznij rozmowę");
        statusBar.setProgress(1.0);
    }
    
    /**
     * Nie udało się nawiązać połączenia
     */
    public void setStatusFAIL()
    {
        statusLabel.setText("zakończone niepowodzeniem!");
        ButtonCancel.setText("Powrót");
        statusBar.setProgress(0.33);
    }
    
    
    
    
}
