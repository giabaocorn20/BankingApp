package coe528.bankingapp.customer;

/**
 * Overview: The SilverLevelState class is a mutable class that represents the Silver level state of a customer in a banking application.
 * The state is determined by the level and fee associated with the customer. The class is mutable as the fee and level can be changed.
 *
 * The abstraction function is:
 * AF(c) = a Silver level state where c.fee represents the fee associated with the Silver level and c.level represents the Silver level of the customer
 *
 * The rep invariant is:
 * RI(c) = c.fee >= 0 && c.level != null && c.level.equals("Silver")
 */
public class SilverLevelState extends CustomerLevelState {
    public SilverLevelState() {
        this.fee = 20;
        this.level = "Silver";
    }

    /**
     * Returns the fee associated with the Silver level.
     *
     * @return The fee associated with the Silver level
     * @effects Returns the fee associated with the Silver level
     */
    @Override
    public double getFee() {
        return fee;
    }

    /**
     * Returns the Silver level of the customer.
     *
     * @return The Silver level of the customer
     * @effects Returns the Silver level of the customer
     */
    @Override
    public String getLevel() {
        return level;
    }

    /**
     * Returns a string representation of the Silver level state.
     *
     * @return A string representation of the Silver level state
     * @effects Returns a string that represents the Silver level state
     */
    @Override
    public String toString() {
        return "Level: " + getLevel() + ", Fee: $" + getFee();
    }

    /**
     * Checks the integrity of the SilverLevelState object.
     * The object is considered valid if the fee is non-negative and the level is Silver.
     *
     * @return true if the object is valid, false otherwise
     * @effects Returns true if the rep invariant holds for this; otherwise returns false
     */
    public boolean repOk() {
        return fee >= 0 && level != null && level.equals("Silver");
    }
}