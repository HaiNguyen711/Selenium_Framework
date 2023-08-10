package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.ClickableControl;
import core.element.manager.base.type.ElementType;

public class RadioButton extends ClickableControl {
	
	public RadioButton(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public RadioButton(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public RadioButton(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public RadioButton(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public RadioButton(String element, String string) {
		super(element, string);
	}
	
	public RadioButton(String element, String[] string) {
		super(element, string);
	}
}
