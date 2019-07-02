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
	
	String couponCode = "GURU50";

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

	
	public void MobileMenu_01_VerifyProductCostInListPageAndDetailsPageAreEqual() {
		log.info("MobileMenu_01 - Step 01: Click on Mobile menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "Mobile");
		mobilePage = PageFactoryManager_LiveGuru.getMobilePage(driver);

		log.info("MobileMenu_01 - Step 02: Get cost of Sony Xperia in products list");
		String xperiaCostInList = mobilePage.getDynamicText(driver, MobilePageUI.PRODUCT_PRICE_IN_PRODUCTS_LIST,
				"Sony Xperia");

		log.info("MobileMenu_01 - Step 03: Click on Sony Xperia detail");
		mobilePage.clickToDynamicElement(driver, MobilePageUI.PRODUCT_VIEW_DETAIL_LINK, "Sony Xperia");
		String xperiaCostInDetail = mobilePage.getTextInElement(driver, MobilePageUI.PRODUCT_PRICE_IN_PRODUCT_DETAIL);

		log.info("MobileMenu_01 - Step 04: Compare Sony Xperia price in list and in detail page");
		verifyEquals(xperiaCostInDetail, xperiaCostInList);
	}

	
	public void MobileMenu_02_VerifyDiscountCoupon() {
		log.info("MobileMenu_02 - Step 01: Click on Mobile menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "Mobile");
		mobilePage = PageFactoryManager_LiveGuru.getMobilePage(driver);
		
		log.info("MobileMenu_02 - Step 02: Add IPHONE to cart");
		mobilePage.clickToDynamicAddToCartButton(driver, "IPhone");
		
		log.info("MobileMenu_02 - Step 03: Verify add IPhone to cart successfully");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "IPhone was added to your shopping cart."));
		
		log.info("MobileMenu_02 - Step 04: Input Coupon code to Discount codes textbox");
		mobilePage.inputToDynamicButtonOrTextboxTextArea(driver, "coupon_code", couponCode);
	
		log.info("MobileMenu_02 - Step 05: Click Apply coupon code");
		mobilePage.clickToElement(driver, MobilePageUI.APPLY_COUPON_CODE_BUTTON);
		
		log.info("MobileMenu_02 - Step 06: Verify apply coupon code successfully");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "Coupon code \"GURU50\" was applied."));
	
		log.info("MobileMenu_02 - Step 07: Verify the dicounted generated");
		verifyEquals(mobilePage.getTextInElement(driver, MobilePageUI.DISCOUNT_AMOUNT_LABEL),"-$25.00");
		
		log.info("MobileMenu_02 - Step 08: Verify the grand total");
		verifyEquals(mobilePage.getTextInElement(driver, MobilePageUI.GRAND_TOTAL_TEXT),"-$475.00");
	}
	
	@Test
	public void MobileMenu_03_VerifyCantAddMore500ItemsOfProduct() {
		log.info("MobileMenu_03 - Step 01: Click on Mobile menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "Mobile");
		mobilePage = PageFactoryManager_LiveGuru.getMobilePage(driver);
		
		log.info("MobileMenu_03 - Step 02: Add Sony Xperia to cart");
		mobilePage.clickToDynamicAddToCartButton(driver, "Sony Xperia");
		
		log.info("MobileMenu_03 - Step 03: Input to Qty textbox value 501");
		mobilePage.inputToDynamicTextboxOrTextArea(driver, AbstractPageUI_LiveGuru.DYNAMIC_TEXTBOX_TEXTAREA, "Qty", "501");
		
		log.info("MobileMenu_03 - Step 04: Click Submit button");
		mobilePage.clickAnElement(driver, MobilePageUI.UPDATE_CART_BUTTON);
		
		log.info("MobileMenu_03 - Step 05: Verify error message 'The maximum quantity allowed for purchase is 500.' is displayed");
		verifyEquals(mobilePage.getTextInElement(driver, MobilePageUI.CART_ERROR_MESSAGE),"* The maximum quantity allowed for purchase is 500.");
	
		log.info("MobileMenu_03 - Step 06: Click Empty cart link");
		mobilePage.clickToElement(driver, MobilePageUI.UPDATE_CART_ACTION_BUTTON, "Empty Cart");
		
		log.info("MobileMenu_03 - Step 07: Verify message 'Shopping Cart is Empty' and 'You have no items in your shopping cart.' are displayed");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_PAGE_TITLE, "Shopping Cart is Empty"));
		verifyTrue(mobilePage.isDynamicDisplayed(driver, MobilePageUI.CART_EMPTY_MESSAGE_TEXT, "You have no items in your shopping cart."));
	}
	
	
	public void MobileMenu_04_VeriyCompareTwoProducts() {
		log.info("MobileMenu_04 - Step 01: Click on Mobile menu");
		homePage.clickToDynamicElement(driver, HomePageUI.HEADER_MENU, "Mobile");
		mobilePage = PageFactoryManager_LiveGuru.getMobilePage(driver);
		
		log.info("MobileMenu_04 - Step 02: Click on Add to Compare Sony Xperia mobile");
		mobilePage.clickToDynamicAddToCompareButton(driver, "Sony Xperia");
		
		log.info("MobileMenu_04 - Step 03: Verify add Sony Xperia mobile to comparison list successfully");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "The product Sony Xperia has been added to comparison list."));
		
		log.info("MobileMenu_04 - Step 04: Click on Add to Compare IPhone mobile");
		mobilePage.clickToDynamicAddToCompareButton(driver, "IPhone");
		
		log.info("MobileMenu_04 - Step 05: Verify add IPhone mobile to comparison list successfully");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, "The product IPhone has been added to comparison list."));
		
		log.info("MobileMenu_04 - Step 06: Click Compare button");
		mobilePage.clickAnElement(driver, MobilePageUI.COMPARE_BUTTON);
		
		log.info("MobileMenu_04 - Step 07: Switch to Comparison window");
		mobilePage.switchToWindowByTitle(driver, "Products Comparison List - Magento Commerce");
		
		log.info("MobileMenu_04 - Step 08: Verify Comparison pop up is opened and 2 selected mobiles: IPhone and Sony Xperia are displayed in Comparison pop up");
		verifyEquals(mobilePage.getTextInElement(driver, MobilePageUI.COMPARE_PRODUCT_TITLE_IN_POP_UP),"COMPARE PRODUCTS");
		
		log.info("MobileMenu_04 - Step 09: Verify Sony Xperia and IPhone are listed in Comparion pop-up");
		verifyTrue(mobilePage.isDynamicDisplayed(driver, MobilePageUI.PRODUCT_NAME_IN_COMPARISON_POPUP, "Sony Xperia"));
		verifyTrue(mobilePage.isDynamicDisplayed(driver, MobilePageUI.PRODUCT_NAME_IN_COMPARISON_POPUP, "IPhone"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
