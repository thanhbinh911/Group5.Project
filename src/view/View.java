package view;

import controller.BackendManager;
import javax.swing.*;

public class View extends JFrame {
    private BackendManager backendManager;

    public View(BackendManager backendManager) {
        this.backendManager = backendManager;

        // Initialize the main GUI with login
        setTitle("E-Commerce System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Start with the login interface
        showLogin();
    }

    private void showLogin() {
        LoginGUI loginGUI = new LoginGUI(backendManager);
        loginGUI.setVisible(true);

        // Once login is successful, dispose of the login GUI and open the main interface
        loginGUI.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                // Decide next action based on the user's role
                if (backendManager.getCurrentUser() != null) {
                    if (backendManager.getCurrentUser().getRole().equals("Manager")) {
                        new ManagerDashboardGUI(backendManager).setVisible(true);
                    } else if (backendManager.getCurrentUser().getRole().equals("Customer")) {
                        new ShoppingAppGUI(backendManager).setVisible(true);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        BackendManager backendManager = new BackendManager();
        SwingUtilities.invokeLater(() -> {
        	View app = new View(backendManager);
        	app.setVisible(true);
        	app.showLogin();
        });
    }
}
