package core.element.manager.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import core.driver.manager.manage.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.element.manager.base.interfaces.ISideActionableControl;
import core.element.manager.base.interfaces.IValidateControl;
import core.element.manager.base.interfaces.IWaitableControl;
import core.element.manager.base.type.ElementType;
import core.report.Log;
import io.netty.handler.timeout.TimeoutException;
import utils.constant.Constant;

public class BaseControl implements IWaitableControl, IValidateControl, ISideActionableControl {
	private By by;
	Actions builder = new Actions(getDriver());
	private String locator;
	
	public WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	/**
	 * Element with given xpath
	 * 
	 * @param locator - Locator without type is assigned to xpath
	 */	
	public BaseControl(String locator) {
		this.by = getByLocator(locator);
	}
	
	/**
	 * Element with given locator type
	 * 
	 * @param locator - type: id, xpath, css,...
	 */
	public BaseControl(By locator) {
		this.by = locator;
	}
	
	/**
	 * Element with given element type
	 * 
	 * @param by    - Type of element
	 * @param value - value of element
	 */
	public BaseControl(ElementType by, String value) {
		this.by = getByLocator(by, value);
	}
	
	/**
	 * Handle dynamic element with element type
	 * 
	 * @param by        - Type of element
	 * @param value     - value of element
	 * @param string - Variable dynamic value
	 */
	public BaseControl(ElementType by, String value, String... string) {
		this.by = getByLocator(by, String.format(value, string));
	}
	
	

	public static BaseControl getElement(String element, String... string) {
		return new BaseControl(String.format(element, string));
	}
		
		
	
	public static int countElement(String element) {
		List<WebElement> elements = DriverManager.getDriver().findElements(By.xpath(element));
		return elements.size();
	}
	
	public int countElement() {
		return getElements().size();
	}
	
	/**
	 * Get web element from web page Locator without type is assigned to xpath
	 * 
	 * @return element
	 */
	@Override
	public WebElement getElement() {
		return this.getDriver().findElement(by);
	}

	/**
	 * Get web element from web page Locator without type is assigned to xpath
	 * 
	 * @return List element
	 */
	@Override
	public List<WebElement> getElements() {
		return this.getDriver().findElements(by);
	}
	
	/**
	 * Get element By locator
	 * 
	 * @return By locator of the element
	 */
	@Override
	public By getLocator() {
		return this.by;
	}
	
	/**
	 * Get string locator
	 * @return a string locator
	 */
	@Override
	public String getElementXpathAsString() {
		String eleXpath = "";

		try {
			if (this.locator != null || this.by == null) {
				return this.locator;
			}

			String sLocator = this.by.toString();
			String byType = sLocator.split(": ")[0].split("\\.")[1];
			String byValue = sLocator.split(": ")[1];
			switch (byType) {
			case "id":
				eleXpath = "//*[@id='byValue']";
			case "xpath":
				eleXpath = byValue;
			}
		} catch (Exception e) {
			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			throw e;
		}

		return eleXpath;
	}
	
	/*
	 * ========================= Side Action ================================================================================
	 */
	
