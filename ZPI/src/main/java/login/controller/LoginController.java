package login.controller;

import org.parse4j.callback.UserModel;
import login.view.LoginView;

public class LoginController {
	UserModel userModel;
	LoginView loginView;
	
	public LoginController(UserModel userModel) {
		this.userModel = userModel;
		loginView = new LoginView(this, userModel);
		userModel.initialize();
		loginView.initialize();	
	}
	
	public void log(String username, String password){
		userModel.log(username, password);
	}
}
