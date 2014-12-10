/**
 * 
 */
package Werteliste;
import max.Util;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Einfache GUI-Applikation als Test der selbsterstellten
 * generischen Methoden (max, min, random)
 * <br/>
 * Die GUI läßt die Eingabe beliebig vieler alphanumerischer Werte zu.<br/>
 * Der Aufbau der GUI besteht aus<br/><br/>
 * 
 * <li> Wert: Eingabefeld
 * <li> Anzahl: Ausgabefeld 
 * <li> Maximum: Ausgabefeld
 * <li> Manimum: Ausgabefeld
 * <li> Zufall: Ausgabefeld
 * <br/>
 * Die Applikation kann mittels Button ("Beenden")<br/>
 * bzw. durch Schließen des Fensters beendet werden.
 * 
 * 
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel{
	// Konstanten
		// Background für alle Komponenten
	private static final Color myColor=Color.LIGHT_GRAY;
		// Gewinn-Matrix mit folgendem Format:
	// 1. Zeile	
	private JLabel jlWert;				// Wert
	private JTextField jtfWert;			// Eingabefeld dazu
	// 2. Zeile
	private JLabel jlAnzahl;			// Anzahl derzeit
	private JTextField jtfAnzahl;		// Ausgabefeld für die derzeit vorhandenen Werte
	// 3. Zeile
	private JLabel jlMaximum;			// Maximum derzeit
	private JTextField jtfMaximum;		// Ausgabefeld dazu
	// 4. Zeile
	private JLabel jlMinimum;			// Maximum derzeit
	private JTextField jtfMinimum;		// Ausgabefeld dazu
	// 5. Zeile
	private JLabel jlZufall;			// Maximum derzeit
	private JTextField jtfZufall;		// Ausgabefeld dazu
	// 6. Zeile
	private JButton jbEnde;				// Button für das Beenden

	// Actionlistener + Keyadapter!
	private MyListener myL;
	
	// Speicher
	private LinkedList<String> speicher=new LinkedList<String>();
	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// 
		myL=new MyListener();									// Actionlistener+KeyAdapter wird erstellt
		
		//////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 5 Zeilen und 2 Spalten
		// Zeile 1
		JPanel mittePanel=new JPanel(new GridLayout(5,2,4,4));
		// Label: Wert
		this.jlWert=new JLabel("Wert");
		mittePanel.add(jlWert);
		// Textfield Wert
		jtfWert=new JTextField("");
		jtfWert.addKeyListener(myL);
		jtfWert.setToolTipText("Bitte einen Wert eingeben!");
		mittePanel.add(jtfWert);
		// Label: Anzahl
		this.jlAnzahl=new JLabel("Anzahl");
		mittePanel.add(jlAnzahl);
		// Textfield Anzahl
		jtfAnzahl=new JTextField("0");
		jtfAnzahl.setToolTipText("Anzahl der eingebenen Werte");
		jtfAnzahl.setEditable(false);
		mittePanel.add(jtfAnzahl);
		// Label Maximum
		this.jlMaximum=new JLabel("Maximum");
		mittePanel.add(jlMaximum);
		// Textfield Maximum
		jtfMaximum=new JTextField();
		jtfMaximum.setToolTipText("Maximum der eingebenen Werte");
		jtfMaximum.setEditable(false);
		mittePanel.add(jtfMaximum);
		// Label Minimum
		this.jlMinimum=new JLabel("Minimum");
		mittePanel.add(jlMinimum);
		// Textfield Minimum
		jtfMinimum=new JTextField();
		jtfMinimum.setToolTipText("Minimum der eingebenen Werte");
		jtfMinimum.setEditable(false);
		mittePanel.add(jtfMinimum);
		// Label Zufall
		this.jlZufall=new JLabel("Zufall");
		mittePanel.add(jlZufall);
		// Textfield Minimum
		jtfZufall=new JTextField();
		jtfZufall.setToolTipText("Zufallswert der eingebenen Werte");
		jtfZufall.setEditable(false);
		mittePanel.add(jtfZufall);
		//
		mittePanel.setBackground(myColor);		
		/////////////////////////////////////////////////////////////
		// Panel mitte FlowLayout
		// Zeile 6
		JPanel untenPanel=new JPanel(new FlowLayout());
		
		untenPanel.setBackground(myColor);
		// Die JButtons werden noch erstellt
		jbEnde=new JButton("Beenden");
		jbEnde.setToolTipText("Das Programm wird verlassen");	// und gleichnamigen ToolTip
		jbEnde.addActionListener(myL);					// ActionListener hinzufügen
		untenPanel.add(jbEnde);							// dem Panel hinzufügen
		
		///////////////////////////////////////////////////////////////7/
		// Main Panel BorderLayout mit gaps zwischen allen Komponenten
		this.setLayout(new BorderLayout(2,2));
		this.add(BorderLayout.CENTER,mittePanel);
		this.add(BorderLayout.SOUTH,untenPanel);
		this.setBackground(myColor);
		// 10 Pixel Rand lassen
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	/**
	 * Der GUI wird mit neuen Werten dargestellt
	 * 
	 * @param s der neue Wert
	 */
	private void actualGUI(String s) {
		speicher.add(s); // neuen Eintrag hinzufügen
		String sArray[]=speicher.toArray(new String[1]); //???????????
		jtfAnzahl.setText(""+speicher.size());
		jtfMaximum.setText(Util.max(sArray));
		jtfMinimum.setText(Util.min(sArray));
		jtfZufall.setText(Util.random(sArray));
		jtfWert.setText("");
		this.repaint();
	}
/**
 * MyListener
 * 
 * @author Walter Rafeiner-Magor
 *
 */
	private class MyListener extends KeyAdapter implements ActionListener{
/**
 * Enter-Taste im Eingabefeld auswerten
 */
	@Override
		public void keyTyped(KeyEvent e){
			char c=e.getKeyChar();
			String s="";
			if(c==KeyEvent.VK_ENTER ) 
				s=jtfWert.getText();
			//Falls tatsächlich ein Wert eingegeben
			// und mittels ENTER bestätigt wurde, 
			// macht die Aktualisierung des GUI's Sinn!
			if (!s.equals("")) actualGUI(s);
		}
		/**
		 * Ende-Button
		 * auswerten 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
}
