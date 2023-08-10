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
	
	TextBox txtEmail = locator.getLocator(ControlType.TEXTBOX, "txtEmail");
	TextBox txtPassword = locator.getLocator(ControlType.TEXTBOX, "txtPassword");
	Button btnLogin = locator.getLocator(ControlType.BUTTON, "btnLogin");
	Label lblLogin = locator.getLocator(ControlType.LABEL, "lblLogin");
	

	public HomePage login(Account account) {
		
		txtEmail.enter(account.getEmail());
		txtPassword.enter(account.getPassword());
		btnLogin.click();
		return new HomePage();
	}
	
	public LoginPage waitForLoginPageIsDisplayed() {
		lblLogin.waitForVisibility();
		return this;
	}

}
