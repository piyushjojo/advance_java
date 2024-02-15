package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Product;

public interface IProductDao {
	String addNewProduct(Product p) throws SQLException;
	List<Product> getAllProducts() throws SQLException;
}
