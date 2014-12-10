package simplecalcV2;

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
public class CalcLogic {
    
    //-- Instance variables.
    private int currentTotal;   // The current total is all we need to remember.
    
    /** Constructor */
    public CalcLogic() {
        currentTotal = 0;
    }
    
    public String getTotalString() {
        return "" + currentTotal;
    }
    
    public void setTotal(String n) {
        currentTotal = convertToNumber(n);
    }
    
    public void add(String n) {
        currentTotal += convertToNumber(n);
    }
    
    public void subtract(String n) {
        currentTotal -= convertToNumber(n);
    }
    
    public void multiply(String n) {
        currentTotal *= convertToNumber(n);
    }
    
    public void divide(String n) {
        currentTotal /= convertToNumber(n);
    }
 
    public String changeSign(String n) {
		return new Integer(convertToNumber(n)*-1).toString();
    }

    public String sqrt(String n) {
		return new Integer((int)Math.sqrt(convertToNumber(n))).toString();
    }
    public String square(String n) {
		return new Integer((int)Math.pow((int)convertToNumber(n),2)).toString();
    }
    public void modulo(String n) {
        currentTotal %= convertToNumber(n);
    }
    
    private int convertToNumber(String n) {
        return Integer.parseInt(n);
    }
}
