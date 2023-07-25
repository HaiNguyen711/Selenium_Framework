package core.element.manager.base.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.element.manager.base.type.ElementType;

public interface IBaseControl {

	
	/**
	 * Get element By locator
	 * 
	 * @return By locator of the element
	 */
	By getLocator();
	
	/**
	 * Get web element from web page Locator without type is assigned to xpath
	 * 
	 * @return List element
	 */
	List<WebElement> getElements();
	
	/**
	 * Get string locator
	 * @return a string locator
	 */
	String getElementXpathAsString();
	
	
	/**
	 * Get Element of web
	 * 
	 * @param by    Locator type of element
	 * @param value Locator value of element
	 * 
	 * @return - Find element By
	 */
	public default By getByLocator(ElementType by, String value) {
		switch (by) {
		case CSS_SELECTOR:
			return By.cssSelector(value);
		case ID:
			return By.id(value);
		case CLASS_NAME:
			return By.className(value);
		case NAME:
			return By.name(value);
		case TAG_NAME:
			return By.tagName(value);
		case XPATH:
			return By.xpath(value);
		default:
			return By.xpath(value);
		}
	}
	
	
	/**
	 * 
	 * @param locator 
	 * @return a Locator by xpath
	 */
	public default By getByLocator(String locator) {
		return getByLocator(ElementType.XPATH, locator);
	}
}
