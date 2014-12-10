/**
 * 
 */
package simplecalcV3;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	MyPanel calc=new MyPanel();
	public MyFrame() {
		// �berschrift setzen
		this.setTitle("Simple Calc");
		// Hintergrundfarbe setzen
		this.setBackground(Color.BLUE);
		// Schlie�e Anwendung, Falls der Frame geschlossen wurde
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(BorderLayout.CENTER,calc);
		
		this.pack();

		
		this.setVisible(true);
	}
}
