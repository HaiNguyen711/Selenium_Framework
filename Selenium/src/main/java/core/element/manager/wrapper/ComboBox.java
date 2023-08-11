package core.element.manager.wrapper;

import org.openqa.selenium.By;

import core.element.manager.base.BaseControl;
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

	public ComboBox(String element, String string) {
		super(element, string);
	}
	
	public ComboBox(String element, String[] string) {
		super(element, string);
	}
	
	public void clickOnItem(String value) {
		ComboBox selectedItem = new ComboBox(getElementXpathAsString() + "//option[@ng-reflect-value='"+ value +"']");
        selectedItem.waitForClickable();
        selectedItem.waitForVisibility();
        selectedItem.click();
    }
	
	public void select(String selectedText) {
		String mainXpath = getElementXpathAsString();
        ComboBox mainElement = new ComboBox(mainXpath);
        mainElement.waitForClickable();
        mainElement.click();
        enter(selectedText);
        clickOnItem(selectedText);
    }

    public void selectWithoutSearch(String value) {
        String mainXpath = getElementXpathAsString();
        ComboBox mainElement = new ComboBox(mainXpath);
        BaseControl listItem = new BaseControl(mainXpath + "//option");
        mainElement.waitForClickable();
        mainElement.click();
        /*
         * By Default, list item will constants attribute 'ng-hide'.
         * After that user click on combobox, this attribute will disappear.
         *
         */
        listItem.waitForVisibility();
        clickOnItem(value);
    }
}
