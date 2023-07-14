package utils.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.constant.Constant;

public class JsonHelper {
	
	public static Object getValue(String filePath, String key) {
		Object value = null;
		try {
			value = getJsonObject(filePath).get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static JSONArray getJsonArray(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(filePath));
		JSONArray jo = (JSONArray) obj;
		return jo;
	}
	public static JSONObject getJsonObject(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader(filePath));
		JSONObject jo = (JSONObject) obj;
		return jo;
	}
	
	public static String getValue(String key) {
		return (String)getValue(Constant.VARIABLE_DATA, key);
	}
}
