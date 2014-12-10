/**
 * 
 */
package sudoko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Einfaches Sudoko-Panel
 * 
 * <br/>
 * Jedes Panel enthaelt x Sudoko-Quadraten, welche im Controller festgelegt werden
 * Jedes Sudoko innerhalb des Panels verwendet den Sudoko-Observer<br/><br/>
 * <br/>
 * Fuer jedes Sudoko-Quadrat werden folgende Abhaengigkeiten gespeichert<br/>
 * 
 * <li>Alle Sudoko-Quadrate in der selben Zeile
 * <li>Alle Sudoko-Quadrate in der selben Spalte
 * <br/><br/>
 * Aenderungen in abhängigen Sudokos:
 * <br/>
 * <li>neuer, geaenderter oder geloeschter Wert</li>
 * -> valueChanged()
 * <br/>

 * <br/><br/>
 * Struktur:
 * <li>Panel oben: fuer Informationen zum Spiel
 * <li>Panel mitte: enthaelt die Sudoko-Quadrate
 * <li>Panel unten: enthaelt den Button fuer den Neustart
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
	private static final Color MYCOLOR=Color.yellow;
		// GUI-Elemente
	// 1. Zeile	
	private JLabel zeile1;
	// 2. Zeile
	private JLabel zeile2;
	// 3-x. Zeile
	private Sudoko[] sudoko;			// Jedes Quadrat ist ein eigenes Sudoku
	// x+1. Zeile
	private JButton init;				// Button für einen Neustart
 
	/**
	 * Konstruktor
	 */
	public MyPanel(Controller controller, int sumOfSudokos) {
		// Die Anzahl der Sudoko-Quadrate pro Zeile
		// errechnet sich als Wurzel aus der Gesamtzahl
		int sumOfRows=(int)Math.sqrt(sumOfSudokos);

		// Arrays erstellen
		
		sudoko=new Sudoko[sumOfSudokos];			// Sudoku-Array anlegen
		//////////////////////////////////////////////////////////////////
		// Panel oben GridLayout mit 2 Zeilen und 1 Spalten
		// Enthaelt Informationen ueber das Spiel
		// Zeile 1
		
		JPanel obenPanel=new JPanel(new GridLayout(2,1,4,4));
		zeile1=new JLabel("Bitte im Quadrat, Zeile und Spalte jede Zahlen nur einmal verwenden");
		zeile1.setHorizontalAlignment(JLabel.CENTER);
		// Zeile 2
		zeile2=new JLabel("Fehlerhafte Eingaben werden rot hinterlegt!");
		zeile2.setHorizontalAlignment(JLabel.CENTER);
		// Zeilen hinzufuegen
		obenPanel.add(zeile1);	
		obenPanel.add(zeile2);
		obenPanel.setBackground(MYCOLOR);  //Globale Farbe verwenden
		/////////////////////////////////////////////////////////////
		// Panel mitte GridLayout
		// Enthaelt die Sudoko-Quadrate
		// Zeilen: Anzahl abhaengig von Gesamtzahl  
		JPanel mittePanel= new JPanel(new GridLayout(sumOfRows,sumOfRows,4,4));
		// 
		// Die Sudoko-Quadrate werden noch erstellt

		for (int i=0;i<sudoko.length;i++){
			this.sudoko[i]=new Sudoko(sumOfSudokos);				
			mittePanel.add(sudoko[i]);							// dem Panel hinzufügen
		}
		for (int i=0;i<sudoko.length;i++){
			int myCol=i%sumOfRows;
			int myRow=i/sumOfRows*sumOfRows;
			// Abhaenigkeiten beim Observer anmelden:
			// Alle Sudokus in der selben Spalte
			for(int j=myCol;j<sudoko.length;j+=sumOfRows){
				if(i==j) continue; //Das sind wir selber!!
				sudoko[i].addObserver(sudoko[j]);
			}
			// Alle Sudokus in der selben Zeile
			for(int j=myRow;j<myRow+sumOfRows;j++){
				if(i==j) continue;//Das sind wir selber!!
				sudoko[i].addObserver(sudoko[j]);
			}
			
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
		// Feldinhalte initialisieren
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
	 * Anfangszustand des sudoko-arrays herstellen
	 * 
	 */
	public void initialisieren(){
		
		for (int i=0;i<sudoko.length;i++){
			this.sudoko[i].init();				// Sudoko-Quadrate regenerieren
		}

	}
	


}
