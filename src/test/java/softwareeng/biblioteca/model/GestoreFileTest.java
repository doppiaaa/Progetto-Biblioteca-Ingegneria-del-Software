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
public class GestoreFileTest {
    
    public GestoreFileTest() {
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
     * Test of salva method, of class GestoreFile.
     */
    @Test
    public void testSalva() {
        System.out.println("salva");
        GestioneLibri catalogo = null;
        GestioneUtenti utenti = null;
        GestionePrestiti prestiti = null;
        GestoreFile instance = new GestoreFile();
        instance.salva(catalogo, utenti, prestiti);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of carica method, of class GestoreFile.
     */
    @Test
    public void testCarica() {
        System.out.println("carica");
        GestioneLibri catalogo = null;
        GestioneUtenti utenti = null;
        GestionePrestiti prestiti = null;
        GestoreFile instance = new GestoreFile();
        instance.carica(catalogo, utenti, prestiti);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class GestoreFile.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        GestoreFile instance = new GestoreFile();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
