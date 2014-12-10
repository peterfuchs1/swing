/**
 * 
 */
package jtextarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * JPanel mit JTextField (zur Eingabe) , JTextArea und Buttons
 * zur einfachen Bearbeitung von Texten
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel implements ActionListener {
	private JTextArea jta;			// TextArea f�r mehrzeiligen Text
	private JTextField jtf;			// TextField f�r einzeiligen Text
	private JPanel button_panel;	// Panel f�r die Aufnahme der Buttons
	private JButton select_all;		// Button select all
	private JButton del_all;		// Button delete all
	private MyAL mal;				// eigener ActionListener f�r die Buttons
	/**
	 * Standard-Konstruktor
	 * ruft Konstruktor mit einem Parameter (boolean) auf
	 */
	public MyPanel() {
		this(true);
	}
	/**
	 * Kontruktor mit einem Parameter
	 * 
	 * @param lw Zeilenumbruch
	 */
	public MyPanel(boolean lw){
		mal=new MyAL();
		
		// Standard-Layoutmanager (Flow-Layout) wird durch BOrder-Layout ersetzt
		this.setLayout(new BorderLayout());
		// Label wird erstellt
		new JLabel("Text: ");
		// Textfield wird erstellt
		jtf=new JTextField(20);
		// ActionListener (aktueller Klasse) wird angemeldet
		jtf.addActionListener(this);
		// Textfield wird am HauptPanel platziert
		this.add(BorderLayout.NORTH,jtf);
		// Fokus wird f�r das Textfield angefordert
		jtf.requestFocus();
		// TextArea wird erstellt
		jta=new JTextArea(10,20);
		// Zeilenumbruch wird gesetzt
		jta.setLineWrap(lw);
		// F�r die TextArea wird eine JScrollPane erstellt  
		JScrollPane scroller=new JScrollPane(jta);
		// Die JScrollPane (inkl. TextArea) wird am Haupt-Panel paltziert
		this.add(BorderLayout.CENTER,scroller);
		// F�r den Scroller werden horizontale und vertikale Bars gesetzt
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Erstellt Panel zur Aufnahme der Buttons
		button_panel=new JPanel();
		// Hintergrund f�r den Button-Panel wird gesetzt
		button_panel.setBackground(Color.LIGHT_GRAY);
		// Buttons werden erstellt
		select_all=new JButton("select all");
		del_all=new JButton("del all");
		// F�r die Buttons wird der ActionListener angemeldet
		select_all.addActionListener(mal);
		del_all.addActionListener(mal);
		button_panel.add(del_all);
		button_panel.add(select_all);
		// Panel zur Aufnahme der Buttons fertig
		// Button-Panel wird im Haupt-Panel platziert
		this.add(BorderLayout.SOUTH,button_panel);
	}
	/**
	 * Wird eine eingegeben Zeichenkette im Textfield best�tigt
	 * kommt es zur �bernahme in die TextArea
	 * 
	 * @param ae ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Text aus Textfield kopieren
		String s=jtf.getText();
		// Text aus TextArea kopieren
		String s2=null;
		try {
			s2=jta.getText();
		}
		catch(NullPointerException e) {s2="";}
		// Neuen Text oben einf�gen
		jta.setText(s+"\n"+s2);
		// Text im Textfield selektieren
		jtf.selectAll();
	}
	/**
	 * Selektiert die Eingaben in der TextArea
	 */
	public void selectAll(){
		jta.selectAll();
	}
	/**
	 * L�scht alle Eintr�ge in der TextArea
	 */
	public void delAll(){
		jta.setText("");
	}
	/**
	 * Fordert den Fokus f�r das Textfield an
	 */
	public void setFocusTF() {
		jtf.requestFocus();
	}
	/**
	 * Fordert den Fokus f�r die TextArea an
	 */
	public void setFocusTA() {
		jta.requestFocus();
	}
	/**
	 * ActionListener zur Interaktion mit den Buttons und den Eintr�gen in der TextArea
	 * @author Walter Rafeiner-Magor
	 *
	 */
	class MyAL implements ActionListener{
		/**
		 * Falls der Button "Del all" gedr�ckt wurde,
		 * werden alle Eintr�ge in der TextArea gel�scht
		 * und der Fokus im TextField gesetzt
		 * 
		 * Falls der Button "Select all" gedr�ckt wurde,
		 * werden alle Eintr�ge in der TextArea markiert
		 * und der Fokus in der TextArea angefordert
		 * 
		 * @param e ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Button "del all" gedr�ckt
			if (e.getActionCommand().equals("del all")) {
				delAll();
				setFocusTF();
			}
			// Button " select all" gedr�ckt
			else if (e.getActionCommand().equals("select all")) {
				selectAll();
				setFocusTA();
			}
			
		}
		
	}
}
