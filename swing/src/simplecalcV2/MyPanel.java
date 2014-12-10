/**
 * 
 */
package simplecalcV2;

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
 * @author Walter Rafeiner-Magor
 *
 */
@SuppressWarnings("serial")
public class MyPanel extends JPanel {
	// Konstanten
	private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	private static final Font SMALLER_FONT = new Font("monspaced", Font.PLAIN, 14);
	private static final String[] OPERATIONS=new String[]{
			"+","-","*",
			"/","=","+/-",
			"Sqr","Squ","mod"};
	private static final String[] OP_TOOL_TIP=new String[]{
			"Addition","Subtraktion","Multiplikation",
			"Division","Ergebnis","Vorzeichenwechsel",
			"Wurzel", "Quadrat","Modulo"};
	private static final Color CALC_BACKGROUND=new Color(0,191,255); //skyBlue
	// Indizes für die Listener
	private static	TreeMap<String,Integer> operations=new TreeMap<String,Integer>();
	private static  TreeMap<String,Boolean> op_unare=new TreeMap<String,Boolean>();
	private static ArrayList<String> numbers=new ArrayList<String>();
	// Attribute
	private boolean   startNumber = true;      			// Nun kommt eine Ziffer
	private String    previousOp  = "=";       			// letzte Operation
	private CalcLogic calcModel = new CalcLogic(); 		// Das Berechnungmodell.
	private JTextField displayField;					// Anzeige
	private JButton clearButton;						// Anzeige löschen
	private JButton[] opButtons;						//Operation-Buttons
	private JButton[] numButtons;  						//numerische Buttons
	// Listener
	private NumListener numListener;
	private OpListener opListener;
	/**
	 * Konstruktor
	 */
	public MyPanel() {
		// Operationindex wird aufgebaut
		for(int i=0;i<OPERATIONS.length;i++) {
			operations.put(OPERATIONS[i], i);
			op_unare.put(OPERATIONS[i], false);
		}
		// Für unäre Operationen manuell gesetzt!
		op_unare.put(OPERATIONS[5], true); // changeSign
		op_unare.put(OPERATIONS[6], true); // sqrt
		op_unare.put(OPERATIONS[7], true); // square
		// Ziffernindex wird aufgebaut
		for (int i=0;i<=9;i++) {
			Integer iT=new Integer(i);
			numbers.add(iT.toString());
		}

		// Anzeige des Ein- und Ausgabefeldes
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
        buttonPanel.setLayout(new GridLayout(4, 3, 4, 4));
        for (int i = 0; i < buttonOrder.length; i++) {
        	numButtons[i]=new JButton(buttonOrder[i]);
            if (buttonOrder[i].equals(" ")) {
                //Hier kommt nur ein Platzhalter
            	numButtons[i].setEnabled(false);
            	numButtons[i].setOpaque(false);
            	numButtons[i].setBorderPainted(false);
            } else {
                //Hier kommt ein Ziffern-Button
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
        // Layout auf GridLayout (4 Zeilen) ändern
        opPanel.setBackground(CALC_BACKGROUND);
        
        opPanel.setLayout(new GridLayout(4, 0, 4, 4));
        
        // Buttons aus der OPERATIONS-Konstante erstellen
        // und dem Panel hinzufügen
        opButtons=new JButton[OPERATIONS.length];
        for (int i = 0; i < OPERATIONS.length; i++) {
        	opButtons[i]=new JButton(OPERATIONS[i]);
        	opButtons[i].addActionListener(opListener);
        	if(OPERATIONS[i].length()<3) 
        		opButtons[i].setFont(BIGGER_FONT);
        	opButtons[i].setToolTipText(OP_TOOL_TIP[i]);
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
			// Falls ein unäre Operator kommt, erlauben!
			String s=e.getActionCommand();
           	// OperatorButtons auswerten
        	opTyped(s);
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
		if(op_unare.get(s)) startNumber=false;
		// Auch nach einem Ergebnis weiterrechnen lassen!
		if(previousOp.equals("=")) startNumber=false;
        if (startNumber) { // Error: eine Ziffer ist notwendig - Fehler
            //... aber ein Operator wurde verwendet"
            displayField.setText("Fehler:kein Operator!");
        } else {
 
        	startNumber = true;  // Als nächstes kommt eine Ziffer!
        	try {
        		// Der Wert aus dem Textfield wird ausgewertet
        		String displayText = displayField.getText();
        		// Entsprechend dem Operationindex
        		// wird die Operation angestoßen
        		String temp_op=previousOp;
        		if(op_unare.get(s)){
        			previousOp=s;
        		}
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
            //"+/-"
            	case 5:displayText=calcModel.changeSign(displayText);
            	break;
            //"sqr"
            	case 6:displayText=calcModel.sqrt(displayText);
            	break;
            //"squ"
            	case 7:displayText=calcModel.square(displayText);
            	break;
            //"%"
            	case 8:calcModel.modulo(displayText);break;

        		}
        		// Für unäre Operationen wird das Ergebnis direkt auf einen
        		// Operanden ausgeführt. Bei binären Operationen wird als zweiter
        		// Operand das derzeitige Ergebnis (currentTotal) herangezogen
        		if (op_unare.get(s)) {
        			displayField.setText("" + displayText);
        			// PreviousOP wird zurückgesetzt
        			// neuer Operator verlangt
        			s=temp_op;
        			startNumber=false;
        		}
        		else
        			displayField.setText("" + calcModel.getTotalString());
            
            
        	} catch (NumberFormatException ex) {
        		actionClear();
        		displayField.setText("Fehler");
        	}
        
        	// der aktuelle Operator wird nun als previousOp verwendet
        	previousOp = s;
        }
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
