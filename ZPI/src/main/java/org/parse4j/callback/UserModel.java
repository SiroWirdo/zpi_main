package org.parse4j.callback;

import org.parse4j.ParseUser;
import org.parse4j.ParseException;
import org.parse4j.callback.LoginCallback;

import other.DataBaseConnection;

public class UserModel extends ParseUser {

	public void log(String username, String password) {
		System.out.println(password);
		ParseUser.loginInBackground(username, password, new LoginCallback() {
			public void done(ParseUser user, ParseException e) {
				System.out.println("tuuuuuuuuu");
				if (user != null) {
					System.out.println("Hooray! The user is logged in.");
				} else {
					System.out.println("Signup failed. Look at the ParseException to see what happened.");
				}
			}
		});
	}
	
	public void initialize(){
		DataBaseConnection.initialize();
	}

}
