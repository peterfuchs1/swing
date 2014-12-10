/**
 * 
 */
package jtextarea;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * 2. Swing-Applikation
 * Klasse erbt von JFrame
 * Verwendung des Standard-Layout-Manager (Border-Layout)
 * 1 Panel mit JTextField und JTextArea (JScrollBar) erm�glicht das Markieren, Editieren 
 * und L�schen von einfachen Texten  
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	private	MyPanel mta; 	// Panel mit TextArea

	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame() {
		// festlegung der �berschrift im Frame 
		super("TextArea");
		// Standardoperation f�r das Schlie�en des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		// Erstellen eines Panels mit TextArea
		mta=new MyPanel();
		// Der erstellte Panel wird im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,mta);
		// Gr��e des Frames wird auf 300 x 300 Pixel gestellt
		this.setSize(300, 300);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
