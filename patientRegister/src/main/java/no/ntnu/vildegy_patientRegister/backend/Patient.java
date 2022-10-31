package no.ntnu.vildegy_patientRegister.backend;

//TODO: endre ssn til final
public class Patient {

    private String firstName ;
    private String lastName;
    private String socialSecurityNumber;
    private String generalPractitioner;
    private String diagnosis;

    public Patient(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    //Getters and setters
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets first name to a patient, checks for null values
     * @param firstName to a patient
     */
    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("Name cannot be null!!");
        }

        if (firstName.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name to a patient, checks for null values
     * @param lastName to a patient
     */
    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Name cannot be null!!");
        }
        if (lastName.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    /**
     * sets a general practitioner to a patient
     * checks for null values
     * @param generalPractitioner to a patient
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        if(generalPractitioner == null) {
            throw new IllegalArgumentException("Name cannot be null!!");
        }
        if (generalPractitioner.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.generalPractitioner = generalPractitioner;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets a diagnosis to a patient
     * Checks for null values
     * @param diagnosis to the patient
     */
    public void setDiagnosis(String diagnosis) {
        if(diagnosis == null) {
            throw new IllegalArgumentException("Name cannot be null!!");
        }
        if (diagnosis.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.diagnosis = diagnosis;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the social security number to a patient
     * checks for null values
     * @param socialSecurityNumber
     */
    public void setSSN(String socialSecurityNumber) {
        if (socialSecurityNumber == null) {
            throw new IllegalArgumentException("Name cannot be null!!");
        }

        if (socialSecurityNumber.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.socialSecurityNumber = socialSecurityNumber;
    }


    /**
     * @param o, the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return firstName.equals(p.getFirstName())
                    && lastName.equals(p.getLastName())
                    && socialSecurityNumber.equals(p.getSocialSecurityNumber());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Patient: " + firstName + " " + lastName + '\'' +
                "Social Security Number: '" + socialSecurityNumber;
    }
}
