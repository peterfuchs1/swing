package Figur;

import java.awt.Color;
import java.awt.Graphics;

public class Dreieck extends Figur {

	public Dreieck(int xkord, int ykord, Color farbe) {
		super(xkord, ykord, farbe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics objekt) {
		// TODO Auto-generated method stub
		int [] arx = {getXkord(),getYkord()+100,getXkord()+200};//position für Dreieck
		int [] ary = {getYkord()+50,getYkord(),getYkord()+50};//position für Dreieck
		objekt.setColor(getFarbe());//bestimme Farbe 
		objekt.fillPolygon(arx,ary,arx.length);
	}

}
