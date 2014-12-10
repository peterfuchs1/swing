package icoez_04;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * In dieser Klasse werden 1 JButton, 1 JToggleButton und JLabel erstellt.
 * Den beiden Buttons wird als Hintegrund ein Bild übergeben.
 * Wenn man den Jbutton drück soll eine Zufallszahl zwischen 1 und 45 erstellt
 * werden und im JLabel angezeigt werden. Wenn man das JToggleButton drückt,
 * dann soll sich das JButton sperren.
 * 
 * @author Gizem ICÖZ
 * @version 2009-10-06
 */
public class MyTooglePanel extends JPanel{
	private JButton eins;//Attribute werden erstellt
	private JToggleButton sperren;
	private JLabel l;
	
	private static final long serialVersionUID = 1L;
	/**
	 * default - Konstruktor
	 */
	public MyTooglePanel() {
		l = new JLabel(" ");//Label wird deklariert
		eins = new JButton(new ImageIcon("./src/icoez_04/green.gif"));//dem Button wird ein Bild übergeben sowie..
		sperren = new JToggleButton(new ImageIcon("./src/icoez_04/red.gif"));//..dem ToggleButton
		MyButtonHandler bh = new MyButtonHandler();//ButtonHandler baucht man um die Ereignissteuerung der Buttons zu steuern
		eins.addActionListener(bh);//hier werden die..
		sperren.addActionListener(bh);//..Buttons dem ButtonHandler überwiesen
		this.add(eins);//die Attribute werden an dem Layout geaddet
		this.add(sperren);
		this.add(l);
		// 10 Pixel Rand lassen
//		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	/**
	 * Innere Klasse für die Ereignissteuerung der Buttons
	 * @author Gizem ICÖZ
	 * @version 2009-20-06
	 */
	public class MyButtonHandler implements ActionListener {
		/**eig. würde da jetzt @Override, damit man die Methode actionPerformed überschreiben kann.
		 * Doch wenn ich das hinschriebe kommt bei mir eine Fehlermeldung heraus. Voriges Mal hab ich das dem
		 * Herr Prof. VITOVEC gezeigt, er meinte das ich es auskommentieren soll
		 */
		
		/**
		 * Methode des Interface ActionListener
		 */
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getSource() == eins) {//wenn eins gedrückt wird
				int zufall = (int)(Math.random()*45) + 1;//soll eine Zufallszahl zwischen 1 und 45 ausgerechnet werden und..
				l.setText(zufall + "");//..wird als Text bei JLabel angezeigt
			}
			if(sperren.isSelected()) {//wenn sperren ausgewählt wurde..
				eins.setEnabled(false);//..wird eins auf setEnableb(false) gewechselt damit man diesen Button nicht mehr betätigen kann also sie wird gesperrt
			}
			else {//wenn nicht..
				eins.setEnabled(true);//..dann kann man eins nochimmer betätigen
			}
		}
		
	}
}

