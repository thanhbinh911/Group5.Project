package view;

import controller.BackendManager;
import model.Product;  // Import the Product class

import javax.swing.*;
import java.awt.*;

public class ShoppingAppGUI extends JFrame {
    private BackendManager backendManager;

    public ShoppingAppGUI(BackendManager backendManager) {
        this.backendManager = backendManager;

        setTitle("Shopping App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Display products or other shopping functionalities
        // For example, showing a list of products
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 1));

        // Adding the products to the panel
        for (Product product : backendManager.getAllProducts()) {
            productPanel.add(new JLabel(product.getProduct_name()));  // Display product names
        }

        // Add the panel to the frame in a scrollable view
        add(new JScrollPane(productPanel));
    }
}
