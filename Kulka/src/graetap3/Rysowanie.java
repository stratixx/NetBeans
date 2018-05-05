package graetap3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** klasa , kt�ra pozwala na rysowanie element�w gry */
public class Rysowanie extends JPanel implements ActionListener{
    private Mapa map;
    private Gracz player;
    
    private double peroid; // czestot. odświezania ekranu gry
    private Timer timer; // timer załatwiajacy odświeżanie
    private int scale;
    
    Konfiguracja konfiguracja = new Konfiguracja();
    //Rysowanie mapy gry
    Rysowanie()
    {  
        scale = 1;
        setSize(konfiguracja.width*scale, konfiguracja.height*scale);
        setVisible(false);
        map = new Mapa("mapa",(int)scale);
        System.out.println(map.getUnscaledHeight());
        System.out.println(map.getUnscaledWidth());
        player = new Gracz(map);
        peroid = 1000/50; // 1000ms przez 50Hz


    }
    
    public void start()
    {
        setSize(konfiguracja.width*scale, konfiguracja.height*scale);
        setVisible(true);
        timer = new Timer((int)peroid, this);
        timer.setInitialDelay(0);
        timer.start();
    }
    
    public void stop()
    {
        timer.stop();
    }
    
    private void update(){
        player.update();
    }

    /** funkcja, kt�a rysuje gracza i mape */
    public void paint ( Graphics g ) {
       Insets b = getInsets();
       g.translate (b.left, b.top);
       map.draw(g);
       player.draw(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        
        this.repaint();
    }
    
    public void setScale(int newScale)
    {
        scale = newScale;
        map.setTileSize(newScale);
    }


}
