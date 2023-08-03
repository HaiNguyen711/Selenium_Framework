package tests.Agoda;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.AllureListener;
import dataObjects.Agoda.Account;
import dataObjects.Agoda.enums.Users;
import pageobject.Agoda.BasePage;
import pageobject.Agoda.HomePage;
import pageobject.Agoda.LoginPage;
import tests.BaseTest;
import utils.helper.AccountHepler;
import utils.helper.AssertHelper;
import utils.helper.Environment;

@Listeners(AllureListener.class)
public class LoginTest extends BaseTest {
	
	/**
	 * Test id: Test case 001
	 * Verify User is able to login
	 */
	@Test
	public void TestCase01() {
		
		
		Environment environment = new Environment();
		BasePage basePage = new BasePage();
		LoginPage loginPage ;
		HomePage homePage;
		
		loginPage = basePage.navigateToLoginPage(); 
		Account acc = AccountHepler.getUser(Users.TESTER1);
		homePage = loginPage.login(acc);
		System.out.print(environment.getValue("email"));

	}
	
	@Test
	public void TestCase02() {
		HomePage homePage;
		LoginPage loginPage ;
		BasePage basePage = new BasePage();
		
		Log.STEP("1.Navigate to login page");
		loginPage = basePage.navigateToLoginPage();
		
		Log.STEP("2.Login");
		Account acc = AccountHepler.getUser(Users.TESTER1);
		homePage = loginPage.login(acc);
		
		AssertHelper.assertTrue(false, null);
	}
}
