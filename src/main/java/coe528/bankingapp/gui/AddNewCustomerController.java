package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import coe528.bankingapp.manager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

/**
 * This class is a controller for the Add New Customer view in the banking application.
 * It handles the user interactions for adding a new customer.
 * This class is mutable as it allows adding a new customer.

 * Abstraction Function:
 * An AddNewCustomerController is represented by a usernameField, passwordField, balanceField, and a manager.
 * The usernameField, passwordField, and balanceField are used for input, and the manager is used for managing customers.

 * Representation Invariant:
 * The usernameField, passwordField, balanceField, and manager must be non-null.
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
     * Initializes the controller.
     *
     * @effects initializes the controller
     */
    public void initialize() {
    }

    /**
     * Sets the manager instance.
     *
     * @param manager the manager instance
     * @modifies this
     * @effects sets the manager instance
     */
    public static void setManager(Manager manager) {
        AddNewCustomerController.manager = manager;
    }


    /**
     * Handles the Add button click event.
     * It creates a new customer and adds it to the database.
     *
     * @throws IOException if an input or output exception occurred
     * @modifies this
     * @effects creates a new customer and adds it to the database
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
     * Handles the Cancel button click event.
     * It navigates back to the manager view.
     *
     * @throws IOException if an input or output exception occurred
     * @effects navigates back to the manager view
     */
    public void handleCancelButton() throws IOException {
        App.setRoot("managerView");
    }
    /**
     * Returns a string representation of the AddNewCustomerController.
     *
     * @effects returns a string that represents the AddNewCustomerController
     * @return a string representation of the AddNewCustomerController
     */
    @Override
    public String toString() {
        return "AddNewCustomerController with usernameField: " + usernameField.getText() + ", passwordField: " + passwordField.getText() + ", balanceField: " + balanceField.getText();
    }
    /**
     * Checks the representation invariant of the AddNewCustomerController.
     *
     * @return true if the 'usernameField', 'passwordField', 'balanceField', and 'manager' fields are non-null, false otherwise
     * @effects returns a boolean indicating if the 'usernameField', 'passwordField', 'balanceField', and 'manager' fields are non-null
     */
    public boolean repOk() {
        return usernameField != null && passwordField != null && balanceField != null && manager != null;
    }
}