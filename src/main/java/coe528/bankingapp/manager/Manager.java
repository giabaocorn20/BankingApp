package coe528.bankingapp.manager;

import coe528.bankingapp.data.FileManager;
import coe528.bankingapp.templates.AbstractUser;
import coe528.bankingapp.customer.Customer;
import java.util.List;
import java.util.ArrayList;

/**
 * Overview: Manager is a mutable class that represents a manager in the banking application.
 * A Manager has a list of customers and can add or remove customers.
 *
 * Abstraction Function:
 * Represents a manager in the banking application as a Manager object where the list of customers is represented by the 'customers' field.

 * Representation Invariant:
 * The 'customers' field must always be non-null (customers != null).
 */
public class Manager extends AbstractUser {
    // The FileManager instance for handling file operations related to the manager
    private final FileManager fileManager;

    // The role of the user
    private static final String ROLE = "manager";
    // The list of customers of the manager
    private final List<Customer> customers;

    /**
     * Constructs a new Manager with the specified username and password.
     * It also writes the manager's details to a file and loads all customers from the customer directory.
     *
     * @param username the username of the manager
     * @param password the password of the manager
     * @requires username != null && password != null
     * @modifies this
     * @effects creates a new Manager object with the specified username and password, writes the manager's details to a file, and loads all customers from the customer directory
     */
    public Manager(String username, String password) {
        super(username, password);
        fileManager = new FileManager(); // Create a new FileManager instance
        customers = new ArrayList<>(); // Create a new list of customers

        // Write the manager's details to a file
        fileManager.writeToFile("admin.txt", "Username: " + username + "\nPassword: " + password + "\nRole: " + ROLE);

        // Load all customers from the customer directory
        List<String> customerFiles = fileManager.getAllFilesInDirectory();
        for (String filename : customerFiles) { // For each customer file
            String customerData = fileManager.readFromFile(filename); // Read the customer data from the file
            String[] data = customerData.split("\n"); // Split the data by newline
            String usernameFromFile = data[0].split(": ")[1]; // Get the username from the file
            String passwordFromFile = data[1].split(": ")[1];  // Get the password from the file
            double balance = Double.parseDouble(data[2].split(": ")[1]); // Get the balance from the file
            Customer customer = new Customer(usernameFromFile, passwordFromFile, balance); // Create a new customer
            customers.add(customer); // Add the customer to the list
        }
    }

    /**
     * Adds the specified customer to the manager's list of customers.
     * It also writes the customer's details to a file.
     *
     * @param customer the customer to add
     * @requires customer != null
     * @modifies this
     * @effects adds a new customer to the list of customers and writes the customer's details to a file
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        String customerData = "Username: " + customer.getUsername() + "\n" +
                "Password: " + customer.getPassword() + "\n" +
                "Balance: " + customer.getBalance() + "\n" +
                "Level: " + customer.getCustomerLevel();
        fileManager.writeToFile(customer.getUsername() + ".txt", customerData);
    }

    /**
     * Removes the customer with the specified username from the manager's list of customers.
     * It also deletes the file containing the customer's details.
     *
     * @param usernameToRemove the username of the customer to remove
     * @requires usernameToRemove != null
     * @modifies this
     * @effects removes the customer with the specified username from the list of customers and deletes the file containing the customer's details
     */
    public void removeCustomer(String usernameToRemove) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(usernameToRemove)) {
                customers.remove(customer);
                break;
            }
        }
        fileManager.deleteFile(usernameToRemove + ".txt");
    }
    /**
     * Returns the customer with the specified username.
     *
     * @param username the username of the customer
     * @requires username != null
     * @effects returns the customer with the specified username, or null if no such customer exists
     * @return the customer with the specified username, or null if no such customer exists
     */
    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }
    /**
     * Returns a string representation of the Manager.
     *
     * @return a string representation of the Manager
     * @effects returns a string that represents the Manager
     */
    @Override
    public String toString() {
        return "Manager with " + customers.size() + " customers.";
    }
    /**
     * Checks if the 'customers' field is non-null.
     *
     * @return true if the 'customers' field is non-null, false otherwise
     * @effects returns a boolean indicating if the 'customers' field is non-null
     */
    public boolean repOk() {
        return customers != null;
    }
}