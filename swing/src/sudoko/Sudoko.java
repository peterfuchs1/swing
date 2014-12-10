/**
 * 
 */
package sudoko;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sudoko.observer.SObservable;
import sudoko.observer.SObserver;

/**
 * Zentrale Klasse
 * <li>Jedes Sudoko-Quadrat enthält eine Anzahl an Eingabefeldern,
 * welche im Controller festgelegt wurde 
 * <li>Jedes Sudoko-Quadrat bekommt eine ID, welche die Adresse
 * im Panel festlegt: <br/>zb.: 4 Quadrate ID=0(S1/Z1), ID=1(S2/Z1), ID=2(S1/Z1), ID=3(S2/Z2)
 * <li>Jedes Sudoko ist Observer und Observable für alle weiteren Sudokos
 * <li>Jedes Sudoko meldet sich bei betroffenen Sudokos an (gleiche Spalte oder gleiche Zeile) 
 * 
 * @author Walter Rafeiner-Magor
 * 
 */
public class Sudoko extends JPanel implements  SObserver, SObservable{
	// Klassenattribute
	private static final long serialVersionUID = 1L;
	private static int ID=0;
	private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	// Objektattribute
	private int sumOfEntries; // Anzahl der Eintraege
	private int id;	// Nummer des Sudokus
	private boolean[] possibleValues; // Werte
	private int sumOfRows; // Anzahl der Zeilen=Spalten
	private int prevValue;  // Vorheriger Wert

	// Jedes Sudoko enthaelt sumOfEntries Eingabefelder
	private MyTextField[] entries; // Eingabefelder
	
	private SudokuController sc; // My Listener (ActionListener + FocusAdapter
	private LinkedList<SObserver> observers; // Liste aller Beobachter
	/**
	 * Standardkonstruktor
	 */
	public Sudoko() {
		this(ID,9);
	}
	/**
	 * Konstruktor
	 * Falls keine Nummer angegeben wurde,
	 * kann eine StandardNummer verwendet werden
	 * @param anz
	 */
	public Sudoko(int anz){
		this(ID,anz);
	}
/**
 * Konstruktor
 * @param id	Nummer des neuen Sudokos
 * @param anz	Summe der Eintraege
 */
	public Sudoko(int id,int anz) {
		// Standard-Nummer erhoehen
		Sudoko.ID=id+1;
		this.id=id;
		this.sumOfEntries = anz;
		// Sudoko ist nur fuer Quadratwerte definiert!!
		this.sumOfRows = (int) Math.floor(Math.sqrt(anz));
		if (sumOfRows * sumOfRows != anz)
			throw new NumberFormatException("" + anz
					+ " is not a possible value in Sudoko!");
		possibleValues = new boolean[anz+1]; // Eingaben-Array erstellen
		entries = new MyTextField[anz]; // Eingabefelder-Array erstellen
		// Eingaben und Validierung initialisieren
		// Tabelle zeichnen
		sc = new SudokuController();
		this.observers=new LinkedList<SObserver>();
		this.setLayout(new GridLayout(sumOfRows, sumOfRows, 3, 3));
		int col,row;
		for (int i = 0; i < entries.length; i++) {
			// Berechnung der aktuellen Spalte im Sudoko-Quadrat
			col=i%sumOfRows;
			// Berechnung der aktuellen Zeile im Sudoko-Quadrat
			row=i/sumOfRows;
			// Neues Eingabefeld erstellen
			entries[i] = new MyTextField("",col,row);
			// Ein- und Austritt aus dem Eingabefeld beobachten
			this.entries[i].addFocusListener(sc);
			// Mauszeiger am Eingabefeld beobachten
			this.entries[i].addMouseListener(sc);
			// Eingabe zentrieren
			this.entries[i].setHorizontalAlignment(JTextField.CENTER);
			this.entries[i].setFont(BIGGER_FONT);
			// Eingabefeld zum Sudoko-Quadrat hinzufuegen
			this.add(entries[i]);

		}
		// Eingaben initialisieren
		this.init();

	}
	/**
	 * Ueberpruefung, ob der Werte bereits verwendet wurde Falls noch nicht,
	 * wird der Wert der Vergleichstabelle hinzugefuegt und true retourniert
	 * 
	 * @param a Eingabewert
	 * @return true oder false
	 */
	public boolean isValueAvailable(int a) {
		boolean ret = false;
		if (a > 0 && a <= sumOfEntries) {
			if (!possibleValues[a]) {
				ret=possibleValues[a]=true;
			
			}
		}
		return ret;
	}
	/**
	 * Wert wird wieder fuer die Eingabe
	 * moeglich
	 * @param a
	 */
	public void removeValue(int a) { 
		possibleValues[a]=false;
	}
	
