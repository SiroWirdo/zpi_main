import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.ColorUIResource;

import main.controller.MainMenuController;
import main.model.MapModel;

public class MainMenuTest {
	public static void main(String[] args) {

		UIManager.put("nimbusBase", new Color(0,68,102));
		UIManager.put("nimbusBlueGrey", new Color(60,145,144));
		UIManager.put("control", new Color(43,82,102));
		UIManager.put("text", new Color(255,255,255));
		UIManager.put("Table.alternateRowColor", new Color(0,68,102));
		UIManager.put("TextField.font", new Font("Font", Font.BOLD, 12));
		UIManager.put("TextField.textForeground", new Color(0,0,0));
		UIManager.put("PasswordField.foreground", new Color(0,0,0));
		UIManager.put("TextArea.foreground", new Color(0,0,0));
		UIManager.put("FormattedTextField.foreground", new Color(0,0,0));

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

		UIManager.getLookAndFeelDefaults().put("Table:\"Table.cellRenderer\".background", new ColorUIResource(new Color(74,144,178)));
		UIManager.getLookAndFeelDefaults().put("Table.background", new ColorUIResource(new Color(74,144,178)));


		MapModel menuModel = new MapModel();
		MainMenuController menuController = new MainMenuController(menuModel);
	}
}
