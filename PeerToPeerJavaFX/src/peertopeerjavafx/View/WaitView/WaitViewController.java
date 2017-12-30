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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Skrzatt
 */
public class WaitViewController extends Stage{

    //@FXML
    //private Label infoLabel;
    //@FXML
    //private Label statusLabel;
    //@FXML
    //private ProgressBar statusBar;
    @FXML
    private Button ButtonCancel;
    
    
    Parent content;
    
    public Parent getContent()
    {
        return content;
    }
    
    public WaitViewController( Stage talkViewStage, WaitViewCallbacks callbacks ) throws IOException
    {
        super();
        
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(talkViewStage.getScene().getWindow());
        content = FXMLLoader.load(getClass().getResource("waitView_.fxml"));
        
        ButtonCancel = (Button)content.lookup("#Button");
        this.setScene(new Scene(content));
        
        
        ButtonCancel.setOnMouseClicked((MouseEvent event) -> {
            callbacks.buttonClicked();
            event.consume();
        });
        
        this.setOnCloseRequest((WindowEvent event) -> {
            callbacks.onClose();
            System.out.println(".handle() on exit");
            event.consume();
        });
    }
    
    @Override
    public void hide()
    {
        super.hide();
        setStatusDefault();
    }
    
    
    public void showView()
    {
        setStatusDefault();
        super.show();
    }
    
    public void setElementVisible( String element, Boolean visible)
    {
        content.lookup(element).setVisible(visible);
    }
    
    /**
     * Połączenie zakończone przed rozpoczęciem rozmowy
     */
    public void setStatusEnd()
    {
        setElementVisible("#statusLabelDefault", false);
        setElementVisible("#statusLabelFail", false);
        setElementVisible("#statusLabelOK", false);
        setElementVisible("#statusLabelEnd", true);
        //statusLabel.setText("połączenie zerwane!");
        ButtonCancel.setText("Powrót");
        //statusBar.setProgress(0.66);
    }
    
    /**
     * Stan początkowy, nawiązywanie połączenia
     */
    public void setStatusDefault()
    {
        setElementVisible("#statusLabelDefault", true);
        setElementVisible("#statusLabelFail", false);
        setElementVisible("#statusLabelOK", false);
        setElementVisible("#statusLabelEnd", false);
        //statusLabel.setText("w trakcie...");
        ButtonCancel.setText("Anuluj");
        //statusBar.setProgress(-1.0);
    }
    
    /**
     * Połączenie nawiązane
     */
    public void setStatusOK()
    {
        setElementVisible("#statusLabelDefault", false);
        setElementVisible("#statusLabelFail", false);
        setElementVisible("#statusLabelOK", true);
        setElementVisible("#statusLabelEnd", false);
        //statusLabel.setText("zakończone sukcesem!");
        ButtonCancel.setText("Rozpocznij rozmowę");
        //statusBar.setProgress(1.0);
    }
    
    /**
     * Nie udało się nawiązać połączenia
     */
    public void setStatusFAIL()
    {
        setElementVisible("#statusLabelDefault", false);
        setElementVisible("#statusLabelFail", true);
        setElementVisible("#statusLabelOK", false);
        setElementVisible("#statusLabelEnd", false);
        //statusLabel.setText("zakończone niepowodzeniem!");
        ButtonCancel.setText("Powrót");
        //statusBar.setProgress(0.33);
    }
    
    
    
    
}
