package admin.user.add.model;

import org.parse4j.ParseException;
import org.parse4j.ParseUser;

import other.DataBaseConnection;

public class AddUserModel {

	public void initialize(){
		DataBaseConnection.initialize();
	}

	public ParseUser addUser(String username, String password, String email, boolean admin){
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.put("admin", admin);
		try {
			user.signUp();
			//user.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
}
