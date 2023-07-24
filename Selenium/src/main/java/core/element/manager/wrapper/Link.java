package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class Link extends ClickableControl {

	public Link(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public Link(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public Link(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public Link(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
}
