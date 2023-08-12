package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.Account;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class LoginPage extends BasePage {
	
	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, LoginPage.class);
	
	private final TextBox txtEmail = locator.getLocator(ControlType.TEXTBOX, "txtEmail");
	private final TextBox txtPassword = locator.getLocator(ControlType.TEXTBOX, "txtPassword");
	private final Button btnLogin = locator.getLocator(ControlType.BUTTON, "btnLogin");
	private final Label lblLogin = locator.getLocator(ControlType.LABEL, "lblLogin");
	

	public HomePage login(Account account) {
		
		txtEmail.enter(account.getEmail());
		txtPassword.enter(account.getPassword());
		btnLogin.click();
		return new HomePage();
	}
	
	public LoginPage waitForLoginPageIsDisplayed() {
		lblLogin.waitForVisibility(Constant.LONG_TIMEOUT);
		return this;
	}

}
