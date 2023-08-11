package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.Link;
import dataObjects.enums.ControlType;
import dataObjects.enums.SideBar;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, BasePage.class);

	private final Button btnConfirmationYes = locator.getLocator(ControlType.BUTTON, "btnConfirmationYes");
	private final Link lnkLogo = locator.getLocator(ControlType.LINK, "lnkLogo");
	private final Button btnLogout = locator.getLocator(ControlType.BUTTON, "btnLogout");
	private final Label lblLogoutComfirm = locator.getLocator(ControlType.LABEL, "lblLogoutComfirm");
	private final Button btnLastPage = locator.getLocator(ControlType.BUTTON, "btnLastPage");
	private final ComboBox slPerPage = locator.getLocator(ControlType.COMBOBOX, "slPerPage");
	private final Label lblErrorMessage = locator.getLocator(ControlType.LABEL, "lblErrorMessage");
	private final Label lblErrorMessageImage = locator.getLocator(ControlType.LABEL, "lblErrorMessageImage");
	private final Label lblErrorMessageFirstLastName = locator.getLocator(ControlType.LABEL, "lblErrorMessageFirstLastName");

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

	public LoginPage logout() {
		btnLogout.click();
		btnConfirmationYes.waitForVisibility();
		btnConfirmationYes.click();
		return new LoginPage();
	}
	
	public HomePage clickSiteLogo() {
		lnkLogo.click();
		return new HomePage();
	}
	
	public BasePage goTolastPage() {
		btnLastPage.waitForEnabled();
		btnLastPage.click();
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
	
	public String getErrorMessage() {
		lblErrorMessage.waitForVisibility();
		return lblErrorMessage.getText();
	}
	
	public String getErrorMessageImage() {
		lblErrorMessageImage.waitForVisibility();
		return lblErrorMessageImage.getText();
	}
	
	public String getErrorMessageFirstLastName() {
		lblErrorMessageFirstLastName.waitForVisibility();
		return lblErrorMessageFirstLastName.getText();
	}
	
}
