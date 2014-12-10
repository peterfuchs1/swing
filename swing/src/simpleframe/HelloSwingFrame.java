package simpleframe;
import java.awt.*;
import javax.swing.*; 
 
public class HelloSwingFrame 
{ 
  public static void main( String[] args ) 
  { 
    JFrame f = new JFrame( "TopLevelDemo" ); 
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
  //Erstelle eine Menubar in olivgruen.
    JMenuBar greenMenuBar = new JMenuBar();
    greenMenuBar.setBackground(new Color(154, 165, 127));
    greenMenuBar.setPreferredSize(new Dimension(200, 20));
    //Erstelle ein Label mit Hintergrundfarbe Orange.
    JLabel yellowLabel = new JLabel("JLabel");
    yellowLabel.setOpaque(true); //
    yellowLabel.setBackground(new Color(248, 213, 131));

    //Setze die Menubar und den Label in das ContentPane
    f.setJMenuBar(greenMenuBar);
    f.getContentPane().add(yellowLabel, BorderLayout.CENTER);

    f.setSize( 300, 300 ); 
    f.setVisible( true ); 
  } 
}


