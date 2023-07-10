package core.driver.manager;

import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.constant.Constant;

public class Driver extends BaseDriver {
	
	private static final Logger Logger = Constant.createLogger(Driver.class.getName());
	private WebDriver driver;
	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	ChromeOptions chrome = new ChromeOptions();
	FirefoxOptions firefox = new FirefoxOptions();
	
	public Driver(DriverType type, String runningMode, String hub) {
		try {
			Logger.info(String.format("Creating new %s driver", type.getValue()));
			switch (type.getValue().toLowerCase()) {
			case "chrome":
				if(runningMode.equalsIgnoreCase("remote")) {
					driver = new RemoteWebDriver(new URL(hub), chrome);
				}else if (runningMode.equalsIgnoreCase("local")){
					chrome.addArguments("--remote-allow-origins=*");
					driver = new ChromeDriver(chrome);
				}
				break;
			case "firefox":
				if(runningMode.equalsIgnoreCase("remote")) {
					driver = new RemoteWebDriver(new URL(hub), firefox);
				}else if (runningMode.equalsIgnoreCase("local")) {
					firefox.addArguments("--remote-allow-origins=*");
					driver = new FirefoxDriver(firefox);
				}
				break;
			default:
				System.out.println(String.format("Invalid driver value => '%s'", type.getValue()));
				break;
			}
			DriverManagement.addDriver(driver);
			
		} catch (Exception e) {
			Logger.severe(e.getMessage());
		}
	}

	
}
