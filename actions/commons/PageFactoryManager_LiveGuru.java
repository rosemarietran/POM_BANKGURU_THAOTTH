package commons;

import org.openqa.selenium.WebDriver;

import liveguru.pageObjects.AccountInformationPageObject;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.LoginPageObject;
import liveguru.pageObjects.MobilePageObject;
import liveguru.pageObjects.RegisterPageObject;
import liveguru.pageObjects.TVPageObject;

public class PageFactoryManager_LiveGuru {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static AccountInformationPageObject getAccountInformationPage(WebDriver driver) {
		return new AccountInformationPageObject(driver);
	}
	
	public static MobilePageObject getMobilePage(WebDriver driver) {
		return new MobilePageObject(driver);
	}
	
	public static TVPageObject getTVPage(WebDriver driver) {
		return new TVPageObject(driver);
	}

}
