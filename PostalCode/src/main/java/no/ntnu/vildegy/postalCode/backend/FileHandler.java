package no.ntnu.vildegy.postalCode.backend;

import java.io.*;

public class FileHandler {

    private static String delimiter = Constants.CSV_DELIMITER_STRING;

    private final File FILE;


    /**
     * @param path
     * @throws IOException
     */
    public FileHandler(String path) throws IOException {
        if (!path.endsWith(".txt")) {
            throw new IOException(path + " is not a .txt file.");
        }
        this.FILE = new File(path);

        if (!FILE.exists()) {
            throw new IOException("Did not find the file " + FILE.getAbsolutePath());
        }
    }


    /**
     * Method for exporting postal register to file
     *
     * @param postalCodeRegister a list with all the postal code objects
     * @param file
     * @throws IOException
     */
    public static void exportToFile(PostalCodeRegister postalCodeRegister, File file) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        for (PostalCode postalCode : postalCodeRegister.getAllPostalCodes()) {
            printWriter.println(postalCode.getZIP_CODE() + delimiter + postalCode.getCITY_NAME() + delimiter + postalCode.getMUNICIPALITY_NAME());
        }
        printWriter.close();
    }
}