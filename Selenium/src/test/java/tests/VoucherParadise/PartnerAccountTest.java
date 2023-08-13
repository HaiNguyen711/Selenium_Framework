package tests.VoucherParadise;

import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.AllureListener;
import core.report.Log;
import dataObjects.Account;
import dataObjects.enums.RoleName;
import dataObjects.enums.SideBar;
import dataObjects.enums.UserInfor;
import dataObjects.enums.Users;
import pageobject.VoucherParadise.AccountPage;
import pageobject.VoucherParadise.HomePage;
import pageobject.VoucherParadise.UserAccountPopup;
import pageobject.VoucherParadise.LoginPage;
import tests.BaseTest;
import utils.constant.Constant;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;
import utils.helper.StringHelper;

@Listeners(AllureListener.class)
public class PartnerAccountTest extends BaseTest {

	
	/**
	 * Test id: AC32
	 * Verify that accounts are displayed correctly in Accounts page - Partner role.
	 * 
	 */
	@Test
	public void AC32() {
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4.Select Partner role");
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.verify("Partner accounts page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.PARTNER),"Partner accounts page is displayed");
		
		Log.STEP("4.Logout");
		loginPage = accountPage.logout();
	}
	
	/**
	 * Test id: OT03
	 *  Verify that Invite new partner popup is displayed while click invite user
	 * 
	 */
	@Test
	public void OT03() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.verify("Invite new partner popup is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedPopup(sNewPartnerPopupTitle),"Invite new partner popup is displayed");
		
	}
	
	/**
	 * Test id: AC09
	 *  Verify that user is able to invite new partner by entering the valid data.
	 * 
	 */
	@Test
	public void AC09() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sHighlandsCoffee");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.verify("Newly created partner account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedUsernameInCart(sUsername), "Newly created partner account is displayed");
		
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
		
