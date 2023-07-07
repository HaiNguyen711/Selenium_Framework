package core.driver.manager;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.report.Log;

public class BaseDriver implements WebDriver {
	
	public WebDriver getDriver() {
		return DriverManagement.getDriver();
	}
	
	@Override
	public void get(String url) {
		Log.info(String.format("Load a new web page for the driver %s", DriverManagement.getThreadName()));
		getDriver().get(url);
		
	}

	@Override
	public String getCurrentUrl() {
		Log.info(String.format("Get current url of the driver %s", DriverManagement.getThreadName()));
		return getDriver().getCurrentUrl();
	}

	@Override
	public String getTitle() {
		Log.info(String.format("Get current title of the driver %s", DriverManagement.getThreadName()));
		return getDriver().getTitle();
	}

	@Deprecated
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageSource() {
		Log.info(String.format("Get source of the last load page of the driver %s", DriverManagement.getThreadName()));
		return getDriver().getPageSource();
	}

	@Override
	public void close() {
		Log.info(String.format("Close the current window of the driver %s", DriverManagement.getThreadName()));
		getDriver().close();
	}

	@Override
	public void quit() {
		Log.info(String.format("Quit the driver %s", DriverManagement.getThreadName()));
		getDriver().quit();
	}

	@Deprecated
	public Set<String> getWindowHandles() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Deprecated
	public String getWindowHandle() {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Override
	public TargetLocator switchTo() {
		Log.info(String.format("Send commands to the different frame or window of the driver %s",
				DriverManagement.getThreadName()));
		return getDriver().switchTo();
	}

	@Override
	public Navigation navigate() {
		Log.info(String.format("Navigate to a given url of the driver %s", DriverManagement.getThreadName()));
		return getDriver().navigate();
	}

	@Override
	public Options manage() {
		Log.info(String.format("Get the option interface of the driver %s", DriverManagement.getThreadName()));
		return getDriver().manage();
	}
	
	public void maximize() {
        try {
            getDriver().manage().window().maximize();
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
