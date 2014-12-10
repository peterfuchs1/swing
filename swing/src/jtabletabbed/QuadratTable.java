package jtabletabbed;

import javax.swing.*;
import javax.swing.table.*;

public class QuadratTable
{
  public static void main( String[] args )
  {
	QuadratTableModelSimple model = new QuadratTableModelSimple();
    short maxValue=model.getMaxValue();
    JTable table = new JTable();
    table.setModel( model );
    table.setToolTipText("Bitte nur ganzzahlige Werte zwischen -32768 und +32767 eingeben!");

    JFrame frame = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    JScrollPane jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JTextField jtfq= new JTextField(maxValue);
    JTextField jtfk= new JTextField(maxValue);
    tabbedPane.addTab( "Berechnung", jsp );
    tabbedPane.addTab( "Größtes Quadrat", jtfq );
    tabbedPane.addTab( "Größte Kubik", jtfk );
    
    frame.getContentPane().add( tabbedPane );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.pack();
    frame.setVisible( true );
  }
}
