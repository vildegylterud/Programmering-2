package no.ntnu.vildegy_patientRegister.frontend;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import no.ntnu.vildegy_patientRegister.backend.Patient;

public class PatientDialog extends Dialog<Patient> {

    public enum Mode {
        NEW_PATIENT, EDIT_PATIENT
    }


    private Label dialogTitle;
    private TextField firstNameTextField;
    private TextField surnameTextField;
    private TextField socialSecurityNumberTextField;

    private final Mode mode;
    private Patient existingPatient;

    public PatientDialog() {
        this(Mode.NEW_PATIENT, null);
    }

    public PatientDialog(Mode mode, Patient existingPatient) throws NullPointerException {
        this.mode = mode;
        this.existingPatient = existingPatient;

        VBox main = null;

        this.getDialogPane().setContent(main);
        this.getDialogPane().setMaxHeight(250);
        this.setWidth(450);
        this.setHeight(250);
        main.setMaxHeight(250);

        switch (mode) {
            case EDIT_PATIENT -> initializeEdit();
            case NEW_PATIENT -> initializeNewPatient();
            default -> initializeViewPatient();
        }

        getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        getDialogPane().getButtonTypes().add(ButtonType.OK);

        setResultConverter((buttonType) -> {
            if(buttonType == ButtonType.OK) {
                saveChanges();
                return this.existingPatient;
            } else {
                //changes should not be saved
                return null;
            }
        });
    }



    private void initializeEdit() throws NullPointerException {
        if(existingPatient == null) {
            throw new NullPointerException("No patient provided for edit-dialog");
        }

        dialogTitle.setText("Edit Patient");
        firstNameTextField.setText(existingPatient.getFirstName());
        surnameTextField.setText(existingPatient.getLastName());
        socialSecurityNumberTextField.setText(existingPatient.getSocialSecurityNumber());
    }

    private void initializeViewPatient() throws NullPointerException {
        initializeEdit();

        dialogTitle.setText("View Patient");

        firstNameTextField.setEditable(false);
        surnameTextField.setEditable(false);
        socialSecurityNumberTextField.setEditable(false);
    }

    private void initializeNewPatient() {
        dialogTitle.setText("Add new patient");
    }

    private void saveChanges() throws IllegalArgumentException {
        String firstName = firstNameTextField.getText();
        String surname = surnameTextField.getText();
        String socialSecurityNumber = socialSecurityNumberTextField.getText();

        switch (mode) {
            case EDIT_PATIENT -> {
                try {
                    existingPatient.setFirstName(firstName);
                    existingPatient.setLastName(surname);
                    existingPatient.setSSN(socialSecurityNumber);

                } catch (IllegalArgumentException e) {
                    return;
                }
            }

            case NEW_PATIENT -> {
                try {
                    existingPatient = new Patient(firstName, surname, socialSecurityNumber);
                } catch (IllegalArgumentException e) {
                    return;
                }
            }

        }

        setResult(existingPatient);
    }



}
