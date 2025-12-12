/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.util.Map;
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
public class ListaPrestitiTest {
    
    public ListaPrestitiTest() {
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
     * Test of getElenco method, of class ListaPrestiti.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        ListaPrestiti instance = new ListaPrestiti();
        ObservableList<Prestito> expResult = null;
        ObservableList<Prestito> result = instance.getElenco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungi method, of class ListaPrestiti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Prestito prestito = null;
        ListaPrestiti instance = new ListaPrestiti();
        instance.aggiungi(prestito);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rimuovi method, of class ListaPrestiti.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        Prestito prestito = null;
        ListaPrestiti instance = new ListaPrestiti();
        instance.rimuovi(prestito);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkID method, of class ListaPrestiti.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        String id = "";
        ListaPrestiti instance = new ListaPrestiti();
        boolean expResult = false;
        boolean result = instance.checkID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPrestito method, of class ListaPrestiti.
     */
    @Test
    public void testAddPrestito() {
        System.out.println("addPrestito");
        
    }

    /**
     * Test of disattiva method, of class ListaPrestiti.
     */
    @Test
    public void testDisattiva() {
        System.out.println("disattiva");
        Prestito p = null;
        ListaPrestiti instance = new ListaPrestiti();
        instance.disattiva(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Utente() {
        System.out.println("ricerca");
        Utente u = null;
        ListaPrestiti instance = new ListaPrestiti();
        ObservableList<Prestito> expResult = null;
        ObservableList<Prestito> result = instance.ricerca(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Libro() {
        System.out.println("ricerca");
        Libro l = null;
        ListaPrestiti instance = new ListaPrestiti();
        ObservableList<Prestito> expResult = null;
        ObservableList<Prestito> result = instance.ricerca(l);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifica method, of class ListaPrestiti.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        Prestito prestito = null;
        Map<String, Object> attributi = null;
        ListaPrestiti instance = new ListaPrestiti();
        instance.modifica(prestito, attributi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
