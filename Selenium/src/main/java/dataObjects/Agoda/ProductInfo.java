package dataObjects.Agoda;

public class ProductInfo {
	private String name;
	private String price;
	
	public ProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductInfo(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
