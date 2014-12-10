package bandit;
/**
 * Interface fuer die Sudoko-Oberserver
 * @author Walter Rafeiner-Magor
 *
 */
public interface SObserver {
	// Aenderung des Wertes 
	public void valueChanged(int number,int value);
	public void finished(int number,int value);
}
