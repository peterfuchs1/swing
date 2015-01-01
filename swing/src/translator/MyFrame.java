package translator;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame(MyPanel p, String header, MyController myController){
		this.addWindowListener(myController);
		this.setTitle(header);
		this.getContentPane().add(p,BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
//		this.pack();
		this.setSize(500, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
}
