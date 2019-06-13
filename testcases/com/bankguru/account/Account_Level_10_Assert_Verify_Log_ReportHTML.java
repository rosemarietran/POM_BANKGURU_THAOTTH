package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankguru.pageObjects.DepositPageObject;
import bankguru.pageObjects.FundTransferPageObject;
import bankguru.pageObjects.HomePageObject;
import bankguru.pageObjects.LoginPageObject;
import bankguru.pageObjects.NewAccountPageObject;
import bankguru.pageObjects.RegisterPageObject;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageFactoryManager;

public class Account_Level_10_Assert_Verify_Log_ReportHTML extends AbstractTest {
	WebDriver driver;
	String loginPageUrl, userIdInfor, passwordInfor, email;
	AbstractPage abstractPage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;
	FundTransferPageObject fundTransferPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "selenium09" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_RegisterToSystem() {
		log.info("Register - Step 01: Verify login page");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());

		log.info("Register - Step 02: Get login page URL");
		loginPageUrl = loginPage.getLoginPageUrl();

		log.info("Register - Step 03: Click to 'here' link");
		registerPage = loginPage.clickToHereLink();

		log.info("Register - Step 04: Verify Register page displayed");
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());

		log.info("Register - Step 05: Input to 'Email' textbox and click to 'Login' button");
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToSubmitButton();

		log.info("Get User Id and Password infor");
		userIdInfor = registerPage.getUserIdInfor();
		passwordInfor = registerPage.getPasswordInfor();
	}

	public void TC_02_LoginToSystem() {
		log.info("Login - Step 01: Open Login page again");
		loginPage = registerPage.openLoginPage(loginPageUrl);

		log.info("Login - Step 02: Verify Login page displayed");
		Assert.assertFalse(loginPage.isLoginFormDisplayed());

		log.info("Login - Step 03: Input to UserID and Password to textboxes");
		loginPage.inputToUserIDTextbox(userIdInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);

		log.info("Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - Step 05: Verify Welcome message displayed");
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());

		log.info("Login - Step 06: Verify UserID infor displayed");
		Assert.assertTrue(homePage.isUserIDDisplayed(userIdInfor));
	}

	@Test
	public void TC_03_CheckAssertAndVerify() {
		log.info("Login - Step 01: Open Login page again");
		loginPage = registerPage.openLoginPage(loginPageUrl);

		log.info("Login - Step 02: Verify Login page displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());

		log.info("Login - Step 03: Input to UserID and Password to textboxes");
		loginPage.inputToUserIDTextbox(userIdInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);

		log.info("Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - Step 05: Verify Welcome message displayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());

		log.info("Login - Step 06: Verify UserID infor displayed");
		verifyTrue(homePage.isUserIDDisplayed(userIdInfor));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		driver.close();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
