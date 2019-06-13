package bankguru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.HomePageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class HomePageObject extends AbstractPage{
	private WebDriver driver;

	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	}
	
	public boolean isUserIDDisplayed(String userIdInfor) {
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userIdInfor);
		waitForElementVisible(driver, USER_ID_FORMAT);
		return isControlDisplayed(driver, USER_ID_FORMAT);
	}
	
	public LoginPageObject clickToLogoutLink() {
		waitForElementVisible(driver, HomePageUI.LOG_OUT_LINK);
		clickToElement(driver, HomePageUI.LOG_OUT_LINK);
		waitForAlertPresence(driver);
		acceptAlert(driver);
		return PageFactoryManager.getLoginPage(driver);
	}
	
	public boolean isLoginFormUnDisplayed() {
		waitForElementInvisible(driver, HomePageUI.LOGIN_FORM);
		return isControlUndisplayed(driver, HomePageUI.LOGIN_FORM);
	}

}
