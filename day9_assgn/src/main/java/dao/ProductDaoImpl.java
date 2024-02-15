package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Product;
import static utils.DBUtils.*;

public class ProductDaoImpl implements IProductDao {
	//state
	private Connection cn;
	private PreparedStatement pst1,pst2;
	
	//def ctor
	public ProductDaoImpl() throws SQLException{
		cn=getCn();
		//add a product
		pst1=cn.prepareStatement("insert into products values(default,?,?,?,?,?)");
		//list all products
		pst2=cn.prepareStatement("select * from products");
		System.out.println("product dao created...");
	}
	

	@Override
	public String addNewProduct(Product p) throws SQLException {
		//set IN params
		//String name, String category, double price, Date expiryDate, String description
		pst1.setString(1, p.getName());
		pst1.setString(2, p.getCategory());
		pst1.setDouble(3, p.getPrice());
		pst1.setDate(4, p.getExpiryDate());
		pst1.setString(5, p.getDescription());
		//DML :exec update
		int updateCount=pst1.executeUpdate();
		if(updateCount==1)
			return "Product added";
		return "Adding product failed !!!!!!!!!!!!";
	}

	@Override
	public List<Product> getAllProducts() throws SQLException {
		List<Product> products=new ArrayList<>();
		try(ResultSet rst=pst2.executeQuery())
		{
			while(rst.next())
				products.add(new Product(rst.getInt(1), rst.getString(2), 
						rst.getString(3),rst.getDouble(4),rst.getDate(5), rst.getString(6)));
		}
		return products;
	}
	public void cleanUp() throws SQLException
	{
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		System.out.println("product dao cleaned up");
	}

}
