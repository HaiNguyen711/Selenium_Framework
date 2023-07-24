package pageobject.Agoda;

import core.driver.manager.BaseDriver;
import core.element.manager.base.type.ElementType;
import core.element.manager.wrapper.Button;
import core.element.manager.wrapper.Label;
import core.element.manager.wrapper.TextBox;

public class LoginPage extends BasePage {
	
	TextBox txtEmail = new TextBox(ElementType.ID,"email");
	TextBox txtPassword = new TextBox(ElementType.ID,"password");
	Button btnLogin = new Button("//button[@data-cy='signin-button']");
	Label lblSignIn = new Label("//h3[.='Sign in']");
	
	public LoginPage waitForLoginPageLoad() {
		lblSignIn.waitForPresent();
		return this;
	}
	public HomePage login(String sEmail, String sPassword) {
		
		new BaseDriver().getDriver().switchTo().frame(0);
		txtEmail.enter(sEmail);
		txtPassword.enter(sPassword);
		btnLogin.click();
		new BaseDriver().getDriver().switchTo().defaultContent();
		return new HomePage();
	}

}
