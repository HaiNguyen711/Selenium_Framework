package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.EditableControl;
import core.element.manager.base.type.ElementType;

public class TextBox extends EditableControl {

	public TextBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public TextBox(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public TextBox(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public TextBox(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
	public TextBox(String element, String string) {
		super(element, string);
	}
	
	public TextBox(String element, String[] string) {
		super(element, string);
	}
}
