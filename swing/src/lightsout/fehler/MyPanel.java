/**
 * 
 */
package lightsout.fehler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
	
	// GUI-Elemente
	// 1. Zeile	
	private JLabel jlSpieler;			// Textfeld: Fields on 
	private JTextField jtfSpieler;		// Ausgabefeld für den Spielstand 
	// 2-6. Zeile
	private Lights[] jb;				// Button für die 25 Elemente
	// 7. Zeile
	private JButton init;				// Button für einen Neustart

	/**
	 * Konstruktor
	 */
	public MyPanel(MyController mc) {
		// Arrays erstellen
		
		// Button-Array anlegen
		jb=new Lights[MyController.ROWS*MyController.ROWS];
		// Anzahl der Reihen festgelegt
		Lights.setLines(MyController.ROWS);											

		
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

		for (int i=0, y=0;y<MyController.ROWS;y++){
			for (int x=0;x<MyController.ROWS;x++){
				// Erstellen der Buttons
				this.jb[i]=new Lights(x,y,false,COLOR_ON,COLOR_OFF);
				// und gleichnamigen ToolTip
				
				// ActionListener hinzufügen
				
				// dem Panel hinzufügen
				
				i++;											
			}
		}
		
		mittePanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		
		/////////////////////////////////////////////////////////////
		// Panel unten mit FLowLayout anlegen
		// Zeile 6
		JPanel untenPanel= new JPanel();
		init=new JButton("Neustart");
		
		untenPanel.setBackground(MYCOLOR);
				
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
	 * @return the jb
	 */
	public Lights getJb(int i) {
		return jb[i];
	}
	/**
	 * Ermittelt die Groesse des jb-Arrays
	 * @return Laenge des jb-Arrays
	 */
	public int getJbLength(){
		return jb.length;
	}
	

	/**
	 * @return the jlSpieler
	 */
	public JLabel getJlSpieler() {
		return jlSpieler;
	}

	/**
	 * @return the jtfSpieler
	 */
	public JTextField getJtfSpieler() {
		return jtfSpieler;
	}

	/**
	 * Anzeige des aktuellen Spiels
	 * für den wartenden Spieler wird "Spiel läuft" 
	 * mit den noch zu bearbeitenden Lichtern angezeigt
	 */
	public void spielerAnzeige() {
		this.jlSpieler.setText("Spiel läuft");
		this.jtfSpieler.setText(""+Lights.lightsOn());

	}
}
