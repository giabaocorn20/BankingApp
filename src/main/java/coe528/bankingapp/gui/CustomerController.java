package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * This class represents the controller for the customer in the banking application.
 * It includes properties and methods for handling customer actions such as deposit, withdraw and logout.
 * This class is mutable as it allows changing the state of the application based on the customer's actions.

 * Abstraction Function:
 * A CustomerController is represented by a customer with a customer number, balance, and customer level, which are used for displaying and updating customer information.

 * Representation Invariant:
 * The 'customer' field must be non-null.
 */
public class CustomerController {

    // The label for the customer number
    @FXML
    private Label customerNumberLabel;

    // The label for the balance
    @FXML
    private Label balanceLabel;

    // The label for the customer level
    @FXML
    private Label customerLevelLabel;

    // The text field for the amount
    @FXML
    private TextField amountField;

    // The customer of the banking application
    private static Customer customer;

    /**
     * Constructs a new CustomerController.
     */
    public CustomerController() {
    }

    /**
     * Initializes the controller and updates the customer information.
     *
     * @effects updates the customer information on the labels
     */
    @FXML
    public void initialize() {
        updateCustomerInfo(); // Update the customer information
    }

    /**
     * Updates the customer information on the labels.
     *
     * @modifies this
     * @effects updates the customer information on the labels
     */
    private void updateCustomerInfo() {
        if (customer != null) { // If the customer is not null
            customerNumberLabel.setText("Customer Number: " + customer.getCustomerNumber()); // Set the customer number label
            balanceLabel.setText("Balance: " + customer.getBalance()); // Set the balance label
            customerLevelLabel.setText("Customer Level: " + customer.getCustomerLevel()); // Set the customer level label
        }
    }

    /**
     * Sets the customer of the controller to the specified customer.
     *
     * @param customer the customer to set
     * @modifies this
     * @effects sets the customer of the controller to the specified customer
     */
    public static void setCustomer(Customer customer) {
        CustomerController.customer = customer; // Set the customer
    }

    /**
     * Handles the deposit button click event.
     *
     * @requires amountField to contain a valid double value
     * @modifies this
     * @effects deposits the amount entered in the amount field to the customer's account and updates the customer information
     */
    @FXML
    public void handleDepositButtonClick() {
        double amount = Double.parseDouble(amountField.getText()); // Get the amount from the amount field
        customer.deposit(amount); // Deposit the amount
        updateCustomerInfo();
    }


    /**
     * Handles the withdraw button click event.
     *
     * @requires amountField to contain a valid double value
     * @modifies this
     * @effects withdraws the amount entered in the amount field from the customer's account and updates the customer information
     */
    @FXML
    public void handleWithdrawButtonClick() {
        double amount = Double.parseDouble(amountField.getText()); // Get the amount from the amount field
        customer.withdraw(amount); // Withdraw the amount
        updateCustomerInfo();
    }

    /**
     * Handles the purchase button click event.
     *
     * @requires amountField to contain a valid double value
     * @modifies this
     * @effects purchase with the amount entered in the amount field from the customer's account and updates the customer information
     */
    @FXML
    public void handlePurchaseButtonClick() {
        double amount = Double.parseDouble(amountField.getText()); // Get the amount from the amount field
        customer.purchase(amount); // Withdraw the amount
        updateCustomerInfo();
    }

    /**
     * Handles the logout button click event.
     *
     * @throws IOException if the view cannot be switched
     * @modifies this
     * @effects logs out the customer and switches the view to the login view
     */
    @FXML
    public void handleLogoutButtonClick() throws IOException {
        customer.logout(); // Log out the customer
        App.setRoot("login");
    }

    /**
     * Returns a string representation of the CustomerController.
     *
     * @effects returns a string that represents the CustomerController
     * @return a string representation of the CustomerController
     */
    @Override
    public String toString() {
        return "CustomerController with customer number: " + customer.getCustomerNumber() + ", balance: " + customer.getBalance() + ", customer level: " + customer.getCustomerLevel();
    }

    /**
     * Checks the representation invariant of the CustomerController.
     *
     * @return true if the 'customer' field is non-null, false otherwise
     * @effects returns a boolean indicating if the 'customer' field is non-null
     */
    public boolean repOk() {
        return customer != null;
    }
}