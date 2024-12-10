package controller;

import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ShopManager {
    private List<Product> products;
    private List<User> users;

    public ShopManager() {
        products = new ArrayList<>();
        users = new ArrayList<>();



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



    public List<Product> getAllProducts() {
        return products;
    }

    //public List<Customer> getAllCustomers() {
    //    return customers;
    //}

    //public List<Order> getAllOrders() {
     //   return orders;
    
}
