package commons;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import liveguru.pageObjects.HomePageObject;



public class AbstractPage_LiveGuru {
	WebElement element;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;
	List<WebElement> elements;
	Actions action;
	By byLocator;

	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void fowardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void clickToElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendKey, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		// highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(valueToSendKey);
	}

	public String getTextElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		return element.getText().trim();
	}

	public String getTextElement(WebDriver driver, String locator, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		return element.getText().trim();
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String value, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemCustomDropdown(WebDriver driver, String parentXpath, String allItemsXpath,
			String expectedItemValue) throws Exception {
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		javascriptExecutor.executeScript("arguments[0].click()", parentDropdown);

		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));

		for (WebElement childElement : allItems) {
			if (expectedItemValue.equals(childElement.getText())) {
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(1500);

				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				}
				Thread.sleep(1500);
				break;

			}
		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getDynamicAttributeValue(WebDriver driver, String locator, String attributeName,
			String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		boolean status = true;
		try {
			highlightElement(driver, locator);
			element = driver.findElement(By.xpath(locator));
			if (element.isDisplayed()) {
				return status;
			}
		} catch (Exception e) {
			Reporter.log("--------------------------Element not displayed--------------------------");
			Reporter.log(e.getMessage());
			System.err.println("--------------------------Element not displayed--------------------------");
			System.err.println(e.getMessage());
			status = false;
		}

		return status;
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		overideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(locator));

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but is not visible/ displayed");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return true;

		} else {
			System.out.println("Element is in DOM and visible/ displayed");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return false;

		}
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator, String... values) {
		overideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		locator = String.format(locator, (Object[]) values);
		List<WebElement> elements = driver.findElements(By.xpath(locator));

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but is not visible/ displayed");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return true;

		} else {
			System.out.println("Element is in DOM and visible/ displayed");
			overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
			return false;

		}
	}

	public void overideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isControlIsSelected(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlIsEnable(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void switchToChildWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);
	}

	public void backToTopWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.doubleClick(element);
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.contextClick(element);
	}

	public void dragAndDrop(WebDriver driver, String source, String target) {
		WebElement sourceElement = driver.findElement(By.xpath(source));
		WebElement targetElement = driver.findElement(By.xpath(target));
		action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement);
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.sendKeys(element, key);
	}

	public void highlightElement(WebDriver driver, String locator) {
		javascriptExecutor = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				"border: 3px solid red; border-style: dashed;");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				originalStyle);
	}

	public Object executeForBrowser(WebDriver driver, String locator, String javaSript) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		return javascriptExecutor.executeScript(javaSript);
	}

	public Object clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		return javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attribute) throws Exception {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attribute, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public void setAttributeInDOM(WebDriver driver, String locator, String attribute, String value) throws Exception {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "');", element);
	}

	public Object scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		return javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		By byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		By byLocator = By.xpath(locator);
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		} catch (Exception e) {
			Reporter.log("--------------------Wait for element not visible-------------------");
			Reporter.log(e.getMessage());
			System.err.println("--------------------Wait for element not visible-------------------");
			System.err.println(e.getMessage() + "\n");
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
		locator = String.format(locator, (Object[]) dynamicValues);
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		By byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		By byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
		overideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		overideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
	}

	public void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
	}

	public HomePageObject openHomePage(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.HOME_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.HOME_PAGE_LINK);
		return PageFactoryManager_LiveGuru.getHomePage(driver);
	}


	public AbstractPage_LiveGuru openMultiplePages(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_LEFT_SIDE_MENU_LINK, pageName);
		clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_LEFT_SIDE_MENU_LINK, pageName);

		switch (pageName) {
		case "Account Information":
			return PageFactoryManager_LiveGuru.getAccountInformationPage(driver);
		default:
			return PageFactoryManager_LiveGuru.getHomePage(driver);
		}
	}

	public void inputToDynamicButtonOrTextboxTextArea(WebDriver driver, String fieldName, String value) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
		if (driver.toString().contains("chrome")) {
			removeAttributeInDOM(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "type", fieldName);
		}
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, value, fieldName);
	}
	
	public void clickToDynamicButtonOrTextboxTextArea(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
	}
	
	public void clickToDynamicButton(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_BUTTON, fieldName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON, fieldName);
	}

	public String getDynamicErrorMessage(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
	}

	public boolean isDynamicPageTitleOrPageMessageDisplayed(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE_OR_MESSAGE, fieldName);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE_OR_MESSAGE, fieldName);
	}

	public String getDynamicValueInTable(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, fieldName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, fieldName);
	}

	public String getDynamicTextValueInTextbox(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
		return getDynamicAttributeValue(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, "value",
				fieldName);
	}

	public String getDynamicTextValueInTextArea(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_CHECKBOX, fieldName);
	}

	public void selectValueInDynamicDropdown(WebDriver driver, String valueItem, String dropDownId) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, dropDownId);
		selectItemInDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, valueItem, dropDownId);
	}

	public String getFirstItemValueInDynamicDropdown(WebDriver driver, String dropDownId) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, dropDownId);
		return getSelectedItemInDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, dropDownId);
	}

	public boolean isDynamicAlertDisplayed(WebDriver driver, String fieldName) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (getTextInAlert(driver).equals(fieldName)) {
			acceptAlert(driver);
			return true;
		}
		return false;
	}
	
	//LIVE GURU
	public void clickToDynamicCartWrapperMenu(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_CART_WRAPPER_MENU, fieldName);
		clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_CART_WRAPPER_MENU, fieldName);		
	}
	
	public boolean isDynamicPageTitleDisplayed(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_PAGE_TITLE, fieldName);
		return isControlDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_PAGE_TITLE, fieldName);
	}
	
	public String getDynamicMessage(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, fieldName);
		return getTextElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_MESSAGE, fieldName);
	}
	
	public boolean isDynamicPageDetailTitleDisplayed(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_PAGE_DETAIL_TITLE, fieldName);
		return isControlDisplayed(driver, AbstractPageUI_LiveGuru.DYNAMIC_PAGE_DETAIL_TITLE, fieldName);
	}
	
	public boolean isDynamicDisplayed(WebDriver driver, String locator, String fieldName) {
		waitForElementVisible(driver, locator, fieldName);
		return isControlDisplayed(driver, locator, fieldName);
	}
	
	public void clickToDynamicElement(WebDriver driver, String locator, String fieldName) {
		waitForElementVisible(driver, locator, fieldName);
		clickToElement(driver, locator, fieldName);		
	}
	
	public String getDynamicText(WebDriver driver, String locator, String fieldName) {
		waitForElementVisible(driver, locator, fieldName);
		return getTextElement(driver, locator, fieldName);
	}
	
	public String getTextInElement(WebDriver driver, String locator) {
		waitForElementVisible(driver, locator);
		return getTextElement(driver, locator);
	}
	
	public void clickToDynamicAddToCartButton(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_CART_BUTTON, fieldName);
		clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_CART_BUTTON, fieldName);
	}
	
	public void clickToDynamicAddToCompareButton(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_COMPARE_LINK, fieldName);
		clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_COMPARE_LINK, fieldName);
	}
	
	public void clickToDynamicAddToWishlistButton(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_WISHLIST_LINK, fieldName);
		clickToElement(driver, AbstractPageUI_LiveGuru.DYNAMIC_ADD_TO_WISHLIST_LINK, fieldName);
	}
	
	public void clickAnElement(WebDriver driver, String locator) {
		waitForElementVisible(driver, locator);
		clickToElement(driver, locator);		
	}
	
	public void inputToDynamicTextboxOrTextArea(WebDriver driver, String locator, String fieldName, String value) {
		waitForElementVisible(driver, locator, fieldName);
		if (driver.toString().contains("chrome")) {
			removeAttributeInDOM(driver, locator , "type", fieldName);
		}
		sendKeyToElement(driver, locator, value, fieldName);
	}
}