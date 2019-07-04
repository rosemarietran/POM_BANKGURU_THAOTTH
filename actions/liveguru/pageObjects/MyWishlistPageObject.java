package liveguru.pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.AbstractPageUI_LiveGuru;
import commons.AbstractPage_LiveGuru;

public class MyWishlistPageObject extends AbstractPage_LiveGuru {
	private WebDriver driver;
	
	public MyWishlistPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
}
