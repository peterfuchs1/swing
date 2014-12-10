/**
 * 
 */
package bandit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfaches GUI-Game nach dem bekannten Spiel Einhaendiger Bandit <br/>
 * Das Spiel soll möglichst allgemein und erweiterbar sein.<br/>
 * Die möglichen Werte sind in einer Konstante: WERTE gespeichert<br/>
 * <br/>
 * Die Gewinnmatrix ist in einer Map mit folgendem Format gespeichert: <li>key:
 * SpielerZug+ComputerZug <li>value: <b>true</b> (Spieler siegt oder
 * Unentschieden) <li>value <b>false</b> (Computer hat gewonnen) <br/>
 * Die Gewinnmatrix wird einmal initialisiert (mit true)<br/>
 * Die negativen Spiele werden manuell korrigiert <br/>
 * <br/>
 * Das GUI wird aus drei JPanels erstellt (oben, mitte, unten) <li>obenPanel:
 * GridLayout mit 2 Zeilen und 3 Spalten <li>mittePanel: FlowLayout <li>
 * untenPanel: GridLayout mit 2 Zeilen und 3 Spalten <br/>
 * <br/>
 * Das HauptPanel nimmt die der JPanels mittels BorderLayout auf <br/>
 * <br/>
 * 
 * 
 * @author Walter Rafeiner-Magor
 * 
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel implements SObserver {
	// 1. Zeile
	private JLabel[] jlBezeichnung; // 3 Bezeichnung
	// 2. Zeile
	private JTextField[] jtfOutput; // aktuelle Werte
	// 3.-4. Zeile
	private JLabel[] jlStatus; 		// Ueberschrift zu Anzahl der Treffer
	private JTextField[] jtfStatus; // Anzahl der Treffer: Wieoft wurde was
	// 5. Zeile
	private JButton jbStart; 		// Button zum Start einer Runde bzw. zur
	private JButton jbEnde;			// Button fuer Beendigung
	private JButton jbNeuStart; 	// Button fuer Neustart des Spiels
	/////////////////////////////////////////////////
	// Attribute
	private Bandit[] banditen; 		// Die BAnditen
	private int[] values; // Die Werte je Bandit
	private boolean allFinished;	// Alle Werte bereits berechnet?
	// Actionlistener
	
	private MyStartListener mySL; 	// ACtionListener für Start-Button
	private MyEndeListener myEL; 	// ACtionListener für Ende-Button
	private MyNeuStartListener myNSL; // Actionlistener fuer Neustart
	/////////////////////////////////////////////////
	//Konstanten
	// Background für alle Komponenten
	private static final Color myColor = Color.LIGHT_GRAY;
	public static int ANZAHL = 3; 	// Anzahl der BAnditen
	public static String NAME="Bandit"; // Basisname des Wertes
										// Die Werte werden durchnummeriert
	/**
	 * Konstruktor
	 * Standardgemaess mit 3 Spalten
	 * und dem Namen "Bandit
	 */
	public MyPanel() {
		this(ANZAHL,"Bandit");
	}
	/**
	 * Konstruktor
	 * @param anzahl der Spalten
	 * @param name	Basisname eines Wertes
	 * 				erster Wert startet mit "NAME "1,....
	 */
	public MyPanel(int anzahl,String name) {
		ANZAHL=anzahl;
		NAME=name;
		// Attribute initialisieren
		jlBezeichnung = new JLabel[ANZAHL]; // Banditenueberschrift
		jtfOutput = new JTextField[ANZAHL]; // Werte der BAnditen darstellen
		jtfStatus = new JTextField[ANZAHL]; // Summe der Treffer
		jlStatus = new JLabel[ANZAHL]; // Ueberschrift zu Summe der Treffer
		allFinished=false;				// noch nicht alle Werte berechnet
		// ////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 2 Zeilen und 3 Spalten
		// Zeile 1
		JPanel obenPanel = new JPanel(new GridLayout(2, ANZAHL, 4, 4));
		// Label : BAnditen
		for (int i = 0; i < ANZAHL; i++) {
			jlBezeichnung[i] = new JLabel(NAME+" " + (i + 1));
			jlBezeichnung[i].setHorizontalAlignment(JLabel.CENTER);
			obenPanel.add(jlBezeichnung[i]);
		}
		// Banditen-Output
		for (int i = 0; i < ANZAHL; i++) {
			jtfOutput[i] = new JTextField("0");
			jtfOutput[i].setHorizontalAlignment(JTextField.CENTER);
			jtfOutput[i].setEditable(false);
			obenPanel.add(jtfOutput[i]);

		}

		obenPanel.setBackground(myColor);
		// ///////////////////////////////////////////////////////////
		// Panel mitte GridLayout mit 2 Zeilen und 3 Spalten
		// Zeile 3
		JPanel mittePanel = new JPanel(new GridLayout(2, ANZAHL-1, 4, 4));
		// Ergebniszeile erstellen
		// Status Ueberschrift
		for (int i = 1; i < ANZAHL; i++) {

			this.jlStatus[i] = new JLabel("" + (i + 1) + "er");
			jlStatus[i].setHorizontalAlignment(JLabel.CENTER);
			mittePanel.add(jlStatus[i]);
		}
		// Zeile 4
		// Summe der Treffer initialisieren
		for (int i = 1; i < ANZAHL; i++) {

			this.jtfStatus[i] = new JTextField("0");
			this.jtfStatus[i].setEditable(false);
			this.jtfStatus[i].setHorizontalAlignment(JTextField.CENTER);
			mittePanel.add(jtfStatus[i]);
		}
		mittePanel.setBackground(myColor);
		// ////////////////////////////////////////////////////////////////////
		// Panel unten GridLayout mit 1 Zeilen und 3 Spalten
		// Zeile 5
		JPanel untenPanel = new JPanel(new GridLayout(1, 3, 4, 4));
		untenPanel.setBackground(myColor);
		// Die JButtons werden noch erstellt
		jbStart = new JButton("Weiter");
		jbEnde = new JButton("Ende");
		jbNeuStart = new JButton("Neustart");

		// Actionlistener werdn erstellt
		mySL = new MyStartListener();
		myEL = new MyEndeListener();
		myNSL = new MyNeuStartListener();

		jbStart.addActionListener(mySL);
		jbEnde.addActionListener(myEL);
		jbNeuStart.addActionListener(myNSL);
		// myAL=new MyActionLister();
		untenPanel.add(jbStart);
		untenPanel.add(jbEnde);
		untenPanel.add(jbNeuStart);
		// /////////////////////////////////////////////////////////////7/
		// Main Panel BorderLayout mit gaps zwischen allen Komponenten
		this.setLayout(new BorderLayout(2, 2));
		this.add(BorderLayout.NORTH, obenPanel);
		this.add(BorderLayout.CENTER, mittePanel);
		this.add(BorderLayout.SOUTH, untenPanel);
		this.setBackground(myColor);
		// 10 Pixel Rand lassen
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	/**
	 * Neue Runde Alle Banditen errechenen einen neuen Wert
	 */
	public void nextRound() {
		banditen = new Bandit[ANZAHL];
		values = new int[ANZAHL];
		for (int i = 0; i < ANZAHL; i++) {
			values[i] = 0;
			banditen[i] = new Bandit(i);
			banditen[i].addObserver(this);
			banditen[i].start();
		}
		// Warten, dass alle Banditen fertig sind
		jbSetEnable(false);
		allFinished=false;
	}

	/**
	 * Alle Statistik-Werte zuruecksetzen
	 */
	public void init() {
		// Wert fuer "eine" Uebereinstimmung
		// wird nicht verwendet
		for (int i = 1; i < ANZAHL; i++) {
			jtfStatus[i].setText("0");
			jtfOutput[i].setText("0");
		}
		jtfOutput[0].setText("0");
	}
	@Override
	/**
	 * Ein Bandit ist mit seiner Berechnung
	 * fertig 
	 */
	public void finished(int number,int value) {
		valueChanged(number, value);
		banditen[number]=null;
		allFinished=true;
		for(int i=0;i<ANZAHL;i++)
			if(banditen[i]!=null){
				allFinished=false;
				break;
			}
		// Wenn alle Werte der Banditen vorhanden sind,
		// kann die Berechnung erfolgen
		if( allFinished){ 
			statistikAktualisieren();
			jbSetEnable(true);
		}
	}
	
	public void jbSetEnable(boolean value){
		jbNeuStart.setEnabled(value);
		jbStart.setEnabled(value);
	}
	
	public void statistikAktualisieren(){
		int[] statistik = new int[ANZAHL];
		int[] aktuelleWerte = new int[ANZAHL];
		
		for (int i = 1; i < ANZAHL; i++) {
			statistik[i] = Integer.parseInt(jtfStatus[i].getText());
			aktuelleWerte[i] = Integer.parseInt(jtfOutput[i].getText());
		}
		// Ersten Wert holen
		aktuelleWerte[0]=Integer.parseInt(jtfOutput[0].getText());
		// Neue Treffer ermitteln
		TreeMap<Integer, Integer> map=new TreeMap<Integer,Integer>();
		for(int i=0;i<ANZAHL;i++){
			if(map.containsKey(aktuelleWerte[i]))
				map.put(aktuelleWerte[i], map.get(aktuelleWerte[i])+1);
			else
				map.put(aktuelleWerte[i], 1);
		}
		//		Die Anzahl durchsuchen
		Iterator<Integer> values=map.values().iterator();
		// Solange Werte vorhanden
		while (values.hasNext()){
			int value=values.next().intValue();
			// Uebereinstimmung groesser 1
			if(value>1)
				jtfStatus[value-1].setText(Integer.toString(statistik[value-1] + 1));
				
		}
		/*
		for (int i = ANZAHL - 1; i >= 1; i--) {
			// Uebereinstimmung von rechts
			if (aktuelleWerte[0] == aktuelleWerte[i]) {
				jtfStatus[i].setText(Integer.toString(statistik[i] + 1));
				break;
			}
			//Uebereinstimmung von links
			if (i<ANZAHL-1&&aktuelleWerte[ANZAHL-i-1] == aktuelleWerte[ANZAHL-1]) {
				jtfStatus[i].setText(Integer.toString(statistik[i] + 1));
				break;
			}

		}
	*/
	}

	/**
	 * Actionlistener für den Start-Button
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 1.0
	 * 
	 */
	private class MyStartListener implements ActionListener {
		/**
		 * Start-Button gedrueckt
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			nextRound();

		}

	}

	/**
	 * Actionlistener für den NeuStart-Button
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 1.0
	 * 
	 */
	private class MyNeuStartListener implements ActionListener {
		/**
		 * Start-Button gedrueckt
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			init();

		}

	}

	/**
	 * Actionlistener für den Ende-Button
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 1.0
	 * 
	 */
	private class MyEndeListener implements ActionListener {
		/**
		 * Ende-Button gedrueckt
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}

	@Override
	public void valueChanged(int number, int value) {
		values[number] = value;
		jtfOutput[number].setText("" + value);
		// this.repaint();
	}

}
