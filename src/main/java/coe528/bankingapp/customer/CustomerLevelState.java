package coe528.bankingapp.customer;

/**
 * Overview: The CustomerLevelState class is an abstract class that represents the state of a customer in a banking application.
 * The state is determined by the level and fee associated with the customer. The class is mutable as the fee and level can be changed.
 *
 * The abstraction function is:
 * AF(c) = a customer level state where c.fee represents the fee associated with the customer's level and c.level represents the level of the customer
 *
 * The rep invariant is:
 * RI(c) = c.fee >= 0 && c.level != null
 */
public abstract class CustomerLevelState {
    protected double fee;  // The fee associated with the customer's level
    protected String level;  // The level of the customer

    /**
     * Returns the fee associated with the customer's level.
     *
     * @return The fee associated with the customer's level
     * @effects Returns the fee associated with the customer's level
     */
    public abstract double getFee();

    /**
     * Returns the level of the customer.
     *
     * @return The level of the customer
     * @effects Returns the level of the customer
     */
    public abstract String getLevel();

    /**
     * Returns a string representation of the customer level state.
     *
     * @return A string representation of the customer level state
     * @effects Returns a string that represents the customer level state
     */
    @Override
    public String toString() {
        return "Level: " + getLevel() + ", Fee: $" + getFee();
    }

    /**
     * Checks the integrity of the CustomerLevelState object.
     * The object is considered valid if the fee is non-negative and the level is not null.
     *
     * @return true if the object is valid, false otherwise
     * @effects Returns true if the rep invariant holds for this; otherwise returns false
     */
    public boolean repOk() {
        return fee >= 0 && level != null;
    }
}