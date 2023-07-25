package tests.Agoda;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.TestListener;
import dataObjects.Agoda.Account;
import dataObjects.Agoda.enums.Users;
import pageobject.Agoda.BasePage;
import pageobject.Agoda.HomePage;
import pageobject.Agoda.LoginPage;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.JsonHelper;
import utils.helper.Utilities;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
	
	/**
	 * Test id: Test case 001
	 * Verify User is able to login
	 */
	@Test
	public void TestCase01() {
		
		HomePage homePage;
		LoginPage loginPage ;
		BasePage basePage = new BasePage();
		
		loginPage = basePage.navigateToLoginPage(); 
//		homePage = loginPage.login("logi_test01@gmail.com", "password");
		Account acc = AccountHepler.getUser(Users.TESTER1);

	}

}
