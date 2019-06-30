package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteAccountPageObject extends AbstractPage {
	private WebDriver driver;
	
	public DeleteAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
