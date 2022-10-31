package no.ntnu.vildegy_patientRegister.backend;

import java.util.ArrayList;

public class PatientRegister {

    private ArrayList<Patient> patients;

    /**
     * Creates an instance of the Patient register, initialising the instance
     */
    public PatientRegister() {
        this.patients = new ArrayList<>();
    }


    /**
     * Adds a new patient to the list of patients
     *
     * @param patient with first name, last name and social security number
     */
    public void addPatient(Patient patient) {
        if(patient != null) {
            patients.add(patient);
        }
    }

    /**
     * Removes selected patient from the list with patients
     *
     * @param SSN  a String with the patients social security number
     */
    public void removePatient(String SSN) {
        Patient patient = findPatientBySSC(SSN);
        if (patient != null ) {
            this.patients.remove(patient);
        }
    }

    /**
     * Find the patients that match to the given SSN
     *
     * @param SNN a String with the patients social security number
     * @return the details to the patient that matches to the SSN
     */
    private Patient findPatientBySSC(String SNN) {
        Patient foundPatient = null;
        for(Patient p : this.patients) {
            if (p.getSocialSecurityNumber().equals(SNN)) {
                foundPatient = p;
            }
        }
        return foundPatient;
    }


    /**
     * Returns all the patients as a Arraylist
     *
     * @return a Arraylist with all the patients
     */
    public ArrayList<Patient> getAllPatient() {
        return this.patients;
    }


    /**
     * @param o, the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            PatientRegister p = (PatientRegister) o;
            return patients.equals(p.getAllPatient());
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Patients: " + patients;
    }
}
