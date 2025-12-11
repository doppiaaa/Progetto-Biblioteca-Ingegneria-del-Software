/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cashrules
 */
public class LibroTest {
    
    public LibroTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of modifica method, of class Libro.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        Map<String, Object> attributi = null;
        Libro instance = null;
        instance.modifica(attributi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDisponibilità method, of class Libro.
     */
    @Test
    public void testCheckDisponibilità() {
        System.out.println("checkDisponibilit\u00e0");
        Libro instance = null;
        boolean expResult = false;
        boolean result = instance.checkDisponibilità();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrestiti method, of class Libro.
     */
    @Test
    public void testCheckPrestiti() {
        System.out.println("checkPrestiti");
        Libro instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrestiti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upCopie method, of class Libro.
     */
    @Test
    public void testUpCopie() {
        System.out.println("upCopie");
        Libro instance = null;
        instance.upCopie();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downCopie method, of class Libro.
     */
    @Test
    public void testDownCopie() {
        System.out.println("downCopie");
        Libro instance = null;
        instance.downCopie();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Libro.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Libro instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Libro l = null;
        Libro instance = null;
        int expResult = 0;
        int result = instance.compareTo(l);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getISBN method, of class Libro.
     */
    @Test
    public void testGetISBN() {
        System.out.println("getISBN");
        Libro instance = null;
        String expResult = "";
        String result = instance.getISBN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
