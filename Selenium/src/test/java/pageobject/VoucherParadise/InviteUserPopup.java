package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import dataObjects.enums.UserInfor;
import utils.constant.Constant;
import utils.helper.LocatorHelper;
import utils.helper.Utilities;

public class InviteUserPopup extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, InviteUserPopup.class);
	
	private final Button btnSave = locator.getLocator(ControlType.BUTTON, "btnSave");
	private final Button btnCancel = locator.getLocator(ControlType.BUTTON, "btnCancel");
	private final Button btnProfile = locator.getLocator(ControlType.BUTTON, "btnProfile");
			
	public TextBox getXpathFillInfo(String svalue) {
		return locator.getLocator(ControlType.TEXTBOX, "txtInfo", svalue);
	}
	
	public boolean isDisplayedPopup(String sTitle) {
		return locator.getLocator(ControlType.LABEL, "lbltitle", sTitle).isDisplayed();
	}
	
	public void waitForPopupLoad(String sTitle) {
		locator.getLocator(ControlType.LABEL, "lbltitle", sTitle).waitForVisibility();
	}
	
	public InviteUserPopup fillInfo(String sFillName,String sValue) {
		getXpathFillInfo(sFillName).enter(sValue);
		return this;
	}
	
	public AccountPage clickSaveButton() {
		btnSave.waitForEnabled();
		btnSave.waitForClickable();
		btnSave.click();
		return new AccountPage();
	}
	
	public AccountPage clickCancelButton() {
		btnCancel.waitForEnabled();
		btnCancel.waitForClickable();
		btnCancel.click();
		return new AccountPage();
	}
	
	public boolean isEnabledSaveButton() {
		btnSave.waitForEnabled();
		return btnSave.isEnabled();
	}
	
	public boolean isDisabledSaveButton() {
		return !btnSave.isEnabled();
	}
	
	public InviteUserPopup fillAllinfoAdminPopup(String sFirstName, String sLastName, String sUserName, String phone, String filePath) {
		getXpathFillInfo(UserInfor.FIRSTNAME.getText()).enter(sFirstName);
		getXpathFillInfo(UserInfor.LASTNAME.getText()).enter(sLastName);
		getXpathFillInfo(UserInfor.USERNAME.getText()).enter(sUserName);
		getXpathFillInfo(UserInfor.PHONE.getText()).enter(phone);
		btnProfile.click();
		uploadImage(filePath);
		return this;
	}
	
	public AccountPage inviteNewAdmin(String sFirstName, String sLastName, String sUserName, String phone,String filePath) {
		fillAllinfoAdminPopup(sFirstName,sLastName,sUserName, phone,filePath);
		clickSaveButton();
		return new AccountPage();
	}
	
	public InviteUserPopup clickAddImage() {
		btnProfile.click();
		return this;
	}
	
	public InviteUserPopup uploadImage(String filePath) {
		Utilities.uploadFile(filePath);
		return this;
	}
}
