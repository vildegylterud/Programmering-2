package no.ntnu.vildegy.postalCode.backend;

import java.util.HashMap;

public class Constants {

    public enum CSV__POSTAL_CODE_FIELDS {
        ZIP_CODE, CITY_NAME, MUNICIPALITY_NAME
    }

    public static final HashMap<String, CSV__POSTAL_CODE_FIELDS> CSV_FIELD_HEADER = new HashMap<>();
    static {
        CSV_FIELD_HEADER.put("ZIP_CODE", CSV__POSTAL_CODE_FIELDS.ZIP_CODE);
        CSV_FIELD_HEADER.put("CITY_NAME", CSV__POSTAL_CODE_FIELDS.CITY_NAME);
        CSV_FIELD_HEADER.put("MUNICIPALITY_NAME", CSV__POSTAL_CODE_FIELDS.MUNICIPALITY_NAME);
    }

    public static final String CSV_DELIMITER_STRING = ";";
}
