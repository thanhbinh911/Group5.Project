package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Cart;

public class CartDAO implements DAOInterface<Cart> {
	
	public static CartDAO getInstance() {
		return new CartDAO();
	}

	@Override
	public int add(Cart t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO cart (id, customer_id, product_id, quantity)"+
						" VALUE ("+
						t.getId() +" , "+
						t.getCustomer_id() +" , "+
						t.getProduct_id() +" , "+
						t.getQuantity() +")";

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
	public int update(Cart t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE cart "+
						" SET " +
						" customer_id =" + t.getCustomer_id()+""+
						" , product_id =" + t.getProduct_id()+""+
						" , quantity =" + t.getQuantity()+""+
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
	public int delete(Cart t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from cart "+
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
	public ArrayList<Cart> selectAll() {
		ArrayList<Cart> result = new ArrayList<Cart>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM cart";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				Cart cart = new Cart( id, customer_id, product_id, quantity);
				result.add(cart);
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
	public Cart selectById(Cart t) {
		Cart result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM cart where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				Cart cart = new Cart( id, customer_id, product_id, quantity);
				result = cart;
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
	public ArrayList<Cart> selectByCondition(String condition) {
		ArrayList<Cart> result = new ArrayList<Cart>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM cart where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				Cart cart = new Cart( id, customer_id, product_id, quantity);
				result.add(cart);
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