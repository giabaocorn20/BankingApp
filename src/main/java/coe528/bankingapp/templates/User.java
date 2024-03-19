package coe528.bankingapp.templates;

/**
 * This interface represents a user in the banking application.
 * A user is responsible for logging in and logging out of the application.
 * This interface is immutable as it only provides the contract for the methods without any state.
 */
public interface User {

    /**
     * Logs in the user with the specified username and password.
     * This method is used when a user attempts to log in to the application.
     * If the entered username and password match the user's credentials, the user is logged in.
     *
     * @param enteredUsername the username entered by the user
     * @param enteredPassword the password entered by the user
     * @requires enteredUsername != null && enteredPassword != null
     * @effects changes the login status of the user if the entered credentials match the user's credentials
     */
    void login(String enteredUsername, String enteredPassword);

    /**
     * Logs out the user.
     * This method is used when a user attempts to log out of the application.
     * When this method is called, the user's session is ended and they are logged out.
     *
     * @modifies this
     * @effects sets the user's login status to false
     */
    void logout();

}