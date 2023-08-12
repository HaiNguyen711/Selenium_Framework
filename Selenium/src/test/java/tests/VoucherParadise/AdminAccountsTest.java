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
import pageobject.VoucherParadise.PartnersPage;
import tests.BaseTest;
import utils.constant.Constant;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;
import utils.helper.StringHelper;

@Listeners(AllureListener.class)
public class AdminAccountsTest extends BaseTest {
	
	/**
	 * Test id: AC01
	 * Verify that accounts are displayed correctly in Accounts page
	 * 
	 */
	@Test
	public void AC01() {
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		
		Log.verify("Account page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.ADMIN),"Account page is displayed");
		
		
		Log.STEP("4. Logout");
		loginPage = homePage.logout();
	}
	
	/**
	 * Test id: OT01
	 * Verify that accounts are displayed correctly in Accounts page - Admin role.
	 * 
	 */
	@Test
	public void OT01() {
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4.Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.verify("Admin accounts page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.ADMIN),"Admin accounts page is displayed");
		
		Log.STEP("4.Logout");
		loginPage = homePage.logout();
	}
	
	
	/**
	 * Test id: OT02
	 *  Verify that Invite new admin popup is displayed while click invite user
	 * 
	 */
	@Test
	public void OT02() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		
		Log.verify("Invite new admin popup is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedPopup(sNewAdminPopupTitle),"Invite new admin popup is displayed");
		
	}
	
	
	/**
	 * Test id: AC02
	 *   Verify that user is able to invite new admin by entering the valid data.
	 * 
	 */
	@Test
	public void AC02() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.goTolastPage();
		
		Log.verify("Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sFirstName+" "+sLastName),"Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedPhoneInCart(phone),"Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedUsernameInCart(sUsername),"Newly created admin account is displayed");
		
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
		
		Log.STEP("7.Logout");
		loginPage = accountPage.logout();
	}
	
	/**
	 * Test id: OT11
	 *   Verify that Save button is enable
	 * 
	 */
	@Test
	public void OT11() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillAllinfoAdminPopup(sFirstName, sLastName, sUsername, phone, filePath);
		
		Log.verify("Save button enabled");
		assertHelper.assertTrue(userAccountPopup.isEnabledSaveButton(),"Save button enabled");
		
	}
	
	/**
	 * Test id: OT10
	 *   Verify that Save button is disable while invite new admin by no uploading the profile image.
	 * 
	 */
	@Test
	public void OT10() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		
		Log.verify("save button still disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"save button still disabled");
		
	}
	
	/**
	 * Test id: AC03
	 *   Verify that user is unable to invite new admin by entering the invalid format username.
	 * 
	 */
	@Test
	public void AC03() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUsername = StringHelper.getRandomString();
		String sErrorMessage= environment.getValue("errorMessageUsername");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter username invalid format");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		
		Log.verify("message appear: Username invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Username invalid format");
	}
	
