package no.ntnu.vildegy.postalCode.frontend;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import no.ntnu.vildegy.postalCode.backend.FileHandler;
import no.ntnu.vildegy.postalCode.backend.ImportPostalCodes;
import no.ntnu.vildegy.postalCode.backend.PostalCode;
import no.ntnu.vildegy.postalCode.backend.PostalCodeRegister;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Optional;

public class MainController {

    private PostalCodeRegister postalCodeRegister;

    @FXML
    public TextField zipCodeTextField;
    public TextField cityNameTextField;

    private ObservableList<PostalCode> observableList;

    public TableColumn<PostalCode,String> zipCodeColumn;
    public TableColumn<PostalCode,String> cityNameColumn;
    public TableColumn<PostalCode,String> municipalityNameColumn;
    public TableView<PostalCode> tableView;

    public Button searchButton;
    public AnchorPane anchorPane;
    public MenuBar menuBar;
    public Text zipCodeText;
    public Text cityNameText;


    /**
     * Initializing the application
     * creates an instance of postal code register and an
     * observable list to show in tableview
     */
    public void initialize() throws IOException {
        this.postalCodeRegister = new PostalCodeRegister();

        ImportPostalCodes.importAllPostalCodes(postalCodeRegister);

        zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("ZIP_CODE"));
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("CITY_NAME"));
        municipalityNameColumn.setCellValueFactory(new PropertyValueFactory<>("MUNICIPALITY_NAME"));


        zipCodeTextField.setPromptText("Write zip code ..");
        zipCodeTextField.setStyle("-fx-prompt-text-fill: #848484 ; -fx-font-size: 12 ; -fx-focus-traversable: transparent ");

        cityNameTextField.setPromptText("Write city name ..");
        cityNameTextField.setStyle("-fx-prompt-text-fill: #848484 ; -fx-font-size: 12 ; -fx-focus-traversable: transparent ");

        anchorPane.setStyle("-fx-background-color: #424242");

        this.observableList = FXCollections.observableArrayList();
        updateTableView(postalCodeRegister.getAllPostalCodes());

    }

    /**
     * Method for finding postal code from either city name or zip code
     * If both text fields is empty, an dialog box with message to user will be shown
     */
    @FXML
    public void findPostalCode() {

        String textZipCode = zipCodeTextField.getText();
        String cityNameText = cityNameTextField.getText();

        if(!textZipCode.trim().isEmpty()) {
            ArrayList<PostalCode> postalCodeArrayList =  postalCodeRegister.findPostalCodeByZipCode(textZipCode);
            updateTableView(postalCodeArrayList);
        }
        if(!cityNameText.trim().isEmpty()) {
            ArrayList<PostalCode> postalCodeArrayList = postalCodeRegister.findPostalCodeByCityName(cityNameText);
            updateTableView(postalCodeArrayList);
        }
        else if(textZipCode.trim().isEmpty() && cityNameText.trim().isEmpty()) {
            messageAlertToUser("Both inputs cannot be empty");
        }
    }




    /**
     * Shows an about box with information of the program
     */
    @FXML
    public void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog - About");
        alert.setContentText("Vilde Gylterud" + "\n" + "Version: 1.0-SNAPSHOT" );

        //To get the dark mode design on all the dialog boxes
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #424242; -fx-prompt-text-fill: white; -fx-base: #121212; -fx-focus-traversable: false;  -fx-control-inner-background: derive(-fx-base, 35%);\n" +
                "    -fx-control-inner-background-alt: -fx-control-inner-background ; -fx-accent: #212121;\n" +
                "    -fx-focus-color: -fx-accent;");

        alert.showAndWait();
    }


    /**
     * Method for exit the application
     * Asks the user if she really wants to exit the application
     */
    @FXML
    public void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Exit?");
        alert.setContentText("Are you sure you want to exit?");

        //To get the dark mode design on the dialog boxes too
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #424242; -fx-prompt-text-fill: white; -fx-base: #121212; -fx-focus-traversable: false;  -fx-control-inner-background: derive(-fx-base, 35%);\n" +
                "    -fx-control-inner-background-alt: -fx-control-inner-background ; -fx-accent: #212121;\n" +
                "    -fx-focus-color: -fx-accent;");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {

            Platform.exit();
        }
    }

    /** Method that sends a selected message to the user
     *
     * @param message selected message to the user
     */
    public void messageAlertToUser(String message) {
        Alert alert = new Alert((Alert.AlertType.INFORMATION));

        alert.setTitle("Information");
        alert.setContentText(message);

        //To get the dark mode design on all the dialog boxes
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #424242; -fx-prompt-text-fill: white; -fx-base: #121212; -fx-focus-traversable: false;  -fx-control-inner-background: derive(-fx-base, 35%);\n" +
                "    -fx-control-inner-background-alt: -fx-control-inner-background ; -fx-accent: #212121;\n" +
                "    -fx-focus-color: -fx-accent;");

        alert.showAndWait();
    }

    /** Method that clears the tableview
     *  and sets/adds the new values in it
     *
     * @param arrayList a list with all the postal codes
     */
    public void updateTableView(ArrayList<PostalCode> arrayList) {
        tableView.getItems().clear();
        observableList.setAll(arrayList);
        tableView.getItems().addAll(observableList);
    }


    /**
     * Method for exporting Txt file
     */
    @FXML
    public void exportToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Txt files (.txt)", ".txt"));
        File importFile = fileChooser.showSaveDialog(null);

        try {
            FileHandler.exportToFile(this.postalCodeRegister, importFile);
            updateTableView(postalCodeRegister.getAllPostalCodes());
        } catch (IOException e) {
            messageAlertToUser("Error in exporting file. Could not import file");
            e.printStackTrace();
        }
    }

    public void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            findPostalCode();
        }
    }

}
