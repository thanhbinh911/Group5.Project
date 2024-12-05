package dao;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import model.User;
import database.JDBCUtil;

public class UserDAO implements DAOInterface<User>{

	public static UserDAO getInstance() {
		return new UserDAO();
	}

	@Override
	public int add(User t) {
		try {
			//Connect to DB
			Connection con = JDBCUtil.openConnection();
			//Statement
			Statement st = con.createStatement();
			//execute SQL query
			String sql = "INSERT INTO users (username, uPassword, uRole)"+
						" VALUE ('"+t.getUsername()+"' , '"+t.getPassword()+"' , '"+ t.getRole()+"')";
			int result = st.executeUpdate(sql);
			//Print
			System.out.println("Executed: "+ sql);
			System.out.println(result + " value(s) has been changed");
			//Close connection
			JDBCUtil.closeConnection(con);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectById(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
