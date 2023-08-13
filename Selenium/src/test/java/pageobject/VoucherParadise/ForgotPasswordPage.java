package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class ForgotPasswordPage extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, ForgotPasswordPage.class);

	private final TextBox txtEmail = locator.getLocator(ControlType.TEXTBOX, "txtEmail");
	private final Button btnSubmit = locator.getLocator(ControlType.BUTTON, "btnSubmit");
	private final Label lblErrorMessageTopRight = locator.getLocator(ControlType.LABEL, "lblErrorMessageTopRight");
	private final Label lblErrorDescriptionTopRight = locator.getLocator(ControlType.LABEL, "lblErrorDescriptionTopRight");

	public ForgotPasswordPage submitForgotPassword(String sEmail) {
		txtEmail.enter(sEmail);
		btnSubmit.click();
		return this;
	}

	public String getErrorMessageTopRight() {
		return lblErrorMessageTopRight.getText();
	}

	public String getErrorDescriptionTopRight() {
		return lblErrorDescriptionTopRight.getText();
	}
}
