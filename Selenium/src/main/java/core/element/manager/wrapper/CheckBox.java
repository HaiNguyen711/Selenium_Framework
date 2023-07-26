package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class CheckBox extends ClickableControl {
	
	public CheckBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public CheckBox(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public CheckBox(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public CheckBox(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

}
