package no.ntnu.vildegy.postalCode.backend;

import java.io.File;

public class PostalCode {

    /*
     * Chose to make all variables final because it is not
     * natural that they should be changed
     */
    private final String ZIP_CODE;
    private final String CITY_NAME;
    private final String MUNICIPALITY_NAME;


    /**
     * Constructor
     * Initializing a new postal code and checks for invalid inputs
     *
     * @param zipCode the zip code for the postal code
     * @param cityName the city name that belongs to the zip code
     * @param municipalityName the municipality name that belongs to the city name and zip code
     */
    public PostalCode(String zipCode, String cityName, String municipalityName) {
        if(zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Zip code cannot be empty, more- or less than 4 character");
        }

        if(cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }

        if (municipalityName.trim().isEmpty()) {
            throw new IllegalArgumentException("Municipality name cannot be empty");
        }

        this.ZIP_CODE = zipCode;
        this.CITY_NAME = cityName;
        this.MUNICIPALITY_NAME = municipalityName;
    }

    /*
    No set methods because all variables are final
     */
    public String getZIP_CODE() {
        return ZIP_CODE;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public String getMUNICIPALITY_NAME() {
        return MUNICIPALITY_NAME;
    }

    /**
     * @param o, the reference object with which to compare
     * @return true if this object is the same as the obj argument, false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof PostalCode) {
            PostalCode p = (PostalCode) o;
            return ZIP_CODE.equals(p.getZIP_CODE())
                    && CITY_NAME.equals(p.getCITY_NAME())
                    && MUNICIPALITY_NAME.equals(p.getMUNICIPALITY_NAME());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Postal Code: " + "\n" +
                "Zip code: "+ ZIP_CODE + "\n" +
                "City name: " + CITY_NAME + "\n"  +
                "Municipality name: " + MUNICIPALITY_NAME;
    }
}
