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

public class Account_Level_13_DependenciesTestcases extends AbstractTest {
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

	}

	@Test
	public void TC_01_ValidateCustomerNameTextbox() {

	}

	@Test
	public void TC_02_ValidateAddressTextArea() {

	}

	@Test
	public void TC_03_ValidateCityTextbox() {

	}
	
	@Test
	public void TC_04_ValidatePhoneTextbox() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
