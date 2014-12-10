package jtabletabbed;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class SimpleTableCellEditor
  extends AbstractCellEditor
  implements TableCellEditor
{
  private JTextField component = new JTextField();

  public Component getTableCellEditorComponent(
      JTable table, Object value,
      boolean isSelected, int rowIndex, int colIndex )
  {
    component.setText( value.toString() );

    return component;
  }

  public Object getCellEditorValue()
  {
    return component.getText();
  }
}
