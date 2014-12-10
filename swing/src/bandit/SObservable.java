package bandit;
/**
 * Interface fuer das Sudoko-Observable
 * @author Walter Rafeiner-Magor
 *
 */
public interface SObservable {
	public void addObserver(SObserver o); // neuen Beobachter hinzufuegen
	public void deleteObserver(SObserver o); // vorhandenen Beobachter entfernen
	public void notifyObservers(); //Beobachter informieren, wo es eine Aenderung gab
	public void notifyObserversFinished(); //Beobachter informieren, dass Berechnung ist fertig


}
