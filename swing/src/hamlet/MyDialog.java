/**
 * 
 */
package hamlet;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * @author uhs374h
 *
 */
public class MyDialog extends JDialog {

	private static final long serialVersionUID = -2785868839865890693L;
	MyDialog(Frame f,String text){
		super(f,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.add(new JLabel(text));
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
}
