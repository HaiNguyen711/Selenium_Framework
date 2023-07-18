package dataObjects.Agoda;

import org.json.simple.JSONObject;

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
	
	public static User getUser(String userName) {
		User user = new User();
		try {
			JSONObject jo = (JSONObject) JsonHelper.getValue(Constant.USER_DATA, userName);

			user.setEmail((String) jo.get("email"));
			user.setPassword((String) jo.get("password"));
			user.setFirstName((String) jo.get("firstName"));
			user.setLastName((String) jo.get("lastName"));
			user.setCountry((String) jo.get("country"));
			user.setPhone((String) jo.get("phone"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
