package core.element.manager.base.interfaces;

public interface IClickableControl {
	
	void click();
	
	void waitForClickable();
	
	void waitForClickable(long timeOut);
	
	boolean isClickable();
	
	void selectOptionByText(String option);
	
	void selectOptionByValue(String value);
	
	String[] getDropdownListOptions();

}
