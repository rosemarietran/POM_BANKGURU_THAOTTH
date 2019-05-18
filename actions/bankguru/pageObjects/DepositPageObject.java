package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.DepositPageUI;
import bankguru.HomePageUI;
import bankguru.NewAccountPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class DepositPageObject extends AbstractPage {
	private WebDriver driver;

	public DepositPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}


}
