package no.ntnu.vildegy_patientRegister.frontend;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import no.ntnu.vildegy_patientRegister.backend.Patient;
import no.ntnu.vildegy_patientRegister.backend.PatientCSVFileReader;
import no.ntnu.vildegy_patientRegister.backend.PatientRegister;

public class MainController {
    private PatientRegister patientRegister;
    private ObservableList<Patient> observableList;

    @FXML
    public TableView<Patient> patientTableView;
    public TableColumn<Patient,String> firstNameColumn;
    public TableColumn<Patient,String> lastNameColumn;
    public TableColumn<Patient,String> SSNColumn;


    /**
     * initializing the application
     * creates an instance of patient register and an
     * observable list to show in tableview
     */
    @FXML
    public void initialize() {
        patientRegister = new PatientRegister();
        this.patientRegister = new PatientRegister();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        SSNColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        patientRegister.addPatient(new Patient("Vilde", "Gylterud", "12345678"));
        this.observableList = FXCollections.observableArrayList();

        updatePatientTableView();
        //patientTableView.getItems().addAll(patientRegister.getAllPatient());
        // gir feilmelding nullpointerexception
    }

    private Patient getCurrentlySelectedPatient() {
        return patientTableView.getSelectionModel().getSelectedItem();
    }

    /**
     * Method for importing file to CSV
     */
    @FXML
    public void importFromCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("csv", "csv"));
        File importFile = fileChooser.showOpenDialog(null);

        try {
            PatientCSVFileReader csvFileReader = new PatientCSVFileReader(importFile.getAbsolutePath());
            for (Patient p : csvFileReader.getDataFromFileAsPatientObj()) {
                patientRegister.addPatient(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method for exporting file to CSV
     */
    @FXML
    public void exportToCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("csv", "csv"));
        File importFile = fileChooser.showSaveDialog(null);

        try {
            PatientCSVFileReader csvFileReader = new PatientCSVFileReader(importFile.getAbsolutePath());
            csvFileReader.writeToFile(patientRegister,importFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Method for adding patients
     */
    @FXML
    public void addPatient() {
        PatientDialog createNewPatientDialog = new PatientDialog();
        createNewPatientDialog.showAndWait();

        Patient newPatient = createNewPatientDialog.getResult();

        if(newPatient == null) {
            return;
        } else {
            patientRegister.addPatient(newPatient);
            updatePatientTableView();
        }
    }

    /**
     * Methods for edit patients
     */
    @FXML
    public void editPatient() {
        Patient currentPatient = this.getCurrentlySelectedPatient();

        Patient result = null;

        try {
            PatientDialog patientDialog = new PatientDialog(PatientDialog.Mode.EDIT_PATIENT, currentPatient);
            patientDialog.showAndWait();
            result = patientDialog.getResult();
        } catch (NullPointerException e) {
            selectItemDialog();
        }

        if(result != null) {
            updatePatientTableView();
        }

    }

    /**
     * method for remove/delete patients
     */
    @FXML
    public void deletePatient() {
        Patient currentPatient = this.getCurrentlySelectedPatient();

        if(currentPatient == null) {
            selectItemDialog();
        }

        deleteConfirmDialog();

        if (deleteConfirmDialog()) {
            patientRegister.removePatient(currentPatient.getSocialSecurityNumber());
        }
        updatePatientTableView();
    }

    /**
     * Shows the about box with name and version of the program
     */
    public void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog - About");
        alert.setContentText("Vilde Gylterud \n Version: 1.0-SNAPSHOT" );


        alert.showAndWait();
    }

    /**
     * method that updates the patient table view on the application
     */
    private void updatePatientTableView() {
        patientTableView.getItems().clear();
        observableList.setAll(patientRegister.getAllPatient());
        patientTableView.getItems().addAll(observableList);
    }

    /**
     * Ask the user if she really wants to delete the item
     * @return a confirmation alert with message to the user
     */
    public boolean deleteConfirmDialog() {
        boolean deleteConfirmed = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this item?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            deleteConfirmed = (result.get() == ButtonType.OK);
        }
        return deleteConfirmed;
    }


    /**
     * method for exiting the application
     * asks the user if she really wants to exit the application
     */
    public void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Exit?");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {

            Platform.exit();
        }
    }


    /**
     * Giving a message to user that says no item is selected
     */
    public void selectItemDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information");
        alert.setHeaderText("No items selected");
        alert.setContentText("Select an item");

        alert.showAndWait();
    }

}
