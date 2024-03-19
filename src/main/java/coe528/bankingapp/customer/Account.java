package coe528.bankingapp.customer;

/**
 * Overview: Account is a mutable class that represents a bank account with a balance.
 * An Account has a balance that can be increased or decreased.

 * Abstraction Function:
 * Represents a bank account as an Account object where the balance of the account is represented by the 'balance' field.

 * Representation Invariant:
 * The 'balance' field must always be non-negative (balance >= 0).
 */

public class Account {
    // The current balance of the account
    private double balance;

    /**
     * Constructs a new Account with the specified initial balance.
     *
     * @param initialAmount the initial balance of the account
     */
    public Account(double initialAmount) {
        this.balance = initialAmount;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance of the account
     * @effects returns the current balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account to the specified amount.
     *
     * @param newBalance the new balance of the account
     * @requires newBalance >= 0
     * @modifies this
     * @effects updates the balance of the account
     */
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    /**
     * Checks if the account balance is non-negative.
     *
     * @return true if the account balance is non-negative, false otherwise
     * @effects returns a boolean indicating if the account balance is non-negative
     */
    public boolean repOk() {
        return balance >= 0;
    }

    /**
     * Returns a string representation of the account.
     *
     * @return a string representation of the account
     * @effects returns a string that represents the account
     */
    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}