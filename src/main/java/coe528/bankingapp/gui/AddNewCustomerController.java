package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import coe528.bankingapp.manager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

/**
 * This class is a controller for the Add New Customer view.
 * It handles the user interactions for adding a new customer.
 */
public class AddNewCustomerController {
    // Text field for the username input
    @FXML
    private TextField usernameField;

    // Text field for the password input
    @FXML
    private TextField passwordField;

    // Text field for the balance input
    @FXML
    private TextField balanceField;

    // Manager instance to handle the business logic
    private static Manager manager;

    /**
     * Default constructor
     */
    public AddNewCustomerController() {
    }

    /**
     * Method to initialize the controller
     */
    public void initialize() {
    }

    /**
     * Method to set the manager instance
     * @param manager The manager instance
     */
    public static void setManager(Manager manager) {
        AddNewCustomerController.manager = manager;
    }

    /**
     * Method to handle the Add button click event.
     * It creates a new customer and adds it to the database.
     * @throws IOException If an input or output exception occurred
     */
    public void handleAddButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        double balance = Double.parseDouble(balanceField.getText());

        // Add the new customer to the database
        try {
            Customer newCustomer = new Customer(username, password, balance);
            if (newCustomer.repOk()) {
                manager.addCustomer(newCustomer);
            } else {
                new Alert(AlertType.ERROR, "Invalid customer data. Please try again.").showAndWait();
            }
        } catch (NumberFormatException e) {
            new Alert(AlertType.ERROR, "Invalid balance. Please enter a numeric value.").showAndWait();
        }
        App.setRoot("managerView");
    }

    /**
     * Method to handle the Cancel button click event.
     * It navigates back to the manager view.
     * @throws IOException If an input or output exception occurred
     */
    public void handleCancelButton() throws IOException {
        App.setRoot("managerView");
    }
}