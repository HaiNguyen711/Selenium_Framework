package tests.VoucherParadise;

import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.AllureListener;
import core.report.Log;
import dataObjects.Account;
import dataObjects.enums.DateFormat;
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
public class MemberAccountTest extends BaseTest {

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
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		
		Log.STEP("4.Select Member role");
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.verify("Member accounts page is displayed");
		assertHelper.assertTrue(accountPage.isDisplayed(RoleName.MEMBER),"Member accounts page is displayed");
		
		Log.STEP("4.Logout");
		loginPage = accountPage.logout();
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
		UserAccountPopup userAccountPopup;
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.verify("Invite new member popup is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedPopup(sNewMemberPopupTitle),"Invite new member popup is displayed");
		
	}
	
	/**
	 * Test id: AC17
	 *    Verify that user is able to invite new member by entering the valid data.
	 * 
	 */
	@Test
	public void AC17() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter valid data in all require fields");
		Log.STEP("6. Click on Save button");
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.verify("Newly created member account is displayed");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sFirstName + " " + sLastName),"Newly created member account is displayed");
	
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: AC18
	 *    Verify that user is unable to invite new member by entering the invalid fortmat phone number.
	 * 
	 */
	@Test
	public void AC18() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "Phone " + environment.getValue("errorMessageFormat");
		String phone = StringHelper.getRandomNumberToString(11);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter invalid format phone number");
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		
		Log.verify("message appear: Phone invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Phone invalid format");
	}
	
	/**
	 * Test id: AC19
	 *     Verify that user is unable to invite new member by entering the invalid fortmat username.
	 * 
	 */
	@Test
	public void AC19() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "Username " + environment.getValue("errorMessageFormat");
		String sUsername = StringHelper.getRandomNumberToString(11);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter invalid format phone number");
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), "");
		
		
		Log.verify("message appear: Username invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "message appear: Username invalid format");
	}
	
	/**
	 * Test id: AC20
	 *    Verify that admin is able to update member account with valid data.
	 * 
	 */
	@Test
	public void AC20() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "Phone " + environment.getValue("errorMessageFormat");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sNewFirstName = "Ad" + StringHelper.getRandomString();
		String sNewLastName = StringHelper.getRandomString();
		String newPhone = StringHelper.getRandomNumberToString(10);
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. Edit data in some require fields");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sNewFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sNewLastName);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), newPhone);
		
		Log.STEP("7.Click on Save button");
		accountPage = userAccountPopup.clickSaveButton();
		accountPage.waitForMessageIsNotDisplayed(sMessageUpdate);
		
		Log.verify("Updated member account is successfully");
		assertHelper.assertTrue(accountPage.isDisplayedNameInCart(sNewFirstName+" "+sNewLastName),"Updated member account is successfully");
		assertHelper.assertTrue(accountPage.isDisplayedPhoneInCart(newPhone),"Updated member account is successfully");
		
		Log.STEP("Post-condition");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: AC21
	 *    Verify that admin is unable to update member account by entering empty First Name.
	 * 
	 */
	@Test
	public void AC21() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="First name " + environment.getValue("errorMessageRequired");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. Enter empty first name");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: AC22
	 *    Verify that admin is unable to update member account by entering empty Last Name.
	 * 
	 */
	@Test
	public void AC22() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="Last name " + environment.getValue("errorMessageRequired");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. Enter empty last name");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "Last name is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: OT34
	 *    Verify that admin is unable to update member account by entering empty phone.
	 * 
	 */
	@Test
	public void OT34() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="Phone " + environment.getValue("errorMessageRequired");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("6. Enter empty Address");
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: Address is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "Phone is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: OT39
	 *    Verify that user is unable to invite new member by uploading the invalid format profile image.
	 * 
	 */
	@Test
	public void OT39() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Upload image invalid type");
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: File upload support .png/.jpg");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "File upload support .png/.jpg");
	}
	
	/**
	 * Test id: OT35
	 *    Verify that user is unable to invite new Member by uploading the invalid size profile image.
	 * 
	 */
	@Test
	public void OT35() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Upload image invalid size");
		userAccountPopup.uploadImage(filePath);
		
		Log.verify("message appear: Image's dimensions should be 1500x1500");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageImage(),sErrorMessage, "Image's dimensions should be 1500x1500");
	}
	
	/**
	 * Test id: OT36
	 *    Verify that user is unable to invite new Member by firstname is blank
	 * 
	 */
	@Test
	public void OT36() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter firstname is blank");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "First name is required");
	}
	
	/**
	 * Test id: OT37
	 *     Verify that user is unable to invite new Member by lastname is blank
	 * 
	 */
	@Test
	public void OT37() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter lasttname is blank");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "Last name is required");
	}
	
	/**
	 * Test id: OT38
	 *     Verify that user is unable to invite new Member by username is blank
	 * 
	 */
	@Test
	public void OT38() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter username is blank");
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: First name is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "Username is required");
	}
	
	/**
	 * Test id: OT40
	 *     Verify that user is unable to invite new Member by address is blank
	 * 
	 */
	@Test
	public void OT40() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="Address " + environment.getValue("errorMessageRequired");
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter address is blank");
		userAccountPopup.enterAddressMember( " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: address is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "address is required");
	}
	
	/**
	 * Test id: AC23
	 *    Verify that admin is unable to update member account by entering empty address.
	 * 
	 */
	@Test
	public void AC23() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="Address " + environment.getValue("errorMessageRequired");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.STEP("5. Enter address is blank");
		userAccountPopup.enterAddressMember( " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: address is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "address is required");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
		
	}
	
	/**
	 * Test id: OT41
	 *     Verify that user is unable to invite new Member by phone is blank
	 * 
	 */
	@Test
	public void OT41() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
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
		homePage.waitForLoadHomePage();
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter Phone is blank");
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: phone is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "phone is required");
	}
	
	/**
	 * Test id: OT42
	 *     Verify that user is unable to invite new Member by phone is blank
	 * 
	 */
	@Test
	public void OT42() {
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "Birthday " + environment.getValue("errorMessageRequired");
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter Birthday is blank");
		userAccountPopup.fillInfo(UserInfor.BIRTHDAY.getText(), " ",Keys.BACK_SPACE);
		
		Log.verify("message appear: Birthday is required");
		assertHelper.assertEquals(userAccountPopup.getErrorMessage(),sErrorMessage, "Birthday is required");
	}
	
	/**
	 * Test id: OT43
	 *    Verify that user is unable to invite new Member by firstname is invalid format
	 * 
	 */
	@Test
	public void OT43() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "First name " + environment.getValue("errorMessageFormat");
		String sFirstName = "Ad " + StringHelper.getRandomString();
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter invalid format First name");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), "");
		
		
		
		Log.verify("message appear: First name invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "message appear: First name invalid format");
	}
	
	/**
	 * Test id: OT44
	 *    Verify that user is unable to invite new Member by lastname is invalid format
	 * 
	 */
	@Test
	public void OT44() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage= "Last name " + environment.getValue("errorMessageFormat");
		String sLastName = "Ad " + StringHelper.getRandomString();
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter invalid format phone number");
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), "");
		
		
		
		Log.verify("message appear: First name invalid format");
		assertHelper.assertEquals(userAccountPopup.getErrorMessageFirstLastName(),sErrorMessage, "message appear: Last name invalid format");
	}
	
	/**
	 * Test id: OT45
	 *    Member roles - Verify that partner is not update username.
	 * 
	 */
	@Test
	public void OT45() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sErrorMessage="Phone " + environment.getValue("errorMessageRequired");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		String filePath = Constant.IMAGE_DATA + "\\accTest.png";
		String sMessageUpdate = String.format(environment.getValue("msgUpdatePartnerMemberAccount"), sUsername);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Invite new member user");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		accountPage = userAccountPopup.inviteNewMember(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		accountPage.goTolastPage();
		
		Log.STEP("5. Select newly created member user. Click on Edit button");
		accountPage.moveMouseToCartWithUserName(sUsername);
		userAccountPopup = accountPage.clickEditButton(sUsername);
		
		Log.verify("username is disabled");
		assertHelper.assertFalse(userAccountPopup.isEnabledUsername(), "username is disabled");
		
		Log.STEP("Post-condition");
		accountPage = userAccountPopup.clickDeleteButton();
	}
	
	/**
	 * Test id: OT46
	 *    Verify that Save button is disable while invite new member by no uploading the profile image.
	 * 
	 */
	@Test
	public void OT46() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
		
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter nvalid data in some require fields but un upload profile image");
		userAccountPopup.fillInfo(UserInfor.FIRSTNAME.getText(), sFirstName);
		userAccountPopup.fillInfo(UserInfor.LASTNAME.getText(), sLastName);
		userAccountPopup.fillInfo(UserInfor.USERNAME.getText(), sUsername);
		userAccountPopup.fillInfo(UserInfor.PHONE.getText(), phone);
		userAccountPopup.fillInfo(UserInfor.BIRTHDAY.getText(), sDate);
		userAccountPopup.enterAddressMember(sAddress);
		
		Log.verify("save button still disabled");
		assertHelper.assertTrue(userAccountPopup.isDisabledSaveButton(),"save button still disabled");
	}
	
	/**
	 * Test id: OT47
	 *    Member Roles - Verify that Save button is enable
	 * 
	 */
	@Test
	public void OT47() {
		
		Environment environment = new Environment();
		String sNewMemberPopupTitle = environment.getValue("newMemberPopupTitle");
		String sFirstName = "MA" + StringHelper.getRandomString();
		String sLastName = StringHelper.getRandomString();
		String sUsername = sFirstName + "@vp.com";
		String phone = StringHelper.getRandomNumberToString(10);
		String sAddress = StringHelper.getRandomString(20);
		String sDate = StringHelper.getNowTimeDayMinus(DateFormat.FORMAT_1.getText(),18);
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
		
		Log.STEP("3. Go to Accounts page > Member accounts");
		accountPage = homePage.openTab(SideBar.ACCOUNTS);
		accountPage.waitForLoad();
		accountPage.SelectRole(RoleName.MEMBER);
		accountPage.waitForLoad();
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewMemberPopupTitle);
		
		Log.STEP("5. Enter valid data in all require fields");
		userAccountPopup.fillAllinfoMemberPopup(sFirstName, sLastName, sUsername, phone, sAddress, sDate, filePath);
		
		Log.verify("Save button enabled");
		assertHelper.assertTrue(userAccountPopup.isEnabledSaveButton(),"Save button enabled");
	}
}
