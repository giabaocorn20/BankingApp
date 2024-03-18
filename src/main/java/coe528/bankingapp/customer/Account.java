package coe528.bankingapp.customer;

/**
 * This class represents a bank account.
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
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account to the specified amount.
     *
     * @param newBalance the new balance of the account
     */
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    /**
     * Checks if the account balance is non-negative.
     *
     * @return true if the account balance is non-negative, false otherwise
     */
    public boolean repOk() {
        return balance >= 0;
    }

    /**
     * Returns a string representation of the account.
     *
     * @return a string representation of the account
     */
    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}