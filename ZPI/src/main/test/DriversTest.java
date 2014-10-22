import javax.swing.JFrame;

import drivers.controller.DriversController;
import drivers.model.DriversModel;


public class DriversTest {
	
	public static void main(String[] args){
		DriversModel driversModel = new DriversModel();
		DriversController driversController = new DriversController(driversModel);
		
		JFrame frame = new JFrame();
		frame.setSize(1280, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(driversController.getDriversView());
		
		frame.repaint();
		frame.setVisible(true);
				
	}
}
