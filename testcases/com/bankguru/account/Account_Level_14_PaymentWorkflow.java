package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankguru.pageObjects.BalanceEnquiryPageObject;
import bankguru.pageObjects.DeleteAccountPageObject;
import bankguru.pageObjects.DeleteCustomerPageObject;
import bankguru.pageObjects.DepositPageObject;
import bankguru.pageObjects.EditAccountPageObject;
import bankguru.pageObjects.EditCustomerPageObject;
import bankguru.pageObjects.FundTransferPageObject;
import bankguru.pageObjects.HomePageObject;
import bankguru.pageObjects.LoginPageObject;
import bankguru.pageObjects.NewAccountPageObject;
import bankguru.pageObjects.NewCustomerPageObject;
import bankguru.pageObjects.WithdrawalPageObject;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageFactoryManager;

public class Account_Level_14_PaymentWorkflow extends AbstractTest {
	WebDriver driver;

	AbstractPage abstractPage;
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;
	EditCustomerPageObject editCustomerPage;
	DeleteCustomerPageObject deleteCustomerPage;
	EditAccountPageObject editAccountPage;
	DeleteAccountPageObject deleteAccountPage;
	WithdrawalPageObject withdrawalPage;
	BalanceEnquiryPageObject balanceEnquiryPage;
	DepositPageObject depositPage;
	FundTransferPageObject fundTransferPage;

	// Declare variables Information for adding new customer
	String custName, gender, dob, addr, city, state, pin, phone, email, pwd;
	// Declare variable for edit customer information
	String editAddr, editCity, editState, editPin, editPhone, editEmail;
	String custId, savingsAccountId, currentAccountId;
	String savingAccountValue, currentAccountValue, today, initDepostiValue, withdrawalAmountValue, currentBalance, transferAmountValue;

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

		// Map data test for adding new customer
		custName = "Bin Shin";
		gender = "male";
		dob = "1989-01-22";
		addr = "123 Le Van Luong";
		city = "Hanoi";
		state = "Nam Tu Liem";
		pin = "600000";
		phone = "0987654321";
		email = "binshin" + randomNumber() + "@gmail.com";
		pwd = "123456";

		// Map data test for editing customer
		editAddr = "239 Xuan Thuy";
		editCity = "HoChiMinh";
		editState = "Cau Giay";
		editPin = "222666";
		editPhone = "0978635429";
		editEmail = "binshin" + randomNumber() + "@hotmail.com";

		savingAccountValue = "Savings";
		currentAccountValue = "Current";

