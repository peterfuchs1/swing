package Figur;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Rechteck extends Figur{
	

	public Rechteck(int xkord, int ykord, Color farbe) {
		super(xkord, ykord, farbe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics objekt) {
		// TODO Auto-generated method stub
		objekt.setColor(getFarbe());
		objekt.fillRect(getXkord(), getYkord(), 40, 20);
	}
	

}

