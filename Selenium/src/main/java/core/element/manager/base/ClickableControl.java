package core.element.manager.base;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.element.manager.base.interfaces.IClickableControl;
import core.element.manager.base.type.ElementType;
import core.report.Log;
import io.netty.handler.timeout.TimeoutException;
import utils.constant.Constant;

public class ClickableControl extends BaseControl implements IClickableControl  {
	
	/**
	 * Contains log of the element used
	 */
	private static final Logger logger = Constant.createLogger(ClickableControl.class.getName());


	public ClickableControl(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public ClickableControl(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public ClickableControl(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public ClickableControl(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ==============================================================================================
	 */
	
	/**
	 * Click on the element
	 */
	@Override
	public void click() {
		logger.info(String.format("Click to %s", getLocator().toString()));
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
				logger.severe(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	/**
	 * Select on the element by text
	 */
	@Override
	public void selectOptionByText(String option) {
		logger.info(String.format("Get Options on %s", getLocator().toString()));
		Select selectByText = new Select(this.getElement());
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForNotPresent(Constant.TIMEOUT);
				selectByText.selectByVisibleText(option);
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to Select on control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
	
	/**
	 * Select on the element by value
	 */
	@Override
	public void selectOptionByValue(String value) {
		logger.info(String.format("Get Options on %s", getLocator().toString()));
		Select selectByText = new Select(this.getElement());
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForNotPresent(Constant.SHORT_TIMEOUT);
				selectByText.selectByValue(value);
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to Select on control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
	 * Double-click on the Element
	 */
	@Override
	public void doubleClick() {
		logger.info(String.format("Double-click on %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.SHORT_TIMEOUT) {
			i++;
			try {
				this.waitForClickable(Constant.TIMEOUT);
				int count = 0;
				while (count < 2) {
					click();
					count++;
				}
				return;
			} catch (StaleElementReferenceException staleEx) {
				if (i == Constant.SHORT_TIMEOUT)
					throw staleEx;
				logger.severe(String.format("Try to doubleClick control %s again", getLocator().toString()));
			} catch (Exception e) {
				logger.severe(
						String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
		
	}
}
