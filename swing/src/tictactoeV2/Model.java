package tictactoeV2;

public class Model {
	private char[] gewinn;				// Matrix aller Feldelemente
	private int row;					// Anzahl der Spalten=Zeilen
										// = Anzahl der notwendigen Felder für 
										// Gewinnkonstellation
	
	/**
	 * @param row
	 */
	public Model(int row) {
		this.row = row;
		gewinn=new char[row*row];
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
	public boolean sieger(char c) {
		boolean gewonnen=false;
		//Diagonale links oben beginnend
		int nextValue=row+1;
		if(gewinn[0]==c)
			for(int i=nextValue;i< row*row;i+=nextValue){
				gewonnen=true;
				if(gewinn[i]!=c) {
					gewonnen=false;
					break;
				}
			}
		
		
		//Diagonale rechts oben beginnend
		if(!gewonnen && gewinn[row-1]==c)
			nextValue=row-1;
			for(int i=nextValue;i< row*row;i+=nextValue){
				gewonnen=true;
				if(gewinn[i]!=c) {
					gewonnen=false;
					break;
				}
			}
			
		//Zeilen und Spalten
		for (int i=0; !gewonnen && i<row;i++) {
			int a=i*row; //Aktueller Zeile errechnen
			//Horizontaler Sieg
			if(gewinn[a]==c){
				gewonnen=true;
				for(int j=a+1;j<a+row;j++){
					if(gewinn[j]!=c){
						gewonnen=false;
						break;
					}
					
				}
			}
			
			//Vertikaler Sieg
			if(!gewonnen && gewinn[i]==c){
				gewonnen=true;
				for(int j=i+row;j<row*row;j+=row){
					if(gewinn[j]!=c){
						gewonnen=false;
						break;
					}
				}
				
			}
		
		}
		
		return gewonnen;
	}
	/**
	 * Alle Elemente leeren mit ' '
	 */
	public void init(){
		for(int i=0;i<gewinn.length;i++)  gewinn[i]=' ';
	}
	/**
	 * Setze Wert an gegebener Stelle
	 * @param index im Array
	 * @param c zu setzender Werte
	 */
	public void setGewinn(int index, char c){
		gewinn[index]=c;
	}
	/**
	 * Wert an gegebener Stelle 
	 * @param index
	 * @return Wert am index
	 */
	public char valueAt(int index){
		return gewinn[index];
	}
	/**
	 * Sind schon alle Felder belegt?
	 * @return 
	 */
	public boolean full(){
		boolean voll=true;
		for(int i=0;voll && i<gewinn.length;i++){
			if (gewinn[i]==' ') voll=false; 
		}
		return voll;
	}
}
