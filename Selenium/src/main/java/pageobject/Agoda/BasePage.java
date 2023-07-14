package pageobject.Agoda;

import java.util.HashMap;
import core.element.manager.base.BaseControl;
import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.DropdownList;
import core.element.manager.wrapper.Element;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.Agoda.*;
import utils.helper.PageHelper;

public class BasePage {
	private static HashMap<String, Locator> _locators = null;

	public BasePage() {
		_locators = PageHelper.getLocatorsByClassName(this.getClass().getName());
	}

	protected BaseControl getControl(String name) {
		Locator locator = _locators.get(name);
		switch (locator.getsControlType()) {
		case "button":
			return new Button(locator.getsLocator(), locator.getsLocatorType());
		case "textbox":
			return new TextBox(locator.getsLocator(), locator.getsLocatorType());
		case "combobox":
			return new ComboBox(locator.getsLocator(), locator.getsLocatorType());
		case "label":
			return new Label(locator.getsLocator(), locator.getsLocatorType());
		case "dropdown":
			return new DropdownList(locator.getsLocator(), locator.getsLocatorType());
		default:
			return new Element();
//		return new BaseControl(locator.getsLocator(), locator.getsLocatorType());
		}}
	}

