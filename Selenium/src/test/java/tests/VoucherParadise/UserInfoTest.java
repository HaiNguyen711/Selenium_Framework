package tests.VoucherParadise;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.AllureListener;
import core.report.Log;
import dataObjects.Account;
import dataObjects.enums.RoleName;
import dataObjects.enums.SideBar;
import dataObjects.enums.Users;
import pageobject.VoucherParadise.AccountPage;
import pageobject.VoucherParadise.HomePage;
import pageobject.VoucherParadise.LoginPage;
import pageobject.VoucherParadise.UserAccountPopup;
import pageobject.VoucherParadise.UserInfoPage;
import tests.BaseTest;
import utils.constant.Constant;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;
import utils.helper.StringHelper;

@Listeners(AllureListener.class)
public class UserInfoTest extends BaseTest {

	/**
	 * Test id: US01
	 * Verify that user is able to update user profile with valid data.
	 * 
	 */
	@Test
	public void US01() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sNotificationMessageUpdateAdminUserInfo = environment.getValue("notificationMessageUpdateAdminUserInfo");
		String sNotificationDescriptionUpdateadminUserInfo = environment.getValue("notificationDescriptionUpdateadminUserInfo");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sNewFirstName = "NewAd" + StringHelper.getRandomString();
		String sNewLastName = "New" + StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Edit data in some require fields");
		Log.STEP("10.Click on Save button");
		new_acc.setFirstName(sNewFirstName);
		new_acc.setLastName(sNewLastName);
		userInfoPage.editUserInfo(new_acc);
		
		Log.verify("Message: Update successful. Admin information updated successfully.");
		assertHelper.assertEquals(userInfoPage.getMessageTopRight(), sNotificationMessageUpdateAdminUserInfo, 
				"Message: Update successful.");
		assertHelper.assertEquals(userInfoPage.getDescriptionMessageTopRight(), sNotificationDescriptionUpdateadminUserInfo, 
				"Message: Admin information updated successfully.");
		
