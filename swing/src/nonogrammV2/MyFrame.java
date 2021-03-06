package nonogrammV2;

import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Swing-Applikation: GUI-Spiel
 * Klasse erbt von JFrame
 * Verwendung des Standard-Layout-Manager (Border-Layout)
 * 1 Panel mit dem Spiel
 * Fixe Gr��e (pack)
 * 
 *  
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	private MyPanel game;		// Panel unten

	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame() {
		// festlegung der �berschrift im Frame 
		this.setTitle("Let's play Nonogramm!");
		// Standardoperation f�r das Schlie�en des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3 Panels werden erzeugt
		game=new MyPanel();
		// Die erstellten Panels werden im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,game);
		// Der Frame wird gepackt
//		this.pack();
		// Der Frame bekommt die Anfangsgr��e
		this.setSize(800, 800);
		// Gr��e fixieren
//		this.setResizable(false);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}
	public static void main (String[] argv){
		new MyFrame();
	}

}
