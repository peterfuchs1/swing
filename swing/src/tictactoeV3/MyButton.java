package tictactoeV3;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;



public class MyButton extends JButton {
	public static final Color BACKGROUND=Color.LIGHT_GRAY;
	public static final Color FOREGROUND_KREIS=Color.MAGENTA;
	public static final Color FOREGROUND_KREUZ=Color.BLUE;
	public static final Color FOREGROUNG_RECTANGLE=Color.GREEN;
	public Color myBackground;
	private boolean used;
	private boolean kreis;
	private boolean gewinn;
	/**
	 * Standardkonstruktor
	 */
	public MyButton(){
		this(false,false);
	}
	/**
	 * @param used
	 */
	public MyButton(boolean kreis) {
		this(kreis,false);
	}

	/**
	 * @param kreis
	 */
	public MyButton(boolean kreis,boolean used) {
		this.used = used;
		this.kreis = kreis;
		this.myBackground=this.getBackground();
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// kleinste Seite ermitteln
		int size=(this.getHeight()>this.getWidth())?this.getWidth():this.getHeight();
		
		int left=(this.getWidth()-size)/2;
		int right=left+size;
		int up=0;
		int down=this.getHeight();
		if(size>4){
			size-=2;
			left+=2;
			right-=2;
			up=2;
			down-=2;
			size-=2;
		}
		// paint background
		g.setColor(BACKGROUND);
		g.drawOval(left, up, size , size);
		g.drawOval(left+1, up+1, size-2 , size-2);
		g.drawLine(left, up, right, down);
		g.drawLine(left+1, up, right+1, down);
		g.drawLine(right, up, left, down);
		g.drawLine(right+1, up, left+1, down);
		// paint forground
		if(!used) return;
		if(gewinn){
			g.setColor(FOREGROUNG_RECTANGLE);
			g.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
			this.setBackground(Color.WHITE);
		}
		if(kreis){
			g.setColor(FOREGROUND_KREIS);	
			g.drawOval(left, up, size , size);
			g.drawOval(left+1, up+1, size-2 , size-2);
		}
		else{
			g.setColor(FOREGROUND_KREUZ);
			g.drawLine(left, up, right, down);
			g.drawLine(left+1, up, right+1, down);
			g.drawLine(right, up, left, down);
			g.drawLine(right+1, up, left+1, down);
		}	
	}

	/**
	 * @return the gewinn
	 */
	public boolean isGewinn() {
		return gewinn;
	}

	/**
	 * @param gewinn the gewinn to set
	 */
	public void setGewinn(boolean gewinn) {
		this.gewinn = gewinn;
	}

	/**
	 * @return the used
	 */
	public boolean isUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * @return the kreis
	 */
	public boolean isKreis() {
		return kreis;
	}

	/**
	 * @param kreis the kreis to set
	 */
	public void setKreis(boolean kreis) {
		this.kreis = kreis;
	}
	/**
	 * Initialisiert einen MyButton
	 */
	public void init(){
		this.setBackground(myBackground);
		this.setUsed(false);
		this.setEnabled(true);
		this.setGewinn(false);
	}
}
