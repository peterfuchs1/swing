package Figur;

import java.awt.Color;
import java.awt.Graphics;

public class Viereck extends Figur {


	public Viereck(int xkord, int ykord, Color farbe) {
		super(xkord, ykord, farbe);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void draw(Graphics objekt) {
		// TODO Auto-generated method stub
		objekt.setColor(getFarbe());
		objekt.fillRect(getXkord(), getYkord(), 20, 20);
	}
}
