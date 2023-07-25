package core.driver.manager.resources;

import core.driver.manager.Base.BaseDriver;
import core.driver.manager.setting.DriverProperty;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Firefox driver implementation, override methods from BaseDriver
 */
public class FirefoxDriver extends BaseDriver {

	/**
	 * a concept in Selenium WebDriver for manipulation various properties of
	 * Firefox driver
	 */
	protected FirefoxOptions options;

	/**
	 * Initializes Firefox driver properties with given properties
	 * 
	 * @param property - driver properties
	 */
	public FirefoxDriver(DriverProperty property) {
		super(property);
		loadOptions(property);
	}

	/**
	 * Set properties for Firefox to customize driver sessions
	 */
	private void loadOptions(DriverProperty property) {
		options = new FirefoxOptions();
		if (driverProperty.getArguments() != null) {
			options.addArguments(driverProperty.getArguments());
		}
		if (driverProperty.getUserProfilePreference() != null) {
			FirefoxProfile profile = new FirefoxProfile();
			driverProperty.getUserProfilePreference()
					.forEach((key, value) -> profile.setPreference(key, value.toString()));
			options.setProfile(profile);
		}
		options.setHeadless(property.getHeadless());
	}

	/**
	 * Create Firefox local driver with customized driver sessions
	 */
	@Override
	public void createLocalDriver() {
		if (StringUtils.isNotBlank(driverProperty.getDriverExecutable())) {
			System.setProperty("webdriver.gecko.driver", driverProperty.getDriverExecutable());
		}
		webDriver = new org.openqa.selenium.firefox.FirefoxDriver(options);
	}

	/**
	 * Create Firefox remote driver with remote url and customized driver sessions
	 */
	@Override
	public void createRemoteDriver() {
		webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
	}

}
