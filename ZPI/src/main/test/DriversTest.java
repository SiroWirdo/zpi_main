
import javax.swing.JFrame;

import model.Driver;

import org.parse4j.util.ParseRegistry;

import drivers.controller.DriversController;
import drivers.model.DriversModel;


public class DriversTest {
	
	public static void main(String[] args){
		ParseRegistry.registerSubclass(Driver.class);	
		DriversModel driversModel = new DriversModel();
		DriversController driversController = new DriversController(driversModel);
		
		driversModel.addObserver(driversController);
		
		
		JFrame frame = new JFrame();
		frame.setSize(1280, 840);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(driversController.getDriversView());
		
		frame.repaint();
		frame.setVisible(true);
				
	}
}
