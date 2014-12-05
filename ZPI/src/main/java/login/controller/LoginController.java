package login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.model.UserModel;
import login.view.LoginView;

/*
 * Zarz¹dza ekranem logowania i autoryzacj¹ u¿ytkownika
 */
public class LoginController {
	private UserModel userModel;
	private LoginView loginView;
	private boolean isPasswordPrivate = false;

	public LoginController(UserModel userModel) {
//		TODO odkomentowaæ na samym koñcu!
//		Settings.turnOffLogBackLogger();
//		Settings.turnOffSysoutLog();
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
	
	public TextFieldFocusListener getTextFieldFocusListener(){
		return new TextFieldFocusListener();
	}
	
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
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
	
	private class TextFieldFocusListener implements FocusListener{
		
		@Override
		public void focusGained(FocusEvent e) {
			String displayText = null;
			JTextField comp = null;
			
			if((e.getSource().getClass()).equals(JPasswordField.class)){
				comp = (JPasswordField) e.getSource();
				if(comp.getName().equals("haslo")){
					displayText = "has³o";
				}
				
				if(comp.getText().equals(displayText) && !isPasswordPrivate){
	    			comp.setText("");
	    			((JPasswordField) comp).setEchoChar('*');
	    			isPasswordPrivate = true;
	   
				}
	        	else if(comp.getText().equals("")){
	        		((JPasswordField) comp).setEchoChar((char) 0);
	        		isPasswordPrivate = false;
	        		comp.setText(displayText);
				}
	        	else{
	        		((JPasswordField) comp).setEchoChar('*');
	        		isPasswordPrivate = true;
	        	}
			}
			else{
				comp = (JTextField) e.getSource();
				if(comp.getName().equals("login")){
					displayText = "login";
				}
				
				if(comp.getText().equals(displayText)){
	    			comp.setText("");
				}
	        	else if(comp.getText().equals("")){;
	        		comp.setText(displayText);
				}
			}
			loginView.repaint();
			loginView.revalidate();
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField comp = null;
			String displayText = null;
			
			if((e.getSource().getClass()).equals(JPasswordField.class)){
				comp = (JPasswordField) e.getSource();
				
				if(comp.getName().equals("haslo")){
					displayText = "has³o";
				}
				
				if(comp.getText().replaceAll("\\s+","").equals("")){
					((JPasswordField) comp).setEchoChar((char) 0);
					isPasswordPrivate = false;
					comp.setText(displayText);
				}
				else{
					((JPasswordField) comp).setEchoChar('*');
					isPasswordPrivate = true;
				}
			}
			else{
				comp = (JTextField) e.getSource();
				
				if(comp.getName().equals("login")){
					displayText = "login";
				}
				
				if(comp.getText().replaceAll("\\s+","").equals("")){
					comp.setText(displayText);
				}
			}
			loginView.repaint();
        	loginView.revalidate();
		}
		
	}
}
