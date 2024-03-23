package coe528.bankingapp.customer;

/**
 * Overview: The PlatinumLevelState class is a mutable class that represents the Platinum level state of a customer in a banking application.
 * The state is determined by the level and fee associated with the customer. The class is mutable as the fee and level can be changed.
 *
 * The abstraction function is:
 * AF(c) = a Platinum level state where c.fee represents the fee associated with the Platinum level and c.level represents the Platinum level of the customer
 *
 * The rep invariant is:
 * RI(c) = c.fee >= 0 && c.level != null && c.level.equals("Platinum")
 */
public class PlatinumLevelState extends CustomerLevelState {
    public PlatinumLevelState() {
        this.fee = 0;
        this.level = "Platinum";
    }

    /**
     * Returns the fee associated with the Platinum level.
     *
     * @return The fee associated with the Platinum level
     * @effects Returns the fee associated with the Platinum level
     */
    @Override
    public double getFee() {
        return fee;
    }

    /**
     * Returns the Platinum level of the customer.
     *
     * @return The Platinum level of the customer
     * @effects Returns the Platinum level of the customer
     */
    @Override
    public String getLevel() {
        return level;
    }

    /**
     * Returns a string representation of the Platinum level state.
     *
     * @return A string representation of the Platinum level state
     * @effects Returns a string that represents the Platinum level state
     */
    @Override
    public String toString() {
        return "Level: " + getLevel() + ", Fee: $" + getFee();
    }

    /**
     * Checks the integrity of the PlatinumLevelState object.
     * The object is considered valid if the fee is non-negative and the level is Platinum.
     *
     * @return true if the object is valid, false otherwise
     * @effects Returns true if the rep invariant holds for this; otherwise returns false
     */
    public boolean repOk() {
        return fee >= 0 && level != null && level.equals("Platinum");
    }
}