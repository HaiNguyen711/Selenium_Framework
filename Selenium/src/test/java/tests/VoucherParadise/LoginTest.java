package tests.VoucherParadise;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.AllureListener;
import dataObjects.Account;
import dataObjects.enums.SideBar;
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
	 * @author 
	 */
	@Test
	public void LO01() {
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		PartnersPage pa;
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
}
