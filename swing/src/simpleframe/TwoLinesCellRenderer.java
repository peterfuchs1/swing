package simpleframe;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TwoLinesCellRenderer
  extends JTextArea implements TableCellRenderer
{
  public Component getTableCellRendererComponent(
    JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column )
  {
    setText( "1\n2" );    // Text setzen, hier value

    return this;
  }
}

