package pageobject.VoucherParadise;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.ComboBox;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;
import dataObjects.enums.ControlType;
import dataObjects.enums.UserInfor;
import utils.constant.Constant;
import utils.helper.Environment;
import utils.helper.LocatorHelper;
import utils.helper.Utilities;

public class UserAccountPopup extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, UserAccountPopup.class);
	
	private final Button btnSave = locator.getLocator(ControlType.BUTTON, "btnSave");
	private final Button btnCancel = locator.getLocator(ControlType.BUTTON, "btnCancel");
	private final Button InputProfileImage = locator.getLocator(ControlType.BUTTON, "btnProfile");
	private final Button btnConfirmationYes = locator.getLocator(ControlType.BUTTON, "btnConfirmationYes");
	private final Button btnDelete = locator.getLocator(ControlType.BUTTON, "btnDelete");
	private final ComboBox slPartner = locator.getLocator(ControlType.COMBOBOX, "slPartner");
	private final Label lblErrorMessage = locator.getLocator(ControlType.LABEL, "lblErrorMessage");
	private final Label lblErrorMessageImage = locator.getLocator(ControlType.LABEL, "lblErrorMessageImage");
	private final Label lblErrorMessageFirstLastName = locator.getLocator(ControlType.LABEL, "lblErrorMessageFirstLastName");
			
	public TextBox getXpathFillInfo(String svalue) {
		return locator.getLocator(ControlType.TEXTBOX, "txtInfo", svalue);
	}
	
	public boolean isDisplayedPopup(String sTitle) {
		return locator.getLocator(ControlType.LABEL, "lbltitle", sTitle).isDisplayed();
	}
	
	public void waitForPopupLoad(String sTitle) {
		locator.getLocator(ControlType.LABEL, "lbltitle", sTitle).waitForVisibility();
	}
	
	public UserAccountPopup fillInfo(String sFillName,CharSequence... sValue) {
		getXpathFillInfo(sFillName).enter(sValue);
		return this;
	}
	
	public UserAccountPopup clearInfo(String sFillName) {
		
		getXpathFillInfo(sFillName).clear();
		return this;
	}
	
	public AccountPage clickSaveButton() {
		btnSave.waitForEnabled();
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
		btnSave.waitForDisabled();
		return !btnSave.isEnabled();
	}
	
	public UserAccountPopup fillAllinfoAdminPopup(String sFirstName, String sLastName, String sUserName, String phone, String filePath) {
		fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		fillInfo(UserInfor.USERNAME.getText(), sUserName);
		getXpathFillInfo(UserInfor.PHONE.getText()).enter(phone);
		uploadImage(filePath);
		return this;
	}
	
	public AccountPage inviteNewAdmin(String sFirstName, String sLastName, String sUserName, String phone,String filePath) {
		fillAllinfoAdminPopup(sFirstName,sLastName,sUserName, phone,filePath);
		clickSaveButton();
		return new AccountPage();
	}
	
	public UserAccountPopup uploadImage(String filePath) {
		InputProfileImage.uploadFile(filePath);
		
		return this;
	}
	
	public boolean isEnabledUsername() {
		return getXpathFillInfo(UserInfor.USERNAME.getText()).isEnabled();
	}
	
	public AccountPage clickDeleteButton() {
		btnDelete.waitForVisibility();
		btnDelete.click();
		btnConfirmationYes.waitForVisibility();
		btnConfirmationYes.click();
		waitForDeleteMessageIsNotDisplayed();
		return new AccountPage();
	}
	
	public boolean isDisplayedDeleteButon() {
		return btnDelete.isDisplayed();
	}
	
	public UserAccountPopup selectPartner(String sPartner) {
		slPartner.select(sPartner);
		return this;
	}
	
	public UserAccountPopup fillAllinfoPartnerPopup(String sFirstName, String sLastName, String sUserName, String sPartner, String filePath) {
		fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		fillInfo(UserInfor.USERNAME.getText(), sUserName);
		selectPartner(sPartner);
		uploadImage(filePath);
		return this;
	}
	
	public AccountPage inviteNewPartner(String sFirstName, String sLastName, String sUserName, String sPartner, String filePath) {
		Environment environment = new Environment();
		String sMessageCreated = String.format(environment.getValue("msgCreatePartnerMemberAccount"), sUserName);
		fillAllinfoPartnerPopup(sFirstName,sLastName,sUserName,sPartner, filePath);
		clickSaveButton();
		this.waitForMessageIsNotDisplayed(sMessageCreated);
		return new AccountPage();
	}
	
	public String getErrorMessage() {
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
