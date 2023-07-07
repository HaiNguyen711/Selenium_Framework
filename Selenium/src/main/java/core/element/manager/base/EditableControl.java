package core.element.manager.base;

import org.openqa.selenium.StaleElementReferenceException;

import core.element.manager.base.interfaces.IEditableControl;
import core.report.Log;
import utils.constant.Constant;

public class EditableControl extends ClickableControl implements IEditableControl {

	public EditableControl(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	public void enter(String value) {
		this.waitForLoad(Constant.LONG_TIMEOUT);
		clear();
		type(value);
	}

	public void type(String value) {
		Log.info(String.format("Enter %s to %s", value ,getLocator().toString()));
		int i = 0;
		while (i < Constant.LONG_TIMEOUT) {
			i++;
			try {
				this.waitForLoad(Constant.LONG_TIMEOUT);
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

	public void clear() {
		Log.info(String.format("Clear to %s", getLocator().toString()));
		int i = 0;
		while (i < Constant.LONG_TIMEOUT) {
			i++;
			try {
				this.waitForLoad(Constant.LONG_TIMEOUT);
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
