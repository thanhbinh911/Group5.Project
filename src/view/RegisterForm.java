package  view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backToLoginButton;
    private Connection connection;

    public RegisterForm(JFrame frame, Connection connection) {
        this.connection = connection;

        setLayout(new GridLayout(3, 2));

        // Create components for username and password
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        backToLoginButton = new JButton("Back to Login");

        // Add components to the panel
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerButton);
        add(backToLoginButton);

        // Add action listeners for buttons
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isRegistered = registerUser(username, password);
                if (!isRegistered) {
                    JOptionPane.showMessageDialog(frame, "Username already exists or error during registration. Please try again.");
                    usernameField.setText("");
                    passwordField.setText("");
                    usernameField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(frame, "User registered successfully!");
                    // Switch to the LoginForm
                    LoginForm loginForm = new LoginForm(frame);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(loginForm);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go back to LoginForm
                LoginForm loginForm = new LoginForm(frame);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(loginForm);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    // Method to register a new user in the database
    private boolean registerUser(String username, String password) {
        String sql = "INSERT INTO customers (user_name, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);  // Hash password in a real-world app!
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;  // Return true if insertion is successful
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Return false if there's an error (e.g., username already exists)
        }
    }
}
