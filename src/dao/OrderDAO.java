package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Order;

public class OrderDAO implements DAOInterface<Order> {
	
	public static OrderDAO getInstance() {
		return new OrderDAO();
	}

	@Override
	public int add(Order t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO orders (id, customer_id, order_status, created_at, updated_at)"+
						" VALUE ("+
						t.getId() +" , "+
						t.getCustomer_id() +" , '"+
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
	public int update(Order t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE orders "+
						" SET " +
						" customer_id =" + t.getCustomer_id()+""+
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
	public int delete(Order t) {
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
	public ArrayList<Order> selectAll() {
		ArrayList<Order> result = new ArrayList<Order>();
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
				String order_status = rs.getString("order_status");
				
				Order order = new Order( id, customer_id, order_status);
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
	public Order selectById(Order t) {
		Order result = null;
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
				String order_status = rs.getString("order_status");
				
				Order order = new Order( id, customer_id, order_status);
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
	public ArrayList<Order> selectByCondition(String condition) {
		ArrayList<Order> result = new ArrayList<Order>();
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
				String order_status = rs.getString("order_status");
				
				Order order = new Order( id, customer_id, order_status);
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