package jtable;

import java.util.ArrayList;

import javax.swing.table.*;
/**
 * Einfaches TableModel
 * 
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
class MyTableModel extends AbstractTableModel
{
  private final int capacity=20;	
  private ArrayList<Short> al=new ArrayList<Short>(capacity);
  private String[] columns = {"Zahl", "Quadrat", "Kubik"};
  /**
  *  Kontruktor
  *  Initialisiert die ArrayList
  */
  public MyTableModel() {
	super();
	for (short i=0;i<this.capacity;i++)
		al.add(i, new Short(i));
  }
/**
 * Gibt die Spaltennamen, des �bergebenen Spaltenindex zur�ck
 * @param column Spaltenindex
 * @return Spaltenname
 */
@Override
public String getColumnName(int column) {
	return columns[column];
}
/**
 * Gibt die Klasse der Spalten mit dem �bergebenen Spaltenindex zur�ck
 * @param col Spaltenindex
 * @return Klasse der Spalte
 */
public Class<?> getColumnClass(int col) {
	   switch (col) {
	    case 0: //Zahl
	     return Short.class;
	    case 1: //Quadrat
	     return Long.class;
	    case 2: //Kubik
	     return Long.class;
	    default:
	     return null;
	   }
	  }

/**
 * Gibt die Anzahl der Zeilen aus
 * @return Anzahl
 */
  public int getRowCount()
  {
    return capacity;
  }
  /**
   * Gibt die Anzahl der Spalten aus
   * @return Anzahl
   */
  public int getColumnCount()
  {
    return this.columns.length;
  }
  /**
   * Gibt die Editierbarkeit aller Zellen an
   * @param row Zeile
   * @param col Spalte
   * @return editierbar
   * 
   */
  public boolean isCellEditable(int row, int col) {
	  switch(col) {
	  	case 0: return true;
	  	default: return false;
	  }
  }
  /**
   * Berechnet Wert f�r jede Zelle
   * in Spalte 1 wird der Wert aus der Arraylist ausgelesen
   * und f�r die Berechnungen der anderen Spalten herangezogen
   * 
   * @param row Zeile
   * @param col Spalte
   */
  public Object getValueAt( int row, int col )
  {
	  short value=al.get(row);
	  long l=value;
    if ( col == 0 )
      return new Short(value);
    else if ( col == 1 )
      return new Long (l*l);
    else
      return new Long (l*l*l);
  }
  /**
   * Setzt eigegebenen Wert in einer Zelle in der ersten Spalte
   * da nur diese editierbar ist!
   * Die Kontrolle des Eingabewertes entf�llt, da durch das �berschreiben
   * der Methode getColumnClass() der Wertebereich festgelegt wurde 
   * @param value eingegebener Zellenwert
   * 
   * @param row Zeile
   * @param col Spalte
   * 
   */
  public void setValueAt(Object value, int row, int col) {
	   if (col==0) { //Zahl
	     al.set(row, (Short)value);
	   }
	  }
}
