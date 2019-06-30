package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.HomePageUI;
import bankguru.NewAccountPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class EditAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public EditAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}



}
