package tests.VoucherParadise;

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
import pageobject.VoucherParadise.InviteUserPopup;
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
		
		Log.STEP("4.Logout");
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
	 * Test id: AC31
	 * Verify that accounts are displayed correctly in Accounts page - Member role.
	 * 
	 */
	@Test
	public void AC31() {
		
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
		
		Log.STEP("4.Select Member role");
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad(RoleName.MEMBER);
		
		Log.verify("Member accounts page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.MEMBER),"Member accounts page is displayed");
		
		Log.STEP("4.Logout");
		loginPage = homePage.logout();
	}
	
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
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4.Select Partner role");
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad(RoleName.PARTNER);
		
		Log.verify("Partner accounts page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.PARTNER),"Partner accounts page is displayed");
		
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
		InviteUserPopup inviteUserPopup;
		
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
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		
		Log.verify("Invite new admin popup is displayed");
		assertHelper.assertTrue(inviteUserPopup.isDisplayedPopup(sNewAdminPopupTitle),"Invite new admin popup is displayed");
		
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
		InviteUserPopup inviteUserPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Partner accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad(RoleName.PARTNER);
		
		Log.STEP("4. Click on Invite User button");
		inviteUserPopup = accountPage.clickInviteUser();
		inviteUserPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.verify("Invite new partner popup is displayed");
		assertHelper.assertTrue(inviteUserPopup.isDisplayedPopup(sNewPartnerPopupTitle),"Invite new partner popup is displayed");
		
	}

	/**
	 * Test id: OT04
	 *  Verify that Invite new member popup is displayed while click invite user
	 * 
	 */
	@Test
	public void OT04() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoad();
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad(RoleName.MEMBER);
		
		Log.STEP("4. Click on Invite User button");
		inviteUserPopup = accountPage.clickInviteUser();
		inviteUserPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.verify("Invite new member popup is displayed");
		assertHelper.assertTrue(inviteUserPopup.isDisplayedPopup(sNewMemberPopupTitle),"Invite new member popup is displayed");
		
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
		String sFirstName = "Ad" + StringHelper.getRandomString(5);
		String sLastName = StringHelper.getRandomString(5);
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		accountPage = inviteUserPopup.inviteNewAdmin(sFirstName, sLastName, sUsername, phone, filePath);
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.goTolastPage();
		
		Log.verify("Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sFirstName+" "+sLastName),"Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedPhoneInCart(phone),"Newly created admin account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedUsernameInCart(sUsername),"Newly created admin account is displayed");
		
		Log.STEP("7.Logout");
		loginPage = homePage.logout();
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
		String sFirstName = "Ad" + StringHelper.getRandomString(5);
		String sLastName = StringHelper.getRandomString(5);
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillAllinfoAdminPopup(sFirstName, sLastName, sUsername, phone, filePath);
		
		Log.verify("Save button enabled");
		assertHelper.assertTrue(inviteUserPopup.isEnabledSaveButton(),"Save button enabled");
		
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
		String sFirstName = "Ad" + StringHelper.getRandomString(5);
		String sLastName = StringHelper.getRandomString(5);
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		inviteUserPopup.fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		inviteUserPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		inviteUserPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		
		Log.verify("save button still disabled");
		assertHelper.assertTrue(inviteUserPopup.isDisabledSaveButton(),"save button still disabled");
		
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
		String sUsername = StringHelper.getRandomString(5);
		String sErrorMessage= environment.getValue("errorMessageUsername");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		inviteUserPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		
		Log.verify("message appear: Username invalid format");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessage(),sErrorMessage, "message appear: Username invalid format");
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
		String sUsername = StringHelper.getRandomString(5);
		String sErrorMessage= environment.getValue("errorMessageImageType");
		String filePath = Constant.IMAGE_DATA + "\\accTestTypeFormat.jpeg";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		inviteUserPopup.clickAddImage();
		inviteUserPopup.uploadImage(filePath);
		
		Log.verify("message appear: File upload support .png/.jpg");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessageImage(),sErrorMessage, "File upload support .png/.jpg");
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
		String sUsername = StringHelper.getRandomString(5);
		String sErrorMessage= environment.getValue("errorMessageImageSize");
		String filePath = Constant.IMAGE_DATA + "\\accTestSizeFormat.png";
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		inviteUserPopup.clickAddImage();
		inviteUserPopup.uploadImage(filePath);
		
		Log.verify("message appear: Image's dimensions should be 1500x1500");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessageImage(),sErrorMessage, "Image's dimensions should be 1500x1500");
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
		String sErrorMessage= environment.getValue("errorMessagePhoneFormat");
		String phone = StringHelper.getRandomNumberToString(9);
		String sFirstName = "Ad" + StringHelper.getRandomString(5);
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter Phone invalid format");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		inviteUserPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		
		
		Log.verify("message appear: Phone invalid format");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessage(),sErrorMessage, "message appear: Phone invalid format");
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
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter firstname is blank");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		inviteUserPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
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
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter firstname is blank");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.LASTNAME.getText(), "");
		inviteUserPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		
		Log.verify("message appear: Last name is required");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessageFirstLastName(),sErrorMessage, "Last name is required");
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
		InviteUserPopup inviteUserPopup;
		
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
		InviteUserPopup inviteUserPopup;
		
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
		
		Log.STEP("5. Enter Phone invalid format");
		inviteUserPopup.waitForPopupLoad(sNewAdminPopupTitle);
		inviteUserPopup.fillInfo(UserInfor.PHONE.getText(), "");
		inviteUserPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		
		Log.verify("message appear: Phone is required");
		assertHelper.assertEquals(inviteUserPopup.getErrorMessage(),sErrorMessage, "message appear: Phone is required");
	}
}
