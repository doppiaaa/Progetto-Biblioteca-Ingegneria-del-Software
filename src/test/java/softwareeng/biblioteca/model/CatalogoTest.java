/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import javafx.collections.ObservableList;
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
public class CatalogoTest {
    
    public CatalogoTest() {
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
     * Test of getElenco method, of class Catalogo.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        Catalogo instance = new Catalogo();
        ObservableList<Libro> expResult = null;
        ObservableList<Libro> result = instance.getElenco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungi method, of class Catalogo.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Libro libro = null;
        Catalogo instance = new Catalogo();
        instance.aggiungi(libro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuovi method, of class Catalogo.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        Libro libro = null;
        Catalogo instance = new Catalogo();
        instance.rimuovi(libro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifica method, of class Catalogo.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        
    }

    /**
     * Test of checkID method, of class Catalogo.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        String id = "";
        Catalogo instance = new Catalogo();
        boolean expResult = false;
        boolean result = instance.checkID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricercaTitolo method, of class Catalogo.
     */
    @Test
    public void testRicercaTitolo() {
        System.out.println("ricercaTitolo");
        String titolo = "";
        Catalogo instance = new Catalogo();
        ObservableList<Libro> expResult = null;
        ObservableList<Libro> result = instance.ricercaTitolo(titolo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricercaAutore method, of class Catalogo.
     */
    @Test
    public void testRicercaAutore() {
        System.out.println("ricercaAutore");
        String autore = "";
        Catalogo instance = new Catalogo();
        ObservableList<Libro> expResult = null;
        ObservableList<Libro> result = instance.ricercaAutore(autore);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricercaISBN method, of class Catalogo.
     */
    @Test
    public void testRicercaISBN() {
        System.out.println("ricercaISBN");
        String isbn = "";
        Catalogo instance = new Catalogo();
        ObservableList<Libro> expResult = null;
        ObservableList<Libro> result = instance.ricercaISBN(isbn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
