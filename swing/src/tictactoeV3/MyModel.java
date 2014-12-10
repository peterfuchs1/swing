package tictactoeV3;

public class MyModel {
	private Spieler[] gewinn;				// Matrix aller Feldelemente
	private int row;					// Anzahl der Spalten=Zeilen
										// = Anzahl der notwendigen Felder für 
										// Gewinnkonstellation
	private boolean[] ergebnis;
	/**
	 * @param row
	 */
	public MyModel(int row) {
		this.row = row;
		gewinn=new Spieler[row*row];
		ergebnis=new boolean[row*row];
	}


	/**
	 * Testet ob der Spieler mit dem Zeichen c
	 * bereits eine Gewinnerkonstellation erreicht hat<br/>
	 * <b>Achtung: Die Anzahl der Spalten=Zeilen ist variable (row)<br/>
	 * Anzahl entspricht auch der notwendigen Felder
	 * für eine Gewinnkonstellation</b>
	 *  
	 * @param c Zeichen des Spielers
	 * @return hat der Spieler bereits gewonnen?
	 */
	public boolean sieger(Spieler c) {
		boolean gewonnen=false;
		//Diagonale links oben beginnend
		int nextValue=row+1;
		if(gewinn[0]==c){
			ergebnis[0]=true;
			gewonnen=true;
			for(int i=nextValue;gewonnen&&i< row*row;i+=nextValue){
				ergebnis[i]=true;
				if(gewinn[i]!=c) {
					gewonnen=false;
					ergebnis[0]=false;
					ergebnis[i]=false;
					break;
				}
			}
		}
		if(gewonnen) return gewonnen;
		//Diagonale rechts oben beginnend
		if(gewinn[row-1]==c){
			gewonnen=true;
			ergebnis[row-1]=true;
			nextValue=row-1;
			for(int i=nextValue;gewonnen&&i< row*(row-1)+1;i+=nextValue){
				ergebnis[i]=true;
				if(gewinn[i]!=c) {
					gewonnen=false;
					ergebnis[i]=false;
					ergebnis[row-1]=false;
					break;
				}
			}
		}	
		if(gewonnen) return gewonnen;	
		//Zeilen und Spalten
		for (int i=0; !gewonnen && i<row;i++) {
			int a=i*row; //Aktuelle Zeile errechnen
			//Horizontaler Sieg
			if(gewinn[a]==c){
				ergebnis[a]=true;
				gewonnen=true;
				for(int j=a+1;j<a+row;j++){
					ergebnis[j]=true;
					if(gewinn[j]!=c){
						ergebnis[a]=false;
						ergebnis[j]=false;
						gewonnen=false;
						break;
					}			
				}
				if (gewonnen) return gewonnen;
			}
			
			//Vertikaler Sieg
			if(!gewonnen && gewinn[i]==c){
				ergebnis[i]=true;
				gewonnen=true;
				for(int j=i+row;j<row*row;j+=row){
					ergebnis[j]=true;
					if(gewinn[j]!=c){
						ergebnis[i]=false;
						ergebnis[j]=false;
						gewonnen=false;
						break;
					}
				}
				if (gewonnen) return gewonnen;
			}
		}
		
		return gewonnen;
	}
	
	/**
	 * @return the ergebnis
	 */
	public boolean[] getErgebnis() {
		return ergebnis;
	}


	/**
	 * Alle Elemente leeren mit null
	 */
	public void init(){
		for(int i=0;i<gewinn.length;i++){  
			gewinn[i]=null;
			ergebnis[i]=false;
		}
	}
	public void initErgebnis(){
		for(int i=0;i<gewinn.length;i++){  
			ergebnis[i]=false;
		}
	}
	/**
	 * Setze Wert an gegebener Stelle
	 * @param index im Array
	 * @param c zu setzender Werte
	 */
	public void setGewinn(int index, Spieler sp){
		gewinn[index]=sp;
	}
	/**
	 * Wert an gegebener Stelle 
	 * @param index
	 * @return Wert am index
	 */
	public Spieler valueAt(int index){
		return gewinn[index];
	}
	/**
	 * Sind schon alle Felder belegt?
	 * @return 
	 */
	public boolean full(){
		boolean voll=true;
		for(int i=0;voll && i<gewinn.length;i++){
			if (gewinn[i]==null) voll=false; 
		}
		return voll;
	}

}
