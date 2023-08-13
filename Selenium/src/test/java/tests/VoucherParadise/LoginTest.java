package tests.VoucherParadise;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.AllureListener;
import dataObjects.Account;
import dataObjects.enums.Users;
import pageobject.VoucherParadise.*;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;

@Listeners(AllureListener.class)
public class LoginTest extends BaseTest {

	/**
	 * Test id: LO01
	 * Verify that user will be able to login with a valid username and valid password.
	 * 
	 */
	@Test
	public void LO01() {
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Log.STEP("3.Click on LogIn button");
		Account acc = AccountHepler.getUser(Users.ADMIN);
		homePage = loginPage.login(acc);
		
		Log.verify("Login successfully");
		assertHelper.assertEquals(homePage.getHomePageTitle(),sWelcomTitle, "Login successfully");
		
		Log.STEP("4.Logout");
		loginPage = homePage.logout();
	}
	
	/**
	 * Test id: LO02
	 * Verify that user cannot login with a valid username and an invalid password.
	 * 
	 */
	@Test
	public void LO02() {
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		String sInvalidPassword = environment.getValue("invalidPassword");
		String sErrorMessageFailureLogin = environment.getValue("errorMessageFailureLogin");
		String sErrorDescriptionFailureLogin = environment.getValue("errorDescriptionFailureLogin");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username and invalid password");
		Log.STEP("3.Click on LogIn button");
		Account acc = AccountHepler.getUser("Admin");
		acc.setPassword(sInvalidPassword);
		loginPage = loginPage.failureLogin(acc);
		
		Log.verify("Failure login");
		assertHelper.assertEquals(loginPage.getErrorMessageFailureLogin(),sErrorMessageFailureLogin, "Failure login");
		assertHelper.assertEquals(loginPage.getErrorDescriptionFailureLogin(),sErrorDescriptionFailureLogin, "Failure login");
		
	}
	
	/**
	 * Test id: LO03
	 * Verify that user cannot login with a invalid format username.
	 * 
	 */
	@Test
	public void LO03() {
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		String sInvalidUserName = environment.getValue("invalidUserName");
		String sErrorMessageUserName = environment.getValue("errorMessageUsername");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter invalid username");
		loginPage.enterUserName(sInvalidUserName);
		
		Log.verify("User cannot login with invalid username");
		assertHelper.assertEquals(loginPage.getErrorMessageUserName(),sErrorMessageUserName, "User cannot login with invalid username");
		
	}
	
	/**
	 * Test id: LO04
	 * Verify the Remember Me functionality.
	 * 
	 */
	@Test
	public void LO04() {
		AssertHelper assertHelper = new AssertHelper();
		
		LoginPage loginPage = new LoginPage();
		HomePage homePage;
		Account acc1 = AccountHepler.getUser("Admin01");
		Account acc2 = AccountHepler.getUser("Admin");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Turn on Remember Me checkbox");
		Log.STEP("3.Enter valid username/password");
		Log.STEP("4.Click on LogIn button");
		
		homePage = loginPage.login(acc1, true);
		Log.STEP("5.Logout");
		loginPage = homePage.logout();
		
		Log.verify("Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getUserNameText(),acc1.getEmail(), "Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getPasswordText(),acc1.getPassword(), "Username and password on Login page will be filled in automatically");
		
		Log.STEP("6.Login again with another username/password");
		homePage = loginPage.login(acc2);
		
		Log.STEP("7.Logout");
		loginPage = homePage.logout();
		
		Log.verify("Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getUserNameText(),acc2.getEmail(), "Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getPasswordText(),acc2.getPassword(), "Username and password on Login page will be filled in automatically");
		
	}
	
