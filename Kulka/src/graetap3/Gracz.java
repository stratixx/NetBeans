package graetap3;

import java.awt.*;

/** lokalizacja gracza na planszy 
 * 
 * */

public class Gracz {
 
    private double x = 20;
    private double y = 20;
    private int width;
    private int height;

 
    private Mapa map;
 
    /**konstruktor gracza
     * 
     * @param _map
     */
    public Gracz(Mapa _map){
 
        map = _map;
        width = 20;
        height = 20;
    
    }
 
    public void update(){
    	
    }
  /**metoda kolorujaca gracza (kulke)
   * 
   * @param g
   */
    public void draw(Graphics g){
 
        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, width, height);
    }
 
}
 
