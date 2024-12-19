package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Customer;

public class CustomerDAO implements DAOInterface<Customer> {
	
	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}

	@Override
	public int add(Customer t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO customers (id, user_name, full_name, phone_number, email, password, address, created_at, updated_at)"+
						" VALUE ("+
						t.getId() +" , '"+
						t.getUser_name() +"' , '"+
						t.getFull_name() +"' , '"+ 
						t.getPhone_number() +"' , '"+
						t.getEmail() +"' , '"+ 
						t.getPassword() +"' , '"+ 
						t.getAddress() +"' , "+
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
	public int update(Customer t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE customers "+
						" SET " +
						" user_name ='" + t.getUser_name()+"'"+
						" , full_name ='" + t.getFull_name()+"'"+
						" , phone_number ='" + t.getPhone_number()+"'"+
						" , email ='" + t.getEmail()+"'"+
						" , password ='" + t.getPassword()+"'"+
						" , address ='" + t.getAddress()+"'"+
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
	public int delete(Customer t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from customers "+
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
	public ArrayList<Customer> selectAll() {
		ArrayList<Customer> result = new ArrayList<Customer>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM customers";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String full_name = rs.getString("full_name");
				String phone_number = rs.getString("phone_number");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String address = rs.getString("address");
				
				Customer customer = new Customer(id,user_name,full_name,phone_number,email,password,address);
				result.add(customer);
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
	public Customer selectById(Customer t) {
		Customer result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM customers where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String full_name = rs.getString("full_name");
				String phone_number = rs.getString("phone_number");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String address = rs.getString("address");
				
				Customer customer = new Customer(id,user_name,full_name,phone_number,email,password,address);
				result = customer;
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
	public ArrayList<Customer> selectByCondition(String condition) {
		ArrayList<Customer> result = new ArrayList<Customer>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM customers where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String user_name = rs.getString("user_name");
				String full_name = rs.getString("full_name");
				String phone_number = rs.getString("phone_number");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String address = rs.getString("address");
				
				Customer customer = new Customer(id,user_name,full_name,phone_number,email,password,address);
				result.add(customer);
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