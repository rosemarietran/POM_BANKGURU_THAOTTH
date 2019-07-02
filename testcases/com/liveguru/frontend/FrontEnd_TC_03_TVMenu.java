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
import liveguru.TVPageUI;
import liveguru.pageObjects.HomePageObject;
import liveguru.pageObjects.LoginPageObject;
import liveguru.pageObjects.TVPageObject;

public class FrontEnd_TC_03_TVMenu extends AbstractTest {
	WebDriver driver;
	AbstractPage_LiveGuru abstractPage;
	HomePageObject homePage;
	LoginPageObject loginPage;
	TVPageObject tvPage;
	
	String couponCode = "GURU50";

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
	public void TVMenu_01_VerifyCanShareWishlistToOtherPeopleUsingEmail() {
		log.info("TVMenu_01 - Step 01: Click on TV menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "TV");
		tvPage = PageFactoryManager_LiveGuru.getTVPage(driver);

		log.info("TVMenu_01 - Step 02: Add 'LG LCD' product in your wishlist");
		tvPage.clickToDynamicAddToWishlistButton(driver, "LG LCD");
		
		log.info("TVMenu_01 - Step 03: Verify add LG LCD to wishlist successfully");
		verifyTrue(tvPage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "LG LCD has been added to your wishlist. Click"));
		
		log.info("TVMenu_01 - Step 04: Click 'SHARE WISHLISH' button");
		tvPage.clickToDynamicElement(driver, TVPageUI.DYNAMIC_BUTTON, "Share Wishlist");
		
		log.info("TVMenu_01 - Step 05: Enter Email and a message then click Share wishlish");
		tvPage.inputToDynamicButtonOrTextboxTextArea(driver, "emails", "hellovietnam@gmail.com");
		tvPage.inputToDynamicButtonOrTextboxTextArea(driver, "message", "Share my wishlist!");
		
		log.info("TVMenu_01 - Step 06: Click 'SHARE WISHLISH' button");
		tvPage.clickToDynamicElement(driver, TVPageUI.DYNAMIC_BUTTON, "Share Wishlist");
		
		log.info("TVMenu_01 - Step 07: Verify message 'Your Wishlist has been shared.' is displayed");
		verifyTrue(tvPage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "Your Wishlist has been shared."));
		
		log.info("TVMenu_01 - Step 08: Verify in 'My wishlist' page has 1 item LG LCD");
		verifyEquals(tvPage.getTextInElement(driver, TVPageUI.PRODUCT_IN_WISHLIST),"LG LCD");
	}
	
	@Test
	public void TVMenu_02_VerifyCanAddReview() {
		log.info("TVMenu_02 - Step 01: Click on TV menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "TV");
		tvPage = PageFactoryManager_LiveGuru.getTVPage(driver);
		
		log.info("TVMenu_02 - Step 02: Click to 'SAMSUNG LCD' product detail");
		tvPage.clickToDynamicElement(driver, AbstractPageUI_LiveGuru.PRODUCT_VIEW_DETAIL_LINK, "Samsung LCD");
		
		log.info("TVMenu_02 - Step 03: Click to 'Add to review' link");
		tvPage.clickToDynamicElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXT_LINK, "Add Your Review");
		
		log.info("TVMenu_02 - Step 04: Input empty data to 3 fields: Thought/ Your Review/ Your Nickname");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "", "detail");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "", "title");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "", "nickname");
		
		log.info("TVMenu_02 - Step 05: Click Submit Review");
		tvPage.clickToDynamicElement(driver, TVPageUI.DYNAMIC_BUTTON, "Submit Review");

		log.info("TVMenu_02 - Step 06: Verify message 'This is a required field' is displayed");
		verifyEquals(tvPage.getDynamicText(driver, TVPageUI.DYNAMIC_ERROR_MESSAGE_TEXTAREA_OR_TEXTBOX, "detail"),"THIS IS A REQUIRED FIELD.");
		verifyEquals(tvPage.getDynamicText(driver, TVPageUI.DYNAMIC_ERROR_MESSAGE_TEXTAREA_OR_TEXTBOX, "title"),"THIS IS A REQUIRED FIELD.");
		verifyEquals(tvPage.getDynamicText(driver, TVPageUI.DYNAMIC_ERROR_MESSAGE_TEXTAREA_OR_TEXTBOX, "nickname"),"THIS IS A REQUIRED FIELD.");
		
		log.info("TVMenu_02 - Step 07: Input valid data to 3 fields: Thought/ Your Review/ Your Nickname and click Submit Review");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "Good choice", "detail");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "Good choice", "title");
		tvPage.sendKeyToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "GOGI", "nickname");
		tvPage.clickToDynamicElement(driver, TVPageUI.DYNAMIC_BUTTON, "Submit Review");
	
		log.info("TVMenu_02 - Step 08: Verify message 'Your review has been accepted for moderation.' is displayed");
		verifyTrue(tvPage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "Your review has been accepted for moderation."));
	}
		

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
