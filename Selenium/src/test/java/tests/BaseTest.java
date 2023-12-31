package tests;

import java.lang.reflect.Method;

import core.driver.manager.manage.Driver;
import core.driver.manager.manage.DriverManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import core.report.AllureListener;
import core.report.ExtentTestManager;
import pageobject.VoucherParadise.LoginPage;
import utils.constant.Constant;

public class BaseTest {

	@Parameters({ "driverConfig", "platform" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome.local") String driverConfig, @Optional("Windows") String platform,
							 ITestContext context, Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(), AllureListener.testSuite.get(context.getName()));
		DriverManager.loadDriverProperty(Constant.DRIVER_SETTING_FILE, platform, driverConfig);
		DriverManager.initDriver();
		Driver.maximizeBrowser();
		Driver.navigate(Constant.VOUCHER_PARADISE_URL);
		LoginPage loginPage = new LoginPage();
		loginPage.waitForLoginPageIsDisplayed();
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition: Quit");
		DriverManager.getDriver().quit();
	}
}
