package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * This class represents the controller for the customer in the banking application.
 * It includes properties and methods for handling customer actions such as deposit, withdraw and logout.
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
     */
    @FXML
    public void initialize() {
        updateCustomerInfo(); // Update the customer information
    }

    /**
     * Updates the customer information on the labels.
     * This method is called to refresh the displayed information after a transaction.
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
     * This method is used to pass the customer information from the login controller to this controller.
     *
     * @param customer the customer to set
     */
    public static void setCustomer(Customer customer) {
        CustomerController.customer = customer; // Set the customer
    }

    /**
     * Handles the deposit button click event.
     * It deposits the amount entered in the amount field to the customer's account and updates the customer information.
     */
    @FXML
    public void handleDepositButtonClick() {
        double amount = Double.parseDouble(amountField.getText()); // Get the amount from the amount field
        customer.deposit(amount); // Deposit the amount
        updateCustomerInfo();
    }

    /**
     * Handles the withdraw button click event.
     * It withdraws the amount entered in the amount field from the customer's account and updates the customer information.
     */
    @FXML
    public void handleWithdrawButtonClick() {
        double amount = Double.parseDouble(amountField.getText()); // Get the amount from the amount field
        customer.withdraw(amount); // Withdraw the amount
        updateCustomerInfo();
    }

    /**
     * Handles the logout button click event.
     * It logs out the customer and switches the view to the login view.
     *
     * @throws IOException if the view cannot be switched
     */
    @FXML
    public void handleLogoutButtonClick() throws IOException {
        customer.logout(); // Log out the customer
        App.setRoot("login");
    }
}