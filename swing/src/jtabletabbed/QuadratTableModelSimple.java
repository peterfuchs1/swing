package jtabletabbed;

import java.util.ArrayList;

import javax.swing.table.*;
/**
 * Einfaches TableModel
 * 
 * @author Walter Rafeiner-Magor
 *
 */
class QuadratTableModelSimple extends AbstractTableModel
{
  private final int capacity=10;	
  private ArrayList<Short> al=new ArrayList<Short>(capacity);
  private String[] columns = {"Zahl", "Quadrat", "Kubik"};
  private short maxValue;
 /**
  *  Kontruktor
  *  Initialisiert die ArrayList
  */
  public QuadratTableModelSimple() {
	super();
	for (short i=0;i<this.capacity;i++)
		al.add(i, new Short(i));
	maxValue=al.get(this.capacity-1);	
  }
  public short maxValue() {
	  short max=al.get(0);
	  for (int i=1;i<this.capacity;i++)
		  max=(al.get(i)>max)? al.get(i):max;
	  return max;
  }
/**
 * Gibt die Spaltennamen, des übergebenen Spaltenindex zurück
 * @param column Spaltenindex
 * @return Spaltenname
 */
@Override
public String getColumnName(int column) {
	return columns[column];
}
/**
 * Gibt die Klasse der Spalten mit dem übergebenen Spaltenindex zurück
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

/* (non-Javadoc)
 * @see javax.swing.table.AbstractTableModel#fireTableCellUpdated(int, int)
 */
/**
 * @return the maxValue
 */
public short getMaxValue() {
	return maxValue;
}
@Override
public void fireTableCellUpdated(int row, int column) {
	// TODO Auto-generated method stub
	super.fireTableCellUpdated(row, column);
	short value=(Short)getValueAt(row,0);
	maxValue=(value>maxValue)?value:maxValue;
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
   * Berechnet Wert für jede Zelle
   * in Spalte 1 wird der Wert aus der Arraylist ausgelesen
   * und für die Berechnungen der anderen Spalten herangezogen
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
   * Die Kontrolle des Eingabewertes entfällt, da durch das Überschreiben
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
