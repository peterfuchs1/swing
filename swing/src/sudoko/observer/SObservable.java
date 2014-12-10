package sudoko.observer;
/**
 * Interface fuer das Sudoko-Observable
 * @author Walter Rafeiner-Magor
 *
 */
public interface SObservable {
	public void addObserver(SObserver o); // neuen Beobachter hinzufuegen
	public void deleteObserver(SObserver o); // vorhandenen Beobachter entfernen
	public void notifyObservers(int id,int col, int row, int value, int prevValue); //Beobachter informieren, wo es eine Aenderung gab


}
