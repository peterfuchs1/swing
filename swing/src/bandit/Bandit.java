/**
 * 
 */
package bandit;

import java.util.Iterator;
import java.util.LinkedList;



/**
 * @author uhs374h
 *
 */
public class Bandit extends Thread implements SObservable{
	public static final long DURATION=1000L; // maximale Dauer
	public static final int MAX=10; // maximale Zahl
	public static int LAST_NUMBER=0;
	private long duration;
	private int max;
	private int value;
	private int number;
	private LinkedList<SObserver> observers; // Liste aller Beobachter
	
	
	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}



	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}



	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}



	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}



	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}



	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	public Bandit(String name,int number) {
		this(name,number,DURATION,MAX);
	}

	public Bandit(int number) {
		this("Bandit",number,DURATION,MAX);
	}

	/**
	 * @param duration
	 * @param max
	 */
	public Bandit(String name,int number, long duration, int max) {
		super(name);
		this.duration = duration;
		this.max = max;
		this.number=number;
		observers=new LinkedList<SObserver>();
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		long ende=System.nanoTime()+this.duration*1000000;
		while(ende>System.nanoTime()){
			value=(int) (Math.random()*max)+1;
//			this.print();
			this.notifyObservers();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {	}
			
		}
		this.notifyObserversFinished();
	}

	public void print(){
		System.out.println(this.getName()+": Der aktuelle Wert: "+this.value);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
		new Bandit("Banddit",i++).start();
		new Bandit("Banddit",i++).start();
		new Bandit("Banddit",i++).start();
	}


	@Override
	/**
	 * Einen Beobachter hinzufügen
	 * 
	 */
	public void addObserver(SObserver o) {
		observers.add(o);
	}
	/**
	 * Einen Beobachter entfernen
	 */
	@Override
	public void deleteObserver(SObserver o) {
		int index=observers.indexOf(o);
		if(index >=0)
			observers.remove(index);
	}

	/**
	 * Informiere die Beobachter ueber Aenderungen eines Eingabefeldes
	 * @param value		neuer Wert des Eingabefeldes
	 */
	@Override
	public void notifyObservers() {
		// Alle Beobachter dieses Banditen informieren
		Iterator<SObserver> iter=observers.iterator();
		while(iter.hasNext())
			iter.next().valueChanged(number,value);
	}
	/**
	 * Informiere die Beobachter ueber Aenderungen eines Eingabefeldes
	 * @param value		neuer Wert des Eingabefeldes
	 */
	@Override
	public void notifyObserversFinished() {
		// Alle Beobachter dieses Banditen informieren
		Iterator<SObserver> iter=observers.iterator();
		while(iter.hasNext())
			iter.next().finished(number,value);
	}

}
