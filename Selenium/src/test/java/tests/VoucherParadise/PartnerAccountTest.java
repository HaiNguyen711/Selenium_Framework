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
		accountPage.waitForLoad(RoleName.ADMIN);
		
		Log.STEP("4.Select Partner role");
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad(RoleName.PARTNER);
		
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
		accountPage.waitForLoad(RoleName.ADMIN);
		accountPage.SelectRole(RoleName.PARTNER);
		accountPage.waitForLoad(RoleName.PARTNER);
		
		Log.STEP("4. Click on Invite User button");
		userAccountPopup = accountPage.clickInviteUser();
		userAccountPopup.waitForPopupLoad(sNewPartnerPopupTitle);
		
		Log.verify("Invite new partner popup is displayed");
		assertHelper.assertTrue(userAccountPopup.isDisplayedPopup(sNewPartnerPopupTitle),"Invite new partner popup is displayed");
		
	}
}
