/**
 * 
 */
package jpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel, welcher mittels 2 Buttons den Hintergrund heller oder dünkler stellt
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	private JButton jbDarker;		// JButton dünkler 
	private JButton jbBrighter;		// JButton heller
	private MyActionListener mal;	// eigener ActionListener
	private Color bg;				// aktuelle Hintergrundfarbe
	
	/**
	 * Standard-Konstruktor
	 * ruft Konstruktor mit einem Parameter (Color) auf
	 */
	public MyPanel() {
		this(Color.ORANGE);
	}

	/**
	 * Konstruktor mit einem Parameter
	 * @param c Ausgangsfarbe für den Hintergrund
	 */
	public MyPanel(Color c) {
		// Hintergrundfarbe wird übernommen
		bg=c;
		this.setBackground(bg);
		// Standard-Layoutmanager (Flow-Layout) wird durch Border-Layout ersetzt
		this.setLayout(new BorderLayout());
		// Eigener Actionslistener für die Buttons erstellt
		mal=new MyActionListener();
		// Buttons werden erstellt
		jbDarker=new JButton("darker");
		jbBrighter=new JButton("brighter");
		// Die Buttons werden auf dem Panel platziert 
		this.add(BorderLayout.EAST,jbDarker);
		this.add(BorderLayout.WEST,jbBrighter);
		// Für die Buttons wird der ActionListener angemeldet
		jbDarker.addActionListener(mal);
		jbBrighter.addActionListener(mal);	
	}
	/**
	 * MyActionListener als innere Klasse für die
	 * Interaktion mit den beiden Buttons
	 *  
	 * @author Walter Rafeiner-Magor
	 * @version 1.0
	 *
	 */
	class MyActionListener implements ActionListener {
		/**
		 * Reagiert auf Buttons-Klicks
		 * @param ae ActionEvent
		 */
		@Override
		public void actionPerformed(ActionEvent ae) {
			// Je nachdem ob darker oder brighter
			// wird die Hintergrundfarbe verdunkelt oder erhellt 
			if (ae.getActionCommand().equals("darker")) bg=bg.darker();
			else bg=bg.brighter();
			// Neuzeichnen des Hintergrunds
			setBackground(bg);
		}
		
	}

}
