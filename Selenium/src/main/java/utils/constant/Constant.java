package utils.constant;

import java.util.Random;
import java.util.logging.Logger;

import utils.helper.JsonHelper;
import utils.helper.Utilities;


public class Constant {
	public static final String VOUCHER_PARADISE_URL = "http://18.232.153.220:10000/";
	public static final String HUB_URL = JsonHelper.getValue(Utilities.getProjectPath() + "/src/test/resources/grid/node_config.json", "hub") + "/wd/hub";
	public static final String DRIVER_SETTING_FILE = Utilities.getProjectPath() + "/src/test/resources/driver.setting.properties.json";
	public static final String DATA_URL = Utilities.getProjectPath() + "/src/test/resources";
	public static final String USER_DATA = DATA_URL + "/data/user/Users.json";
	public static final String VARIABLE_DATA = DATA_URL + "/data/environment/";
	public static final String LOCATOR_PATH = DATA_URL + "/locator/";
	public static final String IMAGE_DATA = DATA_URL + "/data/images";
	
	public static final String NULL = "null";
	
	public static final int TIMEOUT = 30;
	
	public static final int SHORT_TIMEOUT = 10;
	
	public static final int LONG_TIMEOUT = 60;
	
	
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String getRandomInt() {
        Random random = new Random();
        return "" + random.nextInt(1000);
    }
}
