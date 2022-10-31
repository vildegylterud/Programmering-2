import no.ntnu.vildegy_patientRegister.backend.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for testing methods in Patient.java
 */

public class TestClass {

    @Test
    void testEquals() {
        Patient p1 = new Patient("Vilde", "Gylterud", "12345678");
        Patient p2 = new Patient("Vilde", "Gylterud", "12345678");

        assertTrue(p1.equals(p2));
    }

    @Test
    void testNotEquals() {
        Patient p1 = new Patient("Vilde", "Gylterud", "12345678");
        Patient p2 = new Patient("Vilde", "Gylterud", "12345679");

        assertFalse(p1.equals(p2));
    }

    @Test
    void getSSN() {
        Patient p = new Patient("Vilde", "Gylterud", "12345678");
        assertEquals("12345678", p.getSocialSecurityNumber());
    }
}
