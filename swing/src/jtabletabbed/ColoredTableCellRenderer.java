package jtabletabbed;

import java.awt.*;
import javax.swing.table.*;

class ColoredTableCellRenderer extends DefaultTableCellRenderer
{
  @Override
  public void setValue( Object value )
  {
	if ( value instanceof Long || value instanceof Short)
	 {
      setForeground( (Long)value  < 0 ? Color.RED : Color.BLUE );
      setBackground( (Long)value  >= 0 ? Color.GRAY : Color.LIGHT_GRAY );
      setText( value.toString() );
    }
    else
      super.setValue( value );
  }
}
