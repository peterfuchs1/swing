package awt;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;

public class MyPanel extends Panel {
	Checkbox rot,gruen, gelb;
	/**
	 * 
	 */
	public MyPanel() {
		
		this.setLayout(new BorderLayout());
		Panel p1= new Panel(new GridLayout(3,1));
		CheckboxGroup cbg=new CheckboxGroup();
		rot=new Checkbox("rot",cbg,true);
		gelb=new Checkbox("gelb",cbg,true);
		gruen=new Checkbox("grün",cbg,true);
		p1.add(rot);
		p1.add(gelb);
		p1.add(gruen);
		this.add(p1);
		
		// TODO Auto-generated constructor stub
	}

}
