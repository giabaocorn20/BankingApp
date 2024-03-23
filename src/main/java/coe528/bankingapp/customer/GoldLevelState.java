package coe528.bankingapp.customer;

/**
 * Overview: The GoldLevelState class is a mutable class that represents the Gold level state of a customer in a banking application.
 * The state is determined by the level and fee associated with the customer. The class is mutable as the fee and level can be changed.
 *
 * The abstraction function is:
 * AF(c) = a Gold level state where c.fee represents the fee associated with the Gold level and c.level represents the Gold level of the customer
 *
 * The rep invariant is:
 * RI(c) = c.fee >= 0 && c.level != null && c.level.equals("Gold")
 */
public class GoldLevelState extends CustomerLevelState {
    public GoldLevelState() {
        this.fee = 10;
        this.level = "Gold";
    }

    /**
     * Returns the fee associated with the Gold level.
     *
     * @return The fee associated with the Gold level
     * @effects Returns the fee associated with the Gold level
     */
    @Override
    public double getFee() {
        return fee;
    }

    /**
     * Returns the Gold level of the customer.
     *
     * @return The Gold level of the customer
     * @effects Returns the Gold level of the customer
     */
    @Override
    public String getLevel() {
        return level;
    }

    /**
     * Returns a string representation of the Gold level state.
     *
     * @return A string representation of the Gold level state
     * @effects Returns a string that represents the Gold level state
     */
    @Override
    public String toString() {
        return "Level: " + getLevel() + ", Fee: $" + getFee();
    }

    /**
     * Checks the integrity of the GoldLevelState object.
     * The object is considered valid if the fee is non-negative and the level is Gold.
     *
     * @return true if the object is valid, false otherwise
     * @effects Returns true if the rep invariant holds for this; otherwise returns false
     */
    public boolean repOk() {
        return fee >= 0 && level != null && level.equals("Gold");
    }
}