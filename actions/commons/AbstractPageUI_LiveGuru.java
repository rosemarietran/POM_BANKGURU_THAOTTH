package commons;

public class AbstractPageUI_LiveGuru {
	//1 locator đại diện cho 14 pages
	public static final String DYNAMIC_LINK = "//ul[@class='menusubnav']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX = "(//textarea | //input)[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE = "//td[contains(text(),'%s')]//following-sibling::td/label";
	public static final String DYNAMIC_PAGE_TITLE_OR_MESSAGE = "//div[@class='page-title']//h2[contains(text(),'%s')]";
	public static final String DYNAMIC_VALUE_IN_TABLE = "//td[text()='%s']/following-sibling::td";
	public static final String DYNAMIC_CART_WRAPPER_MENU = "//div[@class='links']//a[@title='%s']";
	public static final String DYNAMIC_PAGE_TITLE = "//div[@class='page-title']/h1[contains(text(),'%s')]";
	public static final String DYNAMIC_MESSAGE = "//li[@class='success-msg']//span[contains(text(),'%s')]";
	public static final String DYNAMIC_LEFT_SIDE_MENU_LINK = "//div[@class='block-content']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_DETAIL_TITLE = "//div[@class='fieldset']/h2[contains(text(),'%s')]";
}
