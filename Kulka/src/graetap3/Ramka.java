package graetap3;

import javax.swing.JFrame;

/**glowny program, wywo³ywanie poszczególnych elementów, pojawienie siê menu i gry */

public class Ramka extends JFrame {

	public Ramka(){
		setSize(400,300);
		setTitle("KULKA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		Panel panel= new Panel();
		add(panel);
		panel.setVisible(true);
		//Rysowanie rys= new Rysowanie();
		//add(rys);
		//rys.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Ramka ramka = new Ramka();
		ramka.setVisible(true);
	}
}
