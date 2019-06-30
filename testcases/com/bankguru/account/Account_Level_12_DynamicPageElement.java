package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankguru.pageObjects.DepositPageObject;
import bankguru.pageObjects.HomePageObject;
import bankguru.pageObjects.LoginPageObject;
import bankguru.pageObjects.NewAccountPageObject;
import bankguru.pageObjects.NewCustomerPageObject;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageFactoryManager;

public class Account_Level_12_DynamicPageElement extends AbstractTest {
	WebDriver driver;

	String loginPageUrl, email;
	AbstractPage abstractPage;
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManager.getLoginPage(driver);

		log.info("Login - Step 01: Verify Login page displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());

		log.info("Login - Step 02: Input to UserID and Password to textboxes");
		loginPage.inputToUserIDTextbox(Account_Common_01_RegisterToSystem.USER_ID_INFO);
		loginPage.inputToPasswordTextbox(Account_Common_01_RegisterToSystem.PASSWORD_INFO);
		
		log.info("Login - Step 03: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 04: Verify Welcome message displayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());
	}

	@Test
	public void TC_01_ValidateErrorMessageAtNewCustomerForm() {
		log.info("ValidateNewCustomer - Step 01: Open new customer page");
		newCustomerPage = (NewCustomerPageObject) homePage.openMultiplePages(driver, "New Customer");
		verifyTrue(newCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Add New Customer"));
		
		log.info("ValidateNewCustomer - Step 02: Click to all fields (empty data) at New Customer Form");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "name");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "dob");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "addr");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "city");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "state");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "pinno");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "telephoneno");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "emailid");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "password");
		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "emailid");
		
		log.info("ValidateNewCustomer - Step 03: Verify all fields displayed error messages '<xxxx> must not be blank'");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Customer Name"), "Customer name must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Date of Birth"), "Date Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Address"), "Address Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "City"), "City Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "State"), "State must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "PIN"), "PIN Code must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Mobile Number"), "Mobile no must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "E-mail"), "Email-ID must not be blank");
		verifyEquals(newCustomerPage.getDynamicErrorMessage(driver, "Password"), "Password must not be blank");
	}
	
	@Test
	public void TC_02_ValidateErrorMessageAtNewAccountForm() {
		log.info("ValidateNewAccount - Step 01: Open new account page");
		newAccountPage = (NewAccountPageObject) homePage.openMultiplePages(driver, "New Account");
		verifyTrue(newAccountPage.isDynamicPageTitleOrPageMessageDisplayed(	driver, "Add new account form"));
		
		log.info("ValidateNewAccount - Step 02: Click to all fields (empty data) at New Account form");
		newAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "cusid");
		newAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "inideposit");
		newAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "cusid");
		
		log.info("ValidateNewAccount - Step 03: Verify all fields displayed error messages '<xxxx> must not be blank'");
		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"), "Customer ID is required");
		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Initial deposit"), "Initial Deposit must not be blank");
	}
	
	@Test
	public void TC_03_ValidateErrorMessageAtDepositForm() {
		log.info("ValidateDeposit - Step 01: Open deposit page");
		depositPage = (DepositPageObject) homePage.openMultiplePages(driver, "Deposit");
		verifyTrue(depositPage.isDynamicPageTitleOrPageMessageDisplayed(	driver, "Amount Deposit Form"));
		
		log.info("ValidateNewAccount - Step 02: Click to all fields (empty data) at New Account form");
		depositPage.clickToDynamicButtonOrTextboxTextArea(driver, "accountno");
		depositPage.clickToDynamicButtonOrTextboxTextArea(driver, "ammount");
		depositPage.clickToDynamicButtonOrTextboxTextArea(driver, "desc");
		depositPage.clickToDynamicButtonOrTextboxTextArea(driver, "accountno");
		
		log.info("ValidateNewAccount - Step 03: Verify all fields displayed error messages '<xxxx> must not be blank'");
		verifyEquals(depositPage.getDynamicErrorMessage(driver, "Account No"), "Account Number must not be blank");
		verifyEquals(depositPage.getDynamicErrorMessage(driver, "Amount"), "Amount field must not be blank");
		verifyEquals(depositPage.getDynamicErrorMessage(driver, "Description"), "Description can not be blank");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}



}
