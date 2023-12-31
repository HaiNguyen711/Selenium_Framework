package utils.helper;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import core.driver.manager.manage.Driver;
import core.driver.manager.manage.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

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
  
	public static String getDateNow(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		
		return formatter.format(date);
	}
	
	public static String getDateNow() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Long.toString(timestamp.getTime());
	}
	
	public static void refresh() {
		DriverManager.getDriver().navigate().refresh();
	}
}