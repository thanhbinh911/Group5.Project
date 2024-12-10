
package view;

import javax.swing.*;

public class Main {

        public static void main(String[] args) {
            // Create the frame for the main application window
            JFrame mainFrame = new JFrame("Application");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 300);  // Adjust size as needed

            // Create a login form and add it to the frame
            LoginForm loginForm = new LoginForm(mainFrame);  // Pass reference to mainFrame to close it when needed
            mainFrame.add(loginForm);
            mainFrame.setVisible(true);
        }
    }
