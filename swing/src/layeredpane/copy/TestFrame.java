package layeredpane.copy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;





public class TestFrame extends JFrame implements ActionListener {
private JPanel game;		// Panel unten
public static int WINDOW_WIDTH, WINDOW_HEIGHT;
private Dimension d;
private JPanel mW,rp;
private JLayeredPane layeredPane;
private JButton ein,aus,zwei;
	/**
	 * Standard-Konstruktor
	 * 
	 */
	public TestFrame() {
		// festlegung der Überschrift im Frame 
		this.setTitle("Let's play with overlays");
		// Standardoperation für das Schließen des Frames festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   GraphicsEnvironment env =
			     GraphicsEnvironment.getLocalGraphicsEnvironment();
			   /*
			     The next line determines if the   taskbar (win) is covered
			     if unremarked, the task will not be covered by
			     the maximized JFRAME.
			   */
			   // this.setMaximizedBounds(env.getMaximumWindowBounds());
			   this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		Rectangle maxBounds=env.getMaximumWindowBounds();
		WINDOW_HEIGHT=maxBounds.height;
		WINDOW_WIDTH=maxBounds.width;
		d=new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setSize(d);
		// 3 Panels werden erzeugt
		System.out.println("height: "+WINDOW_HEIGHT);
		System.out.println("width: "+WINDOW_WIDTH);
		d=new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setPreferredSize(d);
		
		
		// Die erstellten Panels werden im ContentPane des Frames platziert
		createOverlay();
		this.getContentPane().add(layeredPane);
		// Der Frame wird gepackt
		
		// Der Frame bekommt die Anfangsgröße
		
		// Größe fixieren
//		this.setResizable(false);
		// Nach dem Aufbau, wird der Frame nun auch sichtbar
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setVisible(true);
	}
	   public void createOverlay() {

		   
	        // Neue Instanz des JLayeredPane erzeugen und einrichten
	        //JLayeredPane 
	        layeredPane = new JLayeredPane();
	        layeredPane.setPreferredSize(new Dimension(d));
	        layeredPane.setBorder(BorderFactory.createEmptyBorder());
	 
	        // Jetzt kommen die Elemente hinzu:
	        // Der unterste Layer soll das gesamte Fenster ausfuellen und volle
	        // Deckkraft haben
	        
	        
	        JPanel layer0=new JPanel();
//	        layer0.setBackground(Color.white);
	        layer0.setOpaque(false);
	        
	        layer0.setPreferredSize(new Dimension(d));
	 
	        // Wichtig: mit SetBounds Groesse und Position festlegen
	        // Ohne setBounds wird das Element nicht angezeigt!!
	        layer0.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
	        // Wichtig: Bei der Angabe der "Z-Ordner" muss ein Integer-Objekt
	        // uebergeben werden, keinen primitiven Datentyp verwenden
	        layeredPane.add(layer0, new Integer(1));
            // Um zu zeigen, dass enthaltene Elemente die volle Deckkraft
            // behalten, wird ein JButton eingefuegt
            ein = new JButton("Overlay einblenden");
            ein.addActionListener(this);
//            ein.setBounds(100, 200, 200, 30);
           layer0.add(ein);
	 
	        // Um die Ueberdeckung weiter zu verdeutlichen, wird dem untersten Layer
	        // noch ein JLabel hinzugefuegt
	        // Das HTML im String bewirkt automatischen Zeilenumbruch im JLabel
	        JLabel staticJLabel = new JLabel("<html><p>Das ist ein darunterliegendens " +
	                "JLabel mit statischem Inhalt. Der Text ist schwarz, "
	                + "der Hintergrund ist weiß, alles wird korrekt dargestellt.</p></html>");
	 
	        // Ist nur fuer die Positionierung notwendig, hat nichts mit dem
	        // LayeredPane zu tun!
//	        staticJLabel.setBounds(50, 50, 300, 100);
	        layer0.add(staticJLabel);
	 
	        // Aktualisierung des JLabels
	 
	        // Als naechstes kommt das "modale Fenster"
	        mW= new JPanel();
	        mW.setOpaque(false);
	        mW.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	        
 
            // Auch hier ist wieder wichtig, die Position und Groesse zu setzen.
            mW.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
 
            // Um zu zeigen, dass enthaltene Elemente die volle Deckkraft
            // behalten, wird ein JButton eingefuegt
            aus = new JButton("Overlay ausblenden");
            aus.addActionListener(this);
//            aus.setBounds(100, 200, 200, 30);
//            mW.setBackground(Color.GREEN);
            mW.add(new JLabel(" "));
            mW.add(aus);
       	 
	        // Da das Problem ja nur bei sich aktualisierenden Elementen auftritt,
	        // wird noch ein weiteres JLabel hinzugefuegt, dass sich jede Sekunde
	        // aktualisiert
	        final JLabel updatedJLabel = new JLabel("Ein weiterer Text");
	        // Ist nur fuer die Positionierung notwendig, hat nichts mit dem
	        // LayeredPane zu tun!
//	        updatedJLabel.setBounds(250, 100, 300, 100);

	        mW.add(updatedJLabel);
        
	        layeredPane.add(mW, new Integer(2));

	        // Layer Basis
			   rp=new JPanel(new BorderLayout());
			   rp.setPreferredSize(new Dimension(d));
			   rp.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
			   ImageIcon ii=new ImageIcon("picture.gif");
			   JScrollPane sp=new JScrollPane(new JLabel(ii));
			   rp.add(sp,BorderLayout.CENTER);
			   layeredPane.add(rp, new Integer(0));

	        
	    }
	 
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	        // Da nur ein Button existiert, muss hier keine Fallunterscheidung
	        // stattfinden
	    	JButton o=(JButton)arg0.getSource();
	    	if(o==aus)
	    		mW.setVisible(false);
	    	else 
	    		mW.setVisible(true);
	    }
	    
	public static void main (String[] argv){
		new TestFrame();
	}

}
