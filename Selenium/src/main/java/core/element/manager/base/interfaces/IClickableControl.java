package core.element.manager.base.interfaces;

public interface IClickableControl {
	
	/**
	 * Click on the element
	 */
	void click();

	/**
	 * Double-click on the Element
	 */
	void doubleClick();
	
	/**
	 * Select on the element by text
	 */
	void selectOptionByText(String option);
	
	/**
	 * Select on the element by value
	 */
	void selectOptionByValue(String value);
}
