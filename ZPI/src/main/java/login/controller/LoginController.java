package login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.view.LoginView;

import org.parse4j.callback.UserModel;

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

	public LoginListener getLoginListener(){
		return new LoginListener();
	}

	public CancelListener getCancelListener(){
		return new CancelListener();
	}

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				char[] password = loginView.getPassword();
				log(loginView.getLogin(), String.valueOf(password));
				loginView.dispose();

			}
		}



	private class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

				loginView.dispose();
		}

	}
}
