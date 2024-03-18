package coe528.bankingapp.manager;

import coe528.bankingapp.data.FileManager;
import coe528.bankingapp.templates.AbstractUser;
import coe528.bankingapp.customer.Customer;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a manager in the banking application.
 * It extends the AbstractUser class and includes additional properties and methods specific to a manager.
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
}