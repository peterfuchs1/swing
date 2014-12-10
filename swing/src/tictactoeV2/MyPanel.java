/**
 * 
 */
package tictactoeV2;

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
	
	private Controller controller;
	private Model model;
	// Konstanten
	private final char WERTE[];
		// Background für alle Komponenten
	private static final Color MYCOLOR=Color.yellow;
		// Anzahl der Buttonzeilen = Buttonspalten
	private final int ROWS;
		// Anzahl der Spieler
	private final int ANZ_SPIELER;
	
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
	

	// Attribute allgemein
 
	private int aktSpieler;				// Welcher Spieler ist gerade an der Reihe?
	private int[] siege;				// Anzahl der Siege je Spieler

	/**
	 * Konstruktor
	 */
	public MyPanel(Controller controller, Model model) {
		this.model=model;
		this.controller=controller;
		
		//Konstanten aus controller übernehmen
		WERTE=controller.WERTE;
		ROWS=controller.ROWS;
		ANZ_SPIELER=controller.ANZ_SPIELER;
		// Arrays erstellen
		
		jb=new JButton[ROWS*ROWS];			// Button-Array anlegen
		siege=new int[ANZ_SPIELER];					// Siege-Array anlegen
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
		ActionListener myAL=controller.getMyAL();			// Actionlistener wird erstellt

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
		ActionListener myIL= controller.getMyIL();
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
	 * Anzeige des aktuellen Spielers
	 * für den wartenden Spieler wird "Spiel läuft" angezeigt
	 * für den Spieler der an der Reihe ist wird "zieht" angezeigt
	 */
	public void spielerAnzeige() {
		for (int i=0;i<ANZ_SPIELER;i++) 
			jtfSpieler[i].setText("Spiel läuft");
		jtfSpieler[aktSpieler].setText("zieht");

	}
	/**
	 * Anfangszustand der Buttons und des gewinn-arrays herstellen
	 * Spieler wird ausgelost!
	 */
	public void initialisieren(){
		
		for (int i=0;i<jb.length;i++){
			this.jb[i].setText(""+(i+1));				// Buttons regenerieren
			this.jb[i].setEnabled(true);				
										
		}
		model.init();									// gewinn-Array leeren	
		aktSpieler=(int) Math.round(Math.random());		// Spieler wird ausgewählt beginnt
		this.spielerAnzeige();							// Anzeige aktualisieren
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * durch Abgleich mit dem Array gewinn
	 * @param c Zeichen des aktuellen Spielers
	 * @return true spiel beendet
	 */
	public boolean spielAuswerten(char c) {
		// Gibt es schon einen Sieger?
		boolean beendet=model.sieger(c);
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
		{	//Bereits alle Felder befüllt?
			beendet=model.full();
					
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
	 * JButton mit dem Zeichen des Spielers setzen
	 * @param index
	 * @return Zeichen des Spielers
	 */
	public char buttonSetzen(int index){
		char c=WERTE[aktSpieler];				// Zeichen des Spielers
		jb[index].setText(""+c);					// Wert setzen "O" oder "X"
		jb[index].setEnabled(false);				// Button disablen
		return c;
	}

	/**
	 * @return the aktSpieler
	 */
	public int getAktSpieler() {
		return aktSpieler;
	}
	/**
	 * @param aktSpieler the aktSpieler to set
	 */
	public void setAktSpieler(int aktSpieler) {
		this.aktSpieler = aktSpieler;
	}


}