	/**
	 * Eingabe und Eingabefelder initialisieren
	 */
	public void init() {
		String s="1-"+sumOfEntries;
		for (int i = 0; i < sumOfEntries; i++) {
			entries[i].setText("");
			possibleValues[i] = false;
			entries[i].setFailure(false);			
			this.entries[i].setToolTipText(s);
			this.entries[i].setError(0);
		}
		possibleValues[sumOfEntries]=false;
	}
	
	/**
	 * Textfeld im Array auslesen
	 * @param i index im Eingabefeld-Array
	 */
	public MyTextField getEntries(int i) {
		return entries[i];
	}	
	/**
	 * Pruefung des neuen Inhalts und Information an die
	 * Beobachter
	 * @param f
	 * @param prevValue
	 */
	private void checkInput(MyTextField f, int prevValue) {
		// Aktuellen Wert des Eingabefeldes ermitteln
		int a=f.getValue();
		// Falls Eingabe falsch oder bereits vorhanden
		// Eingabefeld loeschen
		if (a == 0 || !isValueAvailable(a))
			f.setText("");
		// Aenderung des Inhalts eines Eingabefeldes an die
		// Beobachter melden
		notifyObservers(this.id,f.getCol(),f.getRow(),f.getValue(),prevValue);
	}
	/**
	 * @author Walter Rafeiner-Magor
	 * @version 1.1
	 * 
	 * Fokus und Mauszeigereintritt jedes Textfields wird beobachtet
	 * 
	 */
	public class SudokuController extends MouseAdapter implements FocusListener{

		/**
		 * Falls das Eingabefeld verlassen wird soll die
		 * Eingabe geprueft und an die Beobachter weitergegeben werden
		 * @param e
		 */
		@Override
		public void focusLost(FocusEvent e) {
			if (e.isTemporary()) {
	            return;
	        }
			if(prevValue!=0) removeValue(prevValue);
			MyTextField f = (MyTextField) e.getSource();
			checkInput(f,prevValue);
		}
		/**
		 * Falls der Fokus fuer das TextFiels erhalten wurde
		 * soll der Wert gespeichert und
		 * wieder erlaubt werden
		 * Fehlerdarstellung wird ausgeschaltet
		 * @param e 
		 */
		@Override
		public void focusGained(FocusEvent e) {
			MyTextField f = (MyTextField) e.getSource();
			prevValue=f.getValue();
			f.setFailure(false);			
		}
		/**
		 * Falls der Mauszeiger in das TextField eingetreten ist
		 * @param e
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			MyTextField f=(MyTextField)e.getSource();
			setToolTip(f);
		}
	}
	/**
	 * Neuen Tooltip mit den Moeglichkeiten errechnen
	 * @param f
	 */
	public void setToolTip(MyTextField f){
		int a=f.getValue();
		StringBuilder s=new StringBuilder();
		for(int i=1;i<=sumOfEntries;i++){
			if(!possibleValues[i]) {
				s.append(i);
				s.append(";");
			}
		} 
		if(a!=0) s.append(a);
		f.setToolTipText(s.toString());
	}
	@Override
	/**
	 * Einen Beobachter hinzufügen
	 * 
	 */
	public void addObserver(SObserver o) {
		observers.add(o);
	}
	/**
	 * Einen Beobachter entfernen
	 */
	@Override
	public void deleteObserver(SObserver o) {
		int index=observers.indexOf(o);
		if(index >=0)
			observers.remove(index);
	}

	/**
	 * Informiere die Beobachter ueber Aenderungen eines Eingabefeldes
	 * @param id		ID des Sudoko-Quadrates
	 * @param col		Spalte des Eingabefeldes innerhalb des Soduko-Quadrates
	 * @param row		Zeile des Eingabefeldes innerhalb des Soduko-Quadrates
	 * @param value		neuer Wert des Eingabefeldes
	 * @param prevValue	voriger Wert des Eingabefeldes
	 */
	@Override
	public void notifyObservers(int id, int col, int row, int value,int prevValue) {
		// Alle Beobachter dieses Sudoko-Quadrates informieren
		Iterator<SObserver> iter=observers.iterator();
		while(iter.hasNext())
			iter.next().valueChanged(id, col,row, value,prevValue);
	}
	
