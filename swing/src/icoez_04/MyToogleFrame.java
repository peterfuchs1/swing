package icoez_04;

import java.awt.*;
import javax.swing.*;

/**
 * In dieser Klasse wird der Inhalt der Klasse ToggleBesipiel ausgegeben
 * @author Gizem IC�Z
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//das ben�tigt man damit sich sowohl das fenster auch die Applikation schlie�en, wenn man X d�rckt
		this.getContentPane().add(BorderLayout.NORTH, tbsp);//f�gt den Panel in den Frame hinein
		this.pack();
//		this.setSize(1000,200);//die Gr��e des Fensters
		this.setVisible(true);//n�tig damit man das Fenster sehen kann 
	}
	
}
