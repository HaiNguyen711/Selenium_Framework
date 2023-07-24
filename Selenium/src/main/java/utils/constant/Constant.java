package utils.constant;

import java.util.Random;
import java.util.logging.Logger;

import utils.helper.JsonHelper;
import utils.helper.Utilities;


public class Constant {
	public static final String APPLESTORE_URL = "https://www.agoda.com/";
//	public static final String APPLESTORE_URL = "https://www.facebook.com/";
	
	public static final String HUB_URL = JsonHelper.getValue(Utilities.getProjectPath() + "\\src\\test\\resources\\grid\\node_config.json", "hub") + "/wd/hub";
	
	public static final String DATA_URL = Utilities.getProjectPath() + "\\src\\test\\java\\resources\\data\\";
	
	public static final String USER_DATA = DATA_URL + "\\Users.json";
	
	public static final String VARIABLE_DATA = DATA_URL + "\\Data.json";
	
	public static final String NULL = "null";
	
	public static final int TIMEOUT = 30;
	
	public static final int SHORT_TIMEOUT = 5;
	
	public static final int LONG_TIMEOUT = 60;
	
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String getRandomInt() {
        Random random = new Random();
        return "" + random.nextInt(1000);
    }
}
