package com.liveguru.frontend;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.AbstractPageUI_LiveGuru;
import commons.AbstractPage_LiveGuru;
import commons.AbstractTest;
import commons.PageFactoryManager_LiveGuru;
import liveguru.HomePageUI;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.RegisterPageObject;

public class FrontEnd_Common_01_RegisterToSystem extends AbstractTest {
	WebDriver driver;
	public static String EMAIL, PASSWORD, FIRST_NAME, MIDDLE_NAME, LAST_NAME;
	AbstractPage_LiveGuru abstractPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser_LiveGuru(browserName);
		
		//Test data for register account in live guru
		FIRST_NAME = "Bin";
		MIDDLE_NAME = "Shin";
		LAST_NAME = "Tran";
		EMAIL = "tranbinshin" + randomNumber() + "@gmail.com";
		PASSWORD = "123456";

		homePage = PageFactoryManager_LiveGuru.getHomePage(driver);

		log.info("Register - Step 01: Verify home page");
		verifyTrue(homePage.isControlDisplayed(driver, HomePageUI.ACCOUNT_MENU));

		log.info("Register - Step 02: Click on Account menu");
		homePage.clickToElement(driver, HomePageUI.ACCOUNT_MENU);
		
		log.info("Register - Step 03: Choose 'Register' link");
		homePage.clickToDynamicCartWrapperMenu(driver, "Register");
		registerPage = PageFactoryManager_LiveGuru.getRegisterPage(driver);

		log.info("Register - Step 04: Verify Register page displayed");
		verifyTrue(registerPage.isDynamicPageTitleDisplayed(driver, "Create an Account"));

		log.info("Register - Step 05: Input data to textboxes and click to 'Register' button");
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "firstname", FIRST_NAME);
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "middlename", MIDDLE_NAME);
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "lastname", LAST_NAME);
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "email", EMAIL);
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "password", PASSWORD);
		registerPage.inputToDynamicButtonOrTextboxTextArea(driver, "confirmation", PASSWORD);
		registerPage.clickToDynamicButton(driver, "Register");
		
		log.info("Register - Step 06: Verify register successfully");
		verifyTrue(registerPage.isControlDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "Thank you for registering with Main Website Store."));

		log.info("Get User Id and Password infor");

		closeBrowserAndDriver(driver);

	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
