package core.element.manager.base;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import core.element.manager.base.interfaces.IEditableControl;
import core.element.manager.base.type.ElementType;
import core.report.Log;
import utils.constant.Constant;

public class EditableControl extends ClickableControl implements IEditableControl {
	
	/**
	 * Contains log of the element used
	 */
	private static final Logger logger = Constant.createLogger(EditableControl.class.getName());

	public EditableControl(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public EditableControl(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public EditableControl(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public EditableControl(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ==============================================================================================
	 */
	/**
	 * Clear and enter value in element
	 * 
	 * @param value - value need enter
	 */
	@Override
	public void enter(CharSequence... value) {
		clear();
		type(value);
	}

	 /**
	 * Enter value in element
	 * 
	 * @param value - value need enter
	 */
	@Override
	public void type(CharSequence... value) {
		logger.info(String.format("Enter %s to %s", value ,getLocator().toString()));
		int i = 0;
		while (i < Constant.LONG_TIMEOUT) {
			i++;
			try {
				this.waitForPresent();
				getElement().sendKeys(value);
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
    * clear text value for an element
    */
	@Override
	public void clear() {
		logger.info(String.format("Clear to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.LONG_TIMEOUT) {
			i++;
			try {
				this.waitForPresent();
				getElement().clear();
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
}
