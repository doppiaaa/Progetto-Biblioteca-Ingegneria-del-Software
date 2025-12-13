/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.time.LocalDate;
import java.time.Month;
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
    
    private Prestito prestitoAttivo;
    private Prestito prestitoScaduto;
    private Prestito prestitoRestituito;
    private Utente utenteDisponibile;
    private Utente utenteNonDisponibile;
    private Libro libroDisponibile;
    private Libro libroNonDisponibile;
    
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
        Prestito.setContatore(0);
        
        utenteNonDisponibile= new Utente("Mario", "Rossi", "0012323676", "m.rossi12@studenti.unisa.it" );
        utenteDisponibile= new Utente("Maria", "Verdi", "0012323555", "m.verdi9@studenti.unisa.it" );
        
        libroDisponibile = new Libro("Design Pattern", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "978-0201633610", 1994, 5);
        
        libroNonDisponibile= new Libro("Lezioni di automatica", "Francesco Basile, Pasquale Chiacchio", "978-8891647566", 2021, 2);
        
        prestitoAttivo= new Prestito(utenteNonDisponibile, libroNonDisponibile, LocalDate.of(2030, Month.MARCH, 14));
        
        
        prestitoScaduto= new Prestito(utenteNonDisponibile, libroNonDisponibile, LocalDate.of(2004, Month.MARCH, 14));
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
        
        prestitoRestituito= new Prestito(utenteDisponibile, libroDisponibile, LocalDate.of(2030, Month.MARCH, 14));
        assertTrue(utenteDisponibile.getPrestiti().contains(prestitoRestituito));
        assertEquals(1, utenteDisponibile.getPrestiti().size());
        assertEquals(4, libroDisponibile.getCopieDisponibili());
        prestitoRestituito.disattiva();
        assertFalse(utenteDisponibile.getPrestiti().contains(prestitoRestituito));
        assertEquals(5, libroDisponibile.getCopieDisponibili());
        assertEquals(0, utenteDisponibile.getPrestiti().size());
        
    }

    /**
     * Test of setContatore method, of class Prestito.
     */
    @Test
    public void testSetContatore() {
        System.out.println("setContatore");
        
        Prestito.setContatore(23);
        
        assertEquals(23, Prestito.getContatore());
        
    }

    /**
     * Test of getID method, of class Prestito.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        
        assertEquals("1", prestitoAttivo.getID());
        assertEquals("2", prestitoScaduto.getID());
        
        
        
    }

    /**
     * Test of toString method, of class Prestito.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        String result= prestitoAttivo.toString();
        
        assertTrue(result.contains(utenteNonDisponibile.toString()));
        assertTrue(result.contains(libroNonDisponibile.toString()));
        assertTrue(result.contains(LocalDate.of(2030, Month.MARCH, 14).toString()));
        
    }

    /**
     * Test of isAttivo method, of class Prestito.
     */
    @Test
    public void testIsAttivo() {
        System.out.println("isAttivo");
        assertTrue(prestitoAttivo.isAttivo());
        assertTrue(prestitoScaduto.isAttivo());
        prestitoScaduto.disattiva();
        assertFalse(prestitoScaduto.isAttivo());
        
    }

    /**
     * Test of isScaduto method, of class Prestito.
     */
    @Test
    public void testIsScaduto() {
        System.out.println("isScaduto");
        assertFalse(prestitoAttivo.isScaduto());
        assertTrue(prestitoScaduto.isScaduto());
    }

    /**
     * Test of compareTo method, of class Prestito.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        
        assertTrue(prestitoAttivo.compareTo(prestitoScaduto)>0);
        assertTrue(prestitoAttivo.compareTo(prestitoAttivo)==0);
        
    }

    /**
     * Test of getContatore method, of class Prestito.
     */
    @Test
    public void testGetContatore() {
        System.out.println("getContatore");
        
        assertEquals(2, Prestito.getContatore());
        
        Prestito.setContatore(23);
        
        assertEquals(23, Prestito.getContatore());
    }

    /**
     * Test of getUtente method, of class Prestito.
     */
    @Test
    public void testGetUtente() {
        System.out.println("getUtente");
        assertEquals(utenteNonDisponibile, prestitoAttivo.getUtente());
    }

    /**
     * Test of getLibro method, of class Prestito.
     */
    @Test
    public void testGetLibro() {
        System.out.println("getLibro");
        assertEquals(libroNonDisponibile, prestitoAttivo.getLibro());
    }
    
}
