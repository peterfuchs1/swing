/**
 * 
 */
package hamlet;

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

/**
 * MyController stellt die Verbindung zwischen den View-Komponenten und dem MyPanel dar
 * @author Walter Rafeiner-Magor
 *
 */
public class MyController extends WindowAdapter implements ActionListener{
	private MyPanel p;
	private MyFrame f;
	private Hamlet text;
	
	/**
	 * Konstruktor
	 * Bekommt ein StartStoppable zur Steuerung
	 * @param timer
	 */
	public MyController(){
		text=Hamlet.getInstance();
		p=new MyPanel(this,text);
		f=new MyFrame(p, "My Hamlet", this);
		
	}
	/**
	 * Startet die Applikation
	 * @param args
	 */
	public static void main(String[] args) {
		MyController c=new MyController(); // Startet die GUI
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		this.exitNow();
	}
	/**
	 * beendet die Applikation
	 * falls notwendig wird vorher noch der Thread gestoppt!
	 */
	private void exitNow(){
		System.exit(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton) e.getSource();
		if(b==p.getExit()){ // Exit-Button
			this.exitNow();
		}
		else if(b==p.getSolve()){ // Solve-Button
			p.solve();
		}
		else if(b==p.getRestart()){ // Restart-Button
			p.restart();
		}
		else {// Ein Spielebutton wurde gedrückt
			int currentID=p.getCurrentId();
			// Richtige Reihenfolge?
			if(text.correctPosition(b.getText(),currentID)){
				p.setCurrentId(currentID+1);
				p.setCorrectMoves(p.getCorrectMoves()+1);
				p.setOpenMoves(p.getOpenMoves()-1);
				b.setEnabled(false);
				if(p.getOpenMoves()==0)
					new MyDialog(f,"Congratulations!");
			}
			else{
				p.setWrongMoves(p.getWrongMoves()+1);
			}
			p.setMoves(p.getMoves()+1);
			p.showStatus();
		}
		
	}

	
}
