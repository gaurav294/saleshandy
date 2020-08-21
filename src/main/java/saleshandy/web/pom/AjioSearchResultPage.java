package saleshandy.web.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import saleshandy.web.constants.TestConstants;

public class AjioSearchResultPage extends AjioMainpage {

	@FindBy(xpath = "//div[@id = 'products']//div[@class = 'items']//descendant::a[contains(@class, 'products-list')]")
	private WebElement searchResults;

	@FindBy(xpath = "//div[@id='modalId']//div[@class='more-popup-container']//li")
	private WebElement brandPopUpFrame;

	@FindBy(xpath = "//div[@class='modal-content']/form//button[@type='submit']")
	private WebElement brandPopUpApply;

	@FindBy(xpath = "//div[@id='verticalsizegroupformat']")
	private WebElement brandMoreLink;

	@FindBy(xpath = "//div[@id= 'facets']//span[text()='brands']//parent::div")
	private WebElement brandFilter;

	public AjioSearchResultPage(WebDriver driver) {
		super(driver);
		waitForPageLoadComplete();
	}

	public AjioSearchResultPage(WebDriver driver, String url) {
		super(driver);
		this.driver.get(url);
		validate();
	}

	public void validate() {
		logger.info("TITLE::" + getTitle());
	}

	public AjioSearchResultPage loadSignUpPage() {
		gotoURL(TestConstants.WILLHIRE_SIGNUP_PAGE_URL);
		waitForPageLoadComplete();
		logger.info("SignUp Page Load Completed.");
		return new AjioSearchResultPage(driver);
	}

	public AjioItemPage selectItem(String product) {
		WebElement item = searchResults.findElement(By.xpath("//div[@class='name'][text()='" + product + "']"));
		click(item);
		waitForPageLoadComplete();
		AjioItemPage ajioItemPage = new AjioItemPage(driver);
		ajioItemPage.switchToNthWindow(1);
		return ajioItemPage;
	}

	public void selectBrand(String brand) {
		click(brandFilter);
		WebElement we = brandFilter.findElement(By.xpath(".//following::div[@id = 'verticalsizegroupformat']"));
		scrollElementToUserView(we);
		click(we);
		WebElement brandCheckBox = brandPopUpFrame.findElement(By.xpath("//input[@value='" + brand + "']"));
		clickUsingJS(brandCheckBox);
		click(brandPopUpApply);
		waitForPageLoadComplete();
	}
}
