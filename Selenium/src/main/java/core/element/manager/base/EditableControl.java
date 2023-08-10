package core.element.manager.base;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import core.element.manager.base.interfaces.IEditableControl;
import core.element.manager.base.type.ElementType;
import core.report.Log;
import utils.constant.Constant;

public class EditableControl extends ClickableControl implements IEditableControl {

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
	
	public EditableControl(String element, String string) {
		super(element, string);
	}
	
	public EditableControl(String element, String[] string) {
		super(element, string);
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
	public void enter(String value) {
		clear();
		type(value);
	}

	 /**
	 * Enter value in element
	 * 
	 * @param value - value need enter
	 */
	@Override
	public void type(String value) {
		Log.info(String.format("Enter %s to %s", value ,getLocator().toString()));
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
				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}

	/**
    * clear text value for an element
    */
	@Override
	public void clear() {
		Log.info(String.format("Clear to %s", getLocator().toString()));
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
				Log.error(String.format("Try to scroll to control %s again", getLocator().toString()));
			} catch (Exception e) {
				Log.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
				throw e;
			}
		}
	}
}
