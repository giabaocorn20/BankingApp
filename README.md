# Bank Account Application

This is a simple Bank Account Application developed using Java and JavaFX for the graphical user interface (GUI). The application supports two types of users: Manager and Customer.

## Users

### Manager

There is only one manager in the application. The manager has the following capabilities:

- Login
- Logout
- Add customer
- Delete customer

The manager's username is `admin`, password is `admin`, and role is `manager`.

### Customer

There can be zero or more customers using the application. Each customer has only one bank account. A customer can:

- Login
- Logout
- Deposit money
- Withdraw money
- Get balance
- Do online purchases

Every customer has three levels: silver, gold, and platinum. The level is determined by the balance in the customer's account:

- Silver: Less than $10,000
- Gold: $10,000 or more but less than $20,000
- Platinum: $20,000 or more

A customer can do an online purchase of $50 or more using the money in their account. The fee for an online purchase varies by the customer's level:

- Silver: $20 fee
- Gold: $10 fee
- Platinum: No fee

A customer has a username, password, and role (`customer`), along with a bank account and level. The information about the customers is stored in separate files—one file per customer. The filename can have the username of the customer.

## Authentication

When a manager tries to login through the user interface, the manager’s username, password, and role are authenticated. When a customer tries to login through the user interface, the customer’s username, password, and role are authenticated using the information stored in the relevant file.

## Authority

Only the manager of the bank has the authority to add or delete a customer. When the manager adds a customer, they must create the account of the customer as well with a $100 balance in the account. When the manager deletes a customer, the associated account should get deleted as well.

## Assumptions

It is assumed that no two users can have the same username.

## GUI

The GUI of the application has been developed using JavaFX.