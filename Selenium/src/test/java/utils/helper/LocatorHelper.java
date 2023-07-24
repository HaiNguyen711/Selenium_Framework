package utils.helper;

import com.google.gson.JsonObject;

import core.element.manager.base.BaseControl;
import core.element.manager.wrapper.Button;
import utils.constant.Constant;

public class LocatorHelper {
	
	public <T extends BaseControl> T getLocator(String sLocator){
		String locator = JsonHelper.getValue(Constant.LOCATOR_FOLDER_PATH + "LoginPage.Json", sLocator);
		return (T) new Button(locator);
	}

}
