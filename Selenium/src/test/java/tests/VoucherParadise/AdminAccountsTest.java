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
import pageobject.VoucherParadise.PartnersPage;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;

@Listeners(AllureListener.class)
public class AdminAccountsTest extends BaseTest {
	
	/**
	 * Test id: AC01
	 * Verify that accounts are displayed correctly in Accounts page
	 * 
	 */
	@Test
	public void AC01() {
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
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
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
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
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
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
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		AccountPage accountPage;
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
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

}