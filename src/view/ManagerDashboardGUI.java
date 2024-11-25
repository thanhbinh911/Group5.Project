package view;

import controller.BackendManager;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardGUI extends JFrame {
    private BackendManager backendManager;

    public ManagerDashboardGUI(BackendManager backendManager) {
        this.backendManager = backendManager;

        setTitle("Manager Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Example content for manager
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Manager Dashboard!");
        panel.add(label, BorderLayout.CENTER);

        JButton manageProductsButton = new JButton("Manage Products");
        // Add functionality to manage products, customers, etc.
        panel.add(manageProductsButton, BorderLayout.SOUTH);

        add(panel);
    }
}
