package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.Account;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class UserInfoPage extends BasePage {
	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, UserInfoPage.class);

	private final TextBox txtFirstName = locator.getLocator(ControlType.TEXTBOX, "txtFirstName");
	private final TextBox txtLastName = locator.getLocator(ControlType.TEXTBOX, "txtLastName");
	private final TextBox txtPhone = locator.getLocator(ControlType.TEXTBOX, "txtPhone");
	private final Button btnSave = locator.getLocator(ControlType.BUTTON, "btnSave");
	private final Label lblDescriptionMessageTopRight = locator.getLocator(ControlType.LABEL,
			"lblDescriptionMessageTopRight");
	private final Label lblMessageTopRight = locator.getLocator(ControlType.LABEL, "lblMessageTopRight");
	private final Label lblFirstNameErrorMessage = locator.getLocator(ControlType.LABEL, "lblFirstNameErrorMessage");
	private final Label lblLastNameErrorMessage = locator.getLocator(ControlType.LABEL, "lblLastNameErrorMessage");
	private final Label lblPhoneErrorMessage = locator.getLocator(ControlType.LABEL, "lblPhoneErrorMessage");
	private final TextBox txtCurrentPassword = locator.getLocator(ControlType.TEXTBOX, "txtCurrentPassword");
	private final TextBox txtNewPassword = locator.getLocator(ControlType.TEXTBOX, "txtNewPassword");
	private final TextBox txtConfirmPassword = locator.getLocator(ControlType.TEXTBOX, "txtConfirmPassword");
	private final Label lblNewPasswordErrorMessage = locator.getLocator(ControlType.LABEL,
			"lblNewPasswordErrorMessage");
	private final Label lblConfirmPasswordErrorMessage = locator.getLocator(ControlType.LABEL,
			"lblConfirmPasswordErrorMessage");
	private final Label lblSelectProfile = locator.getLocator(ControlType.LABEL, "lblSelectProfile");
	private final Label lblUserProfileImageErrorMessage = locator.getLocator(ControlType.LABEL, "lblUserProfileImageErrorMessage");
	
	public UserInfoPage uploadImage(String filePath) {
		lblSelectProfile.uploadFile(filePath);
		return this;
	}

	public UserInfoPage fillUserInfo(Account acc) {
		txtFirstName.enter(acc.getFirstName());
		txtLastName.enter(acc.getLastName());
		if (acc.getPhone() != "") {
			txtPhone.enter(acc.getPhone());
		}
		return this;
	}

	public UserInfoPage fillAllInChanePassword(String currentPassword, String newPassword) {
		txtCurrentPassword.enter(currentPassword);
		txtNewPassword.enter(newPassword);
		txtConfirmPassword.enter(newPassword);
		return this;
	}

	public UserInfoPage fillAllInChanePassword(String currentPassword, String newPassword, String confirmPassword) {
		txtCurrentPassword.enter(currentPassword);
		txtNewPassword.enter(newPassword);
		txtConfirmPassword.enter(confirmPassword);
		return this;
	}

	public UserInfoPage chanePassword(String currentPassword, String newPassword) {
		fillAllInChanePassword(currentPassword, newPassword);
		btnSave.click();
		return this;
	}

	public UserInfoPage editUserInfo(Account acc) {
		fillUserInfo(acc);
		btnSave.click();
		return this;
	}

	public String getMessageTopRight() {
		return lblMessageTopRight.getText();
	}

	public boolean isSaveButtonEnabled() {
		return !btnSave.isEnabled();
	}

	public String getDescriptionMessageTopRight() {
		return lblDescriptionMessageTopRight.getText();
	}

	public UserInfoPage enterFirstName(String sFirstName) {
		txtFirstName.enter(sFirstName);
		return this;
	}

	public UserInfoPage enterLastName(String sLastName) {
		txtLastName.enter(sLastName);
		return this;
	}

	public UserInfoPage enterPhone(String sPhone) {
		txtPhone.enter(sPhone);
		return this;
	}

	public String getFirstNameErrorMessage() {
		return lblFirstNameErrorMessage.getText();
	}

	public String getLastNameErrorMessage() {
		return lblLastNameErrorMessage.getText();
	}

	public String getPhoneErrorMessage() {
		return lblPhoneErrorMessage.getText();
	}

	public String getNewPasswordErrorMessage() {
		return lblNewPasswordErrorMessage.getText();
	}

	public String getConfirmPasswordErrorMessage() {
		return lblConfirmPasswordErrorMessage.getText();
	}
	public String getUserProfileImageErrorMessage() {
		return lblUserProfileImageErrorMessage.getText();
	}
}
