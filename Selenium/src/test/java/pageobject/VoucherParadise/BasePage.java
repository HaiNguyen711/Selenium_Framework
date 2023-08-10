package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Link;
import dataObjects.enums.ControlType;
import dataObjects.enums.SideBar;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, BasePage.class);

	Button btnConfirmationYes = locator.getLocator(ControlType.BUTTON, "btnConfirmationYes");

	public <T extends BasePage> T clickSidebarItem(SideBar sideBar) {
		Link lnkSidebarItem = locator.getLocator(ControlType.LINK, "dynSibarItemXpath", sideBar.getText());
		lnkSidebarItem.click();
		switch (sideBar) {
		case PARTNERS:
			return (T) new PartnersPage();
		case ACCOUNTS:
			return (T) new AccountPage();
		case USERINFOR:
			return (T) new UserInfoPage();
		default:
			return null;
		}
	}

	public LoginPage logout() {
		Link lnkLogout = locator.getLocator(ControlType.LINK, "dynSibarItemXpath", SideBar.LOGOUT.getText());
		lnkLogout.click();
		btnConfirmationYes.click();
		return new LoginPage();
	}
	
}
