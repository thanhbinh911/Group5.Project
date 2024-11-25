package view;

import controller.BackendManager;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private BackendManager backendManager;

    public LoginGUI(BackendManager backendManager) {
        this.backendManager = backendManager;

        // Set up GUI components
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");

        // Add components to frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user and get user object
                User user = backendManager.authenticateUser(username, password);

                if (user != null) {
                    if (user.getRole().equals("Manager")) {
                        // Open Manager Dashboard if user is a Manager
                        JOptionPane.showMessageDialog(LoginGUI.this, "Login successful as Manager!");
                        new ManagerDashboardGUI(backendManager).setVisible(true);
                    } else if (user.getRole().equals("Customer")) {
                        // Open Shopping App if user is a Customer
                        JOptionPane.showMessageDialog(LoginGUI.this, "Login successful as Customer!");
                        new ShoppingAppGUI(backendManager).setVisible(true);
                    }
                    dispose();  // Close the login window
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid credentials. Please try again.");
                }
            }
        });
    }

    public static void main(String[] args) {
        BackendManager backendManager = new BackendManager();
        new LoginGUI(backendManager).setVisible(true);
    }
}
