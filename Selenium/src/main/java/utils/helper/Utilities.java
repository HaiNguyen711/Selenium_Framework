package utils.helper;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import core.driver.manager.manage.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.constant.Constant;

/**
 * A collection of methods to deal with various text related activities
 */
public class Utilities {

	/**
	 * Contains log of the utilities used
	 */
	private static final Logger logger = Constant.createLogger(Utilities.class.getName());

	/**
	 * Get project absolute path
	 * 
	 * @return a path string
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * Capture the screenshot
	 * 
	 * @return the output type for a screenshot
	 */
	public static byte[] takeScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
		return (byte[]) (scrShot.getScreenshotAs(OutputType.BYTES));
	}

	/**
	 * Capture the screenshot
	 * 
	 * @param filename - a string of file name
	 * @param filepath - a string of location
	 * @return the specified location
	 * @throws Exception - throw if driver cannot capture the screenshot
	 */
	public static String takeScreenShot(String filename, String filepath) throws Exception {
		String path = "";
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) DriverManager.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filepath + File.separator + filename + ".png");
			FileUtils.copyFile(SrcFile, DestFile);
			path = DestFile.getAbsolutePath();
		} catch (Exception e) {
			logger.severe("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	/**
	 * Put path to your file in a clipboard
	 * 
	 * @param string - file path
	 */
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * Imitate mouse events like ENTER, CTRL+C, CTRL+V to paste the data from
	 * clipboard
	 * 
	 * @param fileLocation - file path
	 */
	public static void uploadFile(String fileLocation) {
		try {
			setClipboardData(fileLocation);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static String getDateNow(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}

<<<<<<< HEAD
=======
	public static String takeScreenShot(String filename, String filepath) throws Exception {
		String path = "";
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) DriverManagement.getDriver());
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filepath + File.separator + filename + ".png");
			FileUtils.copyFile(SrcFile, DestFile);
			path = DestFile.getAbsolutePath();
		} catch (Exception e) {
			System.err.println("An error occurred when capturing screen shot: " + e.getMessage());
		}
		return path;
	}

	/**
	 * Capture the screenshot
	 *
	 * @return the output type for a screenshot
	 */
	public static byte[] takeScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) DriverManagement.getDriver());
		return (byte[]) (scrShot.getScreenshotAs(OutputType.BYTES));
	}

	public static String[] sortArrayAscending(String[] list) {
		Arrays.sort(list);
		return list;
	}

	public static String[] sortArrayDescending(String[] list) {
		Collections.reverse(Arrays.asList(list));
		return list;
	}

	public static String getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Long currentTimestamp = timestamp.getTime();
		return currentTimestamp.toString();
	}

	public static int getRandomNum(int min, int max) {
	      return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
	
	public static String convertStringtoVND(String string) {
		String result = "";
		int i = 0;
		for (i = 0; i < string.length() % 3; i++) {
			result = result.concat(Character.toString(string.charAt(i)));
			if (i == string.length() % 3 - 1) {
				result = result.concat(".");
			}
		}
		int temp = 1;
		for (i = i; i < string.length(); i++) {
			result = result.concat(Character.toString(string.charAt(i)));
			if (temp % 3 == 0 && i != string.length() - 1) {
				result = result.concat(".");
			}
			temp++;
		}
		return result;
    }
	
	public static long convertVNDtoLong(String text) {
		String result = "";
		String[] price = text.split(" ");
		String a = price[0].toString();
		for (int i = 0; i < a.length(); i++) {
			if (!Character.toString(a.charAt(i)).equals(".")) {
				result = result.concat(Character.toString(a.charAt(i)));
			}
		}
		return Long.parseLong(result);
	}
>>>>>>> a151b4d311013f4b9e0b9d0af2287906d7b8d755
}