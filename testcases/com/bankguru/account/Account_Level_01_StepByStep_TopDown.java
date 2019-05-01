package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Account_Level_01_StepByStep_TopDown {
	WebDriver driver;
	String loginPageUrl, userIdInfor, passwordInfor, email;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		
		email = "selenium09" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		/* Step 01 - Check Login Page display */
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());

		/* Step 02 - Click to 'here' link -> Open Register Page */
		loginPageUrl = driver.getCurrentUrl();
		System.out.println("Login page URL: " + loginPageUrl);
		driver.findElement(By.xpath("//a[text()='here']")).click();

		/* Step 03 - Check Register Page displayed */
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());

		/* Step 04 - Input random email to Email textbox */
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);

		/* Step 05 - Click to Submit button */
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		/* Step 06 - Get User/Password information */
		userIdInfor = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passwordInfor = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("User: " + userIdInfor + " - Password: " + passwordInfor);
	}

	@Test
	public void TC_02_LoginToSystem() {
		/* Step 01 - Open login page */
		driver.get(loginPageUrl);
		/* Step 02 - Input valid data to Username and Password textbox*/
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdInfor);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordInfor);
		
		/* Step 03 - Click Submit button*/
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		/* Step 04 - Check Home page displayed*/
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		/* Step 05 - Check UserID infor displayed in Home Page */
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIdInfor + "']")).isDisplayed());
	}
	
	public void TC_03_CreateNewCustomer() {
		
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
