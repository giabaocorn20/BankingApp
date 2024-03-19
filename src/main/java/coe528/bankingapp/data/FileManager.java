package coe528.bankingapp.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Overview: FileManager is a mutable class that handles file operations such as reading, writing and deleting files.
 * It also provides a method to get all files in a directory.

 * Abstraction Function:
 * Represents a file manager as a FileManager object that can perform operations on files such as reading, writing, and deleting.

 * Representation Invariant:
 * The 'CUSTOMER_DIRECTORY' and 'ADMIN_DIRECTORY' fields must always be valid directory paths.
 *
 * Sources: https://docs.oracle.com/javase/tutorial/essential/io/file.html
 * https://codeahoy.com/learn/csfundamentals/ch5/#:~:text=File%20I%2FO%20in%20Java%201%20Reading%20characters%20from,to%20a%20text%20file%20...%204%20Summary%20
 */
public class FileManager {
    // Directory paths for customer and admin data
    private static final String CUSTOMER_DIRECTORY = "src/main/java/coe528/bankingapp/data/customers/";
    private static final String ADMIN_DIRECTORY = "src/main/java/coe528/bankingapp/data/admin/";

    /**
     * Writes the given content to a file with the given filename.
     * If the file does not exist, it will be created.
     *
     * @param filename the name of the file
     * @param content the content to write to the file
     * @requires filename != null && content != null
     * @modifies this
     * @effects creates a new file or modifies an existing file with the given filename and writes the given content to it
     */
    public void writeToFile(String filename, String content) {
        String directory = filename.equals("admin.txt") ? ADMIN_DIRECTORY : CUSTOMER_DIRECTORY;
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the content from a file with the given filename.
     *
     * @param filename the name of the file
     * @requires filename != null
     * @effects returns the content of the file as a string
     * @return the content of the file as a string
     */
    public String readFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_DIRECTORY + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Deletes a file with the given filename.
     *
     * @param filename the name of the file
     * @requires filename != null
     * @modifies this
     * @effects deletes the file with the given filename
     */
    public void deleteFile(String filename) {
        File file = new File(CUSTOMER_DIRECTORY + filename);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    /**
     * Gets all files in the customer directory.
     *
     * @effects returns a list of filenames in the customer directory
     * @return a list of filenames
     */
    public List<String> getAllFilesInDirectory() {
        File directory = new File(CUSTOMER_DIRECTORY);
        File[] files = directory.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
    }

    /**
     * Returns a string representation of the FileManager.
     *
     * @return a string representation of the FileManager
     * @effects returns a string that represents the FileManager
     */
    @Override
    public String toString() {
        return "FileManager with customer directory: " + CUSTOMER_DIRECTORY + ", admin directory: " + ADMIN_DIRECTORY;
    }

    /**
     * Checks if the 'CUSTOMER_DIRECTORY' and 'ADMIN_DIRECTORY' fields are valid directory paths.
     *
     * @return true if the 'CUSTOMER_DIRECTORY' and 'ADMIN_DIRECTORY' fields are valid directory paths, false otherwise
     * @effects returns a boolean indicating if the 'CUSTOMER_DIRECTORY' and 'ADMIN_DIRECTORY' fields are valid directory paths
     */
    public boolean repOk() {
        File customerDir = new File(CUSTOMER_DIRECTORY);
        File adminDir = new File(ADMIN_DIRECTORY);
        return customerDir.isDirectory() && adminDir.isDirectory();
    }
}