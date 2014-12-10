package icoez_04;

import java.awt.*;
import javax.swing.*;

/**
 * In dieser Klasse wird der Inhalt der Klasse ToggleBesipiel ausgegeben
 * @author Gizem ICÖZ
 * @version 2009-10-06
 */
public class MyToogleFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private MyTooglePanel tbsp;//Objekt der Klasse ToggleBeipsiel
	/**
	 * default - Konstruktor
	 */
	public MyToogleFrame() {
		tbsp = new MyTooglePanel();
		this.add(tbsp);//das Objekt wird geaddet
		this.setTitle("Beispiel: ToggleButton");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//das benötigt man damit sich sowohl das fenster auch die Applikation schließen, wenn man X dürckt
		this.getContentPane().add(BorderLayout.NORTH, tbsp);//fügt den Panel in den Frame hinein
		this.pack();
//		this.setSize(1000,200);//die Größe des Fensters
		this.setVisible(true);//nötig damit man das Fenster sehen kann 
	}
	
}
