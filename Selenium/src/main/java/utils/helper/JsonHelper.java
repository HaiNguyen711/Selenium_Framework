package utils.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import utils.constant.Constant;

public class JsonHelper {
	
	public static String getValue(String filePath, String key) {
		String value = "";
		try {
			value = getJsonObject(filePath).get(key).toString();
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
		return getValue(Constant.VARIABLE_DATA, key);
	}
	
	public static JsonObject getJsonToObject(String filePath) throws Exception{
		try {
			JsonObject obj = new JsonObject();
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(filePath));
			obj = gson.fromJson(reader, JsonObject.class);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
