package core.element.manager.base.interfaces;

public interface IEditableControl extends IClickableControl {
	
	/**
	 * Clear and enter value in element
	 * 
	 * @param value - value need enter
	 */
	 void enter(CharSequence... value);
	
	 /**
	 * Enter value in element
	 * 
	 * @param value - value need enter
	 */
	 void type(CharSequence... value);
	 
	 /**
	  * clear text value for an element
	  */
	 void clear();
}
