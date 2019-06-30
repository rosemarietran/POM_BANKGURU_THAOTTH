package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage {
	private WebDriver driver;
	
	public DeleteCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
