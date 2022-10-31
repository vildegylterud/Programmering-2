package no.ntnu.vildegy_patientRegister.backend;

/**
 * Class with constants for file handling
 */

import java.util.HashMap;


public class Constants {
    public enum CSV_PATIENT_FIELDS {
        FIRST_NAME, LAST_NAME, SSN, GENERALPRACTITIONER, DIAGNOSIS
    }

    public static final HashMap<String, CSV_PATIENT_FIELDS> CSV_FIELD_HEADER = new HashMap<>();
    static {
        CSV_FIELD_HEADER.put("firstName", CSV_PATIENT_FIELDS.FIRST_NAME);
        CSV_FIELD_HEADER.put("lastName", CSV_PATIENT_FIELDS.LAST_NAME);
        CSV_FIELD_HEADER.put("socialSecurityNumber", CSV_PATIENT_FIELDS.SSN);
        CSV_FIELD_HEADER.put("generalPractitioner", CSV_PATIENT_FIELDS.GENERALPRACTITIONER);
        CSV_FIELD_HEADER.put( "diagnosis", CSV_PATIENT_FIELDS.DIAGNOSIS);
    }

    public static final String CSV_DELIMITER_STRING = ";";

}

