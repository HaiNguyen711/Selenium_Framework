package core.element.manager.wrapper;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import core.element.manager.base.ClickableControl;
import core.report.Log;
import utils.constant.Constant;

public class DropdownList extends ClickableControl {

	public DropdownList(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
}
