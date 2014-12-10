package simpleframe;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

class QuadratTableModelComplex extends AbstractTableModel
{
  static class ColumnListener extends MouseAdapter
  {
    protected JTable table;

    public ColumnListener(JTable table) {
      this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      TableColumnModel colModel = table.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
      int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

      // Nur wenn wir die erste Spalte erwischen, wollen wir umsortieren

      if ( modelIndex != 0 )
        return;

      if ( sortColumn == modelIndex )
        sortAsc = !sortAsc;
      else
        sortColumn = modelIndex;

      table.tableChanged( new TableModelEvent( table.getModel(),
                                                 TableModelEvent.HEADER_ROW ) );

//      m_table.repaint();  repaint() aktualiert nur die Tabelle, nicht den Kopf. Sonst reicht´s.
    }
  }

  // Ausmaße für Zeilen und Spalten

  public int getRowCount()
  {
    return 20;
  }

  public int getColumnCount()
  {
    return 5;
  }

  // Informationen über Kopfzeile und Spaltentyp

  final static String columnNames[] = {
    "Zahl", "Quadrat", "Quadrat Gerade", "Kubik", "Tolle Zahl"
  };

  static int sortColumn = 0;

  static boolean sortAsc = true;

  @Override
  public String getColumnName( int col )
  {
    String s = columnNames[col];
    return col == 0 ? s + ( sortAsc ? " »" : " «" ) : s;
  }

  final static Class<?> columnClasses[] = {
    Integer.class, Long.class, Boolean.class, String.class, String.class
  };

  @Override
  public Class<?> getColumnClass( int col )
  {
    return columnClasses[col];
  }

  // Wert einer Zelle

  public void setValueAt( int row, int col, Object o )
  {
    System.out.println( row + " " + col + " " + o );
  }

  public boolean isCellEditable()
  {
    return false;
  }

  public Object getValueAt( int row, int col )
  {
    if ( !sortAsc )
      row = getRowCount() - row;

    switch ( col )
    {
      case 0 : return new Integer(row);
      case 1 : return new Long( row*row );
      case 2 : return new Boolean( (row*row)%2 == 0 );
//      case 4 : return new Boolean( (row*row)%2 == 0 );
      default: return ""+(row*row*row);
    }
  }
}

