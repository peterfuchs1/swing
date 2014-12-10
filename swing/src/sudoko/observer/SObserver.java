package sudoko.observer;
/**
 * Interface fuer die Sudoko-Oberserver
 * @author Walter Rafeiner-Magor
 *
 */
public interface SObserver {
	// Aenderung des Wertes id=Sudoko, spalte, zeile, neuer Wert, alter Wert
	public void valueChanged(int id, int col, int row, int value,int prevValue);
}
