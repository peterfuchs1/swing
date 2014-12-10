package tictactoeV3;
/**
 * Tic-Tac-Toe mit gezeichneten Buttons,
 * welche sich an die Größe und Form
 * des Frames anpassen
 * 
 * @author Walter Rafeiner-Magor
 * @version 3
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyController {
	
	// Konstanten

		// Background für alle Komponenten
	public final int ROWS=3;
		// Anzahl der Spieler
	public final int ANZ_SPIELER=2;
	public static final Spieler spieler=Spieler.KREUZ;
	public static final Spieler computer=Spieler.KREIS;
	
	
private MyPanel gui;
private MyFrame myFrame;
private MyModel model;
private MyActionListener myAL;
private MyInitialListener myIL;
private boolean computerAktiv;
	



/**
 * 
 */
public MyController() {
	myAL=new MyActionListener();	// neuer ActionListener für JButtons
	myIL=new MyInitialListener();	// neuer ActionListener für "nochmal"

	model=new MyModel(ROWS);
	gui=new MyPanel(this,model);	// neuer MyPanel
	myFrame=new MyFrame(gui,"Tic - Tac - Toe");		// neues MyFrame
	initialisieren();
}

public void initialisieren(){
	computerAktiv=Math.random()<0.5;
	if(computerAktiv)
		computerZug();
}

public static void main (String[] args){
	new MyController();
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
public void computerZug(){
	int index=-1;
	while(index<0){
		int value=(int)(Math.random()*ROWS*ROWS);
		if(gui.available(value))
			index=value;
	}
	auswerten(index);
}
public void auswerten(int index){
	Spieler sp=(computerAktiv)?computer:spieler;
	gui.buttonSetzen(index,sp);				// Button setzen, Zeichen ermitteln
	model.setGewinn(index, sp);						// GewinnMatrix setzen
	
	if(!gui.spielAuswerten(sp)){						// Spiel auswerten
		computerAktiv=!computerAktiv;
		int aktSpieler=gui.getAktSpieler();			// Falls noch nicht beendet
		gui.setAktSpieler((aktSpieler==0)?1:0);		// Spieler wechseln und anzeigen	
		gui.spielerAnzeige();						
		model.initErgebnis();
		if(computerAktiv)
			computerZug();
	}
	
	
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
			int index=Integer.parseInt(spZug);		// Umrechnung in Indexwert
			gui.setAktSpieler((computerAktiv)?0:1);		// Spieler wechseln und anzeigen			
			auswerten(index);
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
			model.init();
			computerAktiv=!computerAktiv;
			if(computerAktiv)
				computerZug();
		}
		
	}	
}
