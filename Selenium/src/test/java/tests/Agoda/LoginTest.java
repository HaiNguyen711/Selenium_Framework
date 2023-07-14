package tests.Agoda;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.report.Log;
import core.report.TestListener;
import dataObjects.Agoda.User;
import dataObjects.Agoda.enums.UserName;
import pageobject.Agoda.BasePage;
import pageobject.Agoda.HomePage;
import pageobject.Agoda.LoginPage;
import tests.BaseTest;
import utils.constant.Constant;
import utils.helper.AssertHelper;
import utils.helper.JsonHelper;
import utils.helper.UserHelper;
import utils.helper.Utilities;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
	
	/**
	 * Test id: Test case 001
	 * Verify User is able to login
	 */
	@Test
	public void TestCase01() {
		
		
		
//		String value = JsonHelper.getValue(Constant.USER_DATA,"user name");
		
		
		HomePage page = new HomePage();
		page.test();
		User user = UserHelper.getUser("Tester1");
		
		String semail = user.getEmail();
        String sPassword = user.getPassword();
        String sFirstName= user.getFirstName();
        String sLastName = user.getLastName();
        String sCountry = user.getCountry();
        String sPhone= user.getPhone();
//		
		
	}

}
