import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import settings.Settings;
import login.controller.LoginController;
import login.model.UserModel;

public class LogViewTest {
	public static void main(String[] args) {
	
		Settings.setLookAndFeel();
		Settings.turnOffLogBackLogger();
		UserModel us = new UserModel();
		LoginController lc = new LoginController(us);
	}

}
