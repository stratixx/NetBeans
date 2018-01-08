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

/**
 *
 * @author Konrad Winnicki
 */
public class View implements ViewInterface
{
    // referencja na obiekt kontrolera
    private ControllerViewInterface controller;    
    // obiekt okna startowego
    private StartViewController startView;
    // obiekt okna popup oczekiwania
    private WaitViewController waitView;
    // obiekt okna rozmowy
    private TalkViewController talkView;
    // obiekt okna popup zakończenia połączenia
    private EndViewController endView;
        
    /**
     * Ustawienie referencji na kontroler
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerViewInterface controller){
        this.controller = controller;        
    }
    
    /**
     * Inicjacja widoku
     * @param PrimaryStage głowne okno aplikacji
     * @throws java.io.IOException
     */
    @Override
    public void launch( Stage PrimaryStage ) throws IOException{  
        
        // Callbacki widoku startowego
        StartViewCallbacks startViewCallbacks = new StartViewCallbacks() {
            @Override
            public void buttonClicked( Connection connect ) {
                waitView.showView(startView.getX(), startView.getY());
                controller.startConnection(connect);
            }
        };
        
        // Callbacki widoku oczekiwania
        WaitViewCallbacks waitViewCallbacks = new WaitViewCallbacks() {
            @Override
            public void buttonClicked() {
                if( controller.isConnectionOK() ) { talkView.showView(startView.getX(), startView.getY()); waitView.hide(); startView.hide(); }
                else { controller.stopConnection(); waitView.hide(); }
            }

            @Override
            public void onClose() {
                controller.stopConnection();
                waitView.hide();
            }
        };
        
        // Callbacki widoku rozmowy
        TalkViewCallbacks talkViewCallbacks = new TalkViewCallbacks() {
            @Override
            public void buttonSendClicked() {
                if( controller.isConnectionOK() )
                {
                    String inputText = talkView.getInputText();
                    
                    if(!inputText.equals(""))
                    {
                        talkView.setOutputText("Ty: "+inputText);
                        controller.sendText( inputText );
                    }
                }
                else
                    System.out.println(".buttonSendClicked() connection is not ok");
                    //controller.showConnectionEnd();
            }

            @Override
            public void onClose() {
                controller.stopConnection();
                endView.showView(talkView.getX(), talkView.getY());
            }
        };
           
        // Callbacki widoku końcowego
        EndViewCallbacks endViewCallbacks = new EndViewCallbacks() {
            @Override
            public void confirmAction() 
            { 
                talkView.hide(); endView.hide(); startView.showView(talkView.getX(), talkView.getY()); 
            }
        };
        
        // Utworzenie okien widoków
        startView = new StartViewController(startViewCallbacks);
        waitView = new WaitViewController( startView, waitViewCallbacks );
        talkView = new TalkViewController(talkViewCallbacks);
        endView = new EndViewController( talkView, endViewCallbacks );        
        
        startView.show();
    }
    
    /**
     * Pobranie referencji na okno popup oczekiwania
     * @return referencja na okno popup oczekiwania
     */
    @Override
    public WaitViewController getWaitView()
    {
        return waitView;
    }
    
    /**
     * Pobranie referencji na okno popup zakończenia połączenia
     * @return Referencja na okno popup zakończenia połączenia
     */
    @Override
    public EndViewController getEndView()
    {
        return endView;
    }
    
    /**
     * Pobranie referencji na okno rozmowy
     * @return Referencja na okno rozmowy
     */
    @Override
    public TalkViewController getTalkView()
    {
        return talkView;
    }
    
    /**
     * Pobranie referencji na okno startowe
     * @return Referencja na okno startowe
     */
    @Override
    public StartViewController getStartView()
    {
        return startView;
    }    
}