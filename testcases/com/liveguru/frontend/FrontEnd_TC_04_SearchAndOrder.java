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
import liveguru.TVPageUI;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.LoginPageObject;
import liveguru.pageObjects.MyWishlistPageObject;

public class FrontEnd_TC_04_SearchAndOrder extends AbstractTest {
	WebDriver driver;
	AbstractPage_LiveGuru abstractPage;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyWishlistPageObject myWishListPage;

	String country = "United State";
	String state = "New York";
	String zip = "543432";
	String address = "ABC";
	String city = "New York";
	String telephone = "123123123";

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
	public void SearchAndOrder_01_VerifyUserAbleToPurchaseProduct() {
		log.info("SearchAndOrder_01 - Step 01: Click on My Wishlist link");
		homePage = PageFactoryManager_LiveGuru.getHomePage(driver);
		homePage.clickToDynamicElement(driver, HomePageUI.BLOCK_CONTENT, "My Wishlist");
		myWishListPage = (MyWishlistPageObject) homePage.openMultiplePages(driver, "My Wishlist");

		log.info("SearchAndOrder_02 - Step 02: Click 'Add to cart' link");
		myWishListPage.clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_BUTTON, "Add to Cart");

		log.info(
				"SearchAndOrder_02 - Step 03: Input data to 'Country' Combobox, 'State' Combobox, 'Zip' textbox and click Estimate");
		myWishListPage.selectValueInDynamicDropdown(driver, country, "country_id");
		myWishListPage.selectValueInDynamicDropdown(driver, state, "region_id");
		myWishListPage.inputToDynamicButtonOrTextboxTextArea(driver, "estimate_postcode", zip);
		myWishListPage.clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_BUTTON, "Estimate");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
