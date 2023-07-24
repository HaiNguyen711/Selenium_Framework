package core.element.manager.base.interfaces;

import java.util.List;

public interface ISideActionableControl {

	/**
	 * Get the value of a given CSS property
	 * 
	 * @param propertyName: css name
	 * 
	 * @return css value
	 */
	String getCssValue(String propertyName);
	
	/**
	 * Get the value of a the given attribute of the element
	 * 
	 * @param attributeName: attributeName
	 * 
	 * @return attribute value
	 */
	String getAttribute(String attributeName);
	
	/**
	 * Get the visible innerText of this element
	 *
	 * @return The innerText of this element
	 */
	String getText();
	
	/**
	 * Get texts from list of elements
	 * 
	 * @return list of texts
	 */
	List<String> getAllTexts();
	
	/**
	 * Hover at the current mouse location
	 */
	void hover();

	/**
	 * Move over the given element
	 */
	void moveToElement();

	/**
	 * Scroll to an element
	 */
	void scrollIntoView();

	/**
	 * Get value of the value attribute of this element,
	 *
	 * @return The value of this element
	 */
	String getValue();
	
	/**
	 * Get value of the dropdown list
	 *
	 * @return The value of this element
	 */
	String[] getDropdownListOptions();
}
