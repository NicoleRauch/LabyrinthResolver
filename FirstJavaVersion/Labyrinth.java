
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/*
 * Created on 18.04.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author rauch
 */
public class Labyrinth {
		

	protected static LabyrinthWalker lw;

	public static void main(String[] args) {
		

		JFrame frame = new JFrame("Labyrinth");
		final LabyrinthBoard lb = new LabyrinthBoard();
		final LabyrinthCanvas lc = new LabyrinthCanvas(lb);
		frame.getContentPane().add(lc);
		frame.getContentPane().validate();
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		frame.setSize(400,450);
        
		JMenuBar menubar = new JMenuBar();
        
		JMenu initMenu = new JMenu("Backtracking");
        
		JMenuItem startItem = new JMenuItem("Start");
		JMenuItem stopItem = new JMenuItem("Stop");
		JMenuItem initItem  = new JMenuItem("New Labyrinth");

		initMenu.add(startItem);
		initMenu.add(stopItem);
		initMenu.add( new JSeparator());
		initMenu.add(initItem);
		
		startItem.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				lw = new LabyrinthWalker(lb, lc);		
				lw.start();
		   }
		});
		stopItem.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
				if( lw == null ) return;
				lw.stop();
		   }
		});
		initItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if( lw != null ) lw.stop();
				lb.initBoard();
			}
		});
		
		menubar.add(initMenu);
        
		frame.setJMenuBar(menubar);
        
		frame.pack();
		frame.setVisible(true);   
	}
	
	
}
