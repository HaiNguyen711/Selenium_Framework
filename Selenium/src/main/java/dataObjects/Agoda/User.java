package dataObjects.Agoda;

import dataObjects.Agoda.enums.UserName;
import utils.helper.JsonHelper;

public class User {

	private String email;
	private String password;
	private String name;
	
	private UserName userName;
	
	public User() {
		
	}
	
	public User(String name, String email, String password) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser(UserName userName) {
		User user = new User();
        String stremail = null;
        String strPassword = null;
        String strName= null;
		switch (userName) {
		case LOC:
			stremail = JsonHelper.getValue("emailLoc");
			strPassword = JsonHelper.getValue("passwordLoc");
			strName = JsonHelper.getValue("nameLoc");
			break;
		default:
			break;
		}
		
		user.setEmail(stremail);
		user.setPassword(strPassword);
		user.setName(strName);
		return user;
	}
	
}
