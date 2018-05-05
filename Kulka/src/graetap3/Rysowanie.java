package graetap3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** klasa , kt�ra pozwala na rysowanie element�w gry */
public class Rysowanie extends JPanel{
	private Mapa map;
	private Timer timer;
	private Gracz player;
	Konfiguracja konfiguracja = new Konfiguracja();
	//Rysowanie mapy gry
Rysowanie()
{  
 setSize(new Dimension(konfiguracja.width, konfiguracja.height));
 setVisible(false);
 map = new Mapa("mapa",4);
 player = new Gracz(map);
 
 
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


}
