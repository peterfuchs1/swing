package sudoko;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	// ANZAHL DER QUADRATE
	// Durch Festlegung der Anzahl der Quadrate (pro Zeile) ist
	// die Groesse des Sudokos festgelegt.
	public static final int ANZAHL_QUADRATE = 16;
	// laut MVC gui
	private MyPanel gui;
	// und Controller
	private MyInitialListener myIL;

	/**
	 * Standardkonstruktor
	 */
	public Controller() {
		myIL = new MyInitialListener(); // neuer ActionListener für "nochmal"
		// neuer MyPanel
		gui = new MyPanel(this,ANZAHL_QUADRATE);
		// neues MyFrame
		new MyFrame(gui,"Sudoko for beginner's");
	}
	/**
	 * Startmethode
	 * @param args
	 */
	public static void main(String[] args) {
		new Controller();
	}
	/**
	 * @return the myIL
	 */
	public MyInitialListener getMyIL() {
		return myIL;
	}

	/**
	 * Actionlistener für den JButton "nochmal"
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 * 
	 */
	private class MyInitialListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.initialisieren();
		}
	}
}
