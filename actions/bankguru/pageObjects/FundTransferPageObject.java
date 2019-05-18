package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.DepositPageUI;
import bankguru.FundTransferPageUI;
import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class FundTransferPageObject extends AbstractPage {
	private WebDriver driver;

	public FundTransferPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}



}
