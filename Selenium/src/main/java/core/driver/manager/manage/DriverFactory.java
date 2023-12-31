package core.driver.manager.manage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import com.google.common.base.Throwables;

import core.driver.manager.Base.BaseDriver;
import core.driver.manager.setting.DriverProperty;
import utils.constant.Constant;

/**
 * Driver factory pattern, where only the concrete classes need to know exactly
 * how to create a new instance. This helps create a simple one way to create
 * WebDriver instance. Containing all the concrete implementations for every
 * browser. If we want to create an instance of Chrome Driver, we just pass the
 * chrome driver property and it will get type and mode of the corressponding
 * driver class.
 */
public class DriverFactory {

	/**
	 * Contains log of the class
	 */
	private static final Logger logger = Constant.createLogger(DriverFactory.class.getName());

	/**
	 * Driver type class full name
	 */
	private static final String DRIVER_CLASS_FULLNAME = "core.driver.manager.resources.%sDriver";

	/**
	 * Driver mode method in driver type class
	 */
	private static final String METHOD_NAME = "create%sDriver";

	/**
	 * Depend on driver property to create new instance of driver(Chrome,
	 * Firefox,...). It gets type and mode of the driver to catch exactly each
	 * resource for the instance
	 * 
	 * @param property - Driver property contains set of attributes(Type, Platform,
	 *                 Mode,...)
	 * @return a new instance of driver
	 */
	public static synchronized BaseDriver newInstance(DriverProperty property) {
		String classFullName = String.format(DRIVER_CLASS_FULLNAME, property.getDriverType());
		String methodName = String.format(METHOD_NAME, property.getMode());

		try {
			Class<?> cla = Class.forName(classFullName);
			Constructor<?> cons = cla.getDeclaredConstructor(new Class[] { DriverProperty.class });
			Object obj = cons.newInstance(property);

			// Create driver
			Method method = cla.getDeclaredMethod(methodName);
			method.setAccessible(true);
			method.invoke(obj);

			return (BaseDriver) obj;
		} catch (Exception e) {
			logger.severe(String.format("Cannot create new %s driver instance. %s", property.getDriverType().toString(),
					Throwables.getStackTraceAsString(e)));
			return null;
		}
	}

}
