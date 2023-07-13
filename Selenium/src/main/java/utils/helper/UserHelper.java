package utils.helper;

import org.json.simple.JSONObject;

import dataObjects.Agoda.User;
import utils.constant.Constant;

public class UserHelper {
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
