/**
 * 
 */
package nonogrammV2;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JToggleButton;

/**
 * Fields erweitern JToggleButton
 * Mittels statischer HashMap werden die
 * vorhandenen Fields gespeichert
 * 
 * @author Walter Rafeiner-Magor
 * @version 1.0
 * 
 */

public class Fields extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710757753170595686L;
	/**
	 * Objekt-Attribute
	 */
	private int xCoord, yCoord; 	// Koordinaten
	private boolean marked;			// leer markiert?
	private Color colorSelected;	// Farbe bei gesetzt
	private Color colorUnSelected;	// Farbe bei released
	private Color colorMarked;		// Farbe bei Markierung
	private boolean value;			// false nicht gesetzt, true gesetzt
 
	/**
	 * statische Attribute
	 */
	private static int lines = 0;	// Anzahl der Zeilen=Spalten
	// Collection: Hält alle vorhandenen Fields
	private static HashMap<String, Fields> fields = new HashMap<String, Fields>();
	public static int rowSum[][], colSum[][]; //Zeilenn- und Spaltensummen
	/**
	 * Standardkonstruktor 
	 */
	public Fields() {
		this(0, 0);
	}
	/**
	 * @return the rowSum
	 */
	public static int[][] getRowSum() {
		return rowSum;
	}
	/**
	 * @param rowSum the rowSum to set
	 */
	public static void setRowSum(int[][] rowSum) {
		Fields.rowSum = rowSum;
	}
	/**
	 * erstellt das Array der Zeilensummen
	 */
	public static void createRowSum() {
		Fields.rowSum = new int[lines][lines/2+1];
	}
	/**
	 * @return the colSum
	 */
	public static int[][] getColSum() {
		return colSum;
	}
	public static void makeColSum() {
		for(int i=0;i<Fields.lines;i++){
			int colNo=Fields.lines/2;				// Spaltensumme beginnt ganz unten
			boolean filled=false;					// letzter Wert war leer
			int sum=0;								// Summe beginnt bei 0
			for (int j=0;j<lines;j++){
				Fields f=fields.get(""+i+" "+j);	// Button auslesen
				boolean value=f.getValue();			// Wert ermittlen
				if(!filled && !value)   			// bis jetzt nur Nullen und nun auch keine Eins
					continue;						// schnell weiter
				else if (!filled && value) {		// bis jetzt nur Nullen, aber jetzt eine Eins
					sum=1;							// Summe nun 1
					filled=true;					// letztes Feld war eine Eins
				}
				else if(filled && !value){			// bis jetzt waren bereits Einser, aber nun kommt eine Null
					colSum[i][colNo]=sum;			// aktuelle Spaltensumme gespeichert
					colNo--;						// nächste Spaltensumme adressiert
					sum=0;							// Summe startet bei 0
					filled=false;					// letztes Feld war leer!
				}
				else 								// bis jetzt waren bereits Einser und nun kommt wieder eine Eins
					sum++;							// Summe erhöhen
					
			}
			if (sum!=0)								// Letzte Summe schreiben
				colSum[i][colNo]=sum;
		}
	}
	/**
	 * Berechnung der Zeilensummen
	 * aus Basis der value
	 */
	public static void makeRowSum() {
		for(int i=0;i<lines;i++){
			int rowNo=0;							// Zeilensumme beginnt ganz links
			boolean filled=false;					// letzter Wert war leer
			int sum=0;								// Summe beginnt mit 0
			for (int j=0;j<lines;j++){
				Fields f=fields.get(""+j+" "+i);	// nächsten Button auslesen
				boolean value=f.getValue();			// Wert ermitteln
				if(!filled && !value)   			// bis jetzt nur Nullen und nun auch keine Eins
					continue;						// schnell weiter
				else if (!filled && value) {		// bis jetzt nur Nullen, aber jetzt eine Eins
					sum=1;							// Summe nun 1
					filled=true;					// letztes Feld war eine Eins
				}
				else if(filled && !value){			// bis jetzt waren bereits Einser, aber nun kommt eine Null
					rowSum[i][rowNo]=sum;			// aktuelle Zeileensumme gespeichert
					rowNo++;						// nächste Zeilensumme adressiert
					sum=0;							// Summe startet bei 0
					filled=false;					// letztes Feld war leer!
				}
				else 								//bis jetzt waren bereits Einser und nun kommt wieder eine Eins
					sum++;							// Summe erhöhen		
			}
			if (sum!=0)								// Letzte Summe schreiben
				rowSum[i][rowNo]=sum;
		}
	}
	/**
	 * @param colSum the colSum to set
	 */
	public static void setColSum(int[][] colSum) {
		Fields.colSum = colSum;
	}
	/**
	 * erstellt Array der Spaltensummen
	 */
	public static void createColSum() {
		Fields.colSum = new int[lines][lines/2+1];
	}
	/**
	 * Löscht alle Spalten- und Zeilensummen
	 * für die Initialisierung
	 */
	public static void clearAllSum(){
		for (int i=0;i<lines;i++)
			for(int j=0;j<colSum[i].length;j++){
				colSum[i][j]=0;
				rowSum[i][j]=0;
			}
	}
	/**
	 * Basis-Konstruktor mit 2 Koordinaten
	 * @param xCoord
	 * @param yCoord
	 */
	public Fields(int xCoord, int yCoord) {
		this(xCoord, yCoord, false);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Konstruktor mit 3 Parameter
	 * @param xCoord
	 * @param yCoord
	 * @param marked
	 */
	public Fields(int xCoord, int yCoord, boolean marked) {
		this(xCoord, yCoord, marked, Color.lightGray);
	}
	/**
	 * Konstruktor mit 4 Parameter
	 * @param xCoord
	 * @param yCoord
	 * @param marked
	 * @param color
	 */
	public Fields(int xCoord, int yCoord, boolean marked, Color color) {
		this(xCoord, yCoord, marked, color, Color.white);
	}
	/**
	 * Konstruktor mit allen Parameter
	 * @param xCoord	X-Koordinate
	 * @param yCoord	Y-Koordinate
	 * @param marked		Lichtstatus
	 * @param colorSelected	Farbe wenn geklickt
	 * @param colorUnSelected	Farbe wenn nicht geklickt
	 */
	public Fields(int xCoord, int yCoord, boolean marked, Color colorSelected, Color colorUnSelected) {
//		this.setSize(8, 8);
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.marked = marked;
		this.colorSelected = colorSelected;
		this.colorUnSelected = colorUnSelected;
		this.value=false;
		this.setToggleBackground();
		this.setVisible(true);
		// Fügt das Field zur Collection hinzu
		Fields.fields.put("" + xCoord +" "+ yCoord, this);
		this.colorMarked=Color.orange;
	}
	/**
	 * @return the xCoord
	 */
	public int getXCoord() {
		return xCoord;
	}
	/**
	 * @param xCoord
	 *            the xCoord to set
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 * @return the yCoord
	 */
	public int getYCoord() {
		return yCoord;
	}
	/**
	 * @param yCoord  the yCoord to set
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	 * Ändert den Lichtstatus an gegebenen Buttons
	 * @param x	x-Koordinate
	 * @param y y-Koordinate
	 */
	public static void switchToggle(int x, int y) {
		// center
		Fields f=Fields.fields.get("" + x+" " + y);
		f.setToggleBackground();
	}
	/*
	 * Schaltet die Hintergrundfarbe um
	 */
	public void switchToggle(){
		this.setToggleBackground();
	}
	/**
	 * Gibt die Anzahl der Buttons mit Value==true zurück
	 * 
	 * @return Anzahl
	 */
	public static int fieldsMarked() {
		int ret = 0;
		for (int i = 0; i < Fields.lines; i++)
			for (int j = 0; j < Fields.lines; j++) {
				if (Fields.fields.get("" + i+" " + j).getValue())
					ret++;
			}
		return ret;
	}
	/**
	 * Wieviele Felder sind bereits gefunden?
	 * @return anzahl der gefundenen Felder
	 */
	public static int openFields() {
		int ret = 0;
		for (int i = 0; i < Fields.lines; i++)
			for (int j = 0; j < Fields.lines; j++) {
				Fields f=Fields.fields.get("" + i+" " + j);
				if (f.isSelected()&& f.getValue())
					ret++;
			}
		return ret;
	}
	/**
	 * Ist das Feld mit rechtsclick markiert?
	 * @return the marked
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * @return the lines
	 */
	public static int getLines() {
		return lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public static void setLines(int lines) {
		Fields.lines = lines;
	}

	/**
	 * @param marked  the marked to set
	 */
	public void setMarked(boolean marked) {
		this.marked = marked;
		if (marked) {
			this.setSelected(false);
			this.setBackground(this.colorMarked);
		}
		else {
			this.setBackground(this.colorUnSelected);
		}
	}
	/**
	 * Ändert und setzt die Markierung
	 * 
	 */
	public void markedToggle() {
		this.marked=(marked)?false:true;
		setMarked(marked);
	}
	/**
	 * Setzt eine neue Hintergrundfarbe
	 */
	public void setToggleBackground() {
		if (this.isSelected())
			this.setBackground(colorSelected);
		else
			this.setBackground(colorUnSelected);

	}

	/**
	 * @return the colorSelected
	 */
	public Color getColorSelected() {
		return colorSelected;
	}

	/**
	 * @param colorSelected the colorSelected to set
	 */
	public void setColorSelected(Color colorSelected) {
		this.colorSelected = colorSelected;
	}

	/**
	 * @return the colorUnSelected
	 */
	public Color getColorUnSelected() {
		return colorUnSelected;
	}

	/**
	 * @param colorUnSelected the colorUnSelected to set
	 */
	public void setColorUnSelected(Color colorUnSelected) {
		this.colorUnSelected = colorUnSelected;
	}

	/**
	 * @return the value
	 */
	public boolean getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(boolean value) {
		this.value = value;
	}
}