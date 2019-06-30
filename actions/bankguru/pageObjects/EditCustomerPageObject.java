package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	private WebDriver driver;
	
	public EditCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
