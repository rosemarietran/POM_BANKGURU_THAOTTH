package liveguru;

public class MobilePageUI {
	public static final String PRODUCT_PRICE_IN_PRODUCTS_LIST = "//a[contains(text(),'%s')]//parent::h2//following-sibling::div[@class='price-box']//span[@class='price']";
	public static final String PRODUCT_VIEW_DETAIL_LINK = "//a[contains(text(),'%s')]";
	public static final String PRODUCT_PRICE_IN_PRODUCT_DETAIL = "//div[@class='price-box']//span[@class='price']";
}
