package core.element.manager.base.interfaces;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import core.driver.manager.manage.DriverManager;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import utils.constant.Constant;

public interface IWaitableControl extends IGetElementableControl {


	/**
	 * Contains log of the element used
	 */
	public static final Logger logger = Constant.createLogger(IWaitableControl.class.getName());
	
	/**
	 * Wait for element is presented in DOM
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForPresent(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						getElement();
					} catch (NoSuchElementException e) {
						return false;
					}
					return true;
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element isn't presented in DOM
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForNotPresent(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						getElement();
					} catch (NoSuchElementException e) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}

	/**
	 * Wait for element is presented in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForPresent() {
		this.waitForPresent(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element isn't presented in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForNotPresent() {
		this.waitForNotPresent(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element is click element
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForClickable() {
		this.waitForClickable(Constant.TIMEOUT);
	}

	
	/**
	 * Wait for element is click element
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForClickable(long timeOut) {
		try {
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut))
					.until(ExpectedConditions.elementToBeClickable(getElement()));
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is enabled
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForEnabled(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return  getElement().isDisplayed() && getElement().isEnabled();
					} catch (NoSuchElementException e) {
						return false;
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is enabled
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForEnabled() {
		this.waitForEnabled(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element is disable
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForDisabled(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return  !(getElement().isDisplayed() && getElement().isEnabled());
					} catch (NoSuchElementException e) {
						return false;
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is disable
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForDisabled() {
		this.waitForDisabled(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element is displayed in DOM
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForDisplayed(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return getElement().isDisplayed();
					} catch (NoSuchElementException e) {
						return false;
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element isn't displayed in DOM
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForNotDisplayed(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return !(getElement().isDisplayed());
					} catch (NoSuchElementException e) {
						return true;
					} catch (StaleElementReferenceException e) {
						return true;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is displayed in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForDisplayed() {
		waitForDisplayed(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element isn't displayed in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForNotDisplayed() {
		waitForNotDisplayed(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element is selected
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForSelected(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return getElement().isSelected();
					} catch (NoSuchElementException e) {
						return true;
					} catch (StaleElementReferenceException e) {
						return true;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element isn't selected
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForNotSelected(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						return !(getElement().isSelected());
					} catch (NoSuchElementException e) {
						return true;
					} catch (StaleElementReferenceException e) {
						return true;
					}
				}
			});
		} catch (Exception error) {
			logger.severe(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is selected
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForSelected() {
		waitForNotSelected(Constant.LONG_TIMEOUT);
	}
	
	/**
	 * Wait for element isn't selected
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForNotSelected() {
		waitForNotSelected(Constant.LONG_TIMEOUT);
	}
}
