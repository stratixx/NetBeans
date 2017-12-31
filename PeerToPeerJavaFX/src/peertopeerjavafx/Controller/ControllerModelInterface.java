package peertopeerjavafx.Controller;


/**
 * funkcje komunikujące Model z Controller
 * @author Skrzatt
 */
public interface ControllerModelInterface {
    
    
    /**
     * 
     */
    public void showConnectionEnd();
    
    /**
     * 
     */
    public void showConnectionOK();
    
    /**
     * 
     */
    public void showConnectionFAIL();
    
    /**
     * 
     */
    public void showConnectionDefault();
    
    public void showText( String text );
    
    public void clearOutput();
}
