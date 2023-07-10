package dataObjects.Agoda;

import dataObjects.Agoda.enums.UserName;
import utils.constant.Constant;
import utils.helper.JsonHelper;

public class User {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String phone;
	
	public User(String email, String password, String firstName, String lastName, String country, String phone) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.phone = phone;
	}

	public User() {
		
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser(UserName userName) {
		User user = new User();
        String semail = null;
        String sPassword = null;
        String sFirstName= null;
        String sLastName = null;
        String sCountry = null;
        String sPhone= null;
		switch (userName) {
		case LOC:
			semail = JsonHelper.getValue(Constant.USER_DATA,"emailLoc");
			sPassword = JsonHelper.getValue(Constant.USER_DATA,"passwordLoc");
			sFirstName = JsonHelper.getValue(Constant.USER_DATA,"firstNameLoc");
			sLastName = JsonHelper.getValue(Constant.USER_DATA,"lastNameLoc");
			sCountry = JsonHelper.getValue(Constant.USER_DATA,"country");
			sPhone = JsonHelper.getValue(Constant.USER_DATA,"phone");
			break;
		default:
			break;
		}
		
		user.setEmail(semail);
		user.setPassword(sPassword);
		user.setFirstName(sFirstName);
		user.setLastName(sLastName);
		user.setCountry(sCountry);
		user.setPhone(sPhone);
		return user;
	}
	
}
