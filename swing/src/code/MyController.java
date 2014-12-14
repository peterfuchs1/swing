/**
 * 
 */
package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * MyController stellt die Verbindung zwischen den View-Komponenten und dem
 * Model dar
 * 
 * @author Walter Rafeiner-Magor
 *
 */
public class MyController extends WindowAdapter implements ActionListener {
	private MyPanel p;
	private MyFrame f;
	private Enigma t;

	/**
	 * Konstruktor Bekommt ein StartStoppable zur Steuerung
	 * 
	 * @param timer
	 */
	public MyController() {
		p = new MyPanel(this);
		f = new MyFrame(p, "My simple Enigma", this);
		t = new Enigma();
	}

	/**
	 * Startet die Applikation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyController c = new MyController(); // Startet die GUI
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.exitNow();
	}

	/**
	 * beendet die Applikation falls notwendig wird vorher noch der Thread
	 * gestoppt!
	 */
	private void exitNow() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			if (b == p.getJbAdd()) { // add-Button
				String key = p.getJtfNum().getText();
				char value = p.getJtfChar().getText().charAt(0);
				t.add(key, value);
				p.getJtfNum().requestFocus();
				p.getJtfNum().selectAll();
			} else if (b == p.getJbConvert()) { // Convert-Button
				this.convert();
			}
		} else {
			JTextField f = (JTextField) e.getSource();
			if (f == p.getJtfNum()) { // Key entered
				p.getJtfChar().requestFocus();
				p.getJtfChar().selectAll();
			} else if (f == p.getJtfChar()) { // Value entered
				p.getJbAdd().requestFocus();
			} else if (f == p.getJtfToConvert()) { // String to convert entered
				this.convert();
			}
		}

	}

	public void convert() {
		String keys = p.getJtfToConvert().getText();
		String out = t.convert(keys);
		try {
			p.getlResult().setText(out);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
