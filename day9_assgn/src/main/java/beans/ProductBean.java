package beans;

import java.sql.Date;
import java.sql.SQLException;

import dao.ProductDaoImpl;
import pojos.Product;

public class ProductBean {
	private String name;
	private String category;
	private double price;
	private String expiryDate;
	private String description;
	//dependency : dao
	private ProductDaoImpl productDao;
	//def ctor auto invoked by WC upin trigger of <jsp:useBean>
	public ProductBean() throws SQLException{
		// create dao instance
		productDao=new ProductDaoImpl();
		System.out.println("product bean created ");
		
	}
	//setters n getters
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
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductDaoImpl getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}
	//Add B.L method to add new product 
	public String insertProductDetails() throws SQLException
	{
		System.out.println("product details "+name+" "+price);
		//create Product instance using the info sent by clnt --> jsp ---> jb
		Product newProduct=new Product(name, category, price, Date.valueOf(expiryDate), description);
		//invoke dao's method 
		return productDao.addNewProduct(newProduct);
	}
	
}
