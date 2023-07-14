package utils.helper;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;

import core.element.manager.base.BaseControl;
import core.report.Log;
import dataObjects.Agoda.Locator;
import utils.constant.Constant;

public class PageHelper {
	public static By getByLocator(String type, String value) {
		switch (type) {
		case "css":
			return By.cssSelector(value);
		case "id":
			return By.id(value);
		case "link":
			return By.linkText(value);
		case "tagName":
			return By.tagName(value);
		case "name":
			return By.name(value);
		default:
			return By.xpath(value);
		}
	}
	
	public static HashMap<String, Locator> getLocatorsByClassName(String className) {
		HashMap<String, Locator> locators = new HashMap<String, Locator>();
		className = className.substring(className.lastIndexOf('.') + 1);

		String filePath = Constant.DATA_URL + "/" + className + ".json";

		try {
			JSONArray jsonArray = (JSONArray) JsonHelper.getJsonArray(filePath);;

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonLocator = (JSONObject) jsonArray.get(i);
				Locator locator = new Locator((String) jsonLocator.get("control type"),(String) jsonLocator.get("type"),(String) jsonLocator.get("value"));
				locators.put((String) jsonLocator.get("name"), locator);
			}
		} catch (Exception e) {
			Log.error(String.format("Has error: ", e.getMessage()));
		}
		return locators;
	}
	
}
