
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Driver;

import org.parse4j.util.ParseRegistry;

import drivers.controller.DriversController;
import drivers.model.DriversModel;


public class DriversTest {

	public static void main(String[] args){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		ParseRegistry.registerSubclass(Driver.class);
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
