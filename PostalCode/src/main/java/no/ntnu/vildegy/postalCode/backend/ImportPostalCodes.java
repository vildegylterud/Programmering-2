package no.ntnu.vildegy.postalCode.backend;
/**
 * A class for importing the given PostalCodes.txt
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ImportPostalCodes {


    public static void importAllPostalCodes(PostalCodeRegister postalCodeRegister) throws IOException {

        try {
            ClassLoader classLoader = ImportPostalCodes.class.getClassLoader();

            // Return a input stream for reading the specific resource
            InputStream inputStream = classLoader.getResourceAsStream("PostalCodes.txt");

            //Reads the text from the inputStream. Standard charset set to UTF 8
            BufferedReader readText = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            // Reads a line of text and returns a string containing the contents of the line
            for (String line; (line = readText.readLine()) != null; ) {
                String[] fields = line.split("\t"); //split the string on every "tab"
                postalCodeRegister.addPostalCode(new PostalCode(fields[0], fields[1], fields[3])); //adds all the values to the correct postal code values
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

