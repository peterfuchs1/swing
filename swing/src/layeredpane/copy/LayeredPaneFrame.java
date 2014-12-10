package layeredpane.copy;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class LayeredPaneFrame extends JFrame implements ActionListener {
 
    private static final long serialVersionUID = 1L;
 
    private final static int WINDOW_WIDTH = 400;
    private final static int WINDOW_HEIGHT = 400;
 
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
 
    private ModalWindow modalWindow;
 
    public LayeredPaneFrame() {
        super("Beispielfenster");
        getContentPane().add(createOverlay());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
 
    public static void main(String[] argv) {
        new LayeredPaneFrame();
    }
 
    /**
     * Diese Methode zeigt die Verwendung des JLayeredPane.
     *
     * @return
     */
    public JLayeredPane createOverlay() {
 
        // Neue Instanz des JLayeredPane erzeugen und einrichten
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        layeredPane.setBorder(BorderFactory.createEmptyBorder());
 
        // Jetzt kommen die Elemente hinzu:
        // Der unterste Layer soll das gesamte Fenster ausfuellen und volle
        // Deckkraft haben
        JPanel layer0 = new JPanel(null);
        layer0.setBackground(Color.white);
        layer0.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
 
        // Wichtig: mit SetBounds Groesse und Position festlegen
        // Ohne setBounds wird das Element nicht angezeigt!!
        layer0.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Wichtig: Bei der Angabe der "Z-Ordner" muss ein Integer-Objekt
        // uebergeben werden, keinen primitiven Datentyp verwenden
        layeredPane.add(layer0, new Integer(0));
 
        // Um die Ueberdeckung weiter zu verdeutlichen, wird dem untersten Layer
        // noch ein JLabel hinzugefuegt
        // Das HTML im String bewirkt automatischen Zeilenumbruch im JLabel
        JLabel staticJLabel = new JLabel("<html><p>Das ist ein darunterliegendens " +
                "JLabel mit statischem Inhalt. Der Text ist schwarz, "
                + "der Hintergrund ist weiﬂ, alles wird korrekt dargestellt.</p></html>");
 
        // Ist nur fuer die Positionierung notwendig, hat nichts mit dem
        // LayeredPane zu tun!
        staticJLabel.setBounds(50, 50, 300, 100);
        layer0.add(staticJLabel);
 
        // Da das Problem ja nur bei sich aktualisierenden Elementen auftritt,
        // wird noch ein weiteres JLabel hinzugefuegt, dass sich jede Sekunde
        // aktualisiert
        final JLabel updatedJLabel = new JLabel("");
        // Ist nur fuer die Positionierung notwendig, hat nichts mit dem
        // LayeredPane zu tun!
        updatedJLabel.setBounds(50, 100, 300, 100);
        layer0.add(updatedJLabel);
 
        // Aktualisierung des JLabels
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updatedJLabel.setText("Countdown: " +
                                    Math.round(Math.random() * 10) + " Sekunden");
                        }
                    });
                } catch (RuntimeException ex) {
                    // Fuer das Beispiel ok, in "echtem" System worst practice
                    // hehe
                    System.out.println(ex);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
 
        // Als naechstes kommt das "modale Fenster"
        layeredPane.add(modalWindow = new ModalWindow(), new Integer(1));
 
        return layeredPane;
    }
 
    /**
     * Da wie oben beschrieben die paintComponent()-Methode ueberschrieben
     * werden muss, definieren wir eine Subklasse von JPanel, um hier die
     * Methode zu ueberschreiben. Das ginge natuerlich mit einer anonymen
     * (Sub-)Klasse, wird aber wegen besserer Lesbarkeit so gemacht.
     *
     */
    private class ModalWindow extends JPanel {
 
        private static final long serialVersionUID = 1L;
 
        public ModalWindow() {
            super(null);
 
            // Wichtig, da sonst darunterliegende Objekte nicht dargestellt
            // werden, auch wenn eine Transparenz angegeben wurde
            setOpaque(false);
            setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
 
            // Auch hier ist wieder wichtig, die Position und Groesse zu setzen.
            setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
 
            // Um zu zeigen, dass enthaltene Elemente die volle Deckkraft
            // behalten, wird ein JButton eingefuegt
            JButton demoButton = new JButton("Overlay ausblenden");
            demoButton.addActionListener(LayeredPaneFrame.this);
            demoButton.setBounds(100, 200, 200, 30);
            add(demoButton);
        }
 
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Color ppColor = new Color(0, 0, 0, 0.5f);
            g.setColor(ppColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Da nur ein Button existiert, muss hier keine Fallunterscheidung
        // stattfinden
        modalWindow.setVisible(false);
    }
}