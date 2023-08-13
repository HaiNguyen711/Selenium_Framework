package utils.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;

public class StringHelper {
	
	 private final static String ALPHA_NUMERIC_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	 private final static String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	 private final static String NUMERIC_STRING = "0123456789";
	 
 	public static String getRandomString(int len) {
 		len = len - 13;
        StringBuilder builder = new StringBuilder();
        while (len-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString() + Utilities.getDateNow();
    }
 	
 	public static String getRandomString() {
        return Utilities.getDateNow();
    }
 	
 	public static String getRandomNumberToString(int len) {
        StringBuilder builder = new StringBuilder();
        while (len-- != 0) {
            int character = (int) (Math.random() * NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
 	
 	public static Date getPreviousTimeFromDate(Date date, int dateBack) {
        Date previousTime = AddDay(date, -dateBack);
        return previousTime;
    }

    public static Date AddDay(Date date, int dateBack) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dateBack);
        return cal.getTime();
    }
    
    public static String getNowTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    public static String getNowTimeDayPlus(String format, int dayPlus) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        Date datePlus = DateUtils.addDays(date, dayPlus);
        return dateFormat.format(datePlus);
    }
    
    @SuppressWarnings("deprecation")
	public static String getNowTimeDayMinus(String format, int dayMinus) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
		Date datePlus = DateUtils.setYears(date, date.getYear() + 1900 - dayMinus);
        return dateFormat.format(datePlus);
    }
    
    public static String format(String sDate, String oldFormat, String newFormat) {
        if (sDate != null) {
            try {
                Date date = new SimpleDateFormat(oldFormat).parse(sDate);
                DateFormat dateFormat = new SimpleDateFormat(newFormat);
                return dateFormat.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
