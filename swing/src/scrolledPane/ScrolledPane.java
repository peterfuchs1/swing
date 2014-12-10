package scrolledPane;
import java.awt.*;

import javax.swing.*;

class ScrolledPane 	extends 	JFrame
{
	private		JScrollPane scrollPane;

	public ScrolledPane()
	{
		setTitle( "Scrolling Pane Application" );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   GraphicsEnvironment env =
			     GraphicsEnvironment.getLocalGraphicsEnvironment();
			   /*
			     The next line determines if the   taskbar (win) is covered
			     if unremarked, the task will not be covered by
			     the maximized JFRAME.
			   */
			   this.setMaximizedBounds(env.getMaximumWindowBounds());
			   this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
//		Rectangle maxBounds=env.getMaximumWindowBounds();
//		
//		Dimension d=new Dimension(maxBounds.width,maxBounds.height);
//		this.setSize(d);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		Icon image = new ImageIcon( "picture.gif" );
		JLabel label = new JLabel( image );

		// Create a scrollpane
		scrollPane = new JScrollPane(label);
		topPanel.add( scrollPane,BorderLayout.CENTER);
		getContentPane().add( topPanel );

	}


	public static void main( String args[] )
	{
		// Create an instance of the test application
		ScrolledPane mainFrame	= new ScrolledPane();
		mainFrame.setVisible( true );
	}
} 