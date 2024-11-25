package controller;

import model.Product;
import model.Customer;
import model.Order;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BackendManager {
    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;

    public BackendManager() {
        products = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();

        // Create sample users and customers
        User adminUser = new User("admin", "password123", "Manager");  // Manager role
        Customer adminCustomer = new Customer(1, "Admin User", "admin@example.com", "123 Admin St", adminUser);
        customers.add(adminCustomer);

        // Create a normal customer
        User user1 = new User("user1", "password1", "Customer");  // Customer role
        Customer customer1 = new Customer(2, "John Doe", "john@example.com", "456 Main St", user1);
        customers.add(customer1);
    }

    // Add Product
    public void addProduct(Product product) {
        products.add(product);
    }

    // Add Customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Create Order
    public void createOrder(Customer customer, List<Product> products) {
        Order order = new Order(orders.size() + 1, customer, products);
        orders.add(order);
    }

    // Authenticate User
    public User authenticateUser(String username, String password) {
        for (Customer customer : customers) {
            User user = customer.getUser();
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;  // Return the authenticated user
            }
        }
        return null;  // Return null if no match found
    }


    public List<Product> getAllProducts() {
        return products;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
