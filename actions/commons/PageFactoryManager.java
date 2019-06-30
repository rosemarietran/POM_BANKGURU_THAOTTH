package commons;

import org.openqa.selenium.WebDriver;

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
import bankguru.pageObjects.RegisterPageObject;
import bankguru.pageObjects.WithdrawalPageObject;

public class PageFactoryManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}

	public static EditAccountPageObject getEditAccountPage(WebDriver driver) {
		return new EditAccountPageObject(driver);
	}

	public static DeleteAccountPageObject getDeleteAccountPage(WebDriver driver) {
		return new DeleteAccountPageObject(driver);
	}

	public static DepositPageObject getDepositPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}

	public static FundTransferPageObject getFundTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}

	public static WithdrawalPageObject getWithdrawalPage(WebDriver driver) {
		return new WithdrawalPageObject(driver);
	}

	public static BalanceEnquiryPageObject getBalanceEnquiryPage(WebDriver driver) {
		return new BalanceEnquiryPageObject(driver);
	}

	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}

	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}

	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}
}