	/**
	 * Test id: AC05
	 *   Verify that user is unable to invite new admin by uploading the invalid format profile image.
	 * 
	 */
	@Test
	public void AC05() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUsername = StringHelper.getRandomString();
		String sErrorMessage= environment.getValue("errorMessageImageType");
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter username invalid format");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: File upload support .png/.jpg");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "File upload support .png/.jpg");
	}
	
	/**
	 * Test id: OT05
	 *   Verify that user is unable to invite new admin by uploading the invalid size profile image.
	 * 
	 */
	@Test
	public void OT05() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUsername = StringHelper.getRandomString();
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter username invalid format");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: Image's dimensions should be 1500x1500");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "Image's dimensions should be 1500x1500");
	}
	
	/**
	 * Test id: AC04
	 *   Verify that user is unable to invite new admin by entering the invalid format phone number.
	 * 
	 */
	@Test
	public void AC04() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sErrorMessage= "Phone " + environment.getValue("errorMessageFormat");
		String phone = StringHelper.getRandomNumberToString(9);
		String sFirstName = "Ad" + StringHelper.getRandomString();
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter Phone invalid format");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		
		
		Log.verify("message appear: Phone invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Phone invalid format");
	}
	
	/**
	 * Test id: OT06
	 *   Verify that user is unable to invite new admin by firstname is blank.
	 * 
	 */
	@Test
	public void OT06() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter firstname is blank");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
	}
	
	/**
	 * Test id: OT07
	 *  Verify that user is unable to invite new admin by lastname is blank
	 * 
	 */
	@Test
	public void OT07() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter firstname is blank");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		
		Log.verify("message appear: Last name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "Last name is required");
	}
	
	/**
	 * Test id: OT08
	 *   Verify that user is unable to invite new admin by username is blank
	 * 
	 */
	@Test
	public void OT08() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sErrorMessage= "Username " + environment.getValue("errorMessageRequired");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup inviteUserPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		inviteUserPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter username invalid format");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.USERNAME.getText(), "");
		inviteUserPopup.fillInfo(UserInfor.PHONE.getText(), "");
		
		Log.verify("message appear: Username is required");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessage(),sErrorMessage, "message appear: Username is required");
	}
	
	/**
	 * Test id: OT09
	 *   Verify that user is unable to invite new admin by phone is blank
	 * 
	 */
	@Test
	public void OT09() {
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sErrorMessage= "Phone " + environment.getValue("errorMessageRequired");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter Phone invalid format");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), "");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		Log.verify("message appear: Phone is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Phone is required");
	}
	
	
	/**
	 * Test id: AC06
	 *   Verify that admin is able to update admin account.
	 * 
	 */
	@Test
	public void AC06() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sNewFirstName = "Ad" + StringHelper.getRandomString();
		String sNewLastName = StringHelper.getRandomString();
		String newPhone = StringHelper.getRandomNumberToString(10);
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter valid data in all require fields");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("6. Move mouse to newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("7. Edit data in some require fields");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sNewFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sNewLastName);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), newPhone);
		
		Log.STEP("8.Click on Save button");
		accountPage = userAccountPopup.clickSaveButton();
		
		Log.verify("Admin user data is updated");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sFirstName+" "+sLastName),"Admin user data is updated");
		assertHelper.assertTrue(accountPage.isDisplayedPhoneInCart(phone),"Admin user data is updated");
		
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
		
		Log.STEP("7.Logout");
		loginPage = accountPage.logout();
	} 
	
	/**
	 * Test id: OT12
	 *   Verify that admin is not update username.
	 * 
	 */
	@Test
	public void OT12() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sNewFirstName = "Ad" + StringHelper.getRandomString();
		String sNewLastName = StringHelper.getRandomString();
		String newPhone = StringHelper.getRandomNumberToString(10);
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		
		Log.STEP("5. Enter valid data in all require fields");
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("6. Move mouse to newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.verify("username is disabled");
		assertHelper.assertFalse(userAccountPopup.isEnabledUsername(), "username is disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: AC07
	 *   Verify that admin is unable to update admin account by entering the empty First Name.
	 * 
	 */
	@Test
	public void AC07() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit Fiirst Name is blank");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("Save Button is Disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"Save Button is Disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: AC08
	 *   Verify that admin is unable to update admin account by entering the empty Last Name.
	 * 
	 */
	@Test
	public void AC08() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit Last Name is blank");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("Save Button is Disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"Save Button is Disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: OT13
	 *   Verify that admin is unable to update admin account by entering the empty phone.
	 * 
	 */
	@Test
	public void OT13() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit phone is blank");
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("Save Button is Disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"Save Button is Disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: OT14
	 *   Verify that admin is unable to update admin account by entering the invalid format phone
	 * 
	 */
	@Test
	public void OT14() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sErrorMessage= "Phone " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sNewPhone = StringHelper.getRandomNumberToString(11);
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit phone invalid format");
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), sNewPhone);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		
		Log.verify("message appear: Phone invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Phone invalid format");
	
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id:  OT15
	 *  Verify that admin is unable to update admin account by entering the invalid format First Name.
	 * 
	 */
	@Test
	public void OT15() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sErrorMessage = "First name " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sNewFirstName = "Ad " + StringHelper.getRandomString();
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit First Name is invalid format");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sNewFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		
		Log.verify("message appear: First Name invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "message appear: First Name invalid format");
	
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id:  OT16
	 *  Verify that admin is unable to update admin account by entering the  invalid format Last Name.
	 * 
	 */
	@Test
	public void OT16() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sErrorMessage = "Last name " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sNewLastName = "Ad " + StringHelper.getRandomString();
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		
		Log.STEP("6. Edit First Name is invalid format");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sNewLastName);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		Log.verify("message appear: First Name invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "message appear: Last name invalid format");
	
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id:  OT17
	 *  Verify that delete button is displayed in update admin popup
	 * 
	 */
	@Test
	public void OT17() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sErrorMessage = "Last name " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sNewLastName = "Ad " + StringHelper.getRandomString();
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		Log.verify("Delete button is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedDeleteButon(),"Delete button is displayed");
	
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id:  OT18
	 *  Verify that admin can delete user
	 * 
	 */
	@Test
	public void OT18() {
		
		Environment environment = new Environment();
		String sNewAdminPopupTitle = environment.getValue("newAdminPopupTitle");
		String sUpdateAdminTitle = environment.getValue("updateAdminTitle");
		String sErrorMessage = "Last name " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sNewLastName = "Ad " + StringHelper.getRandomString();
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Admin accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("5. Select newly created admin user. Click on Edit button");
		accountPage.goTolastPage();
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		userAccountPopup.waitForPopupLoad(sUpdateAdminTitle);
		
		Log.STEP("6. click Delete button");
		Log.STEP("7. click Yes button");
		accountPage = userAccountPopup.clickDeleteButton();
		
		Log.verify("user is deleted");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sUsername),"user is deleted");

	}
	
}
