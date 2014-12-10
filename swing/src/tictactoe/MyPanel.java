/**
 * 
 */
package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfaches GUI-Game nach dem bekannten Kinderspiel
 * Tic - Tac - Toe
 * <br/>
 * Das Spiel soll möglichst allgemein und erweiterbar sein.<br/>
 * Die möglichen Werte sind in einer Konstante: WERTE gespeichert<br/><br/>
 * Die Gewinnmatrix ist in einem Array mit folgendem Format gespeichert:
 * <br/>
 * Die Gewinnmatrix wird einmal initialisiert (mit ' ')<br/>
 * <br/><br/>
 * Das GUI wird aus drei JPanels erstellt (oben, mitte, unten)
 * <li>obenPanel:	GridLayout mit 2 Zeilen und 3 Spalten
 * <li>mittePanel:	GridLayout mit 3 Zeilen und 3 Spalten
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
	private static final char WERTE[]={'X','O'};
		// Background für alle Komponenten
	private static final Color MYCOLOR=Color.yellow;
		// Anzahl der Buttonzeilen = Buttonspalten
	private static final int ROWS=3;
		// Anzahl der Spieler
	private static final int ANZ_SPIELER=2;
	
	// GUI-Elemente
	// 1. Zeile	
	private JLabel[] jlSpieler;			// Spieler
	private JLabel	 jlStand;			// Spielstand "0:0"
	// 2. Zeile
	private JTextField[] jtfSpieler;	// Ausgabefeld für den Spielstand der Spieler
	// 3-5. Zeile
	private JButton[] jb;				// Button für die 9 Elemente
	// 6. Zeile
	private JButton init;				// Button für einen Neustart
	
	// Actionlistener
	private MyActionListener myAL;		// ActionListener für die JButtons
	private MyInitialListener myIL;		// ActionListener für den Neustart

	// Attribute allgemein
	private char[] gewinn;				// Matrix aller Feldelemente 
	private int aktSpieler;				// Welcher Spieler ist gerade an der Reihe?
	private int[] siege;				// Anzahl der Siege je Spieler

	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Arrays erstellen
		
		gewinn=new char[ROWS*ROWS];			// Gewinn-Array anlegen
		jb=new JButton[ROWS*ROWS];			// Button-Array anlegen
		siege=new int[2];					// Siege-Array anlegen
		for(int i:siege) siege[i]=0;		// Es beginnt bei 0:0!
		//////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 2 Zeilen und 3 Spalten
		// Zeile 1
		
		JPanel obenPanel=new JPanel(new GridLayout(2,3,4,4));
		
		jlSpieler=new JLabel[ANZ_SPIELER];
		// Label: Spieler
		for (int i=0;i<ANZ_SPIELER;i++) {
			if( i==1) { //Spielstand anlegen
				jlStand=new JLabel("0:0");
				jlStand.setHorizontalAlignment(JLabel.CENTER);
				obenPanel.add(jlStand);	
			}
			this.jlSpieler[i]=new JLabel("Spieler "+(i+1));
			obenPanel.add(jlSpieler[i]);
		}
		// Zeile 2
		jtfSpieler=new JTextField[ANZ_SPIELER];
		// Textfield: Spieler	
		for (int i=0;i<ANZ_SPIELER;i++) {
			if( i==1) { //Dummy einfügen
				JLabel dummy=new JLabel();
				obenPanel.add(dummy);	
			}
			this.jtfSpieler[i]=new JTextField("Spiel läuft");
			this.jtfSpieler[i].setHorizontalAlignment(JTextField.CENTER);
			this.jtfSpieler[i].setEditable(false);
			obenPanel.add(jtfSpieler[i]);
		}
		obenPanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		/////////////////////////////////////////////////////////////
		// Panel mitte GridLayout
		// Zeile 3 - 5
		JPanel mittePanel= new JPanel(new GridLayout(3,3,4,4));
		// 
		// Die JButtons werden noch erstellt
		myAL=new MyActionListener();							// Actionlistener wird erstellt

		for (int i=0;i<jb.length;i++){
			this.jb[i]=new JButton(""+(i+1));				// Erstellen der Buttons inkl. Text
			this.jb[i].setToolTipText(""+(i+1)+". Feld spielen");	// und gleichnamigen ToolTip
			jb[i].addActionListener(myAL);					// ActionListener hinzufügen
			mittePanel.add(jb[i]);							// dem Panel hinzufügen
		}
		mittePanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		
		/////////////////////////////////////////////////////////////
		// Panel unten mit FLowLayout anlegen
		// Zeile 6
		myIL= new MyInitialListener();
		JPanel untenPanel= new JPanel();
		init=new JButton("nochmal");
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
	 * Testet obder Spieler mit dem Zeichen c
	 * bereits eine Gewinnerkonstellation erreicht hat
	 *  
	 * @param c Zeichen des Spielers
	 * @return hat der Spieler bereits gewonnen?
	 */
	private boolean sieger(char c) {
		boolean gewonnen=false;
		//Diagonale
		if (gewinn[0]==c && gewinn[0]==gewinn[4] && gewinn[0]==gewinn[8]) gewonnen=true;
		if (gewinn[2]==c && gewinn[2]==gewinn[4] && gewinn[2]==gewinn[6]) gewonnen=true;
		for (int i=0; !gewonnen && i<ROWS;i++) {
			int a=i*3; //Aktueller Zeile errechnen
			//Horizontaler Sieg
			if (gewinn[a]==c && gewinn[a]==gewinn[a+1]&& gewinn[a]==gewinn[a+2]) gewonnen=true; 
			//Vertikaler Sieg
			if (gewinn[i]==c && gewinn[i]==gewinn[i+3]&& gewinn[i]==gewinn[i+6]) gewonnen=true; 
		}
		
		return gewonnen;
	}
	/**
	 * Anzeige des aktuellen Spielers
	 * für den wartenden Spieler wird "Spiel läuft" angezeigt
	 * für den Spieler der an der Reihe ist wird "zieht" angezeigt
	 */
	private void spielerAnzeige() {
		for (int i=0;i<ANZ_SPIELER;i++) 
			jtfSpieler[i].setText("Spiel läuft");
		jtfSpieler[aktSpieler].setText("zieht");

	}
	/**
	 * Anfangszustand der Buttons und des gewinn-arrays herstellen
	 * Spieler wird ausgelost!
	 */
	private void initialisieren(){
		
		for (int i=0;i<jb.length;i++){
			this.jb[i].setText(""+(i+1));				// Buttons regenerieren
			this.jb[i].setEnabled(true);				
			gewinn[i]=' ';								// gewinn-Array leeren
		}
			
		aktSpieler=(int) Math.round(Math.random());		// Spieler wird ausgewählt beginnt
		this.spielerAnzeige();							// Anzeige aktualisieren
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * durch Abgleich mit dem Array gewinn
	 * @param c Zeichen des aktuellen Spielers
	 * @return true spiel beendet
	 */
	private boolean spielAuswerten(char c) {
		// Gibt es schon einen Sieger?
		boolean beendet=sieger(c);
		// Sieger und Verlierer eintragen
		if (beendet) { 
			for (int i=0;i<ANZ_SPIELER;i++)
				jtfSpieler[i].setText("verliert");
			jtfSpieler[aktSpieler].setText("siegt!");
			siege[aktSpieler]++;
		
		// alle Buttons sperren
			for (int i=0;i<jb.length;i++)
				jb[i].setEnabled(false);
		}
		else // wirklich noch kein Sieger?
		{
			beendet=true;
			for (int i=0;beendet && i<gewinn.length;i++)
				// Falls noch ein Feld nicht besetzt
				 if (gewinn[i]==' ') beendet=false; 
					
			if(beendet)  // Remis
				for (int i=0;i<ANZ_SPIELER;i++){
					jtfSpieler[i].setText("remis");
					siege[i]++;
				}
			
		}
		// Spielstand neu ausgeben
		if(beendet){
			StringBuilder sb=new StringBuilder();
			sb.append(siege[0]).append(":").append(siege[1]);
			jlStand.setText(sb.toString());
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
		 * Spielerzug erfolgt per JButtons 
		 * anschließend wird das Ergebnis
		 * ausgewertet 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String spZug=e.getActionCommand();		// Aktueller Button
			int b=Integer.parseInt(spZug)-1;		// Umrechnung in Indexwert
			char c=WERTE[aktSpieler];				// Zeichen des Spielers
			gewinn[b]=c;
			jb[b].setText(""+c);					// Wert setzen "O" oder "X"
			jb[b].setEnabled(false);				// Button disablen

			if(!spielAuswerten(c)){						// Spiel auswerten
				aktSpieler=(aktSpieler==0)?1:0;			// Falls noch nicht beendet
				spielerAnzeige();						// Spieler wechseln und anzeigen
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
			initialisieren();
		}
		
	}
}
