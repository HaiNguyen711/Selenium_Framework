package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class BasePage {
	
	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, BasePage.class);
	
	Button btnSignIn = locator.getLocator(ControlType.BUTTON, "btnSignIn");
	
	public LoginPage navigateToLoginPage() {
		btnSignIn.click();
		return new LoginPage();
	}
}
