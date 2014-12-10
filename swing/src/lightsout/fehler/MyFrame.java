package lightsout.fehler;

import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Swing-Applikation: GUI-Spiel
 * Klasse erbt von JFrame
 * Verwendung des Standard-Layout-Manager (Border-Layout)
 * 1 Panel mit dem Spiel
 * Fixe Größe (pack)
 * 
 *  
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {

//	private MyPanel game;		// Panel unten

	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame(MyPanel game) {
		// festlegung der Überschrift im Frame 
		this.setTitle("Fields Out!");
		// Standardoperation für das Schließen des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3 Panels werden erzeugt
//		game=new MyPanel(game);
		// Die erstellten Panels werden im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,game);
		// Der Frame wird gepackt
//		this.pack();
		// Der Frame bekommt die Anfangsgröße
		this.setSize(400, 500);
		// Größe fixieren
		this.setResizable(false);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
