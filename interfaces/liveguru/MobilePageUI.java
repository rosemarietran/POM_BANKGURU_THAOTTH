package liveguru;

public class MobilePageUI {
	public static final String PRODUCT_PRICE_IN_PRODUCTS_LIST = "//a[contains(text(),'%s')]//parent::h2//following-sibling::div[@class='price-box']//span[@class='price']";
	public static final String PRODUCT_VIEW_DETAIL_LINK = "//a[contains(text(),'%s')]";
	public static final String PRODUCT_PRICE_IN_PRODUCT_DETAIL = "//div[@class='price-box']//span[@class='price']";
	public static final String APPLY_COUPON_CODE_BUTTON = "//button//span[contains(text(),'Apply')]";
	public static final String DISCOUNT_AMOUNT_LABEL = "//td[contains(text(),'Discount (GURU50)')]//following-sibling::td//span[@class='price']";
	public static final String GRAND_TOTAL_TEXT = "//strong[contains(text(),'Grand Total')]//parent::td//following-sibling::td[@class='a-right']//span[@class='price']";
	public static final String COMPARE_BUTTON = "//div[@class='block block-list block-compare']//span[text()='Compare']";
	public static final String COMPARE_PRODUCT_TITLE_IN_POP_UP = "//div[@class='page-title title-buttons']/h1";
	public static final String PRODUCT_NAME_IN_COMPARISON_POPUP = "//h2[@class='product-name']//a[text()='%s']";
	public static final String CART_ERROR_MESSAGE = "//p[@class='item-msg error']";
	public static final String UPDATE_CART_ACTION_BUTTON = "//button[@name='update_cart_action']//span[contains(text(),'%s')]";
	public static final String CART_EMPTY_MESSAGE_TEXT = "//div[@class='cart-empty']/p[contains(text(),'You have no items in your shopping cart.')]";
	public static final String UPDATE_CART_BUTTON = "//button[@title='Update']";
}

