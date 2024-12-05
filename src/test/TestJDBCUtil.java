package test;

import java.sql.Connection;

import database.JDBCUtil;

public class TestJDBCUtil {
	public static void main(String[] args) {
		Connection connection = JDBCUtil.openConnection();
		System.out.println(connection);
		
		JDBCUtil.printInfo(connection);
		
		JDBCUtil.closeConnection(connection);
		System.out.println(connection);
	}

}
