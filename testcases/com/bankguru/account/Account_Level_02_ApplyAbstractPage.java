package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_Level_02_ApplyAbstractPage {

	WebDriver driver;
	String loginPageUrl, userIdInfor, passwordInfor, email;
	AbstractPage abstractPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
		driver = new FirefoxDriver();
		abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		abstractPage.openAnyUrl(driver, "http://demo.guru99.com/v4/");

		email = "selenium09" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC_01_RegisterToSystem() {
		// Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name='frmLogin']"));

		// loginPageUrl = driver.getCurrentUrl();
		loginPageUrl = abstractPage.getCurrentPageUrl(driver);
		System.out.println("Login page URL: " + loginPageUrl);

		// driver.findElement(By.xpath("//a[text()='here']")).click();
		abstractPage.clickToElement(driver, "//a[text()='here']");

		// Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name='emailid']"));

		// driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", email);

		// driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");

		// userIdInfor = driver.findElement(By.xpath("//td[text()='User ID
		// :']/following-sibling::td")).getText();
		userIdInfor = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");

		// passwordInfor = driver.findElement(By.xpath("//td[text()='Password
		// :']/following-sibling::td")).getText();
		passwordInfor = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
		System.out.println("User: " + userIdInfor + " - Password: " + passwordInfor);
	}

	@Test
	public void TC_02_LoginToSystem() {
		//driver.get(loginPageUrl);
		abstractPage.openAnyUrl(driver, loginPageUrl);
		
		//driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdInfor);
		abstractPage.sendKeyToElement(driver, "//input[@name='uid']", userIdInfor);
		
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordInfor);
		abstractPage.sendKeyToElement(driver, "//input[@name='password']", passwordInfor);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
		//		.isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		
		//Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIdInfor + "']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : " + userIdInfor + "']"));
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
