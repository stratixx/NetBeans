package peertopeerjavafx.View;

import peertopeerjavafx.View.WaitView.*;
import peertopeerjavafx.View.TalkView.*;
import peertopeerjavafx.View.EndView.*;
import peertopeerjavafx.View.StartView.StartViewController;
import java.io.IOException;
import javafx.stage.Stage;
import peertopeerjavafx.Controller.ControllerViewInterface;
import peertopeerjavafx.Controller.ViewInterface;
import peertopeerjavafx.Tools.Connection;
import peertopeerjavafx.View.StartView.StartViewCallbacks;




public class View implements ViewInterface
{
    /** 
     * Referencja do aktywnego kontrolera
     */
    private ControllerViewInterface controller;
    
        
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
     * @throws java.io.IOException
     */
    @Override
    public void launch( Stage PrimaryStage ) throws IOException{  
        
        StartViewCallbacks startViewCallbacks = new StartViewCallbacks() {
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
        
        WaitViewCallbacks waitViewCallbacks = new WaitViewCallbacks() {
            @Override
            public void buttonClicked() {
                if( controller.isConnectionOK() ) { talkView.show(); waitView.hide(); startView.hide(); }
                else { waitView.hide(); controller.stopConnection(); }
            }

            @Override
            public void onClose() {
                waitView.hide();
                controller.stopConnection();
            }
        };
        
        TalkViewCallbacks talkViewCallbacks = new TalkViewCallbacks() {
            @Override
            public void buttonSendClicked() {
                System.out.println("rucham_psa_jak_sra.msg");
            }

            @Override
            public void onClose() {
                //talkView.hide();
                endView.setStatusDefault();
                endView.show();
                controller.stopConnection();
            }
        };
                
        EndViewCallbacks endViewCallbacks = new EndViewCallbacks() {
            @Override
            public void buttonOKClicked() { talkView.hide(); endView.hide(); startView.show(); }
            
            @Override
            public void onClose() 
            { 
                controller.stopConnection(); 
                talkView.hide(); endView.hide(); startView.show(); 
            }
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
    public void showConnectionEnd()
    {
        endView.setStatusEnd();
        waitView.setStatusEnd();
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
        endView.setStatusDefault();
    }
}

