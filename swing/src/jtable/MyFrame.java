/**
 * 
 */
package jtable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;



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

	private MyPanel mpTable;		// Panel mit einer Table

	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame() {
		// Festlegung der Überschrift und Hintergrundfarbe im Frame  
		super("Einfache Tabelle");
		this.setBackground(Color.LIGHT_GRAY);
		// Standardoperation für das Schließen des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Panels werden erzeugt
		mpTable=new MyPanel();
		// Die erstellten Panels werden im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,mpTable);
		// Größe des Frames wird auf 300 x 300 Pixel gestellt
		//		this.setSize(300, 300);
		// Größe auf Minimum reduzieren
		this.pack();
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
