/**
 * 
 */
package lightsout2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfaches GUI-Game nach dem bekannten Kinderspiel
 * LightsOff
 * <br/>
 * Das Spiel soll möglichst allgemein und erweiterbar sein.<br/>
 * Zur einfachen Handhabung wird die Funktionalität<br/><br/>
 * in eine eigene Klasse ausgelagert (erbt von JButton):
 * <br/>
 * Die Initalisierung erfolgt mit (derzeit) 3 Lichtern<br/>
 * <br/><br/>
 * Das GUI wird aus drei JPanels erstellt (oben, mitte, unten)
 * <li>obenPanel:	GridLayout mit 1 Zeilen und 2 Spalten
 * <li>mittePanel:	GridLayout mit 5 Zeilen und 5 Spalten
 * <li>untenPanel:	FlowLayout für den Neustart
 * <br/><br/>
 * Das HauptPanel nimmt die der JPanels mittels BorderLayout auf
 * <br/><br/>
 * 
 * 
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel{
	// Konstanten
		// Background für alle Komponenten
	private static final Color MYCOLOR=Color.lightGray;
		//Farbe bei Licht
	private static final Color COLOR_ON=Color.yellow; //new Color(200,240,240);
		//Farbe bei Dunkelheit
	private static final Color COLOR_OFF=Color.black;//new Color(00,255,255);
		// Anzahl der Buttonzeilen = Buttonspalten
	private static final int ROWS=5;
		// max. Anzahl der Lichter bei Start
	private static final int ANZ_LIGHTS_ON=3;
	
	// GUI-Elemente
	// 1. Zeile	
	private JLabel jlSpieler;			// Textfeld: Fields on 
	private JTextField jtfSpieler;		// Ausgabefeld für den Spielstand 
	// 2-6. Zeile
	private Lights[] jb;				// Button für die 25 Elemente
	

	// 7. Zeile
	private JButton init;				// Button für einen Neustart
	
	// Actionlistener
	private MyActionListener myAL;		// ActionListener für die JButtons
	private MyInitialListener myIL;		// ActionListener für den Neustart

	// Attribute allgemein

	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Arrays erstellen
		

		jb=new Lights[ROWS*ROWS];										// Button-Array anlegen
		Lights.setLines(ROWS);											// Anzahl der Reihen festgelegt


		
		//////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 1 Zeile und 2 Spalten
		// Zeile 1
		
		JPanel obenPanel=new JPanel(new GridLayout(1,2,4,4));
		
		jlSpieler=new JLabel("Fields on:");
		obenPanel.add(jlSpieler);
		this.jtfSpieler=new JTextField("Spiel läuft");
		this.jtfSpieler.setHorizontalAlignment(JTextField.CENTER);
		this.jtfSpieler.setEditable(false);
		obenPanel.add(jtfSpieler);
		obenPanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		/////////////////////////////////////////////////////////////
		// Panel mitte GridLayout
		// Zeile 2 - 6
		JPanel mittePanel= new JPanel(new GridLayout(5,5,4,4));
		// 
		// Die JButtons werden noch erstellt
		myAL=new MyActionListener();										// Actionlistener wird erstellt

		for (int i=0, y=0;y<ROWS;y++){
			for (int x=0;x<ROWS;x++){
				this.jb[i]=new Lights(x,y,false,COLOR_ON,COLOR_OFF);								// Erstellen der Buttons
				this.jb[i].setToolTipText("Feld ["+(y+1)+"/"+(x+1)+"] spielen");	// und gleichnamigen ToolTip
				this.jb[i].addActionListener(myAL);							// ActionListener hinzufügen
				mittePanel.add(this.jb[i]);
				i++;											// dem Panel hinzufügen
			}
		}
		
		mittePanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		
		/////////////////////////////////////////////////////////////
		// Panel unten mit FLowLayout anlegen
		// Zeile 6
		myIL= new MyInitialListener();
		JPanel untenPanel= new JPanel();
		init=new JButton("Neustart");
		init.addActionListener(myIL);
		untenPanel.add(init);
		untenPanel.setBackground(MYCOLOR);
		
		/////////////////////////////////////////////////////////////
		// Felderinhalte initialisieren
		this.initialisieren();
		
		///////////////////////////////////////////////////////////////7/
		// Main Panel BorderLayout mit gaps zwischen allen Komponenten
		this.setLayout(new BorderLayout(4,4));
		this.add(BorderLayout.NORTH,obenPanel);
		this.add(BorderLayout.CENTER,mittePanel);
		this.add(BorderLayout.SOUTH,untenPanel);
		this.setBackground(MYCOLOR);	  //Globale Farbe verwenden
		// 10 Pixel Rand lassen
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	/**
	 * Anzeige des aktuellen Spiels
	 * für den wartenden Spieler wird "Spiel läuft" 
	 * mit den noch zu bearbeitenden Lichtern angezeigt
	 */
	private void spielerAnzeige() {
		this.jlSpieler.setText("Spiel läuft");
		this.jtfSpieler.setText(""+Lights.lightsOn());

	}
	/**
	 * Anfangszustand der Buttons und des gewinn-arrays herstellen
	 * Spieler 1 beginnt!
	 */
	private void initialisieren(){
		for (int i=0;i<jb.length;i++){
			// Buttons regenerieren
			this.jb[i].setOn(false);
			this.jb[i].setEnabled(true);				
			this.jb[i].setToggleBackground();
		}
		// Textfield: Spieler	

		int on;
		Random r=new Random();
		while(Lights.lightsOn()<ANZ_LIGHTS_ON){
			
			on=(int)(r.nextDouble()*(ROWS*ROWS));
			this.jb[on].toggleOn();
			
		} 
		
		this.spielerAnzeige();
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * 
	 * @return true spiel beendet
	 */
	private boolean spielAuswerten() {
		// Gibt es schon einen Sieger?
		int lichter=Lights.lightsOn();
		boolean beendet=(lichter==0)?true:false;
		
		this.jtfSpieler.setText(""+lichter);
		// Sieger eintragen
		if (beendet) { 
			this.jlSpieler.setText("Game over!");
		
		// alle Buttons sperren
			for (int i=0;i<jb.length;i++)
				jb[i].setEnabled(false);
		}
		return beendet;
	}
	/**
	 * ActionListener für die JButtons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class MyActionListener implements ActionListener {
		/**
		 * Spielerzug per JButtons und Computerzug per random
		 * auswerten 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Lights spZug=(Lights)e.getSource();				// Aktueller Button
			int x=spZug.getXCoord();						// Ermittlung der Koordinaten
			int y=spZug.getYCoord();						// 
			Lights.switchLight(x, y, true);					// Invertierung der Lichter
			

			if(!spielAuswerten()){							// Spiel auswerten
				// Falls noch nicht beendet
				spielerAnzeige();						
			}
			
		}
		
	}
	/**
	 * Actionlistener für den Neustart
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 *
	 */
	private class MyInitialListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			initialisieren();
		}
		
	}
}
