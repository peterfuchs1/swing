package sudoko;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Swing-Applikation: GUI-Spiel
 * Klasse erbt von JFrame
 * Verwendung des Standard-Layout-Manager (Border-Layout)
 * 1 Panel mit dem Spiel
 * Fixe Größe (pack)
 * 
 *  
 * @author Walter Rafeiner-Magor
 * @version 2.1
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyFrame(){
		this(new JPanel());
	}
	/**
	 * Konstruktur mit einem Parameter
	 * @param game JPanel
	 */
	public MyFrame(JPanel game){
		this(game,"MyFrame");
	}
	/**
	 * Kontruktor mit zwei Parameter
	 * @param game JPanel
	 * @param titel Titel
	 */
	public MyFrame(JPanel game,String titel) {
		// festlegung der Überschrift im Frame 
		this.setTitle(titel);
		// Standardoperation für das Schließen des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Der erstellte Panel wird im ContentPane des Frames platziert
		this.getContentPane().add(BorderLayout.CENTER,game);
		// Der Frame wird gepackt
		this.setSize(500, 700);
		// Größe fixieren
		this.setResizable(false);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		this.setVisible(true);
	}

}
