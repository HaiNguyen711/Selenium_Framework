package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.Link;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import dataObjects.enums.SideBar;
import utils.constant.Constant;
import utils.helper.Environment;
import utils.helper.LocatorHelper;
import utils.helper.Utilities;

public class BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, BasePage.class);

	private final Button btnConfirmationYes = locator.getLocator(ControlType.BUTTON, "btnConfirmationYes");
	private final Link lnkLogo = locator.getLocator(ControlType.LINK, "lnkLogo");
	private final Button btnLogout = locator.getLocator(ControlType.BUTTON, "btnLogout");
	private final Label lblLogoutComfirm = locator.getLocator(ControlType.LABEL, "lblLogoutComfirm");
	private final Button btnLastPage = locator.getLocator(ControlType.BUTTON, "btnLastPage");
	private final ComboBox slPerPage = locator.getLocator(ControlType.COMBOBOX, "slPerPage");
	private final TextBox txtSearch = locator.getLocator(ControlType.TEXTBOX, "txtSearch");
	private final Button btnConfirmationNo = locator.getLocator(ControlType.BUTTON, "btnConfirmationNo");
	
	public <T extends BasePage> T openTab(SideBar sideBar) {
		Link lnkSidebarItem = locator.getLocator(ControlType.LINK, "dynSibarItemXpath", sideBar.getText());
		lnkSidebarItem.click();
		switch (sideBar) {
		case PARTNERS:
			return (T) new PartnersPage();
		case ACCOUNTS:
			return (T) new AccountPage();
		case USERINFO:
			return (T) new UserInfoPage();
		default:
			return null;
		}
	}
	public UserInfoPage gotoUserInfoPage() {
		Link lnkSidebarItem = locator.getLocator(ControlType.LINK, "dynSibarItemXpath", SideBar.USERINFO.getText());
		lnkSidebarItem.click();
		return new UserInfoPage();
	}
	public LoginPage logout() {
		btnLogout.click();
		btnConfirmationYes.waitForPresent();
		btnConfirmationYes.click();
		return new LoginPage();
	}
	
	public BasePage clickLogoutButton() {
		btnLogout.click();
		return this;
	}
	
	public LoginPage clickConfirmationYesButton() {
		btnConfirmationYes.click();
		return new LoginPage();
	}
	
	public BasePage clickConfirmationNoButton() {
		btnConfirmationNo.click();
		return this;
	}
	
	public boolean isLogoutComfirmNotificationExists() {
		return lblLogoutComfirm.isDisplayed();
	}

	public HomePage clickSiteLogo() {
		lnkLogo.click();
		return new HomePage();
	}
	
	public BasePage goTolastPage() {
		btnLastPage.waitForEnabled();
		while(btnLastPage.isEnabled()) {
			btnLastPage.click();
		}
		return this;
	}
	
	public BasePage selectItemsPerPage(String sValue) {
		Label lblPerPageItem = locator.getLocator(ControlType.LABEL, "lblPerPageItem", sValue);
		slPerPage.click();
		lblPerPageItem.waitForVisibility();
		lblPerPageItem.waitForClickable();
		lblPerPageItem.click();
		return this;
	}
	
	public BasePage search(String value) {
		
		txtSearch.enter(value);
		return this;
	}
	
	public BasePage reFreshPage() {
		Utilities.refresh();
		return this;
	}
	
	private Label getXpathMessageSuccessful(String sMessage) {
		return locator.getLocator(ControlType.LABEL, "lblMessageSuccessful", sMessage);
	}
	
	public void waitForMessageIsNotDisplayed(String sMessage) {
		getXpathMessageSuccessful(sMessage).waitForVisibility();
		getXpathMessageSuccessful(sMessage).waitForNotPresent();
	}
	
	public void waitForDeleteMessageIsNotDisplayed() {
		String sMessage = new Environment().getValue("msgDelete");
		getXpathMessageSuccessful(sMessage).waitForNotPresent();
	}
	
}
