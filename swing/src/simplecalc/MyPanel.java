/**
 * 
 */
package simplecalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Die Panel-Klasse vereint laut dem MVC-Konzept die Viewer und Controller-Komponente<br/>
 * Die Präsentation erfolgt in vier Teilen:
 * <li> Anzeige (displyField)
 * <li> Nummern-Buttons (numButtons[])
 * <li> Operation-Buttons (opButtons[])
 * <li> Lösch-Button (clearButton)
 *<br/><br/>
 * Für alle möglichen Ereignisse werden in Funktionsgruppen spezielle Listeners zur Verfügung gestellt:
 * <li> Operationen (OpListener)
 * <li> Ziffern (NumListener)
 * <li> zum Zurücksetzen (ClearListener)
 * <li> für die Bearbeitung von Tastatureingaben (KeyListener)
 * <br/><br/>
 * Das Abarbeiten der Ereignisse erfolgt in speziellen Methoden:
 * <li> für die Operationen (opTyped)
 * <li> für die Ziffern (numTyped)
 * <li> für das Zurücksetzen (actionClear)
 * <br/><br/>
 *
 * 
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	// Konstanten
	private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	private static final String[] OPERATIONS=new String[]{"+","-","*","/","="};
	private static final Color CALC_BACKGROUND=new Color(0,191,255); //skyBlue
	// Indizes für die Listener
	private static	TreeMap<String,Integer> operations=new TreeMap<String,Integer>();
	private static ArrayList<String> numbers=new ArrayList<String>();
	// Attribute
	private boolean   startNumber = true;      // true: num key next
	private String    previousOp  = "=";       // letzte Operation
	private CalcLogic calcModel = new CalcLogic(); // Das Berechnungmodell.
	private JTextField displayField;	// Anzeige
	private JButton clearButton;	// Anzeige löschen
	private JButton[] opButtons;	//Operation-Buttons
	private JButton[] numButtons;  //numerische Buttons
	// Listener
	private NumListener numListener;
	private OpListener opListener;
	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Operationindex wird aufgebaut
		for(int i=0;i<OPERATIONS.length;i++)
			operations.put(OPERATIONS[i], i);
		// Ziffernindex wird aufgebaut
		for (int i=0;i<=9;i++) {
			Integer iT=new Integer(i);
			numbers.add(iT.toString());
		}

		// Anzeige des Aus- und Eingabefeldes
		this.displayField=new JTextField("0");
		this.displayField.setFont(BIGGER_FONT);
		this.displayField.setHorizontalAlignment(JTextField.RIGHT);
		this.displayField.addKeyListener(new MyKeyListener());
		this.displayField.setBackground(Color.YELLOW);

		
		//////////////////////////////////////////////////////////
		// numButtons
		// Der NumListener muss mehrfach verwendet werden
		numListener=new NumListener();
		
		//... Das Layout der Ziffern in 5 Zeilen und 3 Spalten
        //    in a loop from the chars in a string.
        String[] buttonOrder = {"7","8","9","4","5","6","1","2","3"," ","0"," "};
        this.numButtons=new JButton[buttonOrder.length];
        // ButtonPanel erstellen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(CALC_BACKGROUND);
        //Layout auf GridLayout (5 Zeilen, 3 Spalten) ändern
        buttonPanel.setLayout(new GridLayout(5, 3, 4, 4));
        for (int i = 0; i < buttonOrder.length; i++) {
        	numButtons[i]=new JButton(buttonOrder[i]);
            if (buttonOrder[i].equals(" ")) {
                //... Put a dummy button in this position.
            	numButtons[i].setEnabled(false);
            } else {
                //... Put a digit button in the interface.
            	numButtons[i].addActionListener(numListener);
            	numButtons[i].setFont(BIGGER_FONT);
            }
            buttonPanel.add(numButtons[i]);
        }
		///////////////////////////////////////////////////////////
        // OPButtons
        // Der OpListener muss mehrfach verwendet werden
        opListener = new OpListener();
        //OP-Panel erstellen
        JPanel opPanel = new JPanel();
        // Layout auf GridLayout (5 Zeilen, 1 Spalte) ändern
        opPanel.setLayout(new GridLayout(5, 1, 4, 4));
        opPanel.setBackground(CALC_BACKGROUND);
        // Buttons aus der OPERATIONS-Konstante erstellen
        // und dem Panel hinzufügen
        opButtons=new JButton[OPERATIONS.length];
        for (int i = 0; i < OPERATIONS.length; i++) {
        	opButtons[i]=new JButton(OPERATIONS[i]);
        	opButtons[i].addActionListener(opListener);
        	opButtons[i].setFont(BIGGER_FONT);
            opPanel.add(opButtons[i]);
        }
        ////////////////////////////////////////////////////////////
        // Clear-Panel mit Standard-Layout (FlowLayout)
        JPanel clearPanel = new JPanel();
        clearPanel.setBackground(CALC_BACKGROUND);
        // ClearButton
		this.clearButton=new JButton("clear");
		this.clearButton.addActionListener(new ClearListener());
        clearPanel.add(clearButton);

        ////////////////////////////////////////////////////////////
        // Main Panel
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(CALC_BACKGROUND);
        this.add(displayField  , BorderLayout.NORTH );
        this.add(buttonPanel   , BorderLayout.CENTER);
        this.add(opPanel       , BorderLayout.EAST  );
        this.add(clearPanel    , BorderLayout.SOUTH );
        // 10 Pixel Abstand in alle Richtungen
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        ////////////////////////////////////////////////////////////
        
        
        
	}
	 /**
	  * Zurücksetzen der Eingaben
	  */
	 private void actionClear() {
	        startNumber = true;         // Start mit einer Ziffer
	        displayField.setText("0");	// Initialwert
	        previousOp  = "=";			
	        calcModel.setTotal("0");	// Initialwert
	 }
	/**
	 * ClearListener
	 * Führt ein Reset durch  
	 * @author Walter Rafeiner-Magor
	 *
	 */
	private class ClearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			actionClear();
			// Den Focus zurück ins Textfield setzen
			displayField.requestFocus();
		}
		
	}
	/**
	 * Eingegebene Ziffer wird angezeigt
	 * @param ziffer 
	 */
	private void numTyped(String ziffer) {
		this.numTyped(ziffer,false);
	}
	/**
	 * Eingegebene Ziffer wird angezeigt
	 * Falls die Ziffer bereits über Keyboard im
	 * TextField steht, kommt es zu einer Verdopplung!
	 * @param ziffer
	 * @param consume 
	 */
	private void numTyped(String ziffer, boolean consume) {
		//Falls die Ziffer bereits über Keyboard eingegeben wurde
		if (consume) ziffer="";
		if(startNumber) {
            //Die erste eingegebene Ziffer
            displayField.setText(ziffer);
            startNumber = false;
        } else {
            //weitere Ziffer eingegeben
            displayField.setText(displayField.getText() + ziffer);
        }

	}
	//OPListener
	/**
	 * Interpretiert Operation-Buttons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0 
	 */
	private class OpListener implements ActionListener {

		@Override
        public void actionPerformed(ActionEvent e) {
            // Der Rechner kennt 2 Stati
            // 1. Eine Ziffer wird erwartet - ein Operator ist falsch!
            // 2. Ein Operator wird erartet
            if (startNumber) { // Error: eine Ziffer ist notwendig - Fehler
                //... aber ein Operator wurde verwendet"
//                actionClear();
                displayField.setText("Fehler:kein Operator!");
            } else {
            	// OperatorButtons auswerten
            	opTyped(e.getActionCommand());
           }
            // Den Focus zurück ins Textfield setzen 
            displayField.requestFocus();
        }
		
	}
	/**
	 * Auswerten der Operation
	 * Ausgeben des Ergebnisses
	 * @param s Operation
	 */
	private void opTyped(String s) {
 
        startNumber = true;  // Als nächstes kommt eine Ziffer!
        try {
            // Der Wert aus dem Textfield wird ausgewertet
            String displayText = displayField.getText();
            // Entsprechend dem Operationindex
            // wird die Operation angestoßen
            switch(operations.get(previousOp)){
            //"+"
            	case 0:calcModel.add(displayText);break;
            //"-"
            	case 1:calcModel.subtract(displayText);break;
            //"*"
            	case 2:calcModel.multiply(displayText);break;
            //"/"
            	case 3:calcModel.divide(displayText);break;
            //"="
            	case 4:calcModel.setTotal(displayText);break;
            }
            // Das Ergebnis wird angezeigt
            displayField.setText("" + calcModel.getTotalString());
            
        } catch (NumberFormatException ex) {
            actionClear();
            displayField.setText("Fehler");
        }
        
        // der aktuelle Operator wird nun als previousOp verwendet
        previousOp = s;

	}
	
	// NumListener
	/**
	 * NumListener
	 * Interpretiert Number-Buttons
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class NumListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// Der gedrückte Button wird ausgewertet
			numTyped(e.getActionCommand());
			// Der Focus wird zurück ins Textfield gesetzt
			displayField.requestFocus();
		}
		
	}
	/**
	 * MyKeyListener
	 * Interpretiert Tastendruck im Textfield
	 * für Ziffern und Operationen
	 * 
	 * @author Walter Rafeiner-Magor
	 * @version 2.0
	 */
	private class MyKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// Enter wird in = umgewandelt!
			char c=e.getKeyChar();
			if(c==KeyEvent.VK_ENTER) c='=';
			String s=""+c;
			// Falls eine Nummer eingeben wurde, wie numButtons verwenden
			if(numbers.contains(s))
				numTyped(s,true);
			else
				// Falls eine Operation verwendet wurde
				if (operations.containsKey(s)) {
					// Operation auswerten
					opTyped(s);
					// aber nicht anzeigen!
					e.consume();
				}
			// Ansonsten den Tastendruck vergessen ;-)
			else e.consume();
		}
		
	}


}
