/**
 * 
 */
package hamlet;

/**
 * @author uhs374h
 *
 */
public class Hamlet {
	private String[] text={
			"Sein oder Nichtsein, das ist hier die Frage:",
			"Ob�s edler im Gem�t, die Pfeil� und Schleudern",
			"Des w�tenden Geschicks erdulden, oder",
			"Sich waffnend gegen eine See von Plagen,",
			"Durch Widerstand sie enden. Sterben � schlafen �",
			"Nichts weiter! � und zu wissen, da� ein Schlaf",
			"Das Herzweh und die tausend St��e endet,",
			"Die unsers Fleisches Erbteil � �s ist ein Ziel",
			"Aufs innigste zu w�nschen. Sterben � schlafen �"
	};
	private static Hamlet instance;
	/**
	 * privater Konstruktor
	 * Singleton
	 */
	private Hamlet(){}
	/**
	 * Singleton
	 * @return Instanz 
	 */
	public static Hamlet getInstance(){
		if(instance==null)
			instance=new Hamlet();
		return instance;
	}
	/**
	 * Gr��e der Textstelle - Anzahl der Zeilen
	 * @return
	 */
	public int size(){
		return text.length;
	}
	/**
	 * Vergleich Textstelle mit position
	 * @param t
	 * @param index
	 * @return
	 */
	public boolean correctPosition(String t,int index){
		if(index < 0 || index >= text.length) return false;
		return text[index].equals(t);
	}
	/**
	 * @return the text
	 */
	public String[] getText() {
		return text;
	}
	/**
	 * Text in entsprechender Zeile
	 * @param index
	 * @return
	 */
	public String getText(int index){
		if(index < 0 || index >= text.length) return null;
		return text[index];
	}
}
