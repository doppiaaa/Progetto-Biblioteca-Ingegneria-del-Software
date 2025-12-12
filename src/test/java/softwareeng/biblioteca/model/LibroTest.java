/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.util.*;
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
    
    private Libro libroDisponibile;
    private Libro libroNonDisponibile;
    
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
        // [Implementazione del metodo setUp()]
        
        libroDisponibile = new Libro("Design Pattern", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "978-0201633610", 1994, 5);
        
        libroNonDisponibile= new Libro("Lezioni di automatica", "Francesco Basile, Pasquale Chiacchio", "978-8891647566", 2021, 3);
        libroNonDisponibile.downCopie();
        libroNonDisponibile.downCopie();
        libroNonDisponibile.downCopie();
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
        Map<String, Object> attributi = new HashMap();
        attributi.put("copietotali", 2);
        
        libroDisponibile.modifica(attributi);
        
        assertThrows(softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException.class, () -> {
            libroNonDisponibile.modifica(attributi);
        }, "Il tentativo di riduzione deve fallire per vincolo di integrità.");
        
        assertEquals(2, libroDisponibile.getCopieDisponibili());
        assertEquals(2, libroDisponibile.getCopieTotali());
        assertEquals(3, libroNonDisponibile.getCopieTotali());
        assertEquals(0, libroNonDisponibile.getCopieDisponibili());
        
    }

    /**
     * Test of checkDisponibilità method, of class Libro.
     */
    @Test
    public void testCheckDisponibilita() {
        System.out.println("checkDisponibilita");
        
        assertTrue(libroDisponibile.checkDisponibilita());
        assertTrue(!libroNonDisponibile.checkDisponibilita());
    
        
    }

    /**
     * Test of checkPrestiti method, of class Libro.
     */
    @Test
    public void testCheckPrestiti() {
        System.out.println("checkPrestiti");
        
        assertTrue(libroDisponibile.checkPrestiti());
        assertTrue(!libroNonDisponibile.checkPrestiti());
    }

    /**
     * Test of upCopie method, of class Libro.
     */
    @Test
    public void testUpCopie() {
        System.out.println("upCopie");
        libroNonDisponibile.upCopie();
        assertEquals(1, libroNonDisponibile.getCopieDisponibili());
        libroNonDisponibile.upCopie();
        assertEquals(2, libroNonDisponibile.getCopieDisponibili());
        
        assertEquals(3, libroNonDisponibile.getCopieTotali());
    }

    /**
     * Test of downCopie method, of class Libro.
     */
    @Test
    public void testDownCopie() {
        System.out.println("downCopie");
        libroDisponibile.downCopie();
        assertEquals(4, libroDisponibile.getCopieDisponibili());
        assertEquals(5, libroDisponibile.getCopieTotali());
    }

    /**
     * Test of toString method, of class Libro.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String result= libroDisponibile.toString();
        
        assertTrue(result.contains("Design Pattern"));
        assertTrue(result.contains("978-0201633610"));
        assertTrue(result.contains("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        assertTrue(result.contains("1994"));
        assertTrue(result.contains("5"));
    }

    /**
     * Test of compareTo method, of class Libro.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        assert(libroDisponibile.compareTo(libroNonDisponibile)<0);
        assert(libroDisponibile.compareTo(libroDisponibile)==0);
        
    }

    /**
     * Test of getISBN method, of class Libro.
     */
    @Test
    public void testGetISBN() {
        System.out.println("getISBN");
        assertEquals("978-0201633610", libroDisponibile.getISBN());
        assertEquals("978-8891647566", libroNonDisponibile.getISBN());

    }

    /**
     * Test of getTitolo method, of class Libro.
     */
    @Test
    public void testGetTitolo() {
        System.out.println("getTitolo");
        assertEquals("Design Pattern", libroDisponibile.getTitolo());
        assertEquals("Lezioni di automatica", libroNonDisponibile.getTitolo());
    }

    /**
     * Test of getAutore method, of class Libro.
     */
    @Test
    public void testGetAutore() {
        System.out.println("getAutore");
       System.out.println("getTitolo");
        assertEquals("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", libroDisponibile.getAutore());
        assertEquals("Francesco Basile, Pasquale Chiacchio", libroNonDisponibile.getAutore());
    }

    /**
     * Test of getCopieTotali method, of class Libro.
     */
    @Test
    public void testGetCopieTotali() {
        System.out.println("getCopieTotali");
        assertEquals(5, libroDisponibile.getCopieTotali());
        assertEquals(3, libroNonDisponibile.getCopieTotali());
        
    }

    /**
     * Test of getCopieDisponibili method, of class Libro.
     */
    @Test
    public void testGetCopieDisponibili() {
        System.out.println("getCopieDisponibili");
        assertEquals(5, libroDisponibile.getCopieDisponibili());
        assertEquals(0, libroNonDisponibile.getCopieDisponibili());
    }
    
}
