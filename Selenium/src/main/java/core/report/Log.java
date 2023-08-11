package core.report;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import utils.helper.Utilities;

public class Log {

    //Info Level Logs
	@Step
    public static void info (String message) {
    	Reporter.log("<b>INFO: </b>" + message);
		Reporter.log("Thread " +Thread.currentThread().getId() + "-" + Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - INFO: " + message, true);
		ExtentTestManager.getTest().log(Status.INFO, message);
		Allure.step("Thread " +Thread.currentThread().getId() + "-" + Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - INFO: " + message);
    }
	
	//Info Level Logs
	@Step
    public static void STEP (String message) {
    	Reporter.log("<b>STEP: </b>" + message);
		Reporter.log("Thread " +Thread.currentThread().getId() + "-" + Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - STEP: " + message, true);
		ExtentTestManager.getTest().log(Status.INFO, message);
		Allure.step("Thread " +Thread.currentThread().getId() + "-" + Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - STEP: " + message);
    }

    //Warn Level Logs
    public static void warn (String message) {
    	message = "<b style=\"color: darkorange;word-break:break-word;\"><i>WARNING: </i>" + message + "</b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
    }

    //Error Level Logs
    @Step
    public static void error (String message) {
    	message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: red\">" + message + " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.ERROR, message);
		Allure.step(Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - ERROR: " + message);
    }

    //Debug Level Logs
    public static void debug (String message) {
    	message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: red\">" + message + " </i></b>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.DEBUG, message);
    }
    
    @Step
    public static void verify (String message) {
    	String messageLog = message;
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: orange\">VERIFY POINT: </i>"
				+ message + "</b>";
		Reporter.log(message);
		Reporter.log(Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - VERIFY POINT: " + messageLog, true);
		ExtentTestManager.getTest().log(Status.INFO, message);
		Allure.step(Thread.currentThread().getId() + "-" +Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - VERIFY POINT: " + messageLog);
    }
    
    public static void passedAssertion(String message) {
    	String messageLog = message;
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: #00af00\">Verification passed: " + message
				+ " </i></b>";
		Reporter.log(message);
		Reporter.log(Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - Verification passed: " + messageLog, true);
		ExtentTestManager.getTest().log(Status.PASS, message);
	}

	public static void failedAssertion(String message) {
		String messageLog = message;
		message = "<b style=\"color: blue;word-break:break-word;\"><i style=\"color: red\">Verification failed: " + message + " </i></b>";
		Reporter.log(message);
		Reporter.log(Utilities.getDateNow("MM.dd.yyyy - HH:mm:ss") + " - Verification failed: " + messageLog, true);
		ExtentTestManager.getTest().log(Status.FAIL, message);
	}
	
	public static void bug(String bugId, String bugDesc) {
		String bugInfo = String.format("Bug %s - %s", bugId, bugDesc);
		String message = "<a target=\"_blank\" href=\"" + bugDesc
				+ "\" style=\"color:#DF0101;font-size:14px;word-break:break-word;\">" + bugInfo + "</a>";
		Reporter.log(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
	}
    
}
