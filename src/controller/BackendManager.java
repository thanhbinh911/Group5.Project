package controller;

import model.Product;
import model.Customer;
import model.Order;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BackendManager {
    private List<Product> products;
    private List<User> users;
    private List<Order> orders;

    public BackendManager() {
        products = new ArrayList<>();
        users = new ArrayList<>();
        orders = new ArrayList<>();

        // Create sample users and customers
        User adminUser = new User(1,"admin", "password123", "Manager");  // Manager role
        users.add(adminUser);
        // Create a normal customer
        User user1 = new User(1, "user1", "password1", "Customer");  // Customer role
        users.add(user1);
    }

    // Add Product
    //public void addProduct(Product product) {
      //.add(product);
    //}

    // Add Customer
    //public void addCustomer(Customer customer) {
    //    customers.add(customer);
    //}

    // Create Order
    //public void createOrder(Customer customer, List<Product> products) {
    //    Order order = new Order(orders.size() + 1, customer, products);
    //    orders.add(order);
    //}

    // Authenticate User
    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;  // Return the authenticated user
            }
        }
        return null;  // Return null if no match found
    }


    public List<Product> getAllProducts() {
        return products;
    }

    //public List<Customer> getAllCustomers() {
    //    return customers;
    //}

    public List<Order> getAllOrders() {
        return orders;
    }
}
