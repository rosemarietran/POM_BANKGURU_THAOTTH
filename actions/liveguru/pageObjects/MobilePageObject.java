package liveguru.pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;
import commons.AbstractPage_LiveGuru;
import commons.PageFactoryManager;

public class MobilePageObject extends AbstractPage_LiveGuru {
	private WebDriver driver;

	public MobilePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
