package liveguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.HomePageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.AbstractPage_LiveGuru;
import commons.PageFactoryManager;

public class HomePageObject extends AbstractPage_LiveGuru{
	private WebDriver driver;

	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