		Log.STEP("7.Logout");
		loginPage = accountPage.logout();
	}
	
	/**
	 * Test id: AC10
	 *  Verify that user is unable to invite new partner by entering the invalid format username.
	 * 
	 */
	@Test
	public void AC10() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage= environment.getValue("errorMessageUsername");
		String sUsername = StringHelper.getRandomString();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		
		Log.verify("message appear: Username invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Username invalid format");
	}
	
	/**
	 * Test id: AC12
	 *   Verify that user is unable to invite new partner by uploading the invalid format profile image.
	 * 
	 */
	@Test
	public void AC12() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage= environment.getValue("errorMessageImageSize");
		String filePath = Constant.IMAGE_DATA + "\\accTestTypeFormat.jpeg";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Upload image invalid type");
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: File upload support .png/.jpg");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "File upload support .png/.jpg");
	}
	
	/**
	 * Test id: OT19
	 *   Verify that user is unable to invite new partner by uploading the invalid size profile image.
	 * 
	 */
	@Test
	public void OT19() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage= environment.getValue("errorMessageImageSize");
		String filePath = Constant.IMAGE_DATA + "\\accTestSizeFormat.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Upload image invalid size");
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: Image's dimensions should be 1500x1500");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "Image's dimensions should be 1500x1500");
	}
	
	/**
	 * Test id: OT20
	 *   Verify that user is unable to invite new partner by firstname is blank
	 * 
	 */
	@Test
	public void OT20() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage="First name " + environment.getValue("errorMessageRequired");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter firstname is blank");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
	}
	
	/**
	 * Test id: OT21
	 *  Verify that user is unable to invite new partner by lastname is blank
	 * 
	 */
	@Test
	public void OT21() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage="Last name " + environment.getValue("errorMessageRequired");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter lasrname is blank");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), " ",Keys.BACK_SPACE);
		
		
		Log.verify("message appear: Last name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "Last name is required");
	}
	
	/**
	 * Test id: OT22
	 *  Verify that user is unable to invite new partner by username is blank
	 * 
	 */
	@Test
	public void OT22() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage="Username " + environment.getValue("errorMessageRequired");
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter username is blank");
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: Username invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Username invalid format");
	}
	
	/**
	 * Test id: OT23
	 *  Verify that Save button is disable while invite new partner by no uploading the profile image.
	 * 
	 */
	@Test
	public void OT23() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sHighlandsCoffee");
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter invalid data in some require fields but un upload profile image");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		userAccountPopup.selectPartner(sPartner);
		
		Log.verify("Save button still disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"Save button still disabled");
	}
	
	/**
	 * Test id: OT24
	 *  Partner Roles - Verify that Save button is enable
	 * 
	 */
	@Test
	public void OT24() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sHighlandsCoffee");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Enter valid data in all require fields");
		userAccountPopup.fillAllinfoPartnerPopup(sFirstName, sLastName, sUsername, sPartner, filePath);
		
		Log.verify("Save button enable");
		assertHelper.assertTrue(userAccountPopup.isEnabledSaveButton(),"Save button enable");
		
	}
	
	/**
	 * Test id: OT25
	 *  Partner Roles - Verify that partner is not update username.
	 * 
	 */
	@Test
	public void OT25() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sHighlandsCoffee");
		String sMessageCreated = String.format(environment.getValue("msgCreatePartnerMemberAccount"), sUsername);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.STEP("5. Invite new admin user");
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.goTolastPage();
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.verify("username is disabled");
		assertHelper.assertFalse(userAccountPopup.isEnabledUsername(), "username is disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: AC13
	 *  Verify that admin is able to update partner account.
	 * 
	 */
	@Test
	public void AC13() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sUniqlo");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sNewFirstName = "Ad" + StringHelper.getRandomString();
		String sNewLastName = StringHelper.getRandomString();
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		String sName = sFirstName + " " + sLastName;
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new partner user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created partner user. Click on Edit button");
		accountPage.waitForLoadPartnerAccountCartPage(sName, sPartner);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. Edit data in some require fields");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sNewFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sNewLastName);
		
		Log.STEP("7. Click on Save button");
		accountPage = userAccountPopup.clickSaveButton();
		accountPage.waitForMessageIsNotDisplayed(sMessageUpdate);
		
		Log.verify("Newly Update partner account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sNewFirstName + " " + sNewLastName), "Newly Update partner account is displayed");
		
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: OT28
	 *  Verify that delete button is displayed in update partner popup
	 * 
	 */
	@Test
	public void OT28() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sUniqlo");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		String sName = sFirstName + " " + sLastName;
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new partner user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created partner user. Click on Edit button");
		accountPage.waitForLoadPartnerAccountCartPage(sName,sPartner);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.verify("Delete button is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedDeleteButon(), "Delete button is displayed");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}	
	
	/**
	 * Test id: OT29
	 *  Verify that admin can delete user in partner role
	 * 
	 */
	@Test
	public void OT29() {
		
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sUniqlo");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		String sName = sFirstName + " " + sLastName;
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new partner user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created partner user. Click on Edit button");
		accountPage.waitForLoadPartnerAccountCartPage(sName, sPartner);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. click Delete button");
		Log.STEP("7. click Yes button");
		accountPage = userAccountPopup.clickDeleteButton();
		accountPage.waitForPartnerCartIsDisappear(sName);
		
		Log.verify("user is deleted");
		assertHelper.assertFalse(accountPage.isDisplayedNameInCart(sFirstName + " " + sLastName),"user is deleted");
		
	}	
	
	/**
	 * Test id: OT26
	 *   Verify that user is unable to upload partner by firstname is blank
	 * 
	 */
	@Test
	public void OT26() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage="First name " + environment.getValue("errorMessageRequired");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sUniqlo");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sName = sFirstName + " " + sLastName;
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new partner user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created partner user. Click on Edit button, Enter lastname is blank");
		accountPage.waitForLoadPartnerAccountCartPage(sName, sPartner);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: OT27
	 *   Verify that user is unable to upload partner by lastname is blank
	 * 
	 */
	@Test
	public void OT27() {
		Environment environment = new Environment();
		String sNewPartnerPopupTitle = environment.getValue("newPartnerPopupTitle");
		String sErrorMessage="Last name " + environment.getValue("errorMessageRequired");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String sPartner = environment.getValue("sUniqlo");
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sName = sFirstName + " " + sLastName;
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new partner user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		accountPage = userAccountPopup.inviteNewPartner(sFirstName, sLastName, sUsername, sPartner, filePath);
		accountPage.waitForLoad();
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created partner user. Click on Edit button, Enter lastname is blank");
		accountPage.waitForLoadPartnerAccountCartPage(sName, sPartner);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
}
