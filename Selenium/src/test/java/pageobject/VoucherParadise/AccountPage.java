package pageobject.VoucherParadise;

import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import dataObjects.enums.ControlType;
import dataObjects.enums.RoleName;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class AccountPage extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, AccountPage.class);
	
	private final ComboBox slRole = locator.getLocator(ControlType.COMBOBOX, "slRole");
	
	public AccountPage SelectRole(RoleName role) {
		slRole.selectWithoutSearch(role.getText());
		return this;
	}
	
	public Label getXpathTitleRoleAccount(RoleName role) {
		return locator.getLocator(ControlType.LABEL, "lblTitle", role.getText());
	}
	
	public void waitForLoad(RoleName role) { 
		getXpathTitleRoleAccount(role).waitForVisibility();
	}
	
	public boolean isDisplayed(RoleName role) {
		return getXpathTitleRoleAccount(role).isDisplayed();
	}
}
