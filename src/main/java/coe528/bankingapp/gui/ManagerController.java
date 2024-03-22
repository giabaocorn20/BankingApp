package coe528.bankingapp.gui;

import coe528.bankingapp.customer.Customer;
import coe528.bankingapp.data.FileManager;
import coe528.bankingapp.manager.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the controller for the Manager in the banking application.
 * It includes properties and methods for handling manager actions such as adding and deleting customers.
 * This class is mutable as it allows changing the state of the application based on the manager's actions.

 * Abstraction Function:
 * A ManagerController is represented by a manager who can add and delete customers, which are used for managing the banking application.

 * Representation Invariant:
 * The 'manager' field must be non-null.
 */
public class ManagerController {
    // The manager of the banking application
    private static Manager manager;

    // The FXML annotated UI components
    @FXML
    private Button refreshButton;
    @FXML
    private TableColumn<Customer, Void> actionColumn;
    @FXML
    private TextField usernameField;
    @FXML
    private  TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer, String> passwordColumn;
    @FXML
    private TableColumn<Customer, Double> balanceColumn;

    /**
     * Default constructor for the ManagerController class.
     */
    public ManagerController() {}

    /**
     * Initializes the Manager GUI.
     *
     * @effects sets up the customer table and refreshes the customer list
     */
    @FXML
    public void initialize() {
        // Set up the customer table
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username")); // The username column
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password")); // The password column
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance")); // The balance column
        actionColumn.setCellFactory(param -> new TableCell<>() { // The action column
            private final Button deleteButton = new Button("Delete"); // The delete button

            { // Set the action for the delete button
                deleteButton.setOnAction(event -> deleteCustomer(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) { // Update the item
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton); // Set the graphic to the delete button if the cell is not empty
            }

        });

        refreshCustomerList(); // Refresh the customer list
    }

    /**
     * Sets the manager for this controller.
     *
     * @param manager The manager to be set
     * @modifies this
     * @effects sets the manager of the controller to the specified manager
     */
    public static void setManager(Manager manager) {
        ManagerController.manager = manager;
    }


    /**
     * Refreshes the list of customers in the Manager GUI.
     * Idea: https://stackoverflow.com/questions/37559584/how-to-add-dynamic-columns-and-rows-to-tableview-in-java-fxml
     * @modifies this
     * @effects updates the list of customers in the Manager GUI
     */
    @FXML
    public void refreshCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList(); // The list of customers to be displayed
        FileManager fileManager = new FileManager();
        List<String> customerFiles = fileManager.getAllFilesInDirectory(); // The list of customer files in the directory

        for (String filename : customerFiles) { // Iterate through the customer files
            String customerData = fileManager.readFromFile(filename); // Read the customer data from the file
            String[] data = customerData.split("\n"); // Split the data into an array
            String username = data[0].split(": ")[1]; // Get the username
            String password = data[1].split(": ")[1]; // Get the password
            double balance = Double.parseDouble(data[2].split(": ")[1]); // Get the balance

            Customer customer = new Customer(username, password, balance); // Create a new customer
            customer.getCustomerLevel(); // Get the customer level

            customerList.add(customer); // Add the customer to the list
        }

        customerTable.setItems(customerList); // Set the items in the customer table
    }

    /**
     * Handles the event when the "Add Customer" button is clicked.
     *
     * @throws IOException if the view cannot be switched
     * @modifies this
     * @effects opens a dialog for the manager to enter the new customer's information
     */
    public void handleAddCustomerButtonClick() throws IOException {
        AddNewCustomerController.setManager(manager); // Set the manager for the add customer controller
        App.setRoot("addNewCustomer"); // Set the root to the add customer view
    }

    /**
     * Deletes a customer from the manager's list of customers.
     *
     * @param customer the customer to be deleted
     * @modifies this
     * @effects removes the customer from the manager's list of customers and refreshes the customer list
     */
    private void deleteCustomer(Customer customer) {
        manager.removeCustomer(customer.getUsername()); // Remove the customer from the manager's list of customers
        refreshCustomerList(); // Refresh the customer list
    }

    /**
     * Handles the event when the "Logout" button is clicked.
     *
     * @throws IOException if the view cannot be switched
     * @modifies this
     * @effects logs out the manager and switches the view to the login view
     */
    public void handleLogoutButtonClick() throws IOException {
        manager.logout(); // Log out the manager
        App.setRoot("login"); // Set the root to the login view
    }

    /**
     * Returns a string representation of the ManagerController.
     *
     * @effects returns a string that represents the ManagerController
     * @return a string representation of the ManagerController
     */
    @Override
    public String toString() {
        return "ManagerController with manager: " + manager.getUsername();
    }

    /**
     * Checks the representation invariant of the ManagerController.
     *
     * @return true if the 'manager' field is non-null, false otherwise
     * @effects returns a boolean indicating if the 'manager' field is non-null
     */
    public boolean repOk() {
        return manager != null;
    }
}