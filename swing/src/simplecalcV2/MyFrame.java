/**
 * 
 */
package simplecalcV2;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * @author Walter Rafeiner-Magor
 *
 */
public class MyFrame extends JFrame {
	MyPanel calc=new MyPanel();
	public MyFrame() {
		// ‹berschrift setzen
		this.setTitle("Simple Calc");
		// Hintergrundfarbe setzen
		this.setBackground(Color.BLUE);
		// Schlieﬂe Anwendung, Falls der Frame geschlossen wurde
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(BorderLayout.CENTER,calc);
		
		this.pack();

		
		this.setVisible(true);
	}
}
