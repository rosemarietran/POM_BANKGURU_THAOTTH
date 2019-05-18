package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.HomePageUI;
import bankguru.NewAccountPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class NewAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public NewAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}



}
