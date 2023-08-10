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
import utils.helper.Environment;

@Listeners(AllureListener.class)
public class LoginTest extends BaseTest {

	/**
	 * Test id: Test case 001 Verify User is able to login
	 */
	@Test
	public void LO01_Verify_that_user_will_be_able_to_login_with_valid_username_and_valid_password() {
		
		Environment environment = new Environment();
		AssertHelper assertHelper = new AssertHelper();
		LoginPage loginPage = new LoginPage();
		HomePage homePage;
		String sWelcomTitle = environment.getValue("welcomTitle");
		
		Log.STEP("1.Navigate to Voucher Paradise Admin Portal website");
		Log.STEP("2.Enter valid username/password");
		Log.STEP("3.Click on LogIn button");
		Account acc = AccountHepler.getUser("Admin");
		homePage = loginPage.login(acc);
		System.out.println(homePage.getHomePageTitle());
		assertHelper.assertEquals(homePage.getHomePageTitle(),sWelcomTitle, "Login successfully");
		Log.STEP("4.Logout");
		homePage.logout();
	}

}
