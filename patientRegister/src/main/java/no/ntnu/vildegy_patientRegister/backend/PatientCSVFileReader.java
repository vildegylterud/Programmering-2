package no.ntnu.vildegy_patientRegister.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PatientCSVFileReader {

        private final File FILE;

        private String delimiter = Constants.CSV_DELIMITER_STRING;

    public PatientCSVFileReader(String path) throws IOException {
            if(!path.endsWith(".csv")) {
                throw new IOException(path + " is not a .csv file.");
            }

            this.FILE = new File(path);

            if(!FILE.exists()) {
                throw new IOException("Did not find the file " + FILE.getAbsolutePath());
            }
        }

    /**
     *Method for getting data from file as patient objects
     */
        public ArrayList<Patient> getDataFromFileAsPatientObj() throws IOException, IllegalArgumentException {
            ArrayList<ArrayList<String>> dataRaw = getRawFileDataFromFile();
            ArrayList<String> dataHeader = dataRaw.remove(0);
            HashMap<Constants.CSV_PATIENT_FIELDS, Integer> headerIndex = new HashMap<>();
            ArrayList<Patient> patients = new ArrayList<>();


            for(int i = 0; i < dataHeader.size(); i++) {
                String header = dataHeader.get(i);
                Constants.CSV_PATIENT_FIELDS field = Constants.CSV_FIELD_HEADER.get(header);

                headerIndex.put(field, i);
            }


            for(ArrayList<String> line : dataRaw) {
                //Required
                String firstName = line.get(headerIndex.get(Constants.CSV_PATIENT_FIELDS.FIRST_NAME));
                String lastName = line.get(headerIndex.get(Constants.CSV_PATIENT_FIELDS.LAST_NAME));
                String SSN = line.get(headerIndex.get(Constants.CSV_PATIENT_FIELDS.SSN));
                String diagnosis = null;
                String generalPractitioner = null;


                try {
                    generalPractitioner = line.get(headerIndex.get(Constants.CSV_PATIENT_FIELDS.GENERALPRACTITIONER));
                    diagnosis = line.get(headerIndex.get(Constants.CSV_PATIENT_FIELDS.DIAGNOSIS));
                } catch (Exception e) {

                }

                try {
                    Patient patient = new Patient(firstName, lastName, SSN);
                    patient.setGeneralPractitioner(generalPractitioner);
                    patient.setDiagnosis(diagnosis);

                    patients.add(patient);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Error at line " + (dataRaw.indexOf(line)+2) + ": " + e.getMessage());
                }

            }

            return patients;
        }


    /**
     *Method for import to file
     */
    public void writeToFile(PatientRegister patientRegister, File file) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        for (Patient p : patientRegister.getAllPatient()) {
            printWriter.println(p.getFirstName() + delimiter + p.getLastName() + delimiter + p.getGeneralPractitioner() + delimiter + p.getSocialSecurityNumber() + p.getDiagnosis() + delimiter);
        }
        printWriter.close();
    }

    /**
     * Method for export to file
     */
    private ArrayList<ArrayList<String>> getRawFileDataFromFile() throws IOException {
        ArrayList<ArrayList<String>> fileData = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                //Splits each line by the given delimiter into an array of Strings
                ArrayList<String> values = new ArrayList<>(Arrays.asList(line.split("\\"+delimiter)));
                fileData.add(values);
            }
        }

        return fileData;
    }
}

