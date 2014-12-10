package simplecalc;
/**
 * Auf Basis des MVC-Konzeptes wird hier ausschlie�lich die Logik 
 * bzw. Funktionalit�t abgebildet.<br/>
 * Diese Funktionalit�t ist somit vollkommen abgekoppelt von Ein- und
 * Ausgabe-Pr�sentation.
 * <br/>
 * Weiteres ist das Modell so konzipiert, dass auch eine einfache
 * Skalierbarkeit und Erweiterbarkeit gew�hrleistet ist.
 * <br/>
 * Auf Basis dieses Modells (derzeit Integer) kann sehr einfach
 * der Datentyp f�r den Calculator ge�ndert werden (z.b BigInteger)
 * <br/>
 * Auch eine �nderung in einen Flie�komma-Datentyp sollten keinen
 * gr��eren Aufwand geben.
 * <br/>
 * Hinweis: Dieses Modell kann (derzeit) nur mit bin�ren Operationen
 * (Operationen mit 2 Operanden) arbeiten. Bei Umstellung auf un�re 
 * Operationen sind kleinere Anpassungen notwendig.
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 */
public class CalcLogic {
    
    
    private int currentTotal;   // Derzeitige Zwischensumme.
    
    /**
     * Konstruktor
     */
    public CalcLogic() {
        currentTotal = 0;
    }
    /**
     * Ausgabe der aktuelle Zwischensumme als String
     * @return aktuelle Zwischensumme als String
     */
    public String getTotalString() {
        return "" + currentTotal;
    }
    /**
     * Berechnung der aktuellen Zwischensumme inklusive Konvertierung 
     * @param n zukovertierender String
     */
    public void setTotal(String n) {
        currentTotal = convertToNumber(n);
    }
    /**
     * Addition 2er Zahlen
     * @param n 2. Operand (1. Operand ist die Zwischensumme!)
     */
    public void add(String n) {
        currentTotal += convertToNumber(n);
    }
    /**
     * Subtraktion 2er Zahlen
     * @param n 2. Operand (1. Operand ist die Zwischensumme!)
     */
    public void subtract(String n) {
        currentTotal -= convertToNumber(n);
    }
    /**
     * Multiplikation 2er Zahlen
     * @param n 2. Operand (1. Operand ist die Zwischensumme!)
     */
    public void multiply(String n) {
        currentTotal *= convertToNumber(n);
    }
    /**
     * Division 2er Zahlen
     * @param n Divisor (Dividend ist die Zwischensumme!)
     */
    public void divide(String n) {
        currentTotal /= convertToNumber(n);
    }
   /**
    * Zentrale Konvertierung eines Strings in den gew�hlten Datentyp
    * <br/> derzeit Integer!
    * @param n zu konvertierender String
    * @return
    */
    private int convertToNumber(String n) {
        return Integer.parseInt(n);
    }
}
