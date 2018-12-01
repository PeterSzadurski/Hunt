package model;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 2856723757650934254L;
	private String username;
	private String password;
	private int userId;
	
	public UserBean() {
		
	}
	
	public UserBean(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int id) {
		this.userId = id;
	}
	
}
