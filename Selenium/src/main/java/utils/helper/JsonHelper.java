package utils.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.constant.Constant;

public class JsonHelper {
	
	public static String getValue(String filePath, String key) {
		String value = "";
		try {
			value = (String) getJsonObject(filePath).get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static JSONObject getJsonObject(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(filePath));
		JSONObject jo = (JSONObject) obj;
		return jo;
	}
	
	public static String getValue(String key) {
		return getValue(Constant.DATA_URL, key);
	}
}
