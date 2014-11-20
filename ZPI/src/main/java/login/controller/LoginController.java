package login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;

import login.model.UserModel;
import login.view.LoginView;

public class LoginController {
	UserModel userModel;
	LoginView loginView;
	Logger rootLogger;

	public LoginController(UserModel userModel) {
		turnOfLogBackLogger();
//		turnOffSysoutLog();
		this.userModel = userModel;
		loginView = new LoginView(this, userModel);
		userModel.initialize();
		loginView.initialize();
	}

	public void turnOfLogBackLogger(){
		Logger orgHibernateLogger = (Logger) LoggerFactory.getLogger("ROOT");
		ch.qos.logback.classic.Level oldLogLevel = orgHibernateLogger.getLevel();
		orgHibernateLogger.setLevel(Level.OFF);
	}
	
	public void turnOffSysoutLog(){
		System.setOut(new PrintStream(new OutputStream() {

		     @Override
		     public void write(int arg0) throws IOException {

		     }
		  }));
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
	
	public PasswordListener getPasswordListener(){
		return new PasswordListener();
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
			loginView.dispose();
			System.exit(0);
		}
	}
	
	public class PasswordListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}


		
	}
}
