package saleshandy.web.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AjioCartPage extends AjioMainpage {

	@FindBy(css = ".card-section.row div.net-price.best-price-strip")
	private List<WebElement> bagItems;

	@FindBy(xpath = "//section[@id='estimatedGst']//span[@class='price-value']")
	private WebElement applicableGST;

	@FindBy(css = "#orderTotal span.price-value")
	private WebElement orderTotal;

	public AjioCartPage(WebDriver driver) {
		super(driver);
		waitForPageLoadComplete();
	}

	public AjioCartPage(WebDriver driver, String url) {
		super(driver);
		this.driver.get(url);
		validate();
	}

	public void validate() {
		logger.info("TITLE::" + getTitle());
	}

	public String getOrderTotal() {
		return orderTotal.getText().trim();
	}

	public String getApplicableGST() {
		return (applicableGST.getText().trim());
	}

	public String[] getCostOfItemsAddedToBag() {
		String[] bagItemsCost = new String[bagItems.size()];
		int i = 0;
		for (WebElement item : bagItems) {
			bagItemsCost[i] = item.getText();
			i++;
		}
		return bagItemsCost;
	}

}
