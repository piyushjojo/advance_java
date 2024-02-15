package pojos;

import java.sql.Date;

public class Product {
	private int productId;
	private String name;
	private String category;
	private double price;
	private Date expiryDate;
	private String description;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String category, double price, Date expiryDate, String description) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.expiryDate = expiryDate;
		this.description = description;
	}
	
	public Product(int productId, String name, String category, double price, Date expiryDate, String description) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.expiryDate = expiryDate;
		this.description = description;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", expiryDate=" + expiryDate + ", description=" + description + "]";
	}
	

}
