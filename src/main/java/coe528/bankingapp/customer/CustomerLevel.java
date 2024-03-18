package coe528.bankingapp.customer;

/**
 * This class represents the level of a customer in the banking application.
 * It includes properties and methods to determine and get the level and fee of a customer based on their account balance.
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
     */
    public CustomerLevel(Account account) {
        this.account = account;
        determineLevelAndFee();
    }

    /**
     * Determines the level and fee of the customer based on their account balance.
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
     */
    public String getLevel() {
        determineLevelAndFee();
        return level;
    }

    /**
     * Returns the fee of the customer.
     *
     * @return the fee of the customer
     */
    public double getFee() {
        return fee;
    }
}