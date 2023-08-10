package tests.VoucherParadise;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.AllureListener;
import dataObjects.Account;
import pageobject.VoucherParadise.*;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;

@Listeners(AllureListener.class)
public class LoginTest extends BaseTest {

	/**
	 * Test id: Test case 001 Verify User is able to login
	 */
	@Test
	public void TestCase01() {
		
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		HomePage homePage;
		
		Log.STEP("1.Navigate to login page");
		Log.STEP("2.Login");
		Account acc = AccountHepler.getUser("Admin");
		homePage = loginPage.login(acc);
		
		assertHelper.assertEquals("Welcome to Voucher Paradise", homePage.getHomePageTitle(), null);
	}

}
