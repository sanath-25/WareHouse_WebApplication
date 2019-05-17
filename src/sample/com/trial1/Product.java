package sample.com.trial1;

public class Product {
	private int ProductID;
	private String ProductName;
	private int UserID;
	private String Weight;
	private double UnitPrice;
	private int Quantity;
	private	String SizeMeasurement;
	private int WarehouseID;
	private int CategoryID;
	private String ProductColor;
	private String WebLink;
	
	
	public String getProductColor() {
		return ProductColor;
	}
	public void setProductColor(String productColor) {
		ProductColor = productColor;
	}
	public String getWebLink() {
		return WebLink;
	}
	public void setWebLink(String webLink) {
		WebLink = webLink;
	}
	
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}
	public double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getSizeMeasurement() {
		return SizeMeasurement;
	}
	public void setSizeMeasurement(String sizeMeasurement) {
		SizeMeasurement = sizeMeasurement;
	}
	public int getWarehouseID() {
		return WarehouseID;
	}
	public void setWarehouseID(int warehouseID) {
		WarehouseID = warehouseID;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

public Product() {
	this.ProductID=0;
	this.ProductName = "default";
	UserID = 0;
	Weight = "size";
	UnitPrice =0;
	this.Quantity = 0 ;
	SizeMeasurement = "default";
	WarehouseID = 0 ;
	CategoryID = 0;
	ProductColor = "default";
	WebLink = "http";
}
public Product(String na,String si,double up, int q, String size) {
	ProductName = na;
	Weight = si;
	UnitPrice = up;
	Quantity =q;
	SizeMeasurement = size;
}
}
