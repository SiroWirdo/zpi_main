package model;

public class User {
	private String id;
	private String username;
	private String password;
	private String email;
	
	public User(String id, String username, String password, String email){
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	/**id musi byæ unikalne**/
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	/**username musi byæ unikalny**/
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	/**email musi byæ unikalny**/
	public void setEmail(String email) {
		this.email = email;
	}
	

}
