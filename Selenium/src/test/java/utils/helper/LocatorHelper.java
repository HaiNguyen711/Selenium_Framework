package utils.helper;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import core.driver.manager.manage.DriverManager;
import core.element.manager.base.BaseControl;
import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.CheckBox;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.Link;
import core.element.manager.wrapper.RadioButton;
import core.element.manager.wrapper.Tab;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import utils.constant.Constant;

public class LocatorHelper {
	
	private JsonObject jsonObject = new JsonObject();
	
	/**
	 * get object from data file
	 * 
	 * SuppressWarnmings: rawtypes: to notify the compiler not to warn of an error in the data type declaration.
	 * @param locatorFolderPath - location of locators file
	 * @param clazz             - class object
	 */
	@SuppressWarnings("rawtypes")
	public LocatorHelper(String locatorFolderPath, Class clazz) {
		jsonObject = JsonHelper.getJsonObject(Paths.get(locatorFolderPath, clazz.getSimpleName() + ".json").normalize().toString());
	}


	/**
	 * Get xpath from file json by control type and elementName
	 * 
	 * @param type: Control type: Button, TextBox.....
	 * @param elementName - variable of this element
	 * @return 
	 * @return - locator value of element
	 */
	public <T extends BaseControl> T getLocator(ControlType type ,String elementName) {
		
		String currentPlatform = DriverManager.getDriverProperty().getPlatform().toString().toLowerCase();
		
		// Get locator from platform-driver key
		JsonElement os = jsonObject.get(currentPlatform);
		switch (type) {
		case BUTTON:
			return (T) new Button(os.getAsJsonObject().get(elementName).getAsString());
		case CHECKBOX:
			return (T) new CheckBox(os.getAsJsonObject().get(elementName).getAsString());
		case COMBOBOX:
			return (T) new ComboBox(os.getAsJsonObject().get(elementName).getAsString());
		case LABEL:
			return (T) new Label(os.getAsJsonObject().get(elementName).getAsString());
		case LINK:
			return (T) new Link(os.getAsJsonObject().get(elementName).getAsString());
		case RADIOBUTTON:
			return (T) new RadioButton(os.getAsJsonObject().get(elementName).getAsString());
		case TAB:
			return (T) new Tab(os.getAsJsonObject().get(elementName).getAsString());
		case TEXTBOX:
			return (T) new TextBox(os.getAsJsonObject().get(elementName).getAsString());
		default:
			return null;
		}
	}
	
	/**
	 * Get Dynamic xpath from file json by control type and elementName
	 * 
	 * @param type: Control type: Button, TextBox.....
	 * @param elementName - variable of this element
	 * @Param value - dynamic value.
	 *  
	 * @return - locator value of element
	 *
	 */
	public <T extends BaseControl> T getLocator(ControlType type ,String elementName, String value) {
		
		String currentPlatform = DriverManager.getDriverProperty().getPlatform().toString().toLowerCase();
		
		// Get locator from platform-driver key
		JsonElement os = jsonObject.get(currentPlatform);
		switch (type) {
		case BUTTON:
			return (T) new Button(os.getAsJsonObject().get(elementName).getAsString(),value);
		case CHECKBOX:
			return (T) new CheckBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		case COMBOBOX:
			return (T) new ComboBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		case LABEL:
			return (T) new Label(os.getAsJsonObject().get(elementName).getAsString(),value);
		case LINK:
			return (T) new Link(os.getAsJsonObject().get(elementName).getAsString(),value);
		case RADIOBUTTON:
			return (T) new RadioButton(os.getAsJsonObject().get(elementName).getAsString(),value);
		case TAB:
			return (T) new Tab(os.getAsJsonObject().get(elementName).getAsString(),value);
		case TEXTBOX:
			return (T) new TextBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		default:
			return null;
		}
	}
	
	/**
	 * Get Dynamic xpath from file json by control type and elementName
	 * 
	 * @param type: Control type: Button, TextBox.....
	 * @param elementName - variable of this element
	 * @Param value - dynamic multiple value.
	 *  
	 * @return - locator value of element
	 *
	 */
	public <T extends BaseControl> T getLocator(ControlType type ,String elementName, String... value) {
		
		String currentPlatform = DriverManager.getDriverProperty().getPlatform().toString().toLowerCase();
		
		// Get locator from platform-driver key
		JsonElement os = jsonObject.get(currentPlatform);
		switch (type) {
		case BUTTON:
			return (T) new Button(os.getAsJsonObject().get(elementName).getAsString(),value);
		case CHECKBOX:
			return (T) new CheckBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		case COMBOBOX:
			return (T) new ComboBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		case LABEL:
			return (T) new Label(os.getAsJsonObject().get(elementName).getAsString(),value);
		case LINK:
			return (T) new Link(os.getAsJsonObject().get(elementName).getAsString(),value);
		case RADIOBUTTON:
			return (T) new RadioButton(os.getAsJsonObject().get(elementName).getAsString(),value);
		case TAB:
			return (T) new Tab(os.getAsJsonObject().get(elementName).getAsString(),value);
		case TEXTBOX:
			return (T) new TextBox(os.getAsJsonObject().get(elementName).getAsString(),value);
		default:
			return null;
		}
	}

}
