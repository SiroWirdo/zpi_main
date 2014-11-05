import login.controller.LoginController;
import login.model.UserModel;

public class LogViewTest {
	public static void main(String[] args) {
		UserModel us = new UserModel();
		LoginController lc = new LoginController(us);	
	}
	
}
