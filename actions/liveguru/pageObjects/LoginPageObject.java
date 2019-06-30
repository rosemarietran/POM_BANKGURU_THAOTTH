package liveguru.pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.AbstractPage_LiveGuru;
import commons.PageFactoryManager;

public class LoginPageObject extends AbstractPage_LiveGuru {
	private WebDriver driver;

	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
