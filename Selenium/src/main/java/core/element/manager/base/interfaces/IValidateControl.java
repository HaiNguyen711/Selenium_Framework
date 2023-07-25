package core.element.manager.base.interfaces;

public interface IValidateControl {
	
	/**
	 * Check element is displayed with default time out
	 * 
	 * @return true or false
	 */
	boolean isDisplayed();

	/**
	 * Check element is displayed with wait for time out
	 * 
	 *  @param timeOutInSeconds - wait for seconds time out
	 *  
	 * @return true or false
	 */
	boolean isDisplayed(int timeOutInSeconds);
	
	/**
	 * Check element enabled or not with default time out
	 * 
	 * @return true or false
	 */
	boolean isEnabled();
	
	/**
	 * Check element enabled with wait time out
	 * 
	 * @param timeOutInSeconds - wait for seconds time out
	 * 
	 * @return true or false
	 */
	boolean isEnabled(int timeOutInSeconds);
	
	/**
	 * Check element selected or not
	 * 
	 * @return true or false
	 */
	boolean isSelected();
	
	/**
	 * Check element selected or not with wait time out
	 * 
	 * @param timeOutInSeconds - seconds to wait until element become visible or
	 *                         undiscovered
	 * 
	 * @return true or false
	 */
	boolean isSelected(int timeOutInSeconds);
	
	/**
	 * Check element is clicked
	 * 
	 * @return true or false
	 */
	boolean isClickable();
}
