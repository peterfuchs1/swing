package Figur;

import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Figur {

	public Kreis(int xkord, int ykord, Color farbe) {
		super(xkord, ykord, farbe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics objekt) {
		// TODO Auto-generated method stub
		objekt.setColor(getFarbe());
		objekt.fillOval(getXkord()+95, getYkord()+2, 10, 10);//Zeichne 
	}

}
