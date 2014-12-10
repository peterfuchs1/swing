/**
 * 
 */
package schere_papier_stein;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfaches GUI-Game nach dem bekannten Kinderspiel
 * Schere - Papier - Stein
 * <br/>
 * Das Spiel soll möglichst allgemein und erweiterbar sein.<br/>
 * Die möglichen Werte sind in einer Konstante: WERTE gespeichert<br/><br/>
 * Die Gewinnmatrix ist in einer Map mit folgendem Format gespeichert:
 * <li> key: SpielerZug+ComputerZug
 * <li> value: <b>true</b> (Spieler siegt oder Unentschieden) 
 * <li> value <b>false</b> (Computer hat gewonnen)
 * <br/>
 * Die Gewinnmatrix wird einmal initialisiert (mit true)<br/>
 * Die negativen Spiele werden manuell korrigiert
 * <br/><br/>
 * Das GUI wird aus drei JPanels erstellt (oben, mitte, unten)
 * <li>obenPanel:	GridLayout mit 2 Zeilen und 3 Spalten
 * <li>mittePanel:	FlowLayout
 * <li>untenPanel:	GridLayout mit 2 Zeilen und 3 Spalten
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
	private static final String WERTE[]={"Schere","Papier","Stein"};
		// Background für alle Komponenten
	private static final Color myColor=Color.LIGHT_GRAY;
		// Gewinn-Matrix mit folgendem Format:
	private static final TreeMap<String, Boolean> gewinn=new TreeMap<String, Boolean>();
	// 1. Zeile	
	private JLabel jlSpieler;			// Spieler
	private JLabel jlComputer;			// Computer
	// 2. Zeile
	private JTextField jtfSpieler;		// Ausgabefeld für den Zug des Spielers
	private JTextField jtfComputer;		// Ausgabefeld für den Zug des Computers
	private JLabel jlZuletzt;			// letzte Züge
	// 3. Zeile
	private JTextField jtfErgebnis;		// Wer hat gewonnen?
	// 4. Zeile
	private JTextField jtfStatusSp;		// Wieviele Spiele hat der Spieler gewonnen?
	private JTextField jtfStatusCp;		// Wieviele Spiele hat der Computer gewonnen?
	private JLabel jlStand;				// aktueller Stand
	// 5. Zeile
	private JButton[] jb;				// Button für Schere, Papier, Stein

	// Actionlistener
	private MyActionLister myAL;		// ActionListener für die 3 JButtons
	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Konstante initialisieren
		for (int i=0;i<WERTE.length;i++)
			for (int j=0; j< WERTE.length;j++) {
				String s=WERTE[i]+WERTE[j];
				gewinn.put(s, true);
			}
		// manell die verlorenen Spiele setzen
		String s=WERTE[1]+WERTE[0];gewinn.put(s,false); // Schere schneidet Papier
		s=WERTE[0]+WERTE[2];gewinn.put(s,false);		// Stein ist stärker als Schere
		s=WERTE[2]+WERTE[1];gewinn.put(s,false);		// Stein verliert gegen Papier
		
		
		//////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 2 Zeilen und 3 Spalten
		// Zeile 1
		JPanel obenPanel=new JPanel(new GridLayout(2,3,4,4));
		// Label: Spieler
		this.jlSpieler=new JLabel("Spieler");
		obenPanel.add(jlSpieler);
		// Dummy-Label
		JLabel dummy=new JLabel();
		obenPanel.add(dummy);
		// Label: Computer
		this.jlComputer=new JLabel("Computer");
		obenPanel.add(jlComputer);
		// Zeile 2
		// Letzte Spielzug des Spielers
		this.jtfSpieler=new JTextField("noch kein Zug");
		this.jtfSpieler.setHorizontalAlignment(JTextField.CENTER);
		this.jtfSpieler.setEditable(false);
		obenPanel.add(jtfSpieler);
		// Label: Spielzug
		this.jlZuletzt=new JLabel("letzter Zug");
		this.jlZuletzt.setHorizontalAlignment(JTextField.CENTER);
		obenPanel.add(jlZuletzt);
		// Letzter Spielzug des Computers
		this.jtfComputer=new JTextField("noch kein Zug");
		this.jtfComputer.setHorizontalAlignment(JTextField.CENTER);
		this.jtfComputer.setEditable(false);
		obenPanel.add(jtfComputer);
		obenPanel.setBackground(myColor);
		/////////////////////////////////////////////////////////////
		// Panel mitte FlowLayout
		// Zeile 3
		JPanel mittePanel= new JPanel();
		// Ergebniszeile erstellen
		this.jtfErgebnis=new JTextField(20);
		this.jtfErgebnis.setText("kein Spiel");
		this.jtfErgebnis.setHorizontalAlignment(JTextField.CENTER);
		this.jtfErgebnis.setEditable(false);
		mittePanel.add(jtfErgebnis);
		mittePanel.setBackground(myColor);
		//////////////////////////////////////////////////////////////////////
		// Panel unten GridLayout mit 2 Zeilen und 3 Spalten
		// Zeile 4
		JPanel untenPanel=new JPanel(new GridLayout(2,3,4,4));
		// Status des Spielers
		this.jtfStatusSp=new JTextField("0");
		this.jtfStatusSp.setEditable(false);
		untenPanel.add(jtfStatusSp);
		// Label
		this.jlStand=new JLabel("Stand");
		this.jlStand.setHorizontalAlignment(JTextField.CENTER);
		untenPanel.add(jlStand);
		// Status des Somputers
		this.jtfStatusCp=new JTextField("0");
		this.jtfStatusCp.setEditable(false);
		untenPanel.add(jtfStatusCp);
		untenPanel.setBackground(myColor);
		// Zeile 5
		// Die JButtons werden noch erstellt
		myAL=new MyActionLister();							// Actionlistener wird erstellt
		jb=new JButton[WERTE.length];
		for (int i=0;i<WERTE.length;i++){
			this.jb[i]=new JButton(WERTE[i]); 				// inklusive Text
			this.jb[i].setToolTipText(WERTE[i]+" spielen");	// und gleichnamigen ToolTip
			jb[i].addActionListener(myAL);					// ActionListener hinzufügen
			untenPanel.add(jb[i]);							// dem Panel hinzufügen
		}
		///////////////////////////////////////////////////////////////7/
		// Main Panel BorderLayout mit gaps zwischen allen Komponenten
		this.setLayout(new BorderLayout(2,2));
		this.add(BorderLayout.NORTH,obenPanel);
		this.add(BorderLayout.CENTER,mittePanel);
		this.add(BorderLayout.SOUTH,untenPanel);
		this.setBackground(myColor);
		// 10 Pixel Rand lassen
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	/**
	 * Der Spieler hat gewonnen!
	 * <li>Ergebnis ausgeben 
	 * <li>Status des Spielers um 1 erhöhen
	 */
	private void spielerSiegt() {
		this.jtfErgebnis.setText("Du hast gewonnen!");
		int i=Integer.parseInt(jtfStatusSp.getText())+1;
		jtfStatusSp.setText(""+i);
		
	}
	/**
	 * Unentschieden!
	 * <li>Ergebnis ausgeben
	 * <li>Status des Computers um 1 erhöhen 
	 * <li>Status des Spielers um 1 erhöhen
	 */
	private void keinSieger() {
		this.jtfErgebnis.setText("Unentschieden!");
		int i=Integer.parseInt(jtfStatusCp.getText())+1;
		jtfStatusCp.setText(""+i);
		i=Integer.parseInt(jtfStatusSp.getText())+1;
		jtfStatusSp.setText(""+i);
		
	}
	/**
	 * Der Computer hat gewonnen!
	 * <li>Ergebnis ausgeben
	 * <li>Status des Computers um 1 erhöhen 
	 * 
	 */
	private void computerSiegt() {
		this.jtfErgebnis.setText("Der Computer siegt!");
		int i=Integer.parseInt(jtfStatusCp.getText())+1;
		jtfStatusCp.setText(""+i);
		
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * durch Abgleich mit der TreeMap gewinn
	 * @param spZug Das hat der SPieler gewählt
	 * @param cpZug So hat der Computer gewählt
	 */
	private void spielAuswerten(String spZug, String cpZug) {
		// SPielzug anzeigen
		this.jtfComputer.setText(cpZug);
		this.jtfSpieler.setText(spZug);
		String key=spZug+cpZug;
		boolean spieler=gewinn.get(key);
		
		// Unentschieden
		if(spZug.equals(cpZug)) {
			keinSieger();
		}
		// Spieler siegt
		else if (spieler) 
				spielerSiegt();
		// Computer siegt
			else
				computerSiegt();
			
	}
	/**
	 * ActionListener für die JButtons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class MyActionLister implements ActionListener {
		/**
		 * Spielerzug per JButtons und Computerzug per random
		 * auswerten 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String spZug=e.getActionCommand();
			// Computerzug
			String cpZug=WERTE[(int)(Math.random()*(double)WERTE.length)];
			// Falls der key vorhanden, den Spielzug auswerten
			if(gewinn.containsKey(spZug+cpZug))
				spielAuswerten(spZug,cpZug);
			
		}
		
	}
	
}
