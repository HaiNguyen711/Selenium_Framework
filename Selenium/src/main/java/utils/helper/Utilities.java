package utils.helper;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.driver.manager.BaseDriver;
import core.driver.manager.DriverManagement;
import core.report.Log;

public class Utilities {

	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	public static String getCurrentIPAddress() {
		String ip = "";
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("google.com", 80));
			ip = socket.getLocalAddress().getHostAddress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	public static String getCurrentUrl() {
		BaseDriver baseDriver = new BaseDriver();
		return baseDriver.getCurrentUrl();
	}

	public static String getAlertMessage() {
		Log.info(String.format("Get current alert message of the driver %s", DriverManagement.getThreadName()));
		try {
			BaseDriver baseDriver = new BaseDriver();
			Thread.sleep(2000);
			return baseDriver.getDriver().switchTo().alert().getText();
		} catch (Exception e) {
			Log.error(String.format("Has error get alert message: %s", e.getMessage()));
			return null;
		}
	}

	public static String toLowerCaseString(String text) {
		return text.toLowerCase();
	}

	public static void acceptAlert() {
		Log.info(String.format("Get current alert message of the driver %s", DriverManagement.getThreadName()));
		try {
			BaseDriver baseDriver = new BaseDriver();
			Thread.sleep(2000);
			baseDriver.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			Log.error(String.format("Has error get alert message: %s", e.getMessage()));
		}
	}

	public static String getDateNow(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}

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
}