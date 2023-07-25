package pageobject.Agoda;

import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.TextBox;

public class BasePage {
	
	Button btnSignIn = new Button("//button[@data-element-name='sign-in-button']");
	TextBox txt = new TextBox("//input[@id=\"textInput\"]");
	
	public LoginPage navigateToLoginPage() {
		btnSignIn.click();
		return new LoginPage();
	}
	
	public void enter(String value) {
		txt.enter(value);
	}
	
}
