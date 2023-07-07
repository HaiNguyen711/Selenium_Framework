package core.element.manager.wrapper;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import core.driver.manager.DriverManagement;
import core.report.Log;
import io.netty.handler.timeout.TimeoutException;
import utils.constant.Constant;

public class Element {
//	private By by;
//	Actions builder = new Actions(getDriver());
//	private String locator;
//
//	public Element(String locator) {
//		by = By.xpath(locator);
//	}
//
//	public WebDriver getDriver() {
//		return DriverManagement.getDriver();
//	}
//
//	public WebElement getElement() {
//		return this.getDriver().findElement(by);
//	}
//	
//	public List<WebElement> getElements() {
//		return this.getDriver().findElements(by);
//	}
//
//	public static Element getElement(String element, String string) {
//		return new Element(String.format(element, string));
//	}
//
//	public static Element getElement(String element, String string1, String string2) {
//		return new Element(String.format(element, string1, string2));
//	}
//	
//	public static int countElement(String element) {
//		List<WebElement> elements = DriverManagement.getDriver().findElements(By.xpath(element));
//		return elements.size();
//	}
//	
//	public int countElement() {
//		return getElements().size();
//	}
//
//	public void enter(String value) {
//		this.waitForDisplayed(Constant.LONG_TIMEOUT);
//		clear();
//		type(value);
//	}
//
//	private void type(String value) {
//		Log.info(String.format("Enter to %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.LONG_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.LONG_TIMEOUT);
//				getElement().sendKeys(value);
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.LONG_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	private void clear() {
//		Log.info(String.format("Clear to %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.LONG_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.LONG_TIMEOUT);
//				getElement().clear();
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.LONG_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public void click() {
//		Log.info(String.format("Click to %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.LONG_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.LONG_TIMEOUT);
//				getElement().click();
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.LONG_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public boolean isDisplayed() {
//		Log.info(String.format("Check if control %s is displayed", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				return getElement().isDisplayed();
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					return false;
//				Log.error(String.format("Try to get value from control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				return false;
//			}
//		}
//		return false;
//	}
//
//	public boolean isEnabled() {
//		Log.info(String.format("Check if control %s is enabled", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				return getElement().isEnabled();
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					return false;
//				Log.error(String.format("Try to get value from control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				return false;
//			}
//		}
//		return false;
//	}
//
//	public By getLocator() {
//		return this.by;
//	}
//
//	public String getCssValue(String propertyName) {
//		Log.info(String.format("Get Css value '%s' of %s", propertyName, getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				return getElement().getCssValue(propertyName);
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					return null;
//				Log.error(String.format("Try to get CSS value '%s' from control %s again", propertyName,
//						getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				return null;
//			}
//		}
//		return null;
//	}
//
//	public String getAttribute(String attributeName) {
//		Log.info(String.format("Get Attribute value '%s' of %s", attributeName, getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				return getElement().getAttribute(attributeName);
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					return null;
//				Log.error(String.format("Try to get Attribute '%s' from control %s again", attributeName,
//						getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				return null;
//			}
//		}
//		return null;
//	}
//
//	public String getText() {
//		Log.info(String.format("Get Text of %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				return getElement().getText();
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					return null;
//				Log.error(String.format("Try to get Text from control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				return null;
//			}
//		}
//		return null;
//	}
//
//	public void waitForDisplayed(int timeOut) {
//		try {
//			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut));
//			wait.until(new Function<WebDriver, Boolean>() {
//				public Boolean apply(WebDriver driver) {
//					try {
//						return getElement().isDisplayed();
//					} catch (NoSuchElementException e) {
//						return false;
//					} catch (StaleElementReferenceException e) {
//						return false;
//					}
//				}
//			});
//		} catch (Exception error) {
//			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
//					error.getMessage()));
//		}
//	}
//	
//	public void waitForInvisibility(int timeOut) {
//		try {
//			Log.info(String.format("Wait for control %s invisibility with timeOut: %s", getLocator().toString(),
//					timeOut));
//			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut));
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator()));
//		} catch (Exception e) {
//			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
//		}
//	}
//
//	public void waitForDisplayed() {
//		this.waitForDisplayed(Constant.LONG_TIMEOUT);
//	}
//
//	public void waitForInvisibility() {
//		this.waitForInvisibility(Constant.LONG_TIMEOUT);
//	}
//
//	public void scrollIntoView() {
//		Log.info(String.format("Scroll to %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
//						getElement());
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public boolean isDisplayedWithTimeout(int timeOutInSeconds) {
//		try {
//			Log.info(String.format("Check if control %s is displayed", getLocator().toString()));
//			this.waitForDisplayed(timeOutInSeconds);
//			return true;
//		} catch (TimeoutException timeOutEx) {
//			return false;
//		} catch (Exception e) {
//			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			return false;
//		}
//	}
//
//	public void hover() {
//		Log.info(String.format("Hover on %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				String mouseHoverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				((JavascriptExecutor) this.getDriver()).executeScript(mouseHoverScript, getElement());
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to hover on control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public void moveToElement() {
//		Log.info(String.format("Hover on %s", getLocator().toString()));
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				builder.moveToElement(getElement()).perform();
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to hover on control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public String getElementXpathAsString() {
//		String eleXpath = "";
//
//		try {
//			if (this.locator != null || this.by == null) {
//				return this.locator;
//			}
//
//			String sLocator = this.by.toString();
//			String byType = sLocator.split(": ")[0].split("\\.")[1];
//			String byValue = sLocator.split(": ")[1];
//			switch (byType) {
//			case "id":
//				eleXpath = "//*[@id='byValue']";
//			case "xpath":
//				eleXpath = byValue;
//			}
//		} catch (Exception e) {
//			Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//			throw e;
//		}
//
//		return eleXpath;
//	}
//
//	public void waitForClickable() {
//		this.waitForClickable(Constant.TIMEOUT);
//	}
//
//	public void waitForClickable(long timeOut) {
//		try {
//			new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut))
//					.until(ExpectedConditions.elementToBeClickable(getElement()));
//		} catch (Exception error) {
//			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
//					error.getMessage()));
//		}
//	}
//
//	public boolean isClickable() {
//		boolean isClickable = true;
//		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constant.TIMEOUT));
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(getElement()));
//			this.getElement().click();
//		} catch (TimeoutException e) {
//			isClickable = false;
//			Log.warn("[isNotDisplayed]: Getting timeout {}" + e.getLocalizedMessage());
//		} catch (Exception e) {
//			isClickable = false;
//		}
//		return isClickable;
//	}
//
//	public void selectOptionByText(String option) {
//		Log.info(String.format("Get Options on %s", getLocator().toString()));
//		Select selectByText = new Select(this.getElement());
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				selectByText.selectByVisibleText(option);
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//	
//	public void selectOptionByValue(String value) {
//		Log.info(String.format("Get Options on %s", getLocator().toString()));
//		Select selectByText = new Select(this.getElement());
//		int i = 0;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				selectByText.selectByValue(value);
//				return;
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//	}
//
//	public String[] getDropdownListOptions() {
//		Log.info(String.format("Select Option on %s", getLocator().toString()));
//		Select dropdownList = new Select(this.getElement());
//		int i = 0;
//		String[] options = null;
//		while (i < Constant.SHORT_TIMEOUT) {
//			i++;
//			try {
//				this.waitForDisplayed(Constant.SHORT_TIMEOUT);
//				List<WebElement> op = dropdownList.getOptions();
//				options = new String[op.size()];
//				for (int i2 = 0; i2 < op.size(); i2++) {
//					options[i2] = op.get(i2).getText();
//				}
//			} catch (StaleElementReferenceException staleEx) {
//				if (i == Constant.SHORT_TIMEOUT)
//					throw staleEx;
//				Log.error(String.format("Try to Select on control %s again", getLocator().toString()));
//			} catch (Exception e) {
//				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
//				throw e;
//			}
//		}
//		return options;
//	}
}
