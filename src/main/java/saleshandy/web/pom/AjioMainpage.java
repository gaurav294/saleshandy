package saleshandy.web.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import saleshandy.web.constants.TestConstants;

public class AjioMainpage extends AppPage {

	@FindBy(xpath = "//div[contains(@class, 'searchbar-view')]//descendant::input[contains(@name, 'search')]")
	private WebElement searchTextBox;

	@FindBy(xpath = "//div[contains(@class, 'searchbar-view')]//descendant::button[@type='submit']")
	private WebElement searchButton;

	@FindBy(xpath = "//div[contains(@class, 'header')]//div[contains(@class, 'cart')]")
	private WebElement goToCartButton;

	public AjioMainpage(WebDriver driver) {
		super(driver);
		waitForPageLoadComplete();
	}

	public AjioMainpage(WebDriver driver, String url) {
		super(driver);
		this.driver.get(url);
		validate();
	}

	public void validate() {
		logger.info("TITLE::" + getTitle());
	}

	public AjioMainpage loadSignUpPage() {
		gotoURL(TestConstants.WILLHIRE_SIGNUP_PAGE_URL);
		waitForPageLoadComplete();
		logger.info("SignUp Page Load Completed.");
		return new AjioMainpage(driver);
	}

	public AjioSearchResultPage searchItem(String item) {
		clearAndType(searchTextBox, item);
		click(searchButton);
		return new AjioSearchResultPage(driver);
	}

	public AjioCartPage goToCart() {
		click(goToCartButton);
		return new AjioCartPage(driver);
	}

}
