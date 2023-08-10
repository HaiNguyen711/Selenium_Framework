package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class Label extends ClickableControl {

	public Label(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public Label(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public Label(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public Label(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	public Label(String element, String string) {
		super(element, string);
	}
	
	public Label(String element, String[] string) {
		super(element, string);
	}
}
