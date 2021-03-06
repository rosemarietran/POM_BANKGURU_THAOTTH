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

public class Account_Level_09_ElementDisplayed_Undisplayed_OverideTimeout extends AbstractTest {

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

		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		System.out.println("Login page URL: " + loginPageUrl);
		registerPage = loginPage.clickToHereLink();

		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToSubmitButton();
		userIdInfor = registerPage.getUserIdInfor();
		passwordInfor = registerPage.getPasswordInfor();
	}

	@Test
	public void TC_02_LoginToSystem() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIdInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		homePage = loginPage.clickToLoginButton();

		//Check Welcome message displayed
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		
		//Check Login form undisplayed
		Assert.assertTrue(homePage.isLoginFormUnDisplayed());
		
		Assert.assertTrue(homePage.isUserIDDisplayed(userIdInfor));
		
	}

	@Test
	public void TC_03_OpenMultiPage() {
		newAccountPage = (NewAccountPageObject) homePage.openMultiplePages(driver, "New Account");
		
		depositPage = (DepositPageObject) newAccountPage.openMultiplePages(driver, "Deposit");
		
		fundTransferPage = (FundTransferPageObject) depositPage.openMultiplePages(driver, "Fund Transfer");
	
		homePage = (HomePageObject) fundTransferPage.openMultiplePages(driver, "Manager");
	}
	
	@Test
	public void TC_04_OpenMultiPage() {
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManager.getNewAccountPage(driver);
		
		depositPage = (DepositPageObject) newAccountPage.openMultiplePages(driver, "Deposit");
		
		fundTransferPage = (FundTransferPageObject) depositPage.openMultiplePages(driver, "Fund Transfer");
	
		homePage = (HomePageObject) fundTransferPage.openMultiplePages(driver, "Manager");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

}
