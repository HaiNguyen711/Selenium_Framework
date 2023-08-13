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
import pageobject.VoucherParadise.UserAccountPopup;
import pageobject.VoucherParadise.LoginPage;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;

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
}