package model;

import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;  // Reference to the Customer who placed the order
    private List<Product> products;  // List of products in the order

    public Order(int orderId, Customer customer, List<Product> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Method to display order details
    public String getOrderDetails() {
        StringBuilder productNames = new StringBuilder();
        for (Product product : products) {
            productNames.append(product.getProduct_name()).append(", ");
        }
        return "Order ID: " + orderId + "\n" +
                "Customer: " + customer.getFull_name() + "\n" +
                "Products: " + productNames.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer.getFull_name() +
                ", products=" + products +
                '}';
    }
}
