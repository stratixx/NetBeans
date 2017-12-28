package peertopeerjavafx.View;

import peertopeerjavafx.View.WaitView.*;
import peertopeerjavafx.View.TalkView.*;
import peertopeerjavafx.View.EndView.*;
import peertopeerjavafx.View.StartView.StartViewInterface;
import peertopeerjavafx.View.StartView.StartViewController;
import java.io.IOException;
import javafx.stage.Stage;
import peertopeerjavafx.Controller.ControllerViewInterface;
import peertopeerjavafx.Controller.ViewInterface;
import peertopeerjavafx.Tools.Connection;




public class View implements ViewInterface
{
    /** 
     * Referencja do aktywnego kontrolera
     */
    private ControllerViewInterface controller;
    
    /**
     * 
     */
    private StartViewInterface startViewCallbacks;
    private WaitViewInterface waitViewCallbacks;
    private TalkViewInterface talkViewCallbacks;
    private EndViewInterface endViewCallbacks;
        
    private StartViewController startView;
    private WaitViewController waitView;
    private TalkViewController talkView;
    private EndViewController endView;
        
    /**
     * Ustaw referencje do obiektu kontrolera
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerViewInterface controller){
        this.controller = controller;
        
    }
    
    
    /**
     * Inicjacja widoku
     * @param PrimaryStage
     */
    @Override
    public void launch( Stage PrimaryStage ) throws IOException{        
        startViewCallbacks = new StartViewInterface() {
            @Override
            public void buttonTXClicked( Connection connect ) {
                waitView.show();
                controller.startConnection(connect);
            }

            @Override
            public void buttonRXClicked( Connection connect ) {
                waitView.show();
                controller.startConnection(connect);
            }
        };
        
        waitViewCallbacks = new WaitViewInterface() {
            @Override
            public void buttonClicked() {
                if( controller.isConnected() ) { waitView.hide(); startView.hide(); talkView.show(); }
                else { waitView.hide(); controller.stopConnection(); }
            }

            @Override
            public void onClose() {
                waitView.hide();
                controller.stopConnection();
            }
        };
        
        talkViewCallbacks = new TalkViewInterface() {
            @Override
            public void buttonSendClicked() {
                System.out.println("rucham_psa_jak_sra.msg");
            }

            @Override
            public void onClose() {
                //talkView.hide();
                endView.show();
                controller.stopConnection();
            }
        };
                
        endViewCallbacks = new EndViewInterface() {
            @Override
            public void buttonOKClicked() { talkView.hide(); endView.hide(); startView.show(); }
            
            @Override
            public void onClose() { talkView.hide(); endView.hide(); startView.show(); }
        };
        
        startView = new StartViewController(startViewCallbacks);
        waitView = new WaitViewController( startView, waitViewCallbacks );
        talkView = new TalkViewController(talkViewCallbacks);
        endView = new EndViewController( talkView, endViewCallbacks );        
        
        startView.show();
    }
    
    
    /**
     * 
     */
    @Override
    public void showConnectionOK()
    {
        waitView.setStatusOK();
    }
    
    /**
     * 
     */
    @Override
    public void showConnectionFAIL()
    {
        waitView.setStatusFAIL();
    }
    
    /**
     * 
     */
    @Override
    public void showConnectionDefault()
    {
        waitView.setStatusDefault();
    }
}

