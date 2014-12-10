package tictactoeV2;

//import gui2.MyFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	
	// Konstanten
	public static final char WERTE[]={'X','O'};
		// Background für alle Komponenten
	public static final int ROWS=3;
		// Anzahl der Spieler
	public static final int ANZ_SPIELER=2;

	
	
private MyPanel gui;
private MyFrame myFrame;
private Model model;
private MyActionListener myAL;
private MyInitialListener myIL;

	



/**
 * 
 */
public Controller() {
	myAL=new MyActionListener();	// neuer ActionListener für JButtons
	myIL=new MyInitialListener();	// neuer ActionListener für "nochmal"

	model=new Model(ROWS);
	gui=new MyPanel(this,model);	// neuer MyPanel
	myFrame=new MyFrame(gui);		// neues MyFrame
	
}

public static void main (String[] args){
	new Controller();
}

/**
 * @return the myAL
 */
public MyActionListener getMyAL() {
	return myAL;
}

/**
 * @return the myIL
 */
public MyInitialListener getMyIL() {
	return myIL;
}

/**
	 * ActionListener für die JButtons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class MyActionListener implements ActionListener {
		/**
		 * Spielerzug erfolgt per JButtons 
		 * anschließend wird das Ergebnis
		 * ausgewertet 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String spZug=e.getActionCommand();		// Aktueller Button
			int index=Integer.parseInt(spZug)-1;		// Umrechnung in Indexwert
			char c=gui.buttonSetzen(index);				// Button setzen, Zeichen ermitteln
			model.setGewinn(index, c);						// GewinnMatrix setzen
			
			if(!gui.spielAuswerten(c)){						// Spiel auswerten
				int aktSpieler=gui.getAktSpieler();			// Falls noch nicht beendet
				gui.setAktSpieler((aktSpieler==0)?1:0);		// Spieler wechseln und anzeigen	
				gui.spielerAnzeige();						
			}
			
		}
		
	}
	/**
	 * Actionlistener für den JButton "nochmal"
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
