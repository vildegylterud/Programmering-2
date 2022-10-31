package no.ntnu.vildegy.PostalCode.backend;

import no.ntnu.vildegy.postalCode.backend.PostalCode;
import no.ntnu.vildegy.postalCode.backend.PostalCodeRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeRegisterTest {

    /**
     * Tests  "find Postal code by zip code"- method
     * Both unsuccessful and successful
     */
    @Test
    void findPostalCodeByZipCodeSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");
        PostalCode postalCode2 = new PostalCode("3122", "Notteroy", "Vestfold og Telemark");

        PostalCodeRegister register = new PostalCodeRegister();
        register.addPostalCode(postalCode1);
        register.addPostalCode(postalCode2);

        assertTrue(register.findPostalCodeByZipCode("3120").equals(postalCode1));
    }

    @Test
    void findPostalCodeByZipCodeUnSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");
        PostalCode postalCode2 = new PostalCode("3122", "Notteroy", "Vestfold og Telemark");

        PostalCodeRegister register = new PostalCodeRegister();
        register.addPostalCode(postalCode1);
        register.addPostalCode(postalCode2);

        assertNull(register.findPostalCodeByZipCode("3121"));

    }


    /**
     * Tests  "find Postal code by city name"- method
     * Both unsuccessful and successful
     */
    @Test
    void findPostalCodeByCityNameSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");

        PostalCodeRegister register = new PostalCodeRegister();
        register.addPostalCode(postalCode1);

        assertTrue(register.findPostalCodeByCityName("Notteroy").equals(postalCode1));
    }

    @Test
    void findPostalCodeByCityNameUnSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");

        PostalCodeRegister register = new PostalCodeRegister();
        register.addPostalCode(postalCode1);

        assertNull(register.findPostalCodeByCityName("Oslo"));
    }
}