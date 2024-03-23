package coe528.bankingapp.customer;

/**
 * Overview: The CustomerLevel class is a mutable class that represents the level of a customer in a banking application.
 * The level is determined by the balance in the customer's account. The level can be one of three types: Silver, Gold, or Platinum.
 * Each level has a different fee associated with it. The state pattern is used to encapsulate the behavior associated with each level.
 *
 * The abstraction function is:
 * AF(c) = a customer level where c.state represents the current level and fee of the customer, and c.account represents the account associated with this customer level
 *
 * The rep invariant is:
 * RI(c) = c.account != null && c.state != null && c.state.getFee() >= 0
 */
public class CustomerLevel {
    private CustomerLevelState state;  // The current state of the customer level
    private final Account account;  // The account associated with this customer level

    /**
     * Constructs a new CustomerLevel object.
     * The initial state is determined based on the balance in the account.
     *
     * @param account The account associated with this customer level
     * @effects Initializes this with a new CustomerLevel object
     * @modifies this
     * @requires account != null
     */
    public CustomerLevel(Account account) {
        this.account = account;
        determineLevelAndFee();
    }

    /**
     * Determines the level and fee based on the balance in the account.
     * If the balance is less than 10,000, the level is Silver and the fee is 20.
     * If the balance is between 10,000 and 20,000, the level is Gold and the fee is 10.
     * If the balance is greater than or equal to 20,000, the level is Platinum and the fee is 0.
     *
     * @effects Sets the state of this based on the balance in the account
     * @modifies this
     */
    private void determineLevelAndFee() {
        if (account.getBalance() < 10000) {
            state = new SilverLevelState();
        } else if (account.getBalance() >= 10000 && account.getBalance() < 20000) {
            state = new GoldLevelState();
        } else if (account.getBalance() >= 20000) {
            state = new PlatinumLevelState();
        }
    }

    /**
     * Returns the level of the customer.
     * The level is determined by the current state.
     *
     * @return The level of the customer
     * @effects Returns the level of the customer
     */
    public String getLevel() {
        determineLevelAndFee();
        return state.getLevel();
    }

    /**
     * Returns the fee associated with the customer's level.
     * The fee is determined by the current state.
     *
     * @return The fee associated with the customer's level
     * @effects Returns the fee associated with the customer's level
     */
    public double getFee() {
        return state.getFee();
    }

    /**
     * Returns a string representation of the customer level.
     * The string includes the level and the fee.
     *
     * @return A string representation of the customer level
     * @effects Returns a string that represents the customer level
     */
    @Override
    public String toString() {
        return "Customer Level: " + getLevel() + ", Fee: $" + getFee();
    }

    /**
     * Checks the integrity of the CustomerLevel object.
     * The object is considered valid if the account is valid, the state is not null, and the fee is non-negative.
     *
     * @return true if the object is valid, false otherwise
     * @effects Returns true if the rep invariant holds for this; otherwise returns false
     */
    public boolean repOk() {
        return account.repOk() && state != null && getFee() >= 0;
    }
}