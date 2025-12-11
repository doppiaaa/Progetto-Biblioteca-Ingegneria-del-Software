/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

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
public class PrestitoTest {
    
    public PrestitoTest() {
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
     * Test of disattiva method, of class Prestito.
     */
    @Test
    public void testDisattiva() {
        System.out.println("disattiva");
        Prestito instance = null;
        instance.disattiva();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContatore method, of class Prestito.
     */
    @Test
    public void testSetContatore() {
        System.out.println("setContatore");
        int = 0;
        Prestito.setContatore(<error>);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Prestito.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Prestito.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Prestito instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAttivo method, of class Prestito.
     */
    @Test
    public void testIsAttivo() {
        System.out.println("isAttivo");
        Prestito instance = null;
        boolean expResult = false;
        boolean result = instance.isAttivo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isScaduto method, of class Prestito.
     */
    @Test
    public void testIsScaduto() {
        System.out.println("isScaduto");
        Prestito instance = null;
        boolean expResult = false;
        boolean result = instance.isScaduto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Prestito.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Prestito = null;
        Prestito instance = null;
        int expResult = 0;
        int result = instance.compareTo(<error>);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
