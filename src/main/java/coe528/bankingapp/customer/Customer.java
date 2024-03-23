package coe528.bankingapp.customer;

import coe528.bankingapp.data.FileManager;
import coe528.bankingapp.templates.AbstractUser;

import java.util.logging.Logger;

/**
 * Overview: Customer is a mutable class that represents a customer in the banking application.
 * A Customer has a username, password, account with a balance, and a customer level.

 * Abstraction Function:
 * Represents a customer in the banking application as a Customer object where the username, password, account balance, and customer level are represented by the respective fields.

 * Representation Invariant:
 * The 'account' field must always be non-negative (account.getBalance() >= 0).
 * The 'customerLevel' field must not be null (customerLevel != null).
 */
public class Customer extends AbstractUser {
    // The role of the user
    private static final String ROLE = "Customer";
    // The level of the customer
    private final CustomerLevel customerLevel;
    // The account of the customer
    private final Account account;

    // The number of the customer
    private int customerNumber ;
    // The count of customers
    private static int customerCount = 0;
    // The file manager instance
    FileManager fileManager;
    // Logger instance

    /**
     * Constructs a new Customer with the specified username, password, and initial amount.
     * If the initial amount is negative, a warning is logged and the initial amount is set to 0.
     *
     * @param username the username of the customer
     * @param password the password of the customer
     * @param initialAmount the initial amount in the customer's account
     */
    public Customer(String username, String password, double initialAmount) {
        super(username, password);
        account = new Account(initialAmount);
        if(!account.repOk()) {
            account.setBalance(0);
        }
        customerLevel = new CustomerLevel(account);
        customerNumber = customerCount;
        customerCount++;
        fileManager = new FileManager();
    }

    /**
     * Deposits the specified amount into the customer's account.
     *
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if the amount is negative
     * @requires amount >= 0
     * @modifies this
     * @effects updates the balance of the account by adding the specified amount
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        account.setBalance(account.getBalance() + amount);

        String customerData = "Username: " + getUsername() + "\nPassword: " + getPassword() + "\nBalance: " + getBalance() + "\nLevel: " + customerLevel.getLevel();

        fileManager.writeToFile(getUsername() + ".txt", customerData);
    }

    /**
     * Withdraws the specified amount from the customer's account.
     *
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if the amount is negative
     * @throws IllegalStateException if the withdrawal would result in a negative balance
     * @requires amount > 0 and account.getBalance() - amount - customerLevel.getFee() >= 0
     * @modifies this
     * @effects updates the balance of the account by subtracting the specified amount
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        else if (account.getBalance() - amount < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        else{
            account.setBalance(account.getBalance() - amount);
            String customerData = "Username: " + getUsername() + "\n" +
                    "Password: " + getPassword() + "\n" +
                    "Balance: " + getBalance() + "\n" +
                    "Level: " + customerLevel.getLevel()+ "\n" +
                    "Customer Number: " + customerNumber + "\n" +
                    "Role: " + ROLE;
            fileManager.writeToFile(getUsername() + ".txt", customerData);
        }
    }

    /**
     * Purchases an item with the specified amount from the customer's account.
     *
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if the amount is negative
     * @throws IllegalStateException if the purchase would result in a negative balance
     * @requires amount > 0 and account.getBalance() - amount - customerLevel.getFee() >= 0
     * @modifies this
     * @effects updates the balance of the account by subtracting the specified amount and the fee
     */
    public void purchase(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        else if (account.getBalance() - amount - customerLevel.getFee() < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        else{
            account.setBalance(account.getBalance() - amount - customerLevel.getFee());
            String customerData = "Username: " + getUsername() + "\n" +
                    "Password: " + getPassword() + "\n" +
                    "Balance: " + getBalance() + "\n" +
                    "Level: " + customerLevel.getLevel()+ "\n" +
                    "Customer Number: " + customerNumber + "\n" +
                    "Role: " + ROLE;
            fileManager.writeToFile(getUsername() + ".txt", customerData);
        }
    }
    /**
     * Returns the level of the customer.
     *
     * @return the level of the customer
     */
    public String getCustomerLevel() {
        return customerLevel.getLevel();
    }

    /**
     * Returns the balance of the customer's account.
     *
     * @return the balance of the customer's account
     */
    public double getBalance() {
        return account.getBalance();
    }

    /**
     * Returns the number of the customer.
     *
     * @return the number of the customer
     */
    public int getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Logs out the customer.
     */
    public void logout() {
        super.logout();
    }

    /**
     * Returns the password of the customer.
     *
     * @return the password of the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the customer's account balance is non-negative and the customer's level is not null.
     *
     * @return true if the customer's account balance is non-negative and the customer's level is not null, false otherwise
     * @effects returns a boolean indicating if the customer's account balance is non-negative and the customer's level is not null
     */

    public boolean repOk() {
        return account.repOk() && customerLevel.getFee() >= 0 && customerLevel.getLevel() != null;
    }

    /**
     * Checks if the customer's account balance is non-negative and the customer's level is not null.
     *
     * @return true if the customer's account balance is non-negative and the customer's level is not null, false otherwise
     * @effects returns a boolean indicating if the customer's account balance is non-negative and the customer's level is not null
     */
    @Override
    public String toString() {
        return "Username: " + getUsername() + ", Customer Number: " + customerNumber + ", Balance: $" + account.getBalance();
    }

}