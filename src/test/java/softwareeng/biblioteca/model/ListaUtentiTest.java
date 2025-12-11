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
public class ListaUtentiTest {
    
    public ListaUtentiTest() {
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
     * Test of getElenco method, of class ListaUtenti.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        ListaUtenti instance = new ListaUtenti();
        ObservableList<Utente> expResult = null;
        ObservableList<Utente> result = instance.getElenco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungi method, of class ListaUtenti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Utente utente = null;
        ListaUtenti instance = new ListaUtenti();
        instance.aggiungi(utente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuovi method, of class ListaUtenti.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        Utente utente = null;
        ListaUtenti instance = new ListaUtenti();
        instance.rimuovi(utente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkID method, of class ListaUtenti.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        String id = "";
        ListaUtenti instance = new ListaUtenti();
        boolean expResult = false;
        boolean result = instance.checkID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
