package saleshandy.web.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AjioItemPage extends AjioMainpage {

	@FindBy(xpath = "//div[@class='prod-content']//descendant::div[contains(@class, 'addtocart')]")
	private WebElement addToBagButton;

	@FindBy(xpath = "//div[@class='size-variant-block']")
	private WebElement selectSizeContainer;

	@FindBy(xpath = "//div[@class='prod-content']//descendant::div[contains(@class, 'addtocart')]//div[@class='btn-cart']/span[2]")
	private WebElement goToBagButton;

	@FindBy(css = ".prod-content div.prod-price-section  div.prod-sp")
	private WebElement itemCost;

	public AjioItemPage(WebDriver driver) {
		super(driver);
		waitForPageLoadComplete();
	}

	public AjioItemPage(WebDriver driver, String url) {
		super(driver);
		this.driver.get(url);
		validate();
	}

	public void validate() {
		logger.info("TITLE::" + getTitle());
	}

	public void addToBagButton() {
		click(addToBagButton);
		waitForPageLoadComplete();
		checkForGoToBagToVisible();
	}

	public void closeItemPage() {
		this.closeWindow();
		this.switchToNthWindow(0);
	}

	public void selectSize(String size) {
		WebElement selectSize = selectSizeContainer
				.findElement(By.xpath("//div[contains(@class, 'size-instock')][contains(text(),'" + size + "')]"));
		click(selectSize);
	}

	public void goToBag() {
		click(goToBagButton);
	}

	public void checkForGoToBagToVisible() {
		waitForElementToAppear(goToBagButton);
		waitForVisible(goToBagButton);
	}

	public String getItemCost() {
		return itemCost.getText();
	}
}
