/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.StartView;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.Tools.ConnectionType;

/**
 * Klasa okna początkowego
 * @author Konrad Winnicki
 */
public class StartViewController extends Stage{

    // Obiekt wprowadzania adresu hosta
    private TextField adressIP;
    // Obiekt wprowadzania portu
    private TextField adressPort;
    // Obiekt przycisku TX/Klient
    private final Button buttonTX;
    // Obiekt przycisku RX/Serwer
    private final Button buttonRX;
    // Obiekt napisu informacyjnego
    private final Label labelStatus;
    // Obiekt zawierający wygląd okna, ładowany z pliku startView.fxml
    private final Parent content;
    
    /**
     *
     * @param calbacks
     * @throws IOException
     */
    public StartViewController( StartViewCallbacks calbacks ) throws IOException
    {
        //super();
        
        // Załadowanie zawartości
        content = FXMLLoader.load(getClass().getResource("startView.fxml"));    
        // Inicjacja obiektów
        adressIP = (TextField)content.lookup("#adressIP");
        adressPort = (TextField)content.lookup("#adressPort");
        buttonTX = (Button)content.lookup("#buttonTX");
        buttonRX = (Button)content.lookup("#buttonRX");
        labelStatus = (Label)content.lookup("#labelStatus");
             
        // Ustawienie reakcji na przycisk TX/Klient   
        buttonTX.setOnMouseClicked((MouseEvent event) -> {
            Connection connect =
                    new Connection(ConnectionType.CLIENT, adressIP.getText(), Integer.parseInt(adressPort.getText()));
            calbacks.buttonClicked(connect);
            event.consume();
        });
        
        // Ustawienie reakcji na przycisk RX/Serwer
        buttonRX.setOnMouseClicked((MouseEvent event) -> {
            Connection connect =
                    new Connection(ConnectionType.SERVER, adressIP.getText(), Integer.parseInt(adressPort.getText()));
            calbacks.buttonClicked(connect);
            event.consume();
        });        
           
        // Ustawienie ikony i tytułu okna
        this.getIcons().add(new Image(getClass().getResourceAsStream("/peertopeerjavafx/View/icon.jpg")));
        this.setTitle("Komunikator PeerToPeer");
        this.setResizable(false);
        this.setScene(new Scene(content));   
    }        
    
    /**
     * Ustawienie stanu początkowego i pokazanie okna
     * @param x pozycja x na ekranie
     * @param y pozycja y na ekranie
     */
    public void showView( double x, double y)
    {
        this.setX(x);
        this.setY(y);
        //setStatusDefault();
        super.show();
    }
}
