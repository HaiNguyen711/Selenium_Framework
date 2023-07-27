package core.driver.manager.resources;

import core.driver.manager.Base.BaseDriver;
import core.driver.manager.setting.DriverProperty;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriver extends BaseDriver {
    /**
     * a concept in Selenium WebDriver for manipulation various properties of Chrome
     * driver
     */
    protected ChromeOptions options;

    /**
     * Initializes Chrome driver properties with given properties
     *
     * @param property - driver properties
     */
    public ChromeDriver(DriverProperty property) {
        super(property);
        loadOptions(property);
    }

    /**
     * Set properties for chrome to customize driver sessions
     */
    private void loadOptions(DriverProperty property) {
        options = new ChromeOptions();
        if (driverProperty.getArguments() != null) {
            options.addArguments(driverProperty.getArguments());
        }
        if (driverProperty.getUserProfilePreference() != null) {
            options.setExperimentalOption("prefs", driverProperty.getUserProfilePreference());
        }
        options.setHeadless(property.getHeadless());
    }

    /**
     * Create chrome local driver with customized driver sessions
     */
    @Override
    public void createLocalDriver() {
        if (StringUtils.isNotBlank(driverProperty.getDriverExecutable())) {
            System.setProperty("webdriver.chrome.driver", driverProperty.getDriverExecutable());
        }
        webDriver = new org.openqa.selenium.chrome.ChromeDriver(options);
    }

    /**
     * Create chrome remote driver with remote url and customized driver sessions
     */
    @Override
    public void createRemoteDriver() {
        webDriver = new RemoteWebDriver(driverProperty.getRemoteUrl(), options);
    }
}
