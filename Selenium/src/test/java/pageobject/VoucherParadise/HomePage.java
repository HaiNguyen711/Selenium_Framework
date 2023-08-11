package pageobject.VoucherParadise;

import core.element.manager.wrapper.Label;
import dataObjects.enums.ControlType;
import utils.constant.Constant;
import utils.helper.LocatorHelper;

public class HomePage extends BasePage {

	LocatorHelper locator = new LocatorHelper(Constant.LOCATOR_PATH, HomePage.class);
	
	private final Label lblTitle = locator.getLocator(ControlType.LABEL, "divTitle");
	
	public String getHomePageTitle() {
		return lblTitle.getText();
	}
	
	public void waitForLoad() {
		lblTitle.waitForVisibility();
	}
}
