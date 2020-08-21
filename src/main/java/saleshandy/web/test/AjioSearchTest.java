package saleshandy.web.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import saleshandy.web.POJO.ItemPurchaseValue;

public class AjioSearchTest extends TestMain {

	protected Logger logger = this.getLogger();
	List<ItemPurchaseValue> itemList = new ArrayList<ItemPurchaseValue>();

	@BeforeTest
	public void beforeTest() {
		itemList.add(getItemPurchaseValuePOJO("", "Panelled Backpack with Adjustable Strap", 0, 0, ""));
		itemList.add(getItemPurchaseValuePOJO("BAGGIT", "Brand Print Bi-Fold Wallet", 0, 0, ""));
		itemList.add(getItemPurchaseValuePOJO("AUSK", "Crew-Neck T-shirt with Contrast Stripes", 0, 0, "L"));
	}

	@Test(priority = 1, groups = { "Regression" })
	public void verifyItemAddToBagTest() throws IOException {
		try {
			loadHomePage();

			for (ItemPurchaseValue item : itemList) {
				ajioSearchResultPage = ajioHomePage.searchItem(item.getItemBrand() + " " + item.getItemName());
				if (!item.getItemBrand().isEmpty())
					ajioSearchResultPage.selectBrand(item.getItemBrand());
				ajioItemPage = ajioSearchResultPage.selectItem(item.getItemName());
				if (!item.getItemSize().isEmpty())
					ajioItemPage.selectSize(item.getItemSize());
				double itemCost = convertToDouble(ajioItemPage.getItemCost());
				item.setItemCost(itemCost);
				ajioItemPage.addToBagButton();
				ajioItemPage.closeItemPage();

				logger.info("ITEM ADDED TO BAG:: ITEM NAME - " + item.getItemBrand() + " " + item.getItemName()
						+ ":: ITEM COST - " + item.getItemCost() + ":: ITEM SIZE - " + item.getItemSize());
			}

		} catch (Exception e) {
			AssertJUnit.fail("Exception happened::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(priority = 2, groups = { "Regression" }, dependsOnMethods = { "verifyItemAddToBagTest" })
	public void verifyItemCartPage() {

		try {
			ajioCartPage = ajioSearchResultPage.goToCart();

			double gstInSummary = convertToDouble(ajioCartPage.getApplicableGST());
			double orderTotalInSummary = convertToDouble(ajioCartPage.getOrderTotal());
			double itemsTotalCost = 0;

			String[] orderedItemsCost = ajioCartPage.getCostOfItemsAddedToBag();
			for (String itemCost : orderedItemsCost) {
				itemsTotalCost += convertToDouble(itemCost);
			}

			if (itemsTotalCost != (orderTotalInSummary - gstInSummary)) {
				AssertJUnit.fail("Total is not equal.");
			}

			logger.info("TOTAL PURCHASE VALUE IS EQUAL TO THE TOTAL OF ALL ITEM'S COST.");
		} catch (Exception e) {
			AssertJUnit.fail("Exception happened::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	public double convertToDouble(String value) {
		double finalValue = 0;
		String originValue = value;

		String[] getNumber = originValue.split("[.]+");
		if (getNumber.length > 2)
			finalValue = Double.parseDouble(getNumber[getNumber.length - 1]) / 100.00;

		String gross = getNumber[1].trim();
		String[] grossSplit = gross.split("[,]+");
		int mul = 1000;
		for (int i = grossSplit.length - 1; i > -1; i--) {
			double valu = Double.parseDouble(grossSplit[i]);
			if (i == grossSplit.length - 1) {
				finalValue = finalValue + valu;
				continue;
			}
			finalValue = finalValue + (mul * valu);
			mul = mul * 100;
		}
		return finalValue;
	}
}
