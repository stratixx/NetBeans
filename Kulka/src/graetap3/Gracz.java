package graetap3;

import java.awt.*;

/** lokalizacja gracza na planszy 
 * 
 * */

public class Gracz {
 
    private double x = 20.0;
    private double y = 20.0;
    private int width;
    private int height;
    
    private double velocity_x = 1.0;
    private double velocity_y = 0.0;
    private double acceleration_x = 0.0;
    private double acceleration_y = 0.05;
    
 
    private Mapa map;
 
    /**konstruktor gracza
     * 
     * @param _map
     */
    public Gracz(Mapa _map){
 
        map = _map;
        width = 10;
        height = 10;
    
    }
 
    public void update(){
    	x += velocity_x;
        y += velocity_y;
        velocity_x += acceleration_x;
        velocity_y += acceleration_y;
        
        if( x>(map.getUnscaledWidth()-width) )   x = map.getUnscaledWidth()-width;
        if( y>(map.getUnscaledHeight()-height) )  y = map.getUnscaledHeight()-height;
        if( x<width )     x = 0.0;
        if( y<height )     y = 0.0;
    }
  /**metoda kolorujaca gracza (kulke)
   * 
   * @param g
   */
    public void draw(Graphics g){
        int scale = map.getTileSize();
        g.setColor(Color.RED);
        g.fillOval((int)x*scale, (int)y*scale, width*scale, height*scale);
    }
 
}
 
