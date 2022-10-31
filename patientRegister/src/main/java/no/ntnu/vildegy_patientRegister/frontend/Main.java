package no.ntnu.vildegy_patientRegister.frontend;

/**
 * Main class to the patient application
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent main = FXMLLoader.load(getClass().getResource("scenes/mainPatient.fxml"));
        Scene scene = new Scene(main);
        stage.setScene(scene);
        stage.show();
    }
}
