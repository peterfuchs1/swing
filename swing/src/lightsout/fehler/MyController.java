
package lightsout.fehler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * MyController
 * Controller laut MVC-Pattern
 * verknuepft GUI, Frame und Controller
 * Nimmt Events der Buttons entgegen
 * und steuert Spielablauf
 * 
 * @author Walter Rafeiner-Magor
 * @version 1.1
 *
 */
public class MyController implements ActionListener {
	// KONSTANTEN
	// Anzahl der Buttonzeilen = Buttonspalten
	public static final int ROWS=5;
	// max. Anzahl der Lichter bei Start
	public static final int ANZ_LIGHTS_ON=3;
	// ATTRIBUTE
	private MyPanel gui;	// MyPanel
	private MyFrame mf;		// MyFrame
	/**
	 * Konstruktor
	 * GUI wird mit Controller verknuepft
	 */
	MyController(){
		gui=new MyPanel(this); 
		mf=new MyFrame(gui);
		// Spiel wurde neu gestartet
		this.initialisieren();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof Lights) {
			// Es wurden Lights-Button gedrueckt
			Lights spZug=(Lights)e.getSource();				// Aktueller Button
			int x=spZug.getXCoord();						// Ermittlung der Koordinaten
			int y=spZug.getYCoord();						// 
			Lights.switchLight(x, y, true);					// Invertierung der Lichter
			

										// Spiel auswerten
				// Falls noch nicht beendet
		}
		else{
			// Es wurde Neu-Start gedrueckt
			
		}
	}
	/**
	 * SPiel initialisieren
	 */
	public void initialisieren(){
		for (int i=0;i<gui.getJbLength();i++){
			// Buttons regenerieren
			gui.getJb(i).setOn(false);
			gui.getJb(i).setEnabled(true);				
			gui.getJb(i).setToggleBackground();

		}
		// Textfield: Spieler	
		int on;
		Random r=new Random();
		// Zufällig Lights anstellen
		while(Lights.lightsOn()<ANZ_LIGHTS_ON){
			on=(int)(r.nextDouble()*(ROWS*ROWS));
			gui.getJb(on).toggleOn();
		} 
		// Spieleranzeige aktualisieren
		gui.spielerAnzeige();
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * Falls keine Lichter (Lights.lighton()==0)
	 * mehr leuchten, soll das Spiel beendet werden
	 * @return true spiel beendet
	 */
	private boolean spielAuswerten() {
		// Gibt es schon einen Sieger?
		int lichter=Lights.lightsOn();
		boolean beendet=true                       ;
		// Lichteranzahl anzeigen
		gui.getJtfSpieler().setText(""+lichter);
		// Sieger eintragen
		if (beendet) { 
			gui.getJlSpieler().setText("Game over!");
		
		// alle Buttons sperren

		}
		return beendet;
	}
	/**
	 * Start des Spiels
	 * @param args
	 */
	public static void main(String[] args) {
		// Main-Methode
		 new MyController();
	}
}
