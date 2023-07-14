package core.element.manager.wrapper;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.element.manager.base.ClickableControl;
import core.report.Log;
import io.netty.handler.timeout.TimeoutException;
import utils.constant.Constant;

public class Button extends ClickableControl {

	public Button(String locator,String type) {
		super(locator,type);
		// TODO Auto-generated constructor stub
	}
}
