package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.EditableControl;
import core.element.manager.base.type.ElementType;

public class ComboBox extends EditableControl {

	public ComboBox(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public ComboBox(ElementType by, String value, String[] string) {
		super(by, value, string);
		// TODO Auto-generated constructor stub
	}

	public ComboBox(ElementType by, String value) {
		super(by, value);
		// TODO Auto-generated constructor stub
	}

	public ComboBox(By locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

}
