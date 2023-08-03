package core.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import core.driver.manager.manage.DriverManager;

import java.io.File;

import org.apache.commons.io.FileUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import utils.helper.Utilities;

public class AllureListener implements ITestListener {

	public static ConcurrentHashMap<String, ExtentTest> testSuite = new ConcurrentHashMap<String, ExtentTest>();

	private static boolean isBackedUp = false;

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Screenshot", type = "image/png")
	public static void screenshot(String description) {
		// Get current driver
		WebDriver driver = DriverManager.getDriver();
		Allure.addAttachment(description,
				new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	}

	public static String screenShot(String filename, String filepath) throws Exception {
		String path = "";
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filepath + File.separator + filename + ".png");
			FileUtils.copyFile(SrcFile, DestFile);
			path = DestFile.getAbsolutePath();
		} catch (Exception e) {
		}
		return path;
	}

	public static void fail(String message) {
		message = "FAILED: " + message;
		Allure.step(message, Status.FAILED);
	}

	public static void STEP(Integer stepNo, String msg) {
		try {
			String time = Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss");
			Allure.step("["+ time + "] - Step " + stepNo + " : " + msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void VP(Integer stepNo, String msg) {
		try {
			String time = Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss");
			Allure.step("["+ time + "] - Verify Point " + stepNo + ": " + msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Attachment(value = "{0}", type = "test/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		backupOldResults();

		// Handle Report
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), null);
			testSuite.put(context.getName(), tmpSuite);
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println(("*** Running test: " + iTestResult.getMethod().getMethodName() + " ..."));
		ExtentTestManager.getTest().assignCategory(iTestResult.getTestContext().getName());
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Passed: " + getTestMethodName(iTestResult));
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		// capture screenshot
		String screenshotFileName = iTestResult.getName() + "_" + Utilities.getDateNow("MMddyy_HHmmss");
		String screenshotFilePath = "";
		try {
			screenshot(iTestResult.getName());
			screenshotFilePath = screenShot(screenshotFileName,
					ExtentReportManager.getScreenshotFolder());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String methodName = iTestResult.getMethod().getMethodName();
			String throwMessage = iTestResult.getThrowable().getMessage();
			// attach screenshots to report
			String message = String.format("*** TEST EXECUTION COMPLETE - ERROR: %s - %s", methodName, throwMessage);
			if (iTestResult.getThrowable() instanceof AssertionError) {
				message = String.format("*** TEST EXECUTION COMPLETE - FAILED: %s - %s", methodName, throwMessage);
				ExtentTestManager.getTest().fail(message,
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
			} else {
				ExtentTestManager.getTest().error(message,
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotFilePath).build());
			}
			Allure.attachment(message, screenshotFilePath);
		} catch (IOException e) {
			Log.info("An exception occured while taking screenshot " + e.getCause());
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.info("*** Test " + iTestResult.getMethod().getMethodName() + " skipped...");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("*** Test failed but within percentage % " + iTestResult.getMethod().getMethodName());
	}

	private static void backupOldResults() {
		if (isBackedUp) {
			return;
		}

		isBackedUp = true;
		final String allureResultsFolder = "allure-results";

		try {
			final Path allureResults = Paths.get(allureResultsFolder);
			final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
			final Path runPath = Paths.get(allureResultsFolder, "run.txt");

			// Make sure there is old results
			if (!Files.exists(allureResults) || !Files.list(allureResults).findAny().isPresent() || !Files.exists(runPath)) {
				Files.write(runPath, Arrays.asList("run_" + timeStamp));
				return;
			}
			
			// Get previous run info
			String backupPath = "run_" + timeStamp;
			if (Files.exists(runPath)) {
				List<String> lines = Files.readAllLines(runPath);
				if (!lines.isEmpty()) {
					backupPath = lines.get(0);
				}
			}
			
			// Write the current run info
			Files.write(runPath, Arrays.asList("run_" + timeStamp));
			
			// Create previous run folder under "old" folder
			final Path backup = Paths.get(allureResultsFolder, "old", backupPath);
			Files.createDirectories(backup);

			// Move all old result files
			try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(allureResultsFolder))) {
				final Pattern matcher = Pattern.compile("^([0-9A-F]{8}-([0-9A-F]{4}-){3}[0-9A-F]{12}).*",
						Pattern.CASE_INSENSITIVE);
				dirStream.forEach(path -> {
					if (matcher.matcher(path.getFileName().toString()).matches()) {
						try {
							System.out.println("Backup: " + path);
							FileUtils.moveFileToDirectory(path.toFile(), backup.toFile(), true);
						} catch (Exception ex) {
							System.out.println(ex);
						}
					}
				});
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
