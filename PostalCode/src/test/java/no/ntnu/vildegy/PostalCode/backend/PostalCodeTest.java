package no.ntnu.vildegy.PostalCode.backend;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.vildegy.postalCode.backend.PostalCode;
import org.junit.jupiter.api.Test;

class PostalCodeTest {

    /**
     * Tests that the equals method in Postal Code class
     * both unsuccessful and successful
     */
    @Test
    void equalsSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");
        PostalCode postalCode2 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");

        assertTrue(postalCode1.equals(postalCode2));
    }

    @Test
    void equalsUnSuccess() {
        PostalCode postalCode1 = new PostalCode("3120", "Notteroy", "Vestfold og Telemark");
        PostalCode postalCode2 = new PostalCode("3122", "Notteroy", "Vestfold og Telemark");

        assertFalse(postalCode1.equals(postalCode2));
    }
}