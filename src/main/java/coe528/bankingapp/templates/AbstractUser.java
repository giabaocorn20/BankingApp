package coe528.bankingapp.templates;

import java.util.Scanner;

/**
 * This abstract class represents a user in the banking application.
 * It includes properties and methods that are common to all types of users.
 */
public abstract class AbstractUser implements User{

    // The username of the user
    protected String username;
    // The password of the user
    protected String password;
    // The role of the user
    protected String role;

    // The login status of the user
    protected boolean isLoggedin = false;
    // The scanner to read user input
    protected final Scanner scanner;

    /**
     * Constructs a new AbstractUser with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public AbstractUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Logs in the user if the entered username and password match the user's username and password.
     * If the credentials match, the user's login status is set to true and a message is printed to the console.
     * If the credentials do not match, a message is printed to the console.
     *
     * @param enteredUsername the entered username
     * @param enteredPassword the entered password
     */
    @Override
    public void login(String enteredUsername, String enteredPassword){
        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            System.out.println("Logged in");
            isLoggedin = true;
        } else {
            System.out.println("Invalid credentials");
        }
    }

    /**
     * Logs out the user.
     * The user's login status is set to false and a message is printed to the console.
     */
    @Override
    public void logout() {
        isLoggedin = false;
        System.out.println("Logged out");
    }

    /**
     * Returns the login status of the user.
     *
     * @return the login status of the user
     */
    public boolean isLoggedIn() {
        return isLoggedin;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }
}