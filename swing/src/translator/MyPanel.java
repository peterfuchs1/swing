/**
 * 
 */
package translator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A very simple game with randomized sequence of buttons to click.
 * @author uhs374h
 * @version 1.1
 */
public class MyPanel extends JPanel {

	public static final int COLUMNS=40;
	private static final long serialVersionUID = 7511310393424396334L;
	
	private MyController c;
	private JTextField jtfKey;
	private JTextField jtfValue;
	private JButton jbAdd;
	private JTextField jtfToConvert;
	private JButton jbConvert;
	private JLabel lResult;


	/**
	 * constructor
	 * @param c MyConstructor for Listener
	 */
	public MyPanel(MyController c) {
		JPanel line1, line2, line4, line5;
		
		this.setLayout(new GridLayout(5,1));
		line1=new JPanel();
		line1.add(new JLabel("Enter a new combination (key, value)"));
		line1.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		this.add(line1);
		
		line2=new JPanel(new GridLayout(1,5,5,5));
		JLabel lnum=new JLabel("new key:");
		JLabel lchar=new JLabel("new value:");
		jtfKey=new JTextField("");
		jtfKey.addActionListener(c);
		jtfValue=new JTextField("");
		jtfValue.addActionListener(c);
		line2.add(lnum);
		line2.add(jtfKey);
		line2.add(lchar);
		line2.add(jtfValue);
		jbAdd=new JButton("add");
		jbAdd.addActionListener(c);
		line2.add(jbAdd);
		this.add(line2,BorderLayout.CENTER);		
		
		JLabel line3=new JLabel("Enter a text with the pattern key"+
				Translator.DELIMITER+"key"+
				Translator.DELIMITER+"key and so on...");
		this.add(line3);
		line4=new JPanel(new BorderLayout(5,5));
		JLabel ltoConvert=new JLabel("Enter the text here:");
		jtfToConvert = new JTextField(COLUMNS);
		jtfToConvert.addActionListener(c);
		jbConvert= new JButton("convert");
		jbConvert.addActionListener(c);
		line4.add(ltoConvert, BorderLayout.WEST);
		line4.add(jtfToConvert, BorderLayout.CENTER);
		line4.add(jbConvert, BorderLayout.EAST);
		this.add(line4);
		line5=new JPanel(new BorderLayout(5,5));
		JLabel ldesc=new JLabel("The result:");
		
		lResult=new JLabel("no result");
		
		lResult.setFont(new Font("Arial", Font.BOLD|Font.ITALIC, 18));
		line5.add(ldesc, BorderLayout.WEST);
		line5.add(lResult, BorderLayout.CENTER);
		this.add(line5);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}


	// GETTER UND SETTER


	/**
	 * @return the lResult
	 */
	public JLabel getlResult() {
		return lResult;
	}

	/**
	 * @return the jtfKey
	 */
	public JTextField getJtfKey() {
		return jtfKey;
	}
	/**
	 * @return the jtfValue
	 */
	public JTextField getJtfValue() {
		return jtfValue;
	}
	/**
	 * @return the jbAdd
	 */
	public JButton getJbAdd() {
		return jbAdd;
	}
	/**
	 * @return the jtfToConvert
	 */
	public JTextField getJtfToConvert() {
		return jtfToConvert;
	}
	/**
	 * @return the jbConvert
	 */
	public JButton getJbConvert() {
		return jbConvert;
	}
	

	
	
}
