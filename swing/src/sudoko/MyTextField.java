/**
 * 
 */
package sudoko;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * Jedes Sudoko-Element basisert auf einem JTextField
 * Die Anzahl der Elemente wird vom Sudoko-Quadrat festlegt
 * Damit ein Sudoko-Element seinen Status merken kann,
 * werden folgende Eigenschaften gespeichert:<br/>
 * <li>Spalte im Sudoko-Quadrat: col
 * <li>Zeile im Sudoko-Quadrat: row
 * <li>Aktuell ein Fehler vorhanden?: failure
 * <li>Anzahl der Fehler fuer dieses Element: errorCount (max 2!)
 * <br/>
 * 
 * @author Walter Rafeiner-Magor
 *
 */
public class MyTextField extends JTextField {
	
	/**
	 * Konstanten
	 */
	private static final long serialVersionUID = -4942235776261637998L;
	public static final int COLUMNS=2;
	public static final Color fColor=Color.RED; //Fehlerfarbe
	public static final Color cColor=Color.WHITE; //Standardfarbe
	// Attribute
	private int col,row;	// Zeilen und Spalten 
	private boolean failure;
	private int errorCount;	// Anzahl der aktuellen Fehler (0=kein, 1 oder 2 Fehler)
	//GETTER und SETTER
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Converting the Text into an int
	 * We return 0 in case of an Exception!
	 * @return the value
	 */
	public int getValue() {
		int value=0;
		try {
			value=Integer.parseInt(this.getText());
		} catch (NumberFormatException e) {}
		return value;
	}
	/**
	 * Converting an int into the Text
	 * @param a
	 */
	public void setValue(int a) {
		this.setText(""+a);
	}

	/**
	 * @return the failure
	 */
	public boolean isFailure() {
		return failure;
	}

	/**
	 * Fehlerwert wird gesetzt
	 * Die Hintergrundfarbe wird neu gesetzt
	 * @param failure the failure to set
	 */
	public void setFailure(boolean failure) {
		this.failure = failure;
		if(failure)this.setBackground(fColor);
		else this.setBackground(cColor);
	}
	/**
	 * Error-Anzahl erhoehen
	 */
	public void incError(){
		errorCount++;
	}
	/**
	 * Error-Anzahl reduzieren
	 */
	public void decError(){
		if(errorCount>0)
			errorCount--;
	}
	/**
	 * Fehler am Feld eingetragen?
	 * @return true  (fuer 1 oder 2 Fehler); false (fuer 0 Fehler)
	 */
	public boolean hasError(){
		return(errorCount==0)?false:true;
	}
	/**
	 * Setzt die Anzahl der Fehler im Feld
	 * @param value
	 */
	public void setError(int value){
		errorCount=value;
	}
/**
 * Konstruktor
 * @param text Anzeige
 * @param col Spalte
 * @param row Zeile
 */
	public MyTextField(String text,int col,int row) {
		super(text);
		this.col=col;
		this.row=row;
		setFailure(false);
		errorCount=0;
	}
}