	/**
	 * Test id: LO05
	 * Verify that user is able to turn off Remember Me function.
	 * 
	 */
	@Test
	public void LO05() {
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		String sEmptyString = environment.getValue("emptyString");
		LoginPage loginPage = new LoginPage();
		HomePage homePage;
		Account acc = AccountHepler.getUser("Admin01");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Turn on Remember Me checkbox");
		Log.STEP("3.Enter valid username/password");
		Log.STEP("4.Click on LogIn button");
		
		homePage = loginPage.login(acc, true);
		Log.STEP("5.Logout");
		loginPage = homePage.logout();
		
		Log.verify("Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getUserNameText(),acc.getEmail(), "Username and password on Login page will be filled in automatically");
		assertHelper.assertEquals(loginPage.getPasswordText(),acc.getPassword(), "Username and password on Login page will be filled in automatically");
		
		Log.STEP("6.Turn off Remember Me checkbox");
		Log.STEP("7.Login again");
		homePage = loginPage.login(acc,false);
		Log.STEP("8.Logout");
		loginPage = homePage.logout();
		
		Log.verify("Username and password are empty");
		assertHelper.assertEquals(loginPage.getUserNameText(),sEmptyString, "Username and password are empty");
		assertHelper.assertEquals(loginPage.getPasswordText(),sEmptyString, "Username and password are empty");
		
	}
	
	/**
	 * Test id: LO06
	 * Verify the Logout functionality.
	 * 
	 */
	@Test
	public void LO06() {
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		BasePage basePage;
		Account acc = AccountHepler.getUser("Admin01");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Log.STEP("3.Click on LogIn button");
		
		basePage = loginPage.login(acc);
		Log.STEP("4.Click on Logout link");
		basePage = basePage.clickLogoutButton();
		
		Log.verify("Verify that confirmation dialog is displayed after click on Logout link");
		assertHelper.assertEquals(basePage.isLogoutComfirmNotificationExists(), true, "Verify that confirmation dialog is displayed after click on Logout link");
		
		Log.STEP("5.Click on Yes button");
		loginPage = basePage.clickConfirmationYesButton();
		
		Log.verify("Verify that confirmation dialog is disappeared");
		assertHelper.assertEquals(basePage.isLogoutComfirmNotificationExists(), false, "Verify that confirmation dialog is disappeared");
		Log.verify("Login page is displayed");
		assertHelper.assertEquals(loginPage.isPageExists(),true, "Login page is displayed");
		
	}
	
	/**
	 * Test id: LO07
	 * Verify that user is able to cancel Logout.
	 * 
	 */
	@Test
	public void LO07() {
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		BasePage basePage;
		Account acc = AccountHepler.getUser("Admin01");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Log.STEP("3.Click on LogIn button");
		
		basePage = loginPage.login(acc);
		Log.STEP("4.Click on Logout link");
		basePage = basePage.clickLogoutButton();
		
		Log.verify("Verify that confirmation dialog is displayed after click on Logout link");
		assertHelper.assertEquals(basePage.isLogoutComfirmNotificationExists(), true, "Verify that confirmation dialog is displayed after click on Logout link");
		
		Log.STEP("5.Click on No button");
		basePage = basePage.clickConfirmationNoButton();
		
		Log.verify("Verify that confirmation dialog is disappeared");
		assertHelper.assertEquals(basePage.isLogoutComfirmNotificationExists(), false, "Verify that confirmation dialog is disappeared");
		
	}
	
	/**
	 * Test id: LO08
	 * Verify the Forgot Password functionality.
	 * 
	 */
	@Test
	public void LO08() {
		Environment environment = new Environment();
		String sNotificationMessageForgotPassword = environment.getValue("notificationMessageForgotPassword");
		String sNotificationDescriptionForgotPassword = environment.getValue("notificationDescriptionForgotPassword");
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		ForgotPasswordPage forgotPasswordPage;
		Account acc = AccountHepler.getUser("Admin01");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Click on Forgot Password link");
		forgotPasswordPage = loginPage.gotoForgotPasswordPage();
		Log.STEP("3.Enter exists username");
		Log.STEP("4.Click on Submit button");
		forgotPasswordPage.submitForgotPassword(acc.getEmail());
		
		Log.verify("Message: Successful Please check your mailbox for the instruction to reset your password.");
		assertHelper.assertEquals(forgotPasswordPage.getErrorMessageTopRight(), sNotificationMessageForgotPassword, 
				"Message: Successful Please check your mailbox for the instruction to reset your password.");
		assertHelper.assertEquals(forgotPasswordPage.getErrorDescriptionTopRight(), sNotificationDescriptionForgotPassword, 
				"Message: Successful Please check your mailbox for the instruction to reset your password.");
		
	}
}
