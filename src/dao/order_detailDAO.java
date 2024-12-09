package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.order_detail;

public class order_detailDAO implements DAOInterface<order_detail> {
	
	public static order_detailDAO getInstance() {
		return new order_detailDAO();
	}

	@Override
	public int add(order_detail t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO orders (id, order_id, product_id, quantity)"+
						" VALUE ("+
						t.getId() +" , "+
						t.getOrder_id() +" , "+
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
	public int update(order_detail t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE order_detail "+
						" SET " +
						" order_id =" + t.getOrder_id()+""+
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
	public int delete(order_detail t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from order_detail "+
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
	public ArrayList<order_detail> selectAll() {
		ArrayList<order_detail> result = new ArrayList<order_detail>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM order_detail";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int order_id = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				order_detail order_detail = new order_detail( id, order_id, product_id, quantity);
				result.add(order_detail);
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
	public order_detail selectById(order_detail t) {
		order_detail result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM order_detail where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int order_id = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				order_detail order_detail = new order_detail( id, order_id, product_id, quantity);
				result = order_detail;
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
	public ArrayList<order_detail> selectByCondition(String condition) {
		ArrayList<order_detail> result = new ArrayList<order_detail>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM order_detail where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				int order_id = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				
				order_detail order_detail = new order_detail( id, order_id, product_id, quantity);
				result.add(order_detail);
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