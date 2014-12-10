package simpleframe;
import javax.swing.JFrame; 
 
public class TopLevelDemo 
{ 
  public static void main( String[] args ) 
  { 
	// Erstelle ein Objekt der Klasse JFrame mit Titel  
    JFrame f = new JFrame( "Das Fenster zur Welt" ); 
    // Definiere die Aktion, welche beim Schlie�en des Fensters 
    //ausgef�hrt werden soll
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    // Definiere die Gr��e des Fensters
    f.setSize( 300, 200 ); 
    // Mache den Frame sichtbar!
    f.setVisible( true ); 
  } 
}