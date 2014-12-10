/**
 * 
 */
package lightsout.fehler;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;

/**
 * Lichter erweitern JButton
 * Mittels statischer HashMap werden die
 * vorhandenen Lichter gespeichert
 * 
 * @author Walter Rafeiner-Magor
 * @version 1.0
 * 
 */

public class Lights extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710757753170595686L;
	/**
	 * Objekt-Attribute
	 */
	private int xCoord, yCoord; 	// Koordinaten
	private boolean on;				// Lichtstatus
	private Color colorOn;			// Farbe bei Licht
	private Color colorOff;			// Farbe bei Dunkelheit
	/**
	 * statische Attribute
	 */
	private static int lines = 0;	// Anzahl der Zeilen=Spalten
	// Collection: Hält alle vorhandenen Lichter
	private static HashMap<String, Lights> lights = new HashMap<String, Lights>();

	/**
	 * Standardkonstruktor 
	 */
	public Lights() {
		this(0, 0);
	}

	/**
	 * Basis-Konstruktor mit 2 Koordinaten
	 * @param xCoord
	 * @param yCoord
	 */
	public Lights(int xCoord, int yCoord) {
		this(xCoord, yCoord, false);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Konstruktor mit 3 Parameter
	 * @param xCoord
	 * @param yCoord
	 * @param on
	 */
	public Lights(int xCoord, int yCoord, boolean on) {
		this(xCoord, yCoord, on, Color.yellow);

	}

	/**
	 * Konstruktor mit 4 Parameter
	 * @param xCoord
	 * @param yCoord
	 * @param on
	 * @param color
	 */
	public Lights(int xCoord, int yCoord, boolean on, Color color) {
		this(xCoord, yCoord, on, color, Color.black);
	}

	/**
	 * Konstruktor mit allen Parameter
	 * @param xCoord	X-Koordinate
	 * @param yCoord	Y-Koordinate
	 * @param on		Lichtstatus
	 * @param colorOn	Farbe bei Licht
	 * @param colorOff	Farbe bei Dunkelheit
	 */
	public Lights(int xCoord, int yCoord, boolean on, Color colorOn, Color colorOff) {
		this.setSize(80, 80);
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.on = on;
		this.colorOn = colorOn;
		this.colorOff = colorOff;
		this.setToggleBackground();
		this.setVisible(true);
		// Fügt das Licht zur Collection hinzu
		Lights.lights.put("" + xCoord + yCoord, this);

	}

	/**
	 * @return the xCoord
	 */
	public int getXCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord
	 *            the xCoord to set
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getYCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord  the yCoord to set
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	 * Ändert den Lichtstatus an gegebenen Buttons
	 * @param x	x-Koordinate
	 * @param y y-Koordinate
	 * @param around auch angrenzende Felder invertieren?
	 */
	public static void switchLight(int x, int y, boolean around) {
		// center
		Lights.lights.get("" + x + y).toggleOn();
		if (around) {
			// left
			if (x > 0)
				Lights.lights.get("" + (x - 1) + y).toggleOn();
			// right
			if (x < (Lights.lines - 1))
				Lights.lights.get("" + (x + 1) + y).toggleOn();
			// top
			if (y > 0)
				Lights.lights.get("" + x + (y - 1)).toggleOn();
			// bottom
			if (y < (Lights.lines - 1))
				Lights.lights.get("" + x + (y + 1)).toggleOn();
		}

	}

	/**
	 * Gibt die Anzahl der lighton Button zurück
	 * 
	 * @return Anzahl
	 */
	public static int lightsOn() {

		int ret = 0;
		for (int i = 0; i < Lights.lines; i++)
			for (int j = 0; j < Lights.lines; j++) {
				if (Lights.lights.get("" + i + j).isOn())
					ret++;
			}
		return ret;
	}

	/**
	 * @return the on
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * @return the lines
	 */
	public static int getLines() {
		return lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public static void setLines(int lines) {
		Lights.lines = lines;
	}

	/**
	 * @param on
	 *            the on to set
	 */
	public void setOn(boolean on) {
		this.on = on;
	}
	/**
	 * Ändert und setzt den neuen Lichtstatus
	 * 
	 */
	public void toggleOn() {
		this.on = (this.on) ? false : true;
		this.setToggleBackground();
	}
	/**
	 * Setzt eine neue Hintergrundfarbe
	 */
	public void setToggleBackground() {
		if (on)
			this.setBackground(colorOn);
		else
			this.setBackground(colorOff);

	}

	/**
	 * @return the colorOn
	 */
	public Color getColorOn() {
		return colorOn;
	}

	/**
	 * @param colorOn
	 *            the colorOn to set
	 */
	public void setColorOn(Color colorOn) {
		this.colorOn = colorOn;
	}

	/**
	 * @return the colorOff
	 */
	public Color getColorOff() {
		return colorOff;
	}

	/**
	 * @param colorOff
	 *            the colorOff to set
	 */
	public void setColorOff(Color colorOff) {
		this.colorOff = colorOff;
	}
}