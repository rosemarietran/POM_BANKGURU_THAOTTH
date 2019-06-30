package commons;

public class AbstractPageUI {
	public static final String HOME_PAGE_LINK = "//ul[@class='menusubnav']//a[text()='Manager']";
	public static final String NEW_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='New Customer']";
	public static final String EDIT_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='Edit Customer']";
	public static final String DELETE_CUSTOMER_LINK = "//ul[@class='menusubnav']//a[text()='Delete Customer']";
	public static final String NEW_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='New Account']";
	public static final String EDIT_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='Edit Account']";
	public static final String DELETE_ACCOUNT_LINK = "//ul[@class='menusubnav']//a[text()='Delete Account']";
	public static final String DEPOSIT_LINK = "//ul[@class='menusubnav']//a[text()='Deposit']";
	public static final String WITHDRAWAL_LINK = "//ul[@class='menusubnav']//a[text()='Withdrawal']";
	public static final String FUND_TRANSFER_LINK = "//ul[@class='menusubnav']//a[text()='Fund Transfer']";
	public static final String CHANGE_PASSWORD_LINK = "//ul[@class='menusubnav']//a[text()='Change Password']";
	public static final String BALANCE_ENQUIRY_LINK = "//ul[@class='menusubnav']//a[text()='Balance Enquiry']";
	public static final String MINI_STATEMENT_LINK = "//ul[@class='menusubnav']//a[text()='Mini Statement']";
	public static final String CUSTOMISED_STATEMENT_LINK = "//ul[@class='menusubnav']//a[text()='Customised Statement']";

	//1 locator đại diện cho 14 pages
	public static final String DYNAMIC_LINK = "//ul[@class='menusubnav']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX = "(//textarea | //input)[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE = "//td[contains(text(),'%s')]//following-sibling::td/label";
	public static final String DYNAMIC_PAGE_TITLE_OR_MESSAGE = "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_VALUE_IN_TABLE = "//td[text()='%s']/following-sibling::td";
	public static final String DYNAMIC_BUTTON = "//button//span[contains(text(),'%s')]";
}
