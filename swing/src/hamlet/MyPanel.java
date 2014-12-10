/**
 * 
 */
package hamlet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A very simple game with randomized sequence of buttons to click.
 * @author uhs374h
 * @version 1.1
 */
public class MyPanel extends JPanel implements IGames {

	private static final long serialVersionUID = 7511310393424396334L;

	private HashSet<JButton> jb;
	private int numberGames, moves, correctMoves, wrongMoves, openMoves, countButtons, currentId;
	private int maxButtons;
	private JPanel center, north, south, east;
	private JLabel jlMoves, jlCorrectMoves, jlWrongMoves, jlOpenMoves, jlNumberGames;
	private JButton solve, restart, exit;
	private MyController c;
	private Hamlet text;


	/**
	 * constructor
	 * @param c MyConstructor
	 * @param maxButtons maximum buttons used
	 */
	public MyPanel(MyController c, Hamlet text) {
		this.text=text;
		this.c=c;
		this.maxButtons=text.size();
		jb=new HashSet<JButton>();
		this.setLayout(new BorderLayout());
		north=new JPanel();
		north.add(new JLabel("Find the correct sequence: Start with the first line!"));
		north.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		this.add(north,BorderLayout.NORTH);
		east=new JPanel(new GridLayout(5,2));
		east.add(new JLabel("Moves: "));
		jlMoves=new JLabel();
		east.add(jlMoves);
		east.add(new JLabel("correct: "));
		jlCorrectMoves=new JLabel();
		east.add(jlCorrectMoves);
		east.add(new JLabel("wrong: "));
		jlWrongMoves=new JLabel();
		east.add(jlWrongMoves);
		east.add(new JLabel("open: "));
		jlOpenMoves=new JLabel();
		east.add(jlOpenMoves);
		east.add(new JLabel("Games: "));
		jlNumberGames=new JLabel();
		east.add(jlNumberGames);
		east.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		this.add(east,BorderLayout.EAST);
		south=new JPanel();
		solve=new JButton("Solve");
		solve.addActionListener(c);
		south.add(solve);
		restart=new JButton("Restart");
		restart.addActionListener(c);
		south.add(restart);
		exit=new JButton("Exit");
		exit.addActionListener(c);
		south.add(exit);
		this.add(south,BorderLayout.SOUTH);
		// dummies einfügen!
		
		center=new JPanel(new GridLayout(maxButtons,1));
		this.add(center,BorderLayout.CENTER);
		initial();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	/**
	 * @see IGames#initial()
	 */
	@Override 
	public void initial(){
		countButtons=text.size();		
		numberGames=0;
		this.restart();
	}
	/**
	 * @see IGames#restart()
	 */
	@Override
	public void restart(){
		jb.clear(); // remove all JButtons from HashSet
		int i=0;
		while(i<countButtons){
			// create a new button
			JButton button=new JButton(text.getText(i));
			i+=1;
			button.addActionListener(c);
			// add it to the set
			jb.add(button);
		}
		Iterator<JButton> iter =jb.iterator();
		center.removeAll(); // remove all JButtons from the container
		while (iter.hasNext()) {// add all new JButtons to the container
			JButton button=iter.next();
			button.setEnabled(true);
			center.add(button);
		}
		// initialize all statistics
		moves=correctMoves=wrongMoves=currentId=0;
		openMoves=countButtons;
		// repaint the container
		this.repaint();
		showStatus();
		numberGames+=1;
	}
	/**
	 *  @see IGames#solve()
	 */
	@Override
	public void solve(){
		Iterator<JButton> iter =jb.iterator();
		while (iter.hasNext()){
			iter.next().setEnabled(false);
		}
	}
	/**
	 * show the current statistics 
	 */
	public void showStatus(){
		jlMoves.setText(""+moves);
		jlCorrectMoves.setText(""+correctMoves);
		jlWrongMoves.setText(""+wrongMoves);
		jlOpenMoves.setText(""+openMoves);
		jlNumberGames.setText(""+numberGames);
	}
	
	// GETTER UND SETTER
	/**
	 * @return the jb
	 */
	public HashSet<JButton> getJb() {
		return jb;
	}

	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}
	/**
	 * @param moves the moves to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}
	/**
	 * @return the correctMoves
	 */
	public int getCorrectMoves() {
		return correctMoves;
	}
	/**
	 * @param correctMoves the correctMoves to set
	 */
	public void setCorrectMoves(int correctMoves) {
		this.correctMoves = correctMoves;
	}
	/**
	 * @return the wrongMoves
	 */
	public int getWrongMoves() {
		return wrongMoves;
	}
	/**
	 * @param wrongMoves the wrongMoves to set
	 */
	public void setWrongMoves(int wrongMoves) {
		this.wrongMoves = wrongMoves;
	}
	/**
	 * @return the openMoves
	 */
	public int getOpenMoves() {
		return openMoves;
	}
	/**
	 * @param openMoves the openMoves to set
	 */
	public void setOpenMoves(int openMoves) {
		this.openMoves = openMoves;
	}
	/**
	 * @return the currentId
	 */
	public int getCurrentId() {
		return currentId;
	}
	/**
	 * @param currentId the currentId to set
	 */
	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}
	/**
	 * @return the solve
	 */
	public JButton getSolve() {
		return solve;
	}
	/**
	 * @return the restart
	 */
	public JButton getRestart() {
		return restart;
	}
	/**
	 * @return the exit
	 */
	public JButton getExit() {
		return exit;
	}
	
	
}
