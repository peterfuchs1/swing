/**
 * 
 */
package jpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

import jpanel.MyPanel;

/**
 * 1. Swing-Applikation
 * Klasse erbt von JFrame
 * Verwendung des Standard-Layout-Manager (Border-Layout)
 * 3 Panels mit jeweils 2 Buttons, welche die Hintergrundfarbe entweder erheller oder verdunkeln
 *  
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	private MyPanel mpSouth;		// Panel unten
	private MyPanel mpNorth;		// Panel oben
	private MyPanel mpCenter;		// Panel im Zentrum

	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame() {
		// festlegung der Überschrift im Frame 
		super("Hintergrundfarbe");
		// Standardoperation für das Schließen des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 3 Panels werden erzeugt
		mpSouth=new MyPanel(Color.white);
		mpNorth=new MyPanel(Color.green);
		mpCenter=new MyPanel(Color.yellow);
		// Die erstellten Panels werden im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.SOUTH, mpSouth);
		this.getContentPane().add(BorderLayout.NORTH,mpNorth);
		this.getContentPane().add(BorderLayout.CENTER,mpCenter);
		// Größe des Frames wird auf 300 x 300 Pixel gestellt
		this.setSize(300, 300);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
