package test;

import java.util.ArrayList;

import dao.CustomerDAO;
import model.Customer;

public class TestDAO {
	public static void main(String[] args) {
		Customer c = new Customer(1213,"1111","11","11","121","211","13");
		CustomerDAO.getInstance().delete(c);
	}
	

}
