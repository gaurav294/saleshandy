package saleshandy.web.POJO;

public class ItemPurchaseValue {

	private double itemGST;
	private double itemCost;
	private String itemBrand;
	private String itemName;
	private String itemSize;

	public ItemPurchaseValue() {
		super();
	}

	public ItemPurchaseValue(double itemGST, double itemCost, String itemBrand, String itemName, String itemSize) {
		super();
		this.itemGST = itemGST;
		this.itemCost = itemCost;
		this.itemBrand = itemBrand;
		this.itemName = itemName;
		this.setItemSize(itemSize);
	}

	public double getItemGST() {
		return itemGST;
	}

	public void setItemGST(double itemGST) {
		this.itemGST = itemGST;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

}
