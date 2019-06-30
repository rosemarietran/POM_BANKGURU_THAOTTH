package com.liveguru.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPageUI_LiveGuru;
import commons.AbstractPage_LiveGuru;
import commons.AbstractTest;
import commons.PageFactoryManager_LiveGuru;
import liveguru.HomePageUI;
import liveguru.LoginPageUI;
import liveguru.MobilePageUI;
import liveguru.pageObjects.AccountInformationPageObject;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.LoginPageObject;
import liveguru.pageObjects.MobilePageObject;

public class FrontEnd_TC_02_MobileMenu extends AbstractTest {
	WebDriver driver;
	AbstractPage_LiveGuru abstractPage;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MobilePageObject mobilePage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser_LiveGuru(browserName);

		homePage = PageFactoryManager_LiveGuru.getHomePage(driver);

		log.info("VerifyAccountInformation - Step 01: Verify home page");
		verifyTrue(homePage.isDynamicDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT, "This is demo site for"));

//		log.info("VerifyAccountInformation - Step 02: Click on Account menu");
//		homePage.clickToElement(driver, HomePageUI.ACCOUNT_MENU);
//
//		log.info("VerifyAccountInformation - Step 03: Choose 'Log in' link");
//		homePage.clickToDynamicCartWrapperMenu(driver, "Log In");
//		loginPage = PageFactoryManager_LiveGuru.getLoginPage(driver);
//
//		log.info("VerifyAccountInformation - Step 04: Verify Login page displayed");
//		verifyTrue(loginPage.isDynamicPageTitleDisplayed(driver, "Login or Create an Account"));
//
//		log.info(
//				"VerifyAccountInformation - Step 05: Input data to 'Email Address' and 'Password' textboxes and click to 'Login' button");
//		loginPage.inputToDynamicButtonOrTextboxTextArea(driver, "login[username]",
//				FrontEnd_Common_01_RegisterToSystem.EMAIL);
//		loginPage.inputToDynamicButtonOrTextboxTextArea(driver, "login[password]",
//				FrontEnd_Common_01_RegisterToSystem.PASSWORD);
//		loginPage.clickToDynamicButton(driver, "Login");
//
//		log.info("VerifyAccountInformation - Step 06: Verify login successfully");
//		String fullName = FrontEnd_Common_01_RegisterToSystem.FIRST_NAME + " "
//				+ FrontEnd_Common_01_RegisterToSystem.MIDDLE_NAME + " " + FrontEnd_Common_01_RegisterToSystem.LAST_NAME;
//		verifyTrue(loginPage.isControlDisplayed(driver, LoginPageUI.WELCOME_MESSAGE_TEXT, "Hello, " + fullName));
	}

	@Test
	public void MobileMenu_01_VerifyProductCostInListPageAndDetailsPageAreEqual() {
		log.info("MobileMenu_01 - Step 01: Click on Mobile menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "Mobile");
		mobilePage = PageFactoryManager_LiveGuru.getMobilePage(driver);

		log.info("MobileMenu_01 - Step 02: Get cost of Sony Xperia in products list");
		String xperiaCostInList = mobilePage.getDynamicText(driver, MobilePageUI.PRODUCT_PRICE_IN_PRODUCTS_LIST, "Sony Xperia");
		
		log.info("MobileMenu_01 - Step 03: Click on Sony Xperia detail");
		mobilePage.clickToDynamicElement(driver, MobilePageUI.PRODUCT_VIEW_DETAIL_LINK, "Sony Xperia");
		String xperiaCostInDetail = mobilePage.getTextInElement(driver, MobilePageUI.PRODUCT_PRICE_IN_PRODUCT_DETAIL);
		
		log.info("MobileMenu_01 - Step 04: Compare Sony Xperia price in list and in detail page");
		verifyEquals(xperiaCostInDetail, xperiaCostInList);		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
