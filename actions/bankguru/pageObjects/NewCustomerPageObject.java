package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage {
	private WebDriver driver;
	
	public NewCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
