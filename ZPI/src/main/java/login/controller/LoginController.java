package login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import login.model.UserModel;
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

	public boolean log(String username, String password){
		return userModel.log(username, password);
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
			boolean success = log(loginView.getLogin(), String.valueOf(password));
			if(success){
				loginView.dispose();
			}else{
				loginView.clearPassword();
				loginView.repaint();
			}
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
