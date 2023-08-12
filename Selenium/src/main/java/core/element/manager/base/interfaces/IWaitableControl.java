package core.element.manager.base.interfaces;

import java.time.Duration;
import java.util.NoSuchElementException;

import core.driver.manager.manage.DriverManager;
import core.report.Log;

import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import utils.constant.Constant;

public interface IWaitableControl extends IGetElementableControl {
	
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
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
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
            wait.until(driver -> getElements().isEmpty());
        } catch (Exception e) {
            Log.error(String.format("waitForElementNotPresent: Has error with control '%s': %s",
                    getLocator().toString(), e.getMessage()));
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
		this.waitForNotPresent(Constant.SHORT_TIMEOUT);
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
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
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
			wait.until((drive) -> this.getElement().isEnabled());
		} catch (Exception error) {
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is enabled
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForEnabled() {
		this.waitForEnabled(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element is disable
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForDisabled(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(driver -> {
                if (getElements().size() == 0)
                    return true;
                return !getElement().isEnabled();
            });
		} catch (Exception error) {
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is disable
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForDisabled() {
		this.waitForDisabled(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element is displayed in DOM
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForDisplayed(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator()));
        } catch (Exception e) {
            Log.error(String.format("WaitForDisplay: Has error with control '%s': %s", getLocator().toString(),
                    e.getMessage()));
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
			Log.error(String.format("Has error when wait for element '%s': %s", getLocator().toString(),
					error.getMessage()));
		}
	}
	
	/**
	 * Wait for element is displayed in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForDisplayed() {
		waitForDisplayed(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element isn't displayed in DOM
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForNotDisplayed() {
		waitForNotDisplayed(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element is selected
	 * 
	 * @param timeOut - wait for seconds time out
	 */
	public default void waitForSelected(int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeSelected(getLocator()));
        } catch (Exception e) {
            Log.error(String.format("waitForNotSelected: Has error with control '%s': %s", getLocator().toString(),
                    e.getMessage()));
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
			wait.until((driver) -> !getElement().isSelected());
        } catch (Exception e) {
            Log.error(String.format("waitForNotSelected: Has error with control '%s': %s", getLocator().toString(),
                    String.format("Expected condition failed: waiting for condition to be valid: element to not be selected" +
                            " (tried for %s second(s) with 500 milliseconds interval", timeOut)));
        }
	}
	
	/**
	 * Wait for element is selected
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForSelected() {
		waitForNotSelected(Constant.SHORT_TIMEOUT);
	}
	
	/**
	 * Wait for element isn't selected
	 * 
	 * @param timeOut - wait for default time out
	 */
	public default void waitForNotSelected() {
		waitForNotSelected(Constant.SHORT_TIMEOUT);
	}
	
	public default void waitForVisibility() {
        waitForVisibility(Constant.SHORT_TIMEOUT);
    }

    public default void waitForVisibility(int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSeconds));

            wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator()));
        } catch (Exception e) {
            Log.error(String.format("waitForVisibility: Has error with control '%s': %s", getLocator().toString(),
                    e.getMessage()));
        }
    }
    
    /**
     * Function    :   waitForPositionNotChange
     * Description :    wait for position of element is not changed (it helpful for waiting animation if stopped)
     *
     * @throws Exception
     * @param: timeOutInSeconds
     */
    public default void waitForPositionNotChange(int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(webDriver -> {
                if (getElement() != null) {
                    WebElement element = getElement();
                    if (element.getLocation() != null) {
                        Point pOld = element.getLocation();
                        Point pNew = element.getLocation();
                        return pOld.equals(pNew);
                    }
                }
                return false;
            });
        } catch (Exception e) {
        	Log.error(String.format("waitForPositionNotChange: Has error with control '%s': %s",
                    getLocator().toString(), e.getMessage()));
        }

    }
    
    /**
     * Function    :   waitForPositionNotChange
     * Description :    wait for position of element is not changed (it helpful for waiting animation if stopped)
     *
     * @throws Exception
     */
    public default void waitForPositionNotChange() {
    	waitForPositionNotChange(Constant.SHORT_TIMEOUT);

    }
}
