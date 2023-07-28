package utils.helper;

import java.nio.file.Paths;
import com.google.gson.JsonObject;
import core.driver.manager.manage.DriverManager;
import utils.constant.Constant;

public class Environment {
	
	private JsonObject jsonObject = new JsonObject();
	
	/**
	 * 
	 * @return json file path
	 */
	public String getPath() {
		System.out.print(Paths.get(Constant.VARIABLE_DATA, DriverManager.getDriverProperty().getPlatform().toString().toLowerCase() + ".json"));
		return Paths.get(Constant.VARIABLE_DATA, DriverManager.getDriverProperty().getPlatform().toString().toLowerCase() + ".json").normalize().toString();
		
	}

	
	/**
	 * get value from data
	 * @param value: variable of value in json file
	 * @return value from data
	 */
	public String getValue(String value) {
		
		// Get locator from platform-driver key
		return JsonHelper.getValue(getPath(), value);
	}

}
