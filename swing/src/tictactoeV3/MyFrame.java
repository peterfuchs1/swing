package tictactoeV3;

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
 * @version 2.1
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {

//	private MyPanel game;		// Panel unten

	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame(MyPanel game, String title) {
		// festlegung der �berschrift im Frame 
		this.setTitle(title);
		// Standardoperation f�r das Schlie�en des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3 Panels werden erzeugt

		// Die erstellten Panels werden im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,game);
		// Der Frame wird gepackt
//		this.pack();
		this.setSize(480, 640);
		// Gr��e fixieren
//		this.setResizable(false);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
