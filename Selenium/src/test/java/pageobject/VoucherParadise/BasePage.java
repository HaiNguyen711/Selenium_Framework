package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Link;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, BasePage.class);

	Button btnConfirmationYes = locator.getLocator(ControlType.BUTTON, "btnConfirmationYes");

	public BasePage clickSidebarItem(String sidebarItem) {
		Link lnkSidebarItem = locator.getLocator(ControlType.LINK, "dynSibarItemXpath", sidebarItem);
		lnkSidebarItem.click();
		return this;
	}

	public LoginPage logout() {
		clickSidebarItem("Logout");
		btnConfirmationYes.click();
		return new LoginPage();
	}
	
}
