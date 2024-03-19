package coe528.bankingapp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the main application in the banking application.
 * It is responsible for managing the main scene of the application, starting the application, setting the root scene, and loading FXML files.
 * This class is mutable as it allows changing the root scene and loading different FXML files.
 *
 * Abstraction Function:
 * An App is represented by a scene which is the main scene of the application.
 *
 * Representation Invariant:
 * The scene must be non-null.
 */
public class App extends Application {

    // The main scene of the application
    private static Scene scene;

    /**
     * Starts the application with the login scene.
     *
     * @param stage the main stage of the application
     * @throws IOException if the FXML file for the login scene cannot be loaded
     * @effects sets the main scene of the application to the login scene
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Sets the root scene of the application to the scene specified by the FXML file.
     *
     * @param fxml the name of the FXML file for the scene
     * @throws IOException if the FXML file cannot be loaded
     * @modifies this
     * @effects changes the root of the main scene to the scene specified by the FXML file
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


    /**
     * Loads the FXML file with the specified name.
     *
     * @param fxml the name of the FXML file
     * @return the root of the scene specified by the FXML file
     * @throws IOException if the FXML file cannot be loaded
     * @effects loads the FXML file and returns the root of the scene specified by the FXML file
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/coe528/bankingapp/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The main method of the application.
     * It is the entry point of the application and is responsible for launching the JavaFX application.
     *
     * @param args the command-line arguments
     * @effects launches the JavaFX application
     */
    public static void main(String[] args) {
        launch();
    }

}