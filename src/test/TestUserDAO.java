package test;

import dao.UserDAO;
import model.User;

public class TestUserDAO {
	public static void main(String[] args) {
		User user1 = new User("binh","12345");
		User user2 = new User("tlinh","12345");
		User user3 = new User("anh","12345");
		User user4 = new User("duong","12345");
		
		UserDAO.getInstance().add(user1);
		UserDAO.getInstance().add(user2);
		UserDAO.getInstance().add(user3);
		UserDAO.getInstance().add(user4);
	}

}
