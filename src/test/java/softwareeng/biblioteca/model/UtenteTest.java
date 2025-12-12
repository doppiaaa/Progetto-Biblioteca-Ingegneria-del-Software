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
public class UtenteTest {
    
    
    public UtenteTest() {
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
     * Test of modifica method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testModifica() {
        System.out.println("modifica");
        Map<String, Object> attributi = null;
        Utente instance = null;
        instance.modifica(attributi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDisponibilita method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCheckDisponibilita() {
        System.out.println("checkDisponibilita");
        Utente instance = null;
        boolean expResult = false;
        boolean result = instance.checkDisponibilita();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPrestiti method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCheckPrestiti() {
        System.out.println("checkPrestiti");
        Utente instance = null;
        boolean expResult = false;
        boolean result = instance.checkPrestiti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungi method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Prestito p = null;
        Utente instance = null;
        instance.aggiungi(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuoviPrestito method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testRimuoviPrestito() {
        System.out.println("rimuoviPrestito");
        Prestito p = null;
        Utente instance = null;
        instance.rimuoviPrestito(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        Utente instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Utente u = null;
        Utente instance = null;
        int expResult = 0;
        int result = instance.compareTo(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatricola method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testGetMatricola() {
        System.out.println("getMatricola");
        Utente instance = null;
        String expResult = "";
        String result = instance.getMatricola();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCognome method, of class Utente.
     */
    @Test
    public void testGetCognome() {
        System.out.println("getCognome");
        Utente instance = null;
        String expResult = "";
        String result = instance.getCognome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
