package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class BalanceEnquiryPageObject extends AbstractPage {
	private WebDriver driver;
	
	public BalanceEnquiryPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
