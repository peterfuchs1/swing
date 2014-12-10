/**
 * 
 */
package jtable;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel, mit einer JTable
 * in welcher eine Spalte (erste SPalte) editierbar ist, und
 * in der zweiten Spalte das Quadrat, und
 * in der dritte Spalte die Kubikzahl angezeigt wird
 * Initial soll die Tabelle mit Werten von 0-19 berechnet werden
 * 
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	private MyTableModel model;		// Tablemodell verwenden 
	private JTable table;			// Table erstellen
	
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public MyPanel() {
		model = new MyTableModel(); 	// Table-Modell erstellt
	    table = new JTable();				// Table erstellt
	    table.setModel( model );			// Table-Modell zugewiesen
	    table.setToolTipText("Bitte nur ganzzahlige Werte zwischen -32768 und +32767 eingeben!");
	    // Table wird mit einer JSrollPane ergänzt
	    JScrollPane jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    // Table im JPanel platziert
	    this.setLayout(new BorderLayout());
	    this.add(BorderLayout.CENTER,jsp);
	}

}