	/**
	 *  Wir wurden ueber eine Aenderung eines abhaengigen Sudoko-Quadrates informiert!
	 * @param id		ID des Sudoko-Quadrates
	 * @param col		Spalte des Eingabefeldes innerhalb des Soduko-Quadrates
	 * @param row		Zeile des Eingabefeldes innerhalb des Soduko-Quadrates
	 * @param newValue	neuer Wert des Eingabefeldes
	 * @param prevValue	voriger Wert des Eingabefeldes
	 *  
	 */
	@Override
	public void valueChanged(int id, int col, int row, int newValue,int prevValue) {
		boolean valueIsNotNull=(newValue!=0)?true:false;
		// Falls voriger Wert==0 ist die Suche nach vorigem Fehler sinnlos!!
		boolean searchForPreviousErrors=(prevValue==0)?false:true; // Suche und entferne vorige Fehler
		boolean searchForErrors=true;	// Suche und setze aktuell Fehler
		int idRow=id/sumOfRows; // Zeile des geänderten Sudokus
		boolean sameRow=(idRow==this.id/sumOfRows)?true:false; // Wir sind in der selben Zeile
		boolean sameCol=!sameRow; //entweder Zeile oder Spalte betroffen
		// Korrektur der Anzeige nach der Eingabe es Wertes
		// Entweder wird die Zeile betrachtet
		if(sameRow){
			row*=sumOfRows;		// Row-Kordinate wird in Array-Index umgerechnet
			int grenze=row+this.sumOfRows; // Grenze fuer die Suche ermittelt
			 
			// Solange der Wert nicht gefunden 
			// oder ein frueherer Fehler nicht behoben
			// und der Index noch innerhalb der selben Zeile
			correctDisplay(row,grenze,1,
					searchForErrors,searchForPreviousErrors,
					valueIsNotNull,newValue, prevValue);
			
		}
		// oder die Aenderung betraf die Spalte
		if(sameCol){
			// Solange der Wert nicht gefunden 
			// oder ein frueherer Fehler nicht behoben
			// und der Index noch innerhalb der selben Spalte
			correctDisplay(col,sumOfEntries,sumOfRows,
					searchForErrors,searchForPreviousErrors,
					valueIsNotNull,newValue,prevValue);
		}
		
	}
	/**
	 * Korrektur der Anzeige: 
	 * - Fehler aufgrund vorigem Wert wird ausgeschaltet
	 * - Fehler aufgrund neuem Wert wird ermittelt und angezeigt
	 * @param start		Start der Schleife
	 * @param border	Grenze der Schleife
	 * @param increment Inkrement der Schleife
	 * @param searchForErrors	Soll nach fehlerhaften Eingaben gesucht werden?
	 * @param searchForPreviousErrors Soll nach fehlerhaften  Eingaben gesucht werden?
	 * @param valueIsNotNull	Ist der aktuelle Wert ungleich Null?
	 * @param newValue			neuer geaenderter Wert
	 * @param prevValue			Wert vor der Aenderung
	 */
	private void correctDisplay(
				int start, int border, int increment,
				boolean searchForErrors,boolean searchForPreviousErrors,
				boolean valueIsNotNull, int newValue, int prevValue
	){
		for(int i=start;(searchForErrors ||searchForPreviousErrors)&&i<border;i+=increment){
			// Ermittle den Wert an dieser Stelle im Array
			int currentValue=entries[i].getValue();
			// Zuerst moegliche Fehleranzeige ausschalten
			if(currentValue==prevValue){
				entries[i].decError();
				// Wenn kein Fehlereintrag mehr vorhanden
				if(!entries[i].hasError())
					entries[i].setFailure(false);
				// Der Fehler konnte nur einmal im Sudoko vorkommen!
				searchForPreviousErrors=false;
			}
			// Nun nach neuen Fehlern suchen
			if(valueIsNotNull&&currentValue==newValue){
				entries[i].setFailure(true);
				entries[i].incError();
				// Der Fehler kann nur einmal im Sudoko vorkommen!
				searchForErrors=false;
			}
		}
	}

}
