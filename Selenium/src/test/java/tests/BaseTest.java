package tests;

import java.lang.reflect.Method;

import core.driver.manager.manage.Driver;
import core.driver.manager.manage.DriverManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import core.report.ExtentTestManager;
import core.report.TestListener;
import pageobject.Agoda.BasePage;
import pageobject.Agoda.LoginPage;
import utils.constant.Constant;

public class BaseTest {

	@Parameters({ "driverConfig", "platform" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome.local") String driverConfig, @Optional("Windows") String platform,
							 ITestContext context, Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(), TestListener.testSuite.get(context.getName()));
		DriverManager.loadDriverProperty(Constant.DRIVER_SETTING_FILE, platform, driverConfig);
		DriverManager.initDriver();
		Driver.maximizeBrowser();
		Driver.navigate(Constant.APPLESTORE_URL);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition: Quit");
		DriverManager.getDriver().quit();
	}
}
