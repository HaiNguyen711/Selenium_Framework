package core.element.manager.base.interfaces;

import org.openqa.selenium.WebElement;

public interface IGetElementableControl extends IBaseControl {
	
	/**
	 * Get web element from web page Locator without type is assigned to xpath
	 * 
	 * @return element
	 */
	WebElement getElement();
	

}
