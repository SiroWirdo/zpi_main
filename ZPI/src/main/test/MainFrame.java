import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame {
	private static MainFrame instance = null;

	public MainFrame(){
		initUI();
	}

	 public final void initUI() {
	    	setSize(500, 700);
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setResizable(false);

	    	setVisible(true);

	    	repaint();
	    }

    public static MainFrame getInstance() {
    	if (instance == null) {
    		instance = new MainFrame();
    	}
    	return instance;
    }

}