		Log.STEP("110.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US02
	 * Verify that user is unable to update user profile with first name is empty.
	 * 
	 */
	@Test
	public void US02() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sMsgErrorFirstName = environment.getValue("msgErrorFirstName");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Edit First Name field");
		userInfoPage.enterFirstName(sEmptyString);
		
		Log.verify("Error message: Confirm password min length 8 characters");
		assertHelper.assertEquals(userInfoPage.getNewPasswordErrorMessage(), sMsgErrorFirstName, 
				"Error message: Confirm password min length 8 characters");
		Log.verify("Error message: Confirm password min length 8 characters");
		assertHelper.assertEquals(userInfoPage.getNewPasswordErrorMessage(), sMsgErrorFirstName, 
				"Error message: Confirm password min length 8 characters");
		
		Log.STEP("10.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US03
	 * Verify that user is unable to update user profile with last name is empty.
	 * 
	 */
	@Test
	public void US03() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sMsgErrorLastName = environment.getValue("msgErrorLastName");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Edit Last Name field");
		userInfoPage.enterLastName(sEmptyString);
		
		Log.verify("Error message: Last name is required");
		assertHelper.assertEquals(userInfoPage.getLastNameErrorMessage(), sMsgErrorLastName, 
				"Error message: Last name is required");
		
		Log.STEP("10.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US04
	 * Verify that user is unable to update user profile with invalid format phone number.
	 * 
	 */
	@Test
	public void US04() {
		
		Environment environment = new Environment();
		String sInvalidPhone = environment.getValue("sInvalidPhone");
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sMsgErrorPhone = environment.getValue("msgErrorPhone");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Edit Phone field");
		userInfoPage.enterPhone(sInvalidPhone);
		
		Log.verify("Error message: Phone is required");
		assertHelper.assertEquals(userInfoPage.getLastNameErrorMessage(), sMsgErrorPhone, 
				"Error message: Phone is required");
		
		Log.STEP("10.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US05
	 * Verify that user is able to change password with valid data.
	 * 
	 */
	@Test
	public void US05() {
		
		Environment environment = new Environment();
		String sNewPassword = environment.getValue("sNewPassword");
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sNotificationMessageUpdateAdminUserInfo = environment.getValue("notificationMessageUpdateAdminUserInfo");
		String sNotificationDescriptionUpdateadminUserInfo = environment.getValue("notificationDescriptionUpdateadminUserInfo");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Change password");
		userInfoPage.chanePassword(sDefaultPassword, sNewPassword);
		
		Log.verify("Message: Update successful. Admin information updated successfully.");
		assertHelper.assertEquals(userInfoPage.getMessageTopRight(), sNotificationMessageUpdateAdminUserInfo, 
				"Message: Update successful.");
		assertHelper.assertEquals(userInfoPage.getDescriptionMessageTopRight(), sNotificationDescriptionUpdateadminUserInfo, 
				"Message: Admin information updated successfully.");
		
		Log.STEP("10.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US06
	 * Verify that user is unble to change password with New Password and Confirm Password length less than 8.
	 * 
	 */
	@Test
	public void US06() {
		
		Environment environment = new Environment();
		String sInvalidLegthNewPassword = environment.getValue("sInvalidLegthNewPassword");
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sMsgErrorNewName = environment.getValue("msgErrorNewName");
		String sMsgErrorConfirmName = environment.getValue("msgErrorConfirmName");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Enter Current Password");
		Log.STEP("10.Enter Current Password");
		Log.STEP("11.Enter Current Password");
		userInfoPage.fillAllInChanePassword(sDefaultPassword, sInvalidLegthNewPassword);
		
		Log.verify("Error message: New password min length 8 characters");
		assertHelper.assertEquals(userInfoPage.getMessageTopRight(), sMsgErrorNewName, 
				"Error message: New password min length 8 characters");
		
		Log.verify("Error message: Confirm password min length 8 characters");
		assertHelper.assertEquals(userInfoPage.getDescriptionMessageTopRight(), sMsgErrorConfirmName, 
				"Error message: Confirm password min length 8 characters");

		Log.STEP("12.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US07
	 * Verify that user is unble to change password when new password is inconsistent and confirm password.
	 * 
	 */
	@Test
	public void US07() {
		
		Environment environment = new Environment();
		String sNewPassword = environment.getValue("sNewPassword");
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Enter Current Password");
		Log.STEP("10.Enter Current Password");
		Log.STEP("11.Enter Current Password");
		userInfoPage.fillAllInChanePassword(sDefaultPassword, sDefaultPassword,  sNewPassword);
		
		Log.verify("Unable to change password");
		assertHelper.assertEquals(userInfoPage.isSaveButtonEnabled(), true, 
				"Unable to change password");
		
		Log.STEP("12.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US08
	 * Verify that user is unble to change password with New Password and Confirm Password are empty.
	 * 
	 */
	@Test
	public void US08() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Enter Current Password");
		Log.STEP("10.Enter Current Password");
		Log.STEP("11.Enter Current Password");
		userInfoPage.fillAllInChanePassword(sDefaultPassword, sEmptyString);
		
		Log.verify("Unable to change password");
		assertHelper.assertEquals(userInfoPage.isSaveButtonEnabled(), true, 
				"Unable to change password");
		
		Log.STEP("12.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US09
	 * Verify that user is unble to change password wrong current password.
	 * 
	 */
	@Test
	public void US09() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sNewPassword = environment.getValue("sNewPassword");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Enter Current Password");
		Log.STEP("10.Enter Current Password");
		Log.STEP("11.Enter Current Password");
		userInfoPage.fillAllInChanePassword(sNewPassword, sNewPassword);
		
		Log.verify("Unable to change password");
		assertHelper.assertEquals(userInfoPage.isSaveButtonEnabled(), true, 
				"Unable to change password");
		
		Log.STEP("12.Logout");
		loginPage = userInfoPage.logout();
	}
	
	/**
	 * Test id: US10
	 * Verify that user is unable to update user profile image with invalid format file.
	 * 
	 */
	@Test
	public void US10() {
		
		Environment environment = new Environment();
		String sEmptyString = environment.getValue("emptyString");
		String sDefaultPassword = environment.getValue("sDefaultPassword");
		String sErrorMessageImageType = environment.getValue("errorMessageImageType");
		
		String sFirstName = "Ad" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String InvalidImagefilePath = Constant.IMAGE_DATA + "\\usTestFileExtension.gif";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		UserInfoPage userInfoPage;
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4. Select Admin role");
		accountPage.SelectRole(RoleName.ADMIN);
		
		
		Log.STEP("5. Invite new admin user");
		userAccountPopup = accountPage.clickInviteUser();
		accountPage = userAccountPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad();
		
		Log.STEP("6. Log out");
		loginPage = accountPage.logout();
		
		Log.STEP("7.Login with newly created user credentials");
		Account new_acc = new Account(sUsername, sDefaultPassword, sFirstName, sLastName, sEmptyString);
		homePage = loginPage.login(new_acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("8.Go to User Profile page");
		userInfoPage = homePage.gotoUserInfoPage();
		
		Log.STEP("9.Click on Select Profile button");
		Log.STEP("10.Select invalid format file");
		userInfoPage.uploadImage(InvalidImagefilePath);
		
		Log.verify("Error message: File upload support .png/.jpg");
		assertHelper.assertEquals(userInfoPage.getUserProfileImageErrorMessage(), sErrorMessageImageType, 
				"Error message: File upload support .png/.jpg");
		
		Log.STEP("11.Logout");
		loginPage = userInfoPage.logout();
	}
}
