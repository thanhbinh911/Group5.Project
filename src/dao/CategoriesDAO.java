package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Categories;

public class CategoriesDAO implements DAOInterface<Categories> {
	
	public static CategoriesDAO getInstance() {
		return new CategoriesDAO();
	}

	@Override
	public int add(Categories t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO categories (id, category_name)"+
						" VALUE ("+
						t.getId() +" , '"+
						t.getCategory_name() +"')";

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
	public int update(Categories t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "UPDATE categories "+
						" SET " +
						" category_name ='" + t.getCategory_name()+"'"+
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
	public int delete(Categories t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "DELETE from categories "+
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
	public ArrayList<Categories> selectAll() {
		ArrayList<Categories> result = new ArrayList<Categories>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM categories";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category_name = rs.getString("category_name");
				
				Categories category = new Categories( id, category_name);
				result.add(category);
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
	public Categories selectById(Categories t) {
		Categories result = null;
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM categories where id ="+t.getId();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category_name = rs.getString("category_name");
				
				Categories category = new Categories( id, category_name);
				result = category;
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
	public ArrayList<Categories> selectByCondition(String condition) {
		ArrayList<Categories> result = new ArrayList<Categories>();
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "SELECT * FROM categories where "+condition;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category_name = rs.getString("category_name");
				
				Categories category = new Categories( id, category_name);
				result.add(category);
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