package simpleframe;
import javax.swing.JFrame; 
 
public class TopLevelDemo 
{ 
  public static void main( String[] args ) 
  { 
	// Erstelle ein Objekt der Klasse JFrame mit Titel  
    JFrame f = new JFrame( "Das Fenster zur Welt" ); 
    // Definiere die Aktion, welche beim Schließen des Fensters 
    //ausgeführt werden soll
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    // Definiere die Größe des Fensters
    f.setSize( 300, 200 ); 
    // Mache den Frame sichtbar!
    f.setVisible( true ); 
  } 
}