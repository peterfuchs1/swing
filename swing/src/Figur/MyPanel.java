/**
 * 
 */
package Figur;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.ListIterator;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Einfache GUI-Applikation als Test der selbsterstellten
 * generischen Methoden (max, min, random)
 * <br/>
 * Die GUI l‰ﬂt die Eingabe beliebig vieler alphanumerischer Werte zu.<br/>
 * Der Aufbau der GUI besteht aus<br/><br/>
 * 
 * <li> Wert: Eingabefeld
 * <li> Anzahl: Ausgabefeld 
 * <li> Maximum: Ausgabefeld
 * <li> Manimum: Ausgabefeld
 * <li> Zufall: Ausgabefeld
 * <br/>
 * Die Applikation kann mittels Button ("Beenden")<br/>
 * bzw. durch Schlieﬂen des Fensters beendet werden.
 * 
 * 
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel{
	// Konstanten
	private JButton Bkreis, Bviereck, Bdreieck, Brechteck;
	private JLabel xlabel,ylabel;
	private JTextField txtx, txty;
	private JPanel s1,s2,s3;
	private LinkedList<Figur> ll;
	private ButtonHandler bh;
	public MyPanel(){
		bh=new ButtonHandler();
//		gc=this.getGraphics();
		ll=new LinkedList<Figur>();
		Bkreis= new JButton("Kreis");
		Bviereck= new JButton("Viereck");
		Bdreieck= new JButton("Dreieck");
		Brechteck =new JButton("Rechteck");
		Bkreis.addActionListener(bh);
		Bviereck.addActionListener(bh);
		Brechteck.addActionListener(bh);
		Bdreieck.addActionListener(bh);
		txtx=new JTextField ();
		txty=new JTextField ();
		xlabel=new JLabel("X Wert:");
		ylabel=new JLabel("Y Wert:");
		this.setLayout(new BorderLayout());
		s1=new JPanel();
		s1.setLayout(new GridLayout(1,4));
		s1.add(Bkreis);
		s1.add(Bviereck);
		s1.add(Bdreieck);
		s1.add(Brechteck);
		this.add(BorderLayout.NORTH,s1);
		s2=new JPanel();
		s2.setLayout(new GridLayout(1,1));
//		s2.add(d);
		this.add(BorderLayout.CENTER,s2);
		s3=new JPanel ();
		s3.setLayout(new GridLayout(1,4));
		s3.add(xlabel);
		s3.add(txtx);
		s3.add(ylabel);
		s3.add(txty);
		this.add(BorderLayout.SOUTH,s3);

	
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics h=s2.getGraphics();

		ListIterator<Figur> i=ll.listIterator();
		
		for (;i.hasNext();){
			i.next().draw(h);
		}
		this.validate();
	}
	private  class ButtonHandler implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			int x=Integer.parseInt(txtx.getText());
			int y=Integer.parseInt(txty.getText());
			if (e.getSource()==Bkreis)
				ll.add(new Kreis(x,y, Color.blue));
			if (e.getSource()==Bviereck)
				ll.add(new Viereck(x,y, Color.cyan));
			if (e.getSource()==Bdreieck)
				ll.add(new Dreieck(x,y, Color.green));
			if (e.getSource()==Brechteck)
				ll.add(new Rechteck(x,y, Color.yellow));
			paintComponent(s2.getGraphics());
		}
	}
}
