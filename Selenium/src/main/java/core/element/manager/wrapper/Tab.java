package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class Tab extends ClickableControl {
	public Tab(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public Tab(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public Tab(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public Tab(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	public Tab(String element, String string) {
		super(element, string);
	}
	
	public Tab(String element, String[] string) {
		super(element, string);
	}
}
