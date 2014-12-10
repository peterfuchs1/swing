package simplecalcV5;

/**
 * Das Kalkulationsmodell für den Taschenrechner<br/> 
 * Entsprechend der Trennung von MVC wird hier nur das Modell abgebildet
 * <br/><br/>
 * Vorteile:
 * <li> Das Modell ist klein und läßt sich für GUI, für Konsolenanwendungen
 *    oder aber auch verwenden. 
 * <li> Für die bessere Arbeitteilung der Entwickler
 * <li> Einfaches Anpassen des darunterliegenden Datentyps (z.B. BigInteger, Long)
 *   ohne das UI anpassen zu müssen
 * <br/>  
 * Erweiterung mit Fließkommazahlen ist ebenfalls möglich, erfordert aber auch
 * eine Anpassung des Interfaces (Controll & View)<br/><br/>  
 * 
 * @author Walter Rafeiner-Magor
 * @version 2.0
 */
public class CalcLogic2<T extends Number>  {
    
    // Attribute
    private double currentTotal;   // aktuelle Summe
    
    /**
     * Konstruktor
     */
    public CalcLogic2() {
        currentTotal =0 ;
    }
    /**
     * Ausgabe der aktuellen Summe
     * @return
     */
    public String getTotalString() {
        return "" + new Double(currentTotal).toString();
    }
    /**
     * Setzen der aktuellen Summe
     * @param n neuer Wert
     */
    public void setTotal(String n) {
        currentTotal = convertToNumber(n);
    }
    /**
     * Addition
     * @param n Operand 2
     */
    public void add(String n) {
        currentTotal =currentTotal+ convertToNumber(n);
    }
    /**
     * Subtraktion
     * @param n Operand 2
     */
    public void subtract(String n) {
        currentTotal -= convertToNumber(n);
    }
    /**
     * Multiplikation
     * @param n Operand 2
     */
    public void multiply(String n) {
        currentTotal *= convertToNumber(n);
    }
    /**
     * Division
     * @param n Divisor
     */
    public void divide(String n) {
        currentTotal /= convertToNumber(n);
    }
    /**
     * Vorzeichenwechsel
     * @param n aktueller Wert
     * @return n*-1
     */
    public String changeSign(String n) {
		return new Double(convertToNumber(n)*-1).toString();
    }
    /**
     * Quadratwurzel
     * @param n aktueller Wert
     * @return n^0.5
     */
    public String sqrt(String n) {
		return new Double(Math.sqrt(convertToNumber(n))).toString();
    }
    /**
     * Quadrat
     * @param n aktueller Wert
     * @return n*n
     */
    public String square(String n) {
		return new Double(Math.pow(convertToNumber(n),2)).toString();
    }
    /**
     * Modulo
     * @param n Operand 2
     */
    public void modulo(String n) {
        currentTotal %= convertToNumber(n);
    }
    /**
     * Umrechnung in BasisDatentyp - derzeit Double
     * @param n String-Repräsentation
     * @return Wert in Double
     */
    private double convertToNumber(String n) {
        return Double.parseDouble(n);
    }
}
