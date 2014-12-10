package Figur;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figur {	
	private int xkord;
	private int ykord;
	private Color farbe;
   
	public Figur(int xkord, int ykord, Color farbe) {
		this.xkord = xkord;
		this.ykord = ykord;
		this.farbe = farbe;
	}
	public int getXkord() {
		return xkord;
	}
	public void setXkord(int xkord) {
		this.xkord = xkord;
	}
	public int getYkord() {
		return ykord;
	}
	public void setYkord(int ykord) {
		this.ykord = ykord;
	}
	public Color getFarbe() {
		return farbe;
	}
	public void setFarbe(Color farbe) {
		this.farbe = farbe;
	}
	
	public abstract void draw(Graphics objekt); 
	
	public String toString(){
		return "";
	}
	
	
	
}
