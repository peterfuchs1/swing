/**
 * 
 */
package simplecalcV5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Controller-Klasse
 * @author Walter Rafeiner-Magor
 * @version 2.1
 *
 */
public class MyController extends KeyAdapter implements ActionListener{
	private MyFrame mf;
	private MyPanel gui;
	private MyModel model;
	/**
	 * @param gui
	 */
	public MyController() {
		// neues Model erstellen
		model=new MyModel();
		// neues Panel erstellen
		gui=new MyPanel(this,model);
		// neuer Frame erstellen
		mf=new MyFrame(gui);
	
				
	}
	/**
	 * Main-Methode zum Start der Applikation
	 * @param args
	 */
	public static void main(String[] args) {
		
		 new MyController();
	}
	
	/**
	 * MyKeyListener
	 * Interpretiert Tastendruck im Textfield
	 * für Ziffern und Operationen
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
		else {
			// Falls eine Operation verwendet wurde
			if (MyPanel.operations.containsKey(s)) 
				// Operation auswerten
				gui.opTyped(s);
			
			// Den Tastendruck vergessen ;-)
 			e.consume();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Buttons gedrückt
		String s=e.getActionCommand();
		if (MyPanel.operations.containsKey(s)) { //OP gedrückt 
	           // Der Rechner kennt 2 Stati
            // 1. Eine Ziffer wird erwartet - ein Operator ist falsch!
            // 2. Ein Operator wird erwartet
			// Falls ein unärer Operator kommt, erlauben!
           	// OperatorButtons auswerten
        	gui.opTyped(s);
            
			
		} else if (MyPanel.numbers.contains(s)) { // Num gedrückt
			// Der gedrückter numerischer Button wird ausgewertet
			gui.numTyped(s);
			
			
		} else 
			// Clear gedrückt
			gui.actionClear();
		// Den Focus zurück ins Textfield setzen
		gui.requestFocus();
	}


}
