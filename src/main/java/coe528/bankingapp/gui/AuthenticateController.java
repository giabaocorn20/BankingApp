package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import coe528.bankingapp.manager.Manager;
import coe528.bankingapp.data.FileManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

/**
 * This class is the controller for the authentication view of the banking application.
 * It handles the login functionality for both customers and the manager.
 * This class is mutable as it allows changing the state of the application based on the login credentials.\

 * Abstraction Function:
 * An AuthenticateController is represented by a usernameField and passwordField which are used for user authentication.

 * Representation Invariant:
 * The usernameField and passwordField must be non-null.
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
    // The label field for displaying login errors
    @FXML
    private Label loginErrorLabel;


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
     *
     * @throws IOException if there is an error switching views
     * @modifies this
     * @effects changes the state of the application based on the login credentials
     */
    public void handleLoginButtonClick() throws IOException {
        loginErrorLabel.setText(""); // Clear any previous error messages

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
        // If the manager is not logged in
        if (!manager.isLoggedIn()) {
            loginErrorLabel.setText("Unable to log in. Please check your username and password.");
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
                // If the customer is not logged in
                if (!customer.isLoggedIn()) {
                    loginErrorLabel.setText("Unable to log in. Please check your username and password.");
                    return;
                }
            }
        }
    }

    /**
     * Switches the view to the manager view.
     *
     * @throws IOException if there is an error switching views
     * @modifies this
     * @effects changes the view of the application to the manager view
     */
    public void switchToManagerView() throws IOException {
        ManagerController.setManager(manager); // Set the manager
        App.setRoot("managerView"); // Switch to the manager view
    }

    /**
     * Switches the view to the customer view.
     *
     * @throws IOException if there is an error switching views
     * @modifies this
     * @effects changes the view of the application to the customer view
     */
    public void switchToCustomerView() throws IOException {
        App.setRoot("customerView"); // Switch to the customer view
    }

    /**
     * Returns a string representation of the AuthenticateController.
     *
     * @effects returns a string that represents the AuthenticateController
     * @return a string representation of the AuthenticateController
     */
    @Override
    public String toString() {
        return "AuthenticateController with usernameField: " + usernameField.getText() + ", passwordField: " + passwordField.getText();
    }

    /**
     * Checks the representation invariant of the AuthenticateController.
     *
     * @return true if the 'usernameField' and 'passwordField' fields are non-null, false otherwise
     * @effects returns a boolean indicating if the 'usernameField' and 'passwordField' fields are non-null
     */
    public boolean repOk() {
        return usernameField != null && passwordField != null;
    }
}