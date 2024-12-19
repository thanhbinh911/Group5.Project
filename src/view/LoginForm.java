package  view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import  database.JDBCUtil;

public class LoginForm extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Connection connection;

    public LoginForm(JFrame frame) {
        setLayout(new GridLayout(3, 2));

        // Create components for username and password
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Add components to the panel
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Set up database connection (single connection shared between forms)
        connection = JDBCUtil.openConnection();  // Assuming you have a Database class that handles connections

        // Add action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Keep prompting the user until they provide valid credentials
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                while (!isValidLogin(username, password)) {
                    // Invalid login, prompt the user again
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                    usernameField.setText("");  // Clear the username field
                    passwordField.setText("");  // Clear the password field
                    usernameField.requestFocus();  // Focus on the username field again
                    break;  // Exit the loop to allow the user to try again
                }
                // If the login is valid, proceed
                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    // After successful login, you can transition to the main app or other screen
                    // Example: new MainApp();
                    frame.dispose(); // Close login window
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to RegisterForm
                RegisterForm registerForm = new RegisterForm(frame, connection);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(registerForm);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    // Method to validate login credentials against the database
    private boolean isValidLogin(String username, String password) {
        String sql = "SELECT * FROM customers WHERE user_name = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // If a row is found, the login is valid
            return resultSet.next(); // Returns true if a matching record is found
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred.");
            return false;
        }
    }
}
