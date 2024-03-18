package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import coe528.bankingapp.manager.Manager;
import coe528.bankingapp.data.FileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

/**
 * This class is the controller for the authentication view of the banking application.
 * It handles the login functionality for both customers and the manager.
 */
public class AuthenticateController {
    // The manager of the banking application
    private static final Manager manager;
    // The FileManager for the banking application
    private static final FileManager fileManager;

    // The TextField for the username input in the authentication view.
    @FXML
    private TextField usernameField;

    // The TextField for the password input in the authentication view.
    @FXML
    private TextField passwordField;

    /**
     * Static initializer block to initialize the manager and fileManager objects.
     */
    static {
        manager = new Manager("admin", "admin"); // Create a new manager with the default username and password
        fileManager = new FileManager(); // Create a new FileManager
    }

    /**
     * Default constructor for the AuthenticateController.
     */
    public AuthenticateController() {
    }

    /**
     * Handles the login button click event.
     * It checks if the entered username and password match a customer or the manager.
     * If a match is found, it switches to the corresponding view.
     * @throws IOException if there is an error switching views
     */
    public void handleLoginButtonClick() throws IOException {
        String username = usernameField.getText(); // Get the username from the username field
        String password = passwordField.getText(); // Get the password from the password field

        // Check if the entered username and password match the manager's credentials
        if ("admin".equals(username)) {
            manager.login(username, password); // Login as the manager
            if (manager.isLoggedIn()) { // If the manager is logged in
                System.out.println("Logged in as manager"); // Print a message to the console
                switchToManagerView();  // Switch to the manager view
                return;
            }
        }

        // Get all the customer files
        List<String> customerFiles = fileManager.getAllFilesInDirectory();
        for (String filename : customerFiles) { // For each customer file
            String customerData = fileManager.readFromFile(filename); // Read the customer data from the file
            String[] data = customerData.split("\n"); // Split the data by newline
            String usernameFromFile = data[0].split(": ")[1]; // Get the username from the file

            // Check if the entered username matches a customer's username
            if (username.equals(usernameFromFile)) {
                Customer customer = manager.getCustomer(usernameFromFile); // Get the customer from the manager
                if (customer == null) { // If the customer does not exist
                    throw new IllegalStateException("Customer does not exist"); // Throw an IllegalStateException
                }
                customer.login(username, password); // Login as the customer
                if (customer.isLoggedIn()) { // If the customer is logged in
                    CustomerController.setCustomer(customer); // Set the customer
                    switchToCustomerView(); // Switch to the customer view
                    return;
                }
            }
        }
    }

    /**
     * Switches the view to the manager view.
     * @throws IOException if there is an error switching views
     */
    public void switchToManagerView() throws IOException {
        ManagerController.setManager(manager); // Set the manager
        App.setRoot("managerView"); // Switch to the manager view
    }

    /**
     * Switches the view to the customer view.
     * @throws IOException if there is an error switching views
     */
    public void switchToCustomerView() throws IOException {
        App.setRoot("customerView"); // Switch to the customer view
    }
}