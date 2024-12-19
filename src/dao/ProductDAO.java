package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Product;

public class ProductDAO implements DAOInterface<Product> {
	
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	@Override
	public int add(Product t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO products (id, product_name, price, product_status, quantity, product_description, created_at, updated_at)"+
						" VALUE ("+
						t.getId() +" , '"+
						t.getProduct_name() +"' , "+
						t.getPrice() +" , '"+
						t.getProduct_status() +"' , "+ 
						t.getQuantity() +" , '"+
						t.getProduct_description() +"' , '"+ 
						"sysdate() , " +
						"sysdate()" +")";
			int result = st.executeUpdate(sql);
			//Print
			System.out.println("Executed: "+ sql);
			System.out.println(result + " value has been changed");
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(Product t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE products "+
						" SET " +
						" product_name ='" + t.getProduct_name()+"'"+
						" , price =" + t.getPrice()+""+
						" , product_status ='" + t.getProduct_status()+"'"+
						" , quantity =" + t.getQuantity()+""+
						" , product_description ='" + t.getProduct_description()+"'"+
						" , updated_at = sysdate()"+
						"  WHERE id ='" + t.getId() + "\'";
			int result = st.executeUpdate(sql);
			//Print
			System.out.println("Executed: "+ sql);
			System.out.println(result + " value has been changed");
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(Product t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from products "+
						" WHERE id ='" + t.getId() + "\'";
			int result = st.executeUpdate(sql);
			//Print
			System.out.println("Executed: "+ sql);
			System.out.println(result + " value has been changed");
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Product> selectAll() {
		ArrayList<Product> result = new ArrayList<Product>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM products";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String product_name = rs.getString("product_name");
				int price = rs.getInt("price");
				String product_status = rs.getString("product_status");
				int quantity = rs.getInt("quantity");
				String product_description = rs.getString("product_description");
				
				Product product = new Product(id, product_name, price, product_status, quantity, product_description);
				result.add(product);
			}
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Product selectById(Product t) {
		Product result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM products where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String product_name = rs.getString("product_name");
				int price = rs.getInt("price");
				String product_status = rs.getString("product_status");
				int quantity = rs.getInt("quantity");
				String product_description = rs.getString("product_description");
				
				Product product = new Product(id, product_name, price, product_status, quantity, product_description);
				result = product;
			}
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Product> selectByCondition(String condition) {
		ArrayList<Product> result = new ArrayList<Product>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM products where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String product_name = rs.getString("product_name");
				int price = rs.getInt("price");
				String product_status = rs.getString("product_status");
				int quantity = rs.getInt("quantity");
				String product_description = rs.getString("product_description");
				
				Product product = new Product(id, product_name, price, product_status, quantity, product_description);
				result.add(product);
			}
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}