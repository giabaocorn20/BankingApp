package coe528.bankingapp.templates;

/**
 * This interface represents a user in the banking application.
 * It includes methods for logging in and logging out a user.
 */
public interface User {

    /**
     * Logs in the user with the specified username and password.
     * This method is used when a user attempts to log in to the application.
     * If the entered username and password match the user's credentials, the user is logged in.
     *
     * @param enteredUsername the username entered by the user
     * @param enteredPassword the password entered by the user
     */
    void login(String enteredUsername, String enteredPassword);

    /**
     * Logs out the user.
     * This method is used when a user attempts to log out of the application.
     * When this method is called, the user's session is ended and they are logged out.
     */
    void logout();
}