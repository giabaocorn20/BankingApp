package coe528.bankingapp.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileManager class is responsible for handling file operations such as reading, writing and deleting files.
 * It also provides a method to get all files in a directory.
 */
public class FileManager {
    // Directory paths for customer and admin data
    private static final String CUSTOMER_DIRECTORY = "src/main/java/coe528/bankingapp/data/customers/";
    private static final String ADMIN_DIRECTORY = "src/main/java/coe528/bankingapp/data/admin/";

    /**
     * Writes the given content to a file with the given filename.
     * If the file does not exist, it will be created.
     * @param filename the name of the file
     * @param content the content to write to the file
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
     * @param filename the name of the file
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
     * @param filename the name of the file
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
}