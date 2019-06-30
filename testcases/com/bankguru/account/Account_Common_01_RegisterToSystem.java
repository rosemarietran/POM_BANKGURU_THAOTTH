package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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

public class Account_Common_01_RegisterToSystem extends AbstractTest {
	WebDriver driver;
	public static String USER_ID_INFO, PASSWORD_INFO;
	String loginPageUrl, email;
	AbstractPage abstractPage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;
	FundTransferPageObject fundTransferPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "selenium09" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryManager.getLoginPage(driver);

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
		USER_ID_INFO = registerPage.getUserIdInfor();
		PASSWORD_INFO = registerPage.getPasswordInfor();
		closeBrowserAndDriver(driver);

	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
