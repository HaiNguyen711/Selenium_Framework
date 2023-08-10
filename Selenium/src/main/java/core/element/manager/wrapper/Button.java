package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class Button extends ClickableControl {

	public Button(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public Button(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public Button(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public Button(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	public Button(String element, String string) {
		super(element, string);
	}
	
	public Button(String element, String[] string) {
		super(element, string);
	}
}
