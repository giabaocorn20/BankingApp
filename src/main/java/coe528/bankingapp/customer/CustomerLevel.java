package coe528.bankingapp.customer;

/**
 * Overview: CustomerLevel is a mutable class that represents the level of a customer in the banking application.
 * A CustomerLevel has a level, fee, and is associated with a customer's account.

 * Abstraction Function:
 * Represents the level of a customer in the banking application as a CustomerLevel object where the level, fee, and account are represented by the respective fields.

 * Representation Invariant:
 * The 'account' field must always be non-negative (account.getBalance() >= 0).
 * The 'level' field must not be null (level != null).
 * The 'fee' field must always be non-negative (fee >= 0).
 */
public class CustomerLevel {
    // The level of the customer
    private String level;
    // The fee of the customer
    private double fee;
    // The account of the customer
    private final Account account;

    /**
     * Constructs a new CustomerLevel with the specified account.
     * It also determines the level and fee of the customer based on their account balance.
     *
     * @param account the account of the customer
     * @requires account != null
     * @modifies this
     * @effects creates a new CustomerLevel object with the specified account
     */
    public CustomerLevel(Account account) {
        this.account = account;
        determineLevelAndFee();
    }

    /**
     * Determines the level and fee of the customer based on their account balance.
     *
     * @modifies this
     * @effects updates the level and fee of the customer based on their account balance
     */
    private void determineLevelAndFee() {
        if (account.getBalance() < 10000) {
            level = "Silver";
            fee = 20;
        } else if (account.getBalance() >= 10000 && account.getBalance() < 20000) {
            level = "Gold";
            fee = 10;
        } else if (account.getBalance() >= 20000) {
            level = "Platinum";
            fee = 0;
        }
    }

    /**
     * Returns the level of the customer.
     * It also determines the level and fee of the customer based on their account balance before returning the level.
     *
     * @return the level of the customer
     * @effects returns the level of the customer
     */
    public String getLevel() {
        determineLevelAndFee();
        return level;
    }

    /**
     * Returns the fee of the customer.
     *
     * @return the fee of the customer
     * @effects returns the fee of the customer
     */
    public double getFee() {
        return fee;
    }

    /**
     * Returns a string representation of the customer level.
     *
     * @return a string representation of the customer level
     * @effects returns a string that represents the customer level
     */
    @Override
    public String toString() {
        return "Customer Level: " + level + ", Fee: $" + fee;
    }

    /**
     * Checks if the customer's account balance is non-negative, the level is not null, and the fee is non-negative.
     *
     * @return true if the customer's account balance is non-negative, the level is not null, and the fee is non-negative, false otherwise
     * @effects returns a boolean indicating if the customer's account balance is non-negative, the level is not null, and the fee is non-negative
     */
    public boolean repOk() {
        return account.repOk() && level != null && fee >= 0;
    }
}