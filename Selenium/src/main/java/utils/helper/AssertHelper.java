package utils.helper;

import org.testng.Assert;

import core.report.Log;
import junit.framework.AssertionFailedError;

public class AssertHelper {

    public static void assertTrue(boolean actualValue, String message) {
        try {
            Assert.assertTrue(actualValue, message);
            Log.info("Verification passed: " + message);
        } catch (AssertionFailedError e) {
        	Log.failedAssertion(e.getMessage());
            try {
            	Log.info("Verification Failed: " + e.getMessage());
            } catch (Throwable throwable) {
            	Log.info("Element: " + "Can't get element");
            }
        }
    }

    public static void assertFalse(boolean actualValue, String message) {
        try {
            Assert.assertFalse(actualValue, message);
            Log.info("Verification passed: " + message);
        } catch (AssertionFailedError e) {
        	Log.failedAssertion(e.getMessage());
            try {
            	Log.info("Verification Failed: " + e.getMessage());
            } catch (Throwable throwable) {
            	Log.info("Element: " + "Can't get element");
            }
        }
    }
    
    public void assertEquals(Object actualValue, Object expectedValue, String message) {
        try {
            Assert.assertEquals(actualValue, expectedValue, message);
            Log.info("Verification passed: " + message);
        } catch (AssertionError e) {
        	Log.failedAssertion(e.getMessage());
            try {
            	Log.info("Verification Failed: " + e.getMessage());
            } catch (Throwable throwable) {
            	Log.info("Element: " + "Can't get element");
            }
        }
    }

    public void assertNotEquals(Object actualValue, Object expectedValue, String message) {
        try {
            Assert.assertNotEquals(actualValue, expectedValue, message);
            Log.info("Verification passed: " + message);
        } catch (AssertionError e) {
        	Log.failedAssertion(e.getMessage());
            try {
            	Log.info("Verification Failed: " + e.getMessage());
            } catch (Throwable throwable) {
            	Log.info("Element: " + "Can't get element");
            }
        }
    }
}
