/**
 * 
 */
package nonogramm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfaches GUI-Game nach dem bekannten Kinderspiel LightsOff <br/>
 * Das Spiel soll möglichst allgemein und erweiterbar sein.<br/>
 * Zur einfachen Handhabung wird die Funktionalität<br/>
 * <br/>
 * in eine eigene Klasse ausgelagert (erbt von JButton): <br/>
 * Die Initalisierung erfolgt mit (derzeit) 3 Lichtern<br/>
 * <br/>
 * <br/>
 * Das GUI wird aus drei JPanels erstellt (oben, mitte, unten) <li>obenPanel:
 * GridLayout mit 1 Zeilen und 2 Spalten <li>mittePanel: GridLayout mit 5 Zeilen
 * und 5 Spalten <li>untenPanel: FlowLayout für den Neustart <br/>
 * <br/>
 * Das HauptPanel nimmt die der JPanels mittels BorderLayout auf <br/>
 * <br/>
 * 
 * 
 * @author Walter Rafeiner-Magor
 * 
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	// Konstanten
	// Background für alle Komponenten
	private static final Color MYCOLOR = Color.white;
	// Farbe bei Licht
	private static final Color COLOR_ON = Color.lightGray; // new
															// Color(200,240,240);
	// Farbe bei Dunkelheit
	private static final Color COLOR_OFF = Color.black;// new Color(00,255,255);
	// Anzahl der Buttonzeilen = Buttonspalten
	private static final int ROWS = 15;
	// max. Anzahl der Lichter bei Start
	private static final int ANZ_FIELDS_EASY=200;
	private static final int ANZ_FIELDS_MEDIUM=150;
	private static final int ANZ_FIELDS_HARD=125;
	private static final int ANZ_FIELDS_EXPERT=90;
	private static final int ANZ_FIELDS_IMPOSSIBLE=50;
	private int anzFieldsChoosen;
	private static final int[] ratingValues={ANZ_FIELDS_EASY,ANZ_FIELDS_MEDIUM,
		ANZ_FIELDS_HARD,ANZ_FIELDS_EXPERT,ANZ_FIELDS_IMPOSSIBLE};
	private static String[] ratings={"Easy","Medium","Hard","Expert","Impossible"};

	// GUI-Elemente
	// 1. Zeile
	private JLabel jlSpieler; // Textfeld: Fields on
	private JTextField jtfSpieler; // Ausgabefeld für den Spielstand
	private JComboBox jcb;			//Auswahl der Schwierigkeit
	// 2-6. Zeile
	private Fields[] jb; // Button für die 225 Elemente

	// 7. Zeile
	private JButton init; // Button für einen Neustart
	private JButton solve;// Button für die Lösung

	// Panel rechts

	private JTextField jtfRows[][];
	private JTextField jtfCols[][];
	// Spiel schon beendet
	boolean beendet;

	// Actionlistener

	private MyInitialListener myIL; // ActionListener für den Neustart
	private MyMouseListener myML; // MouseListener für JToggleButtons
	private MyComboBoxListener myCL; // ActionListener für die ComboBox
	// Attribute allgemein

	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Arrays erstellen

		jb = new Fields[ROWS * ROWS]; // Button-Array anlegen
		Fields.setLines(ROWS); // Anzahl der Reihen festgelegt
		Fields.setColSum(); // Column-Feld erstellen
		Fields.setRowSum(); // Row-Feld erstellen


		// ////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 1 Zeile und 2 Spalten
		// Zeile 1
		JPanel obenPanel=new JPanel(new BorderLayout(4,4));
		JPanel obenMittePanel = new JPanel(new GridLayout((ROWS / 2 + 1), ROWS+ROWS/2+1, 2, 2));

		this.jtfCols = new JTextField[ROWS / 2 + 1][ROWS+ROWS/2+1];
		for (int i = 0; i < jtfCols.length; i++) {
			for (int j = 0; j < jtfCols[0].length; j++) {
				this.jtfCols[i][j] = new JTextField("0");
				if (j < ROWS) {
					this.jtfCols[i][j] = new JTextField("0");
					this.jtfCols[i][j].setEditable(false);
				} 
				else {
					this.jtfCols[i][j].setVisible(false);
					this.jtfCols[i][j].setBackground(MYCOLOR);				
				}

				
				obenMittePanel.add(jtfCols[i][j]);
			}
		}
		obenPanel.add(BorderLayout.CENTER,obenMittePanel);
		obenPanel.setBackground(MYCOLOR);

		// ///////////////////////////////////////////////////////////
		// Panel mitte GridLayout
		// Zeile 2 - 6
		JPanel mittePanel=new JPanel(new BorderLayout(4,4));		
		JPanel mitteMittePanel = new JPanel(new GridLayout(ROWS, ROWS+ROWS/2+1, 2, 2));
		// 
		// Die JButtons werden noch erstellt

		this.jtfRows = new JTextField[ROWS][ROWS / 2 + 1];

		myML = new MyMouseListener(); // Mouselistener wird erstellt
		for (int i = 0, y = 0; y < ROWS; y++) {
			for (int x = 0; x < ROWS; x++) {
				this.jb[i] = new Fields(x, y); // Erstellen der Buttons
				this.jb[i].setToolTipText("Feld [" + (y + 1) + "/" 
						+ (x + 1)+ "] spielen"); // und gleichnamigen ToolTip hinzufügen
				this.jb[i].addMouseListener(myML);
				mitteMittePanel.add(this.jb[i]);
				i++; // dem Panel hinzufügen
			}
			// Summen rechts hinzufügen
			for (int j = 0; j < jtfRows[y].length; j++) {
				this.jtfRows[y][j] = new JTextField("0");
				this.jtfRows[y][j].setEditable(false);
				mitteMittePanel.add(jtfRows[y][j]);
			}

		}

		mittePanel.setBackground(MYCOLOR); // Globale Farbe verwenden
		mittePanel.add(BorderLayout.CENTER,mitteMittePanel);

		// ///////////////////////////////////////////////////////////
		// Panel unten mit FLowLayout anlegen
		// Zeile 6
		JPanel untenPanel = new JPanel(new GridLayout(1, 4, 4, 4));

		jlSpieler = new JLabel("Felder offen:");
		untenPanel.add(jlSpieler);
		this.jtfSpieler = new JTextField("Spiel läuft");
		this.jtfSpieler.setHorizontalAlignment(JTextField.CENTER);
		this.jtfSpieler.setEditable(false);
		untenPanel.add(jtfSpieler);
		untenPanel.setBackground(MYCOLOR); // Globale Farbe verwenden

		myIL = new MyInitialListener();
		solve= new JButton("Lösung");
		solve.addActionListener(myIL);
		init = new JButton("Neustart");
		init.addActionListener(myIL);
		untenPanel.add(solve);
		//JComboBox hinzufügen
		myCL=new MyComboBoxListener();
		jcb=new JComboBox(ratings);
		jcb.setSelectedIndex(1);
		jcb.addActionListener(myCL);
		untenPanel.add(jcb);
		anzFieldsChoosen=ratingValues[jcb.getSelectedIndex()];
		untenPanel.add(init);
		untenPanel.setBackground(MYCOLOR);

		/////////////////////////////////////////////////////////////
		// Spielinhalte initialisieren
		this.initialisieren();

		// /////////////////////////////////////////////////////////////7/
		// Main Panel BorderLayout mit gaps zwischen allen Komponenten
		this.setLayout(new BorderLayout(4, 4));
		this.add(BorderLayout.NORTH, obenPanel);
		this.add(BorderLayout.CENTER, mittePanel);
		this.add(BorderLayout.SOUTH, untenPanel);
		this.setBackground(MYCOLOR); // Globale Farbe verwenden
		// 10 Pixel Rand lassen
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	/**
	 * Anfangszustand der Buttons herstellen Spieler
	 * 
	 */
	private void initialisieren() {
		beendet = false;    //Spiel ist noch nicht zu Ende!

		for (int i = 0; i < jb.length; i++) {
			// Buttons regenerieren
			this.jb[i].setMarked(false);		// kein Button mit rechter 
			this.jb[i].setSelected(false);		// oder linker Taste gedrückt
			this.jb[i].setEnabled(true);
			this.jb[i].setValue(false);            	//Alle Felder sind null
			this.jb[i].setToggleBackground();	// Hintergrund auf Ausgangszustand
		}
		// Zufällige Felder auswählen
		int on;
		Random r = new Random();
		while (Fields.fieldsMarked() < anzFieldsChoosen) {
			on = (int) (r.nextDouble() * (ROWS * ROWS));
			this.jb[on].setValue(true);
		}
		// Summen zurücksetzen und neu bilden
		Fields.clearAllSum();
		Fields.makeColSum();
		Fields.makeRowSum();
		// Summen in Spalten und Zeilen anzeigen
		for (int i = 0; i < jtfCols.length; i++)
			for (int j = 0; j < ROWS; j++) {
				this.jtfCols[i][j].setText("" + Fields.colSum[j][i]);
				this.jtfRows[j][i].setText("" + Fields.rowSum[j][i]);
			}

		// Textfield: initialisieren
		this.jlSpieler.setText("Felder offen:");
		this.jtfSpieler.setText("" + anzFieldsChoosen);
	}
	
	/**
	 * Löse das Spiel:
	 * So einfach war die Lösung ;-)
	 */
	private void solving(){
		for (int i=0;i<jb.length;i++)
			if(jb[i].getValue())
				jb[i].setSelected(true);
	}
	/**
	 * Das aktuelle Spiel wird ausgewertet
	 * 
	 * @return true spiel beendet
	 */
	private boolean spielAuswerten() {
		// Gibt es schon einen Sieger?
		int felderOffen = anzFieldsChoosen - Fields.openFields();
		beendet = (felderOffen == 0) ? true : false;

		this.jtfSpieler.setText("" + felderOffen);
		// Sieger eintragen
		if (beendet) {
			this.jlSpieler.setText("Game over!");

			// alle Buttons sperren
			for (int i = 0; i < jb.length; i++)
				jb[i].setEnabled(false);
		}
		return beendet;
	}

	/**
	 * MouseListener für die JButtons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!beendet) {
				Fields spZug = (Fields) e.getSource(); // Aktueller Button
				//Falls rechte Maustaste
				if ((e.getModifiers() & InputEvent.BUTTON3_MASK) 
						== InputEvent.BUTTON3_MASK) {

					spZug.markedToggle();
				} else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) 
						== InputEvent.BUTTON1_MASK){
					spZug.switchToggle(); // JToggle wurde betätigt

				}
				spielAuswerten(); // Spiel auswerten
			}
		}
	}

	/**
	 * Actionlistener für den Neustart
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 * 
	 */
	private class MyInitialListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton f=(JButton) e.getSource();
			if (f==init) 	// Init-Button
				initialisieren();
			else { 			// Löungs-Button
				solving();
				spielAuswerten(); // Spiel auswerten
			}
		}
	}
	/**
	 * Klasse zur Einstellung des Schwierigkeitsgrades
	 * @author Walter RAfeiner-Magor
	 *
	 */
	private class MyComboBoxListener implements ActionListener	{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox j=(JComboBox)e.getSource();
			// neuer Schwierigkeitsgrad wird gesetzt
			anzFieldsChoosen=ratingValues[j.getSelectedIndex()];
		}
	}
}
