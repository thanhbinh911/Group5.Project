package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.OneOrder;

public class OneOrderDAO implements DAOInterface<OneOrder> {
	
	public static OneOrderDAO getInstance() {
		return new OneOrderDAO();
	}

	@Override
	public int add(OneOrder t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO orders (id, customer_id, product_id, quanity, order_status, created_at, updated_at)"+
						" VALUE ("+
						t.getId() +" , "+
						t.getCustomer_id() +" , "+
						t.getProduct_id() +" , "+
						t.getQuantity() +" , '"+
						t.getOrder_status() +"' , "+
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
	public int update(OneOrder t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE orders "+
						" SET " +
						" customer_id =" + t.getCustomer_id()+""+
						" product_id =" + t.getProduct_id()+""+
						" quanity =" + t.getQuantity()+""+
						" , order_status =" + t.getOrder_status()+""+
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
	public int delete(OneOrder t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from orders "+
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
	public ArrayList<OneOrder> selectAll() {
		ArrayList<OneOrder> result = new ArrayList<OneOrder>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM orders";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				String order_status = rs.getString("order_status");
				
				OneOrder order = new OneOrder( id, customer_id, product_id, quantity, order_status);
				result.add(order);
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
	public OneOrder selectById(OneOrder t) {
		OneOrder result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM orders where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				String order_status = rs.getString("order_status");
				
				OneOrder order = new OneOrder( id, customer_id, product_id, quantity, order_status);
				result = order;
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
	public ArrayList<OneOrder> selectByCondition(String condition) {
		ArrayList<OneOrder> result = new ArrayList<OneOrder>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM orders where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int customer_id = rs.getInt("customer_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				String order_status = rs.getString("order_status");
				
				OneOrder order = new OneOrder( id, customer_id, product_id, quantity, order_status);
				result.add(order);
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