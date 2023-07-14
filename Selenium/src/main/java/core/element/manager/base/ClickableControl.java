package core.element.manager.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.element.manager.base.interfaces.IClickableControl;
import core.report.Log;
import io.netty.handler.timeout.TimeoutException;
import utils.constant.Constant;

public class ClickableControl extends BaseControl implements IClickableControl  {

	public ClickableControl(String locator,String type) {
		super(locator,type);
		// TODO Auto-generated constructor stub
	}
	
	public void click() {
		Log.info(String.format("Click to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.LONG_TIMEOUT) {
			i++;
			try {
				this.waitForClickable();
				getElement().click();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.LONG_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	public void waitForClickable() {
		this.waitForClickable(Constant.TIMEOUT);
	}

	public void waitForClickable(long timeOut) {
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut))
					.until(ExpectedConditions.elementToBeClickable(getElement()));
		} catch (Exception error) {
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}

	public boolean isClickable() {
		boolean isClickable = true;
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constant.TIMEOUT));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getElement()));
			this.getElement().click();
		} catch (TimeoutException e) {
			isClickable = false;
			Log.warn("[isNotDisplayed]: Getting timeout {}" + e.getLocalizedMessage());
		} catch (Exception e) {
			isClickable = false;
		}
		return isClickable;
	}
	
	public void selectOptionByText(String option) {
		Log.info(String.format("Get Options on %s", getLocator().toString()));
		Select selectByText = new Select(this.getElement());
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForLoad(Constant.SHORT_TIMEOUT);
				selectByText.selectByVisibleText(option);
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	public void selectOptionByValue(String value) {
		Log.info(String.format("Get Options on %s", getLocator().toString()));
		Select selectByText = new Select(this.getElement());
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForLoad(Constant.SHORT_TIMEOUT);
				selectByText.selectByValue(value);
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	public String[] getDropdownListOptions() {
		Log.info(String.format("Select Option on %s", getLocator().toString()));
		Select dropdownList = new Select(this.getElement());
		int i = 0;
		String[] options = null;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForLoad(Constant.SHORT_TIMEOUT);
				List<WebElement> op = dropdownList.getOptions();
				options = new String[op.size()];
				for (int i2 = 0; i2 < op.size(); i2++) {
					options[i2] = op.get(i2).getText();
				}
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
		return options;
	}
}
