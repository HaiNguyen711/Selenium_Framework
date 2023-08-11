package utils.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dataObjects.Account;
import dataObjects.enums.Users;
import utils.constant.Constant;

public class AccountHepler {

	public static Account getUser(String userName) {

		Account account = new Account();
		ObjectMapper objectMapper = new ObjectMapper();
		String user_json = JsonHelper.getValue(Constant.USER_DATA, userName);

		// Deserialization into the `User` class
		try {
			account = objectMapper.readValue(user_json, Account.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;
	}
	
	public static Account getUser(Users user) {

		Account account = new Account();
		ObjectMapper objectMapper = new ObjectMapper();
		String user_json = JsonHelper.getValue(Constant.USER_DATA, user.getText());

		// Deserialization into the `User` class
		try {
			account = objectMapper.readValue(user_json, Account.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;
	}
}
