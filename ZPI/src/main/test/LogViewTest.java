import org.parse4j.callback.UserModel;
import login.controller.LoginController;

public class LogViewTest {
	public static void main(String[] args) {
		UserModel us = new UserModel();
		LoginController lc = new LoginController(us);	
	}
	
}
