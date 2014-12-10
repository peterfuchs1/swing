/**
 * 
 */
package simplecalcV4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * @author uhs374h
 *
 */
public class MyController extends KeyAdapter implements ActionListener{
	
	MyPanel gui;
	/**
	 * @param gui
	 */
	public MyController(MyPanel gui) {
		this.gui = gui;
				
	}

	/**
	 * MyKeyListener
	 * Interpretiert Tastendruck im Textfield
	 * f�r Ziffern und Operationen
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Enter wird in = umgewandelt!
		char c=e.getKeyChar();
		if(c==KeyEvent.VK_ENTER) c='=';
		String s=""+c;

		// Falls eine Nummer eingeben wurde, wie numButtons verwenden
		if(MyPanel.numbers.contains(s)){
			boolean consume=true;
			if(s.equals(".")) {
				e.consume();
				consume=false;
			}
			
			gui.numTyped(s,consume);
		}	
		else
			// Falls eine Operation verwendet wurde
			if (MyPanel.operations.containsKey(s)) {
				// Operation auswerten
				gui.opTyped(s);
				// aber nicht anzeigen!
				e.consume();
			}
			// Ansonsten den Tastendruck vergessen ;-)
			else e.consume();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Buttons gedr�ckt
		String s=e.getActionCommand();
		if (MyPanel.operations.containsKey(s)) { //OP gedr�ckt 
	           // Der Rechner kennt 2 Stati
            // 1. Eine Ziffer wird erwartet - ein Operator ist falsch!
            // 2. Ein Operator wird erartet
			// Falls ein un�rer Operator kommt, erlauben!
           	// OperatorButtons auswerten
        	gui.opTyped(s);
            
			
		} else if (MyPanel.numbers.contains(s)) { // Num gedr�ckt
			// Der gedr�ckter numerischer Button wird ausgewertet
			gui.numTyped(s);
			
			
		} else 
			// Clear gedr�ckt
			gui.actionClear();
		// Den Focus zur�ck ins Textfield setzen
		gui.requestFocus();
	}


}
