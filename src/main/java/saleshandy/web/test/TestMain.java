package saleshandy.web.test;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;

import saleshandy.web.POJO.ItemPurchaseValue;
import saleshandy.web.constants.TestConstants;
import saleshandy.web.pom.AjioCartPage;
import saleshandy.web.pom.AjioItemPage;
import saleshandy.web.pom.AjioMainpage;
import saleshandy.web.pom.AjioSearchResultPage;
import saleshandy.web.utility.CommonUtils;
import saleshandy.web.utility.TakeScreenshot;
import saleshandy.web.utility.TakeScreenshotUtils;

public class TestMain extends AppTest {

	protected static Logger logger = LoggerFactory.getLogger(TestMain.class);

	public CommonUtils utils = new CommonUtils();

	public AjioMainpage ajioHomePage;
	public AjioSearchResultPage ajioSearchResultPage;
	public AjioItemPage ajioItemPage;
	public AjioCartPage ajioCartPage;

	public void takeScreenShot(String fileName) {
		WebDriver driver = getDriver();
		if (driver != null) {
			TakeScreenshot ts = new TakeScreenshotUtils(false, "", "", false);
			ts.captureScreenShot(driver, fileName);
			utils.captureFullBrowserScreenShot(fileName, driver);
		} else {
			logger.info("Couldn't take screenshot.. No driver found.");
		}

	}

	public void takeScreenShot() {
		WebDriver driver = getDriver();
		if (driver != null) {
			String fileName = null;
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			fileName = fileName + stackTraceElements[2].getMethodName() + ".png";
			TakeScreenshot ts = new TakeScreenshotUtils(false, "", "", false);
			ts.captureScreenShot(driver, fileName);
			fileName = fileName.replace(".png", "");
			utils.captureFullBrowserScreenShot(fileName, driver);
		} else {
			logger.info("Couldn't take screenshot.. No driver found.");
		}

	}

	public void loadHomePage() throws Exception {
		if (hasDriver() && ajioHomePage != null) {
			ajioHomePage = ajioHomePage.loadSignUpPage();
		}
		if (!hasDriver() || ajioHomePage == null) {
			getDriver().get(TestConstants.WILLHIRE_SIGNUP_PAGE_URL);
			ajioHomePage = new AjioMainpage(getDriver());
		}
		getDriver().manage().window().maximize();
	}

	public void afterTest() {
		stopDriver();
	}

	public void fail(String message) {
		String additionalDetails = "test failed";
		Assert.fail(message + additionalDetails);
	}

	public void fail(String message, Exception e) {
		if (e instanceof SkipException)
			throw new SkipException(e.getMessage());
		if (e instanceof UnhandledAlertException) {
			try {
				Alert a = getDriver().switchTo().alert();
				String alertMessage = a.getText();
				a.accept();
				message += " \nMessage from unhandled alert box: \n" + alertMessage;
			} catch (NoAlertPresentException ex) {
			}
		}
		fail(message + "\nMessage from Exception:\n" + ExceptionUtils.getFullStackTrace(e));
	}

	public ItemPurchaseValue getItemPurchaseValuePOJO(String itemBrand, String itemName, double itemCost,
			double itemGST, String itemSize) {
		return new ItemPurchaseValue(itemGST, itemCost, itemBrand, itemName, itemSize);
	}

}
