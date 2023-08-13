package pageobject.VoucherParadise;

import org.openqa.selenium.Keys;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.CheckBox;
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
	private final Label lblErrorMessageFailureLogin = locator.getLocator(ControlType.LABEL, "lblErrorMessageFailureLogin");
	private final Label lblerrorDescriptionFailureLogin = locator.getLocator(ControlType.LABEL, "lblerrorDescriptionFailureLogin");
	private final Label lblErrorMessageUserName = locator.getLocator(ControlType.LABEL, "lblErrorMessageUserName");
	private final CheckBox chkRememberMe = locator.getLocator(ControlType.CHECKBOX, "chkRememberMe");
	
	public HomePage login(Account account) {
		
		txtEmail.enter(account.getEmail());
		txtPassword.enter(account.getPassword());
		btnLogin.click();
		return new HomePage();
	}
	
public HomePage login(Account account, boolean rememberMe) {
		
		txtEmail.enter(account.getEmail());
		txtPassword.enter(account.getPassword());
		chkRememberMe.set(rememberMe);
		btnLogin.click();
		return new HomePage();
	}
	
	public LoginPage waitForLoginPageIsDisplayed() {
		lblLogin.waitForVisibility(Constant.LONG_TIMEOUT);
		return this;
	}
	
	public String getErrorMessageFailureLogin() {
		return lblErrorMessageFailureLogin.getText();
	}

	public String getErrorDescriptionFailureLogin() {
		return lblerrorDescriptionFailureLogin.getText();
	}
	
	public LoginPage failureLogin(Account account) {

		txtEmail.enter(account.getEmail());
		txtPassword.enter(account.getPassword());
		btnLogin.click();
		return this;
	}
	public LoginPage enterUserName(String sUserName) {
		txtEmail.enter(sUserName);
		txtEmail.enter(Keys.TAB);
		return this;
	}
	
	public String getErrorMessageUserName() {
		lblErrorMessageUserName.waitForVisibility();
		return lblErrorMessageUserName.getText();
	}
	public String getUserNameText() {
		txtEmail.waitForVisibility();
		return txtEmail.getValue();
	}
	public String getPasswordText() {
		txtPassword.waitForVisibility();
		return txtPassword.getValue();
	}
}
