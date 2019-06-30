package com.liveguru.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPage_LiveGuru;
import commons.AbstractTest;
import commons.PageFactoryManager_LiveGuru;
import liveguru.HomePageUI;
import liveguru.LoginPageUI;
import liveguru.pageObjects.AccountInformationPageObject;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.LoginPageObject;

public class FrontEnd_TC_01_AccountInformation extends AbstractTest {
	WebDriver driver;
	AbstractPage_LiveGuru abstractPage;
	HomePageObject homePage;
	LoginPageObject loginPage;
	AccountInformationPageObject accountInformationPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser_LiveGuru(browserName);

		homePage = PageFactoryManager_LiveGuru.getHomePage(driver);

		log.info("VerifyAccountInformation - Step 01: Verify home page");
		verifyTrue(homePage.isControlDisplayed(driver, HomePageUI.ACCOUNT_MENU));

		log.info("VerifyAccountInformation - Step 02: Click on Account menu");
		homePage.clickToElement(driver, HomePageUI.ACCOUNT_MENU);

		log.info("VerifyAccountInformation - Step 03: Choose 'Log in' link");
		homePage.clickToDynamicCartWrapperMenu(driver, "Log In");
		loginPage = PageFactoryManager_LiveGuru.getLoginPage(driver);

		log.info("VerifyAccountInformation - Step 04: Verify Login page displayed");
		verifyTrue(loginPage.isDynamicPageTitleDisplayed(driver, "Login or Create an Account"));

		log.info(
				"VerifyAccountInformation - Step 05: Input data to 'Email Address' and 'Password' textboxes and click to 'Login' button");
		loginPage.inputToDynamicButtonOrTextboxTextArea(driver, "login[username]",
				FrontEnd_Common_01_RegisterToSystem.EMAIL);
		loginPage.inputToDynamicButtonOrTextboxTextArea(driver, "login[password]",
				FrontEnd_Common_01_RegisterToSystem.PASSWORD);
		loginPage.clickToDynamicButton(driver, "Login");

		log.info("VerifyAccountInformation - Step 06: Verify login successfully");
		String fullName = FrontEnd_Common_01_RegisterToSystem.FIRST_NAME + " "
				+ FrontEnd_Common_01_RegisterToSystem.MIDDLE_NAME + " " + FrontEnd_Common_01_RegisterToSystem.LAST_NAME;
		verifyTrue(loginPage.isControlDisplayed(driver, LoginPageUI.WELCOME_MESSAGE_TEXT, "Hello, " + fullName));
	}

	@Test
	public void AccountInformation_01_VerifyUserInforAfterRegisterSuccessfully() {
		log.info("AccountInformation_01 - Step 01: Open Account Information page");
		accountInformationPage = (AccountInformationPageObject) loginPage.openMultiplePages(driver,
				"Account Information");

		log.info("AccountInformation_01 - Step 02: Verify user information are correct");
		verifyEquals(accountInformationPage.getDynamicTextValueInTextbox(driver, "firstname"),
				FrontEnd_Common_01_RegisterToSystem.FIRST_NAME);
		verifyEquals(accountInformationPage.getDynamicTextValueInTextbox(driver, "middlename"),
				FrontEnd_Common_01_RegisterToSystem.MIDDLE_NAME);
		verifyEquals(accountInformationPage.getDynamicTextValueInTextbox(driver, "lastname"),
				FrontEnd_Common_01_RegisterToSystem.LAST_NAME);
		verifyEquals(accountInformationPage.getDynamicTextValueInTextbox(driver, "email"),
				FrontEnd_Common_01_RegisterToSystem.EMAIL);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
