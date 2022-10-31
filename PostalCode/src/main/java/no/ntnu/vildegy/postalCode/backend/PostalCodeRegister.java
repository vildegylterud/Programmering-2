package no.ntnu.vildegy.postalCode.backend;
import java.util.ArrayList;
import java.util.Locale;

public class PostalCodeRegister {

    private ArrayList<PostalCode> postalCodeRegister;

    /**
     * Constructor
     * Creates an instance of the postal code register
     * Initializing the instance
     */
    public PostalCodeRegister() {
        this.postalCodeRegister = new ArrayList<>();
    }

    /**
     * @return a ArrayList of postal codes with all the postal codes
     */
    public ArrayList<PostalCode> getAllPostalCodes() {
        return postalCodeRegister;
    }



    /**
     * Fins a the city name and municipality name to the given zip code
     * Checks for invalid inputs (more than 4 digits or null)
     *
     *
     * @param zipCode a String (1-4) with a zip code
     * @return details of the postal code that matches to the zip code
     * if not found it will return null
     */
    public ArrayList<PostalCode> findPostalCodeByZipCode(String zipCode) {

        ArrayList<PostalCode> foundPostalCode = new ArrayList<>();

        if(zipCode.trim().isEmpty() || zipCode.trim().length() > 4) {
            throw new IllegalArgumentException("Input cannot be null, or more than 4 digits");
        }

        for (PostalCode p : this.postalCodeRegister) {
            if (p.getZIP_CODE().contains(zipCode)) {
                foundPostalCode.add(p);
            }
        }
        return foundPostalCode;
    }


    /**
     * Find the zip codes and municipality names to the given city name
     * Checks for null-inputs
     *
     * @param cityName a String with the city name
     * @return the details of the postal code that matches to the city name
     * if not found it will return null
     */
    public ArrayList<PostalCode> findPostalCodeByCityName(String cityName) {
        ArrayList<PostalCode> foundPostalCode = new ArrayList<>();
        if(cityName.trim().isEmpty()) {
            throw new NullPointerException("Input cannot be null");
        }
        for (PostalCode p : this.postalCodeRegister) {
            if (p.getCITY_NAME().toLowerCase(Locale.ROOT).contains(cityName.toLowerCase())) {
                foundPostalCode.add(p);
            }
        }
        return foundPostalCode;
    }


    /**
     *
     * The method is only used i the test class to test the "findPostalCode" methods
     *
     * @param postalCode with zip code, city name and municipality name
     */
    public void addPostalCode(PostalCode postalCode) {
        if (postalCode != null) {
            postalCodeRegister.add(postalCode);
        }
    }


    /**
     * @param o, the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof PostalCodeRegister) {
            PostalCodeRegister p = (PostalCodeRegister) o;
            return postalCodeRegister.equals(p.getAllPostalCodes());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "PostalCodeRegister: " + "\n" +
                "Postal code register: " + postalCodeRegister;
    }

}
