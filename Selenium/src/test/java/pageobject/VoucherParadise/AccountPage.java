package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import dataObjects.enums.ControlType;
import dataObjects.enums.RoleName;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class AccountPage extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, AccountPage.class);
	
	private final ComboBox slRole = locator.getLocator(ControlType.COMBOBOX, "slRole");
	private final Button btnInviteUser = locator.getLocator(ControlType.BUTTON, "btnInviteUser");
	
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
	
	public UserAccountPopup clickInviteUser() {
		btnInviteUser.click();
		return new UserAccountPopup();
	}
	
	public boolean isDisplayedNameInCart(String sName) {
		return locator.getLocator(ControlType.LABEL, "lblName", sName).isDisplayed();
	}
	
	public boolean isDisplayedPhoneInCart(String sPhone) {
		return locator.getLocator(ControlType.LABEL, "lblphone", sPhone).isDisplayed();
	}
	
	public Label getXpathUserNameInCart(String sUsername) {
		return locator.getLocator(ControlType.LABEL, "lblUsername", sUsername);
	}
	
	public boolean isDisplayedUsernameInCart(String sUsername) {
		return getXpathUserNameInCart(sUsername).isDisplayed();
	}

	public AccountPage moveMouseToCartWithUserName(String sUsername) {
		getXpathUserNameInCart(sUsername).scrollIntoView();
		getXpathUserNameInCart(sUsername).waitForPositionNotChange();
		getXpathUserNameInCart(sUsername).moveToElement();
		return this;
	}
	
	public UserAccountPopup clickEditButton(String sUsername) {
		Button btnEdit = locator.getLocator(ControlType.BUTTON, "btnEdit",sUsername);
		btnEdit.waitForVisibility();
		btnEdit.click();
		return new UserAccountPopup();
	}
	
	public void waitForCartisNotPresent(String sName) {
		locator.getLocator(ControlType.LABEL, "lblName", sName).waitForNotPresent();
	}
}
