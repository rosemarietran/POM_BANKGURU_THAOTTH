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

public class Account_Level_11_ShareClassState_CloseBrowser_WebDriverManagerLib extends AbstractTest {
	WebDriver driver;
	
	String loginPageUrl, email;
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
	public void TC_01_CheckShareClassState() {
		log.info("Login - Step 01: Verify Login page displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());

		log.info("Login - Step 02: Input to UserID and Password to textboxes");
		loginPage.inputToUserIDTextbox(Account_Common_01_RegisterToSystem.USER_ID_INFO);
		loginPage.inputToPasswordTextbox(Account_Common_01_RegisterToSystem.PASSWORD_INFO);

		log.info("Login - Step 03: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - Step 04: Verify Welcome message displayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());

		log.info("Login - Step 05: Verify UserID infor displayed");
		verifyTrue(homePage.isUserIDDisplayed(Account_Common_01_RegisterToSystem.USER_ID_INFO));
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
