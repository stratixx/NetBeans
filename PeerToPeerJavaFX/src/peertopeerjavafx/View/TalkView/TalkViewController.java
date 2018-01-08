/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerjavafx.View.TalkView;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Klasa okna rozmowy
 * @author Konrad Winnicki
 */
public class TalkViewController extends Stage{

    // Przycisku wysłania tekstu
    private final Button buttonSend;
    // Obszar wyświetlający tekst
    private final TextFlow textOutput;
    // Obszar wprowadzania tekstu
    private final TextArea textInput;
    // pasek przewijania wyświetlanego tekstu
    //private ScrollBar scroller;
    // Obiekt zawierający wygląd okna, ładowany z pliku talkView.fxml
    private final Parent content;
    
    /**
     * Konstruktor okna
     * @param callbacks
     * @throws IOException
     */
    public TalkViewController( TalkViewCallbacks callbacks ) throws IOException
    {
        //super();
                
        // Załadowanie zawartości
        content = FXMLLoader.load(getClass().getResource("talkView.fxml"));
        // Inicjacja elementów
        buttonSend = (Button)content.lookup("#buttonSend");
        textOutput = (TextFlow)content.lookup("#textOutput");
        textInput = (TextArea)content.lookup("#textInput");
        //scroller = (ScrollBar)content.lookup("#scroller");
        
        // Reakcja na enter
        textInput.setOnKeyPressed((KeyEvent event) -> {            
            if (event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown() )
            {
                callbacks.buttonSendClicked();
                event.consume();
            }
        });
        
        // Ustawienie reakcji na przycisk send
        buttonSend.setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonSendClicked();
            event.consume();
        });
        
        // Ustawienie reakcji na żądanie zamknięcia okna
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
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
    
    /**
     * Ukrycie i ustawienie stanu początkowego okna
     */
    @Override
    public void hide()
    {
        clearOutputText();
        super.hide();
    }
    
    /**
     * Pobranie tekstu z wejścia i wyczyszczenie wejścia
     * @return Tekst wejściowy
     */
    public String getInputText()
    {
        String inputText = textInput.getText();
        textInput.setText("");
        return inputText;
    }
    
    /**
     * Wyświetlenie tekstu oraz procedura postępującego czyszczenia widoku
     * @param outputText Tekst do wyświetlenia
     */
    public void setOutputText( String outputText )
    {
        Text text = new Text(outputText+"\n");
        
        textOutput.getChildren().add(text);
            
        // Jeśli okno zaczyna się rozrastać, usuń najstarszy element
        if( textOutput.getHeight()>textOutput.getPrefHeight())
        {
            System.out.print("peertopeerjavafx.View.TalkView.TalkViewController.setOutputText() deleting ");
            System.out.print(textOutput.getHeight());
            System.out.print("/");
            System.out.println(textOutput.getPrefHeight());
            textOutput.getChildren().remove(0);
        }      
        // odświeżenie elementu??
        textOutput.requestLayout();        
    }
    
    /**
     * Wyczyszczenie elementu
     */
    public void clearOutputText()
    {
        textOutput.getChildren().clear();
    }
}
