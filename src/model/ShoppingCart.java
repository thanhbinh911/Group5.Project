package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProductToCart(Product product) {
        cartItems.add(product);
    }

    public void removeProductFromCart(Product product) {
        cartItems.remove(product);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
}