		today = getCurrentDay();
		initDepostiValue = "50000";
		withdrawalAmountValue = "15000";		
		transferAmountValue = "10000";
	}

	@Test
	public void Payment_01_CreateNewCustomer() {
		log.info("Payment_01 - Step 01: Open New Customer page");
		newCustomerPage = (NewCustomerPageObject) homePage.openMultiplePages(driver, "New Customer");
		verifyTrue(newCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Add New Customer"));

		log.info("Payment_01 - Step 02: Input valid data to all fields");
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "name", custName);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "dob", dob);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "addr", addr);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "city", city);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "state", state);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "pinno", pin);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "telephoneno", phone);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "emailid", email);
		newCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "password", pwd);

		newCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "sub");

		log.info("Payment_01 - Step 03: Verify message 'Customer Registered Successfully!!!' displayed");
		verifyTrue(newCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver,
				"Customer Registered Successfully!!!"));

		log.info("Payment - Step 04: Verify actual data matching with expected data");
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Customer Name"), custName);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Birthdate"), dob);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Address"), addr);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "City"), city);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "State"), state);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Pin"), pin);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Mobile No."), phone);
		verifyEquals(newCustomerPage.getDynamicValueInTable(driver, "Email"), email);

		custId = newCustomerPage.getDynamicValueInTable(driver, "Customer ID");
	}

	@Test
	public void Payment_02_EditCustomer() {
		log.info("Payment_02 - Step 01: Open Edit Customer Form");
		editCustomerPage = (EditCustomerPageObject) newCustomerPage.openMultiplePages(driver, "Edit Customer");
		verifyTrue(editCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Customer Form"));

		log.info("Payment_02 - Step 02: Input Customer ID and Submit");
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "cusid", custId);
		editCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");

		log.info("Payment_02 - Step 03: Verify actual data matching with expected data");
		verifyTrue(editCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Customer"));
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "name"), custName);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "dob"), dob);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextArea(driver, "addr"), addr);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "city"), city);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "state"), state);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "pinno"), pin);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "telephoneno"), phone);
		verifyEquals(editCustomerPage.getDynamicTextValueInTextbox(driver, "emailid"), email);

		log.info("Payment_02 - Step 04: Input valid data to all editable fields");
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "addr", editAddr);
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "city", editCity);
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "state", editState);
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "pinno", editPin);
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "telephoneno", editPhone);
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "emailid", editEmail);
		editCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "sub");

		log.info("Payment_02 - Step 05: Verify message 'Customer details updated Successfully!!!' displayed");
		verifyTrue(editCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver,
				"Customer details updated Successfully!!!"));

		log.info("Payment_02 - Step 06: Verify actual data matching with expected data");
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Customer Name"), custName);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Birthdate"), dob);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Address"), editAddr);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "City"), editCity);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "State"), editState);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Pin"), editPin);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Mobile No."), editPhone);
		verifyEquals(editCustomerPage.getDynamicValueInTable(driver, "Email"), editEmail);
	}

	@Test
	public void Payment_03_CreatNewAccountAndCheckCurrentAmount() {
		log.info("------------CREAT SAVINGS ACCOUNT---------------");
		log.info("Payment_03 - Step 01: Open New Account Form");
		newAccountPage = (NewAccountPageObject) editCustomerPage.openMultiplePages(driver, "New Account");
		verifyTrue(newAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Add new account form"));

		log.info("Payment_03 - Step 02: Input Customer ID");
		newAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "cusid", custId);
		
		log.info("Payment_03 - Step 03: Select Account type = Savings");
		newAccountPage.selectValueInDynamicDropdown(driver, savingAccountValue, "selaccount");

		log.info("Payment_03 - Step 04: Input Initial Deposit");
		newAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "inideposit", initDepostiValue);

		log.info("Payment_03 - Step 05: Click to Submit button");
		newAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "button2");

		log.info("Payment_03 - Step 06: Verify message 'Account Generated Successfully!!!' displayed");
		verifyTrue(
				newAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("Payment_02 - Step 07: Verify Created Account details displayed");
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Customer ID"), custId);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Customer Name"), custName);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Account Type"), savingAccountValue);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Date of Opening"), today);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Current Amount"), initDepostiValue);
		
		savingsAccountId = newAccountPage.getDynamicValueInTable(driver, "Account ID");
		
		log.info("------------CREAT CURRENT ACCOUNT---------------");
		log.info("Payment_03 - Step 08: Open New Account Form");
		newAccountPage = (NewAccountPageObject) newAccountPage.openMultiplePages(driver, "New Account");
		verifyTrue(newAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Add new account form"));

		log.info("Payment_03 - Step 09: Input Customer ID");
		newAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "cusid", custId);
		
		log.info("Payment_03 - Step 10: Select Account type = Savings");
		newAccountPage.selectValueInDynamicDropdown(driver, currentAccountValue, "selaccount");

		log.info("Payment_03 - Step 11: Input Initial Deposit");
		newAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "inideposit", initDepostiValue);

		log.info("Payment_03 - Step 12: Click to Submit button");
		newAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "button2");

		log.info("Payment_03 - Step 13: Verify message 'Account Generated Successfully!!!' displayed");
		verifyTrue(
				newAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Account Generated Successfully!!!"));

		log.info("Payment_02 - Step 14: Verify Created Account details displayed");
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Customer ID"), custId);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Customer Name"), custName);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Email"), editEmail);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Account Type"), currentAccountValue);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Date of Opening"), today);
		verifyEquals(newAccountPage.getDynamicValueInTable(driver, "Current Amount"), initDepostiValue);
		
		currentAccountId = newAccountPage.getDynamicValueInTable(driver, "Account ID");
	}

	@Test
	public void Payment_04_TransferMoneyToCurrentAccount() {
		log.info("Payment_04 - Step 01: Open Edit Account Form");
		editAccountPage = (EditAccountPageObject) newAccountPage.openMultiplePages(driver, "Edit Account");
		verifyTrue(editAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Account Form"));
		
		log.info("Payment_04 - Step 02: Input Account ID and Submit");
		editAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", savingsAccountId);
		editAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");
		
		log.info("Payment_04 - Step 03: Verify actual data matching with expected data");
		verifyTrue(editAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Account Entry Form"));
		verifyEquals(editAccountPage.getDynamicTextValueInTextbox(driver, "txtcid"), custId);
		verifyEquals(editAccountPage.getFirstItemValueInDynamicDropdown(driver, "a_type"), savingAccountValue);
	}

	@Test
	public void Payment_05_WithdrawMoneyInCurrentAccount() {
		log.info("Payment_05 - Step 01: Open Amount Withdrawal Form");
		withdrawalPage = (WithdrawalPageObject) editAccountPage.openMultiplePages(driver, "Withdrawal");
		verifyTrue(withdrawalPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Amount Withdrawal Form"));
		
		log.info("Payment_05 - Step 02: Input Account Number (Account ID)");
		withdrawalPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", savingsAccountId);
		
		log.info("Payment_05 - Step 03: Input Amount");
		withdrawalPage.inputToDynamicButtonOrTextboxTextArea(driver, "ammount", withdrawalAmountValue);
		
		log.info("Payment_05 - Step 04: Input Description and Submit");
		withdrawalPage.inputToDynamicButtonOrTextboxTextArea(driver, "desc", "Withdrawal");
		withdrawalPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");		
		
		log.info("Payment_05 - Step 05: Verify actual data matching with expected data");
		verifyTrue(withdrawalPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Transaction details of Withdrawal for Account " + savingsAccountId));
		verifyEquals(withdrawalPage.getDynamicValueInTable(driver, "Account No"), savingsAccountId);
		verifyEquals(withdrawalPage.getDynamicValueInTable(driver, "Amount Debited"), withdrawalAmountValue);		
		verifyEquals(withdrawalPage.getDynamicValueInTable(driver, "Type of Transaction"), "Withdrawal");
		verifyEquals(withdrawalPage.getDynamicValueInTable(driver, "Description"), "Withdrawal");
		currentBalance = String.valueOf(Integer.parseInt(initDepostiValue) - Integer.parseInt(withdrawalAmountValue));
		verifyEquals(withdrawalPage.getDynamicValueInTable(driver, "Current Balance"), currentBalance);
	}

	@Test
	public void Payment_06_TransferMoneyToAnotherAccount() {
		log.info("Payment_06 - Step 01: Open Fund transfer");
		fundTransferPage = (FundTransferPageObject) withdrawalPage.openMultiplePages(driver, "Fund Transfer");
		verifyTrue(fundTransferPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Fund transfer"));
		
		log.info("Payment_06 - Step 02: Input Payer account no");
		fundTransferPage.inputToDynamicButtonOrTextboxTextArea(driver, "payersaccount", savingsAccountId);
		
		log.info("Payment_06 - Step 03: Input Payee account no");
		fundTransferPage.inputToDynamicButtonOrTextboxTextArea(driver, "payeeaccount", currentAccountId);
		
		log.info("Payment_06 - Step 04: Input Amount");
		fundTransferPage.inputToDynamicButtonOrTextboxTextArea(driver, "ammount", transferAmountValue);
		
		log.info("Payment_06 - Step 05: Input Description and Submit");
		fundTransferPage.inputToDynamicButtonOrTextboxTextArea(driver, "desc", "Transfer");
		fundTransferPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");		
		
		log.info("Payment_06 - Step 06: Verify actual data matching with expected data");
		verifyTrue(fundTransferPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Fund Transfer Details"));
		verifyEquals(fundTransferPage.getDynamicValueInTable(driver, "From Account Number"), savingsAccountId);
		verifyEquals(fundTransferPage.getDynamicValueInTable(driver, "To Account Number"), currentAccountId);		
		verifyEquals(fundTransferPage.getDynamicValueInTable(driver, "Amount"), transferAmountValue);
		verifyEquals(fundTransferPage.getDynamicValueInTable(driver, "Description"), "Transfer");
	}

	@Test
	public void Payment_07_CheckCurrentAccountBalance() {
		log.info("Payment_07 - Step 01: Open Balance Enquiry Form");
		balanceEnquiryPage = (BalanceEnquiryPageObject) fundTransferPage.openMultiplePages(driver, "Balance Enquiry");
		verifyTrue(balanceEnquiryPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Balance Enquiry Form"));
		
		log.info("Payment_07 - Step 02: Input Account no and click Submit");
		balanceEnquiryPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", savingsAccountId);
		balanceEnquiryPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_07 - Step 03: Verify actual data matching with expected data");
		verifyTrue(balanceEnquiryPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Balance Details for Account " + savingsAccountId));
		verifyEquals(balanceEnquiryPage.getDynamicValueInTable(driver, "Account No"), savingsAccountId);
		verifyEquals(balanceEnquiryPage.getDynamicValueInTable(driver, "Type of Account"), savingAccountValue);	
		currentBalance = String.valueOf(Integer.parseInt(currentBalance) - Integer.parseInt(transferAmountValue));
		verifyEquals(balanceEnquiryPage.getDynamicValueInTable(driver, "Balance"), currentBalance);
	}

	@Test
	public void Payment_08_DeleteCurrentAccountAndVerify() {
		log.info("------------START DELETE SAVINGS ACCOUNT---------------");
		log.info("Payment_08 - Step 01: Open Delete Account Form");
		deleteAccountPage = (DeleteAccountPageObject) balanceEnquiryPage.openMultiplePages(driver, "Delete Account");
		verifyTrue(deleteAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Delete Account Form"));
		
		log.info("Payment_08 - Step 02: Input Account no and click Submit");
		deleteAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", savingsAccountId);
		deleteAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_08 - Step 03: Verify alert message 'Do you really want to delete this Account?' displayed and accept");
		deleteAccountPage.isDynamicAlertDisplayed(driver, "Do you really want to delete this Account?");
		
		log.info("Payment_08 - Step 04: Verify alert message 'Account Deleted Successfully' displayed and accept");
		deleteAccountPage.isDynamicAlertDisplayed(driver, "Account Deleted Sucessfully");
		
		log.info("Payment_08 - Step 05: Open Edit Account page");
		editAccountPage = (EditAccountPageObject) deleteAccountPage.openMultiplePages(driver, "Edit Account");
		verifyTrue(editAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Account Form"));
		
		log.info("Payment_08 - Step 06: Input Account no and click Submit");
		editAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", savingsAccountId);
		editAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_08 - Step 07: Verify alert message 'Account does not exist' displayed and accept");
		editAccountPage.isDynamicAlertDisplayed(driver, "Account does not exist");
		log.info("------------END DELETE SAVINGS ACCOUNT---------------");
		
		log.info("------------START DELETE CURRENT ACCOUNT---------------");
		log.info("Payment_08 - Step 08: Open Delete Account Form");
		deleteAccountPage = (DeleteAccountPageObject) editAccountPage.openMultiplePages(driver, "Delete Account");
		verifyTrue(deleteAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Delete Account Form"));
		
		log.info("Payment_08 - Step 09: Input Account no and click Submit");
		deleteAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", currentAccountId);
		deleteAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_08 - Step 10: Verify alert message 'Do you really want to delete this Account?' displayed and accept");
		deleteAccountPage.isDynamicAlertDisplayed(driver, "Do you really want to delete this Account?");
		
		log.info("Payment_08 - Step 11: Verify alert message 'Account Deleted Successfully' displayed and accept");
		deleteAccountPage.isDynamicAlertDisplayed(driver, "Account Deleted Sucessfully");
		
		log.info("Payment_08 - Step 12: Open Edit Account page");
		editAccountPage = (EditAccountPageObject) deleteAccountPage.openMultiplePages(driver, "Edit Account");
		verifyTrue(editAccountPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Account Form"));
		
		log.info("Payment_08 - Step 13: Input Account no and click Submit");
		editAccountPage.inputToDynamicButtonOrTextboxTextArea(driver, "accountno", currentAccountId);
		editAccountPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_08 - Step 14: Verify alert message 'Account does not exist' displayed and accept");
		editAccountPage.isDynamicAlertDisplayed(driver, "Account does not exist");
		log.info("------------END DELETE CURRENT ACCOUNT---------------");
	}

	@Test
	public void Payment_09_DeleteCurrentCustomerAndVerify() {
		log.info("Payment_09 - Step 01: Open Delete Customer Form");
		deleteCustomerPage = (DeleteCustomerPageObject) editAccountPage.openMultiplePages(driver, "Delete Customer");
		verifyTrue(deleteCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Delete Customer Form"));
		
		log.info("Payment_09 - Step 02: Input Customer ID and click Submit");
		deleteCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "cusid", custId);
		deleteCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_09 - Step 03: Verify alert message 'Do you really want to delete this Customer?' displayed and accept");
		deleteCustomerPage.isDynamicAlertDisplayed(driver, "Do you really want to delete this Customer?");
		
		log.info("Payment_09 - Step 04: Verify alert message 'Customer Deleted Successfully' displayed and accept");
		deleteCustomerPage.isDynamicAlertDisplayed(driver, "Customer deleted Successfully");
		
		log.info("Payment_09 - Step 05: Open Edit Customer page");
		editCustomerPage = (EditCustomerPageObject) deleteCustomerPage.openMultiplePages(driver, "Edit Customer");
		verifyTrue(editCustomerPage.isDynamicPageTitleOrPageMessageDisplayed(driver, "Edit Customer Form"));
		
		log.info("Payment_08 - Step 06: Input Account no and click Submit");
		editCustomerPage.inputToDynamicButtonOrTextboxTextArea(driver, "cusid", custId);
		editCustomerPage.clickToDynamicButtonOrTextboxTextArea(driver, "AccSubmit");	
		
		log.info("Payment_08 - Step 07: Verify alert message 'Customer does not exist!!' displayed and accept");
		editCustomerPage.isDynamicAlertDisplayed(driver, "Customer does not exist!!");
	}

	public int randomNumber() {
		int random = (int) (Math.random() * 50 + 1);
		return random;
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