	/**
	 * Get the value of a given CSS property
	 * 
	 * @param propertyName: css name
	 * 
	 * @return css value
	 */
	@Override
	public String getCssValue(String propertyName) {
		Log.info(String.format("Get Css value '%s' of %s", propertyName, getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getCssValue(propertyName);
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				Log.error(String.format("Try to get CSS value '%s' from control %s again", propertyName,
						getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
	 * Get the value of a the given attribute of the element
	 * 
	 * @param attributeName: attributeName
	 * 
	 * @return attribute value
	 */
	@Override
	public String getAttribute(String attributeName) {
		Log.info(String.format("Get Attribute value '%s' of %s", attributeName, getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getAttribute(attributeName);
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				Log.error(String.format("Try to get Attribute '%s' from control %s again", attributeName,
						getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}

	/**
	 * Get the visible innerText of this element
	 *
	 * @return The innerText of this element
	 */
	@Override
	public String getText() {
		Log.info(String.format("Get Text of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getText();
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				Log.error(String.format("Try to get Text from control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Hover at the current mouse location
	 */
	@Override
	public void hover() {
		Log.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				this.waitForPresent(Constant.SHORT_TIMEOUT);
				((JavascriptExecutor) this.getDriver()).executeScript(mouseHoverScript, getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to hover on control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	/**
	 * Scroll to an element
	 */
	@Override
	public void scrollIntoView() {
		Log.info(String.format("Scroll to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForPresent(Constant.SHORT_TIMEOUT);
				((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
						getElement());
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
	 * Mouse Over on the the given element
	 */
	@Override
	public void moveToElement() {
		Log.info(String.format("Hover on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForPresent(Constant.SHORT_TIMEOUT);
				builder.moveToElement(getElement()).perform();
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				Log.error(String.format("Try to hover on control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	/**
	 * Get texts from list of elements
	 * 
	 * @return list of texts
	 */
	@Override
	public List<String> getAllTexts() {
		Log.info(String.format("Get Text of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			List<WebElement> listOfElement = this.getElements();
			List<String> textLst = new ArrayList<String>();
			try {
				Log.info(String.format("Get all name of elements %s", getLocator().toString()));
				for (int y = 0; i < listOfElement.size(); y++) {
					textLst.add(listOfElement.get(i).getText());
				}
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				Log.error(String.format("Try to get Text from control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Get value of the value attribute of this element
	 *
	 * @return The value of this element
	 */
	@Override
	public String getValue() {
		Log.info(String.format("Get Text of %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				return getElement().getAttribute("value");
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return null;
				Log.error(String.format("Try to get Value from control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Get value of the dropdown list
	 *
	 * @return The value of this element
	 */
	@Override
	public String[] getDropdownListOptions() {
		Log.info(String.format("Select Option on %s", getLocator().toString()));
		Select dropdownList = new Select(this.getElement());
		int i = 0;
		String[] options = null;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForNotPresent(Constant.SHORT_TIMEOUT);
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

	/*
	 * ========================= Validation ================================================================================
	 */
	
	/**
	 * Check element is displayed with default time out
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isDisplayed() {
		return isDisplayed(Constant.TIMEOUT);
	}

	/**
	 * Check element enabled or not with default time out
	 * 
	 * @return true or false
	 */
	public boolean isEnabled() {
		return isEnabled(Constant.SHORT_TIMEOUT);
	}

	/**
	 * Check element is displayed with wait for time out
	 * 
	 *  @param timeOutInSeconds - wait for seconds time out
	 *  
	 * @return true or false
	 */
	@Override
	public boolean isDisplayed(int timeOutInSeconds) {
		try {
			Log.info(String.format("Check if control %s is displayed", getLocator().toString()));
			this.waitForDisabled(timeOutInSeconds);
			return true;
		} catch (TimeoutException timeOutEx) {
			return false;
		} catch (Exception e) {
			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}

	/**
	 * Check element enabled with wait time out
	 * 
	 * @param timeOutInSeconds - wait for seconds time out
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isEnabled(int timeOutInSeconds) {
		Log.info(String.format("Check if control %s is enabled", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForPresent(timeOutInSeconds);
				return getElement().isEnabled();
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					return false;
				Log.error(String.format("Try to get value from control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				return false;
			}
		}
		return false;
	}

	/**
	 * Check element selected or not
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isSelected() {
		return isSelected(Constant.SHORT_TIMEOUT);
	}

	/**
	 * Check element selected or not with wait time out
	 * 
	 * @param timeOutInSeconds - seconds to wait until element become visible or
	 *                         undiscovered
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isSelected(int timeOutInSeconds) {
		Log.info(
				String.format("Check Selected status of %s in %s seconds", getLocator().toString(), timeOutInSeconds));
		try {
			this.waitForSelected(timeOutInSeconds);
			return true;
		} catch (TimeoutException timeOutEx) {
			return false;
		} catch (Exception e) {
			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			return false;
		}
	}
	
	/**
	 * Check element is clicked
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isClickable() {
		boolean isClickable = true;
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constant.TIMEOUT));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getElement()));
			this.getElement().click();
		} catch (TimeoutException e) {
			isClickable = false;
			Log.error("[isNotDisplayed]: Getting timeout {}" + e.getLocalizedMessage());
		} catch (Exception e) {
			isClickable = false;
		}
		return isClickable;
	}

}
