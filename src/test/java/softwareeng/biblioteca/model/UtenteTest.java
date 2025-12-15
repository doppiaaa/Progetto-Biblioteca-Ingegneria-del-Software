/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.time.LocalDate;
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
public class UtenteTest {
    private Utente utenteDisponibile;
    private Utente utenteNonDisponibile;
    private Libro libroProva;
    private Prestito prestito;
    
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
        
        utenteDisponibile= new Utente("Mario", "Rossi", "0012323676", "m.rossi12@studenti.unisa.it" );
        utenteNonDisponibile= new Utente("Maria", "Verdi", "0012323555", "m.verdi9@studenti.unisa.it" );
        libroProva= new Libro("Prova", "Nessuno", "978-8889878760", 2004, 10);
        prestito= new Prestito(utenteNonDisponibile, libroProva, LocalDate.now());
        utenteNonDisponibile.aggiungi(prestito);
        utenteNonDisponibile.aggiungi(prestito);
        utenteNonDisponibile.aggiungi(prestito);
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
        Map<String, Object> attributi = new HashMap();
        attributi.put("nome", "Giovanni");
        attributi.put("Cognome", "Viola");
        utenteDisponibile.modifica(attributi);
        assertEquals("Giovanni", utenteDisponibile.getNome());
        assertEquals("Viola", utenteDisponibile.getCognome());
        
    }

    /**
     * Test of checkDisponibilita method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCheckDisponibilita() {
        System.out.println("checkDisponibilita");
        
        assertTrue(utenteDisponibile.checkDisponibilita());
        assertTrue(!utenteNonDisponibile.checkDisponibilita());
        
    }

    /**
     * Test of checkPrestiti method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCheckPrestiti() {
        System.out.println("checkPrestiti");
        
        assertTrue(utenteDisponibile.checkPrestiti());
        assertTrue(!utenteNonDisponibile.checkPrestiti());
    }

    /**
     * Test of aggiungi method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        
        utenteDisponibile.aggiungi(prestito);
        assertTrue(utenteDisponibile.getPrestiti().contains(prestito));
        
    }

    /**
     * Test of rimuoviPrestito method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testRimuoviPrestito() {
        System.out.println("rimuoviPrestito");
        
        utenteDisponibile.aggiungi(prestito);
        assertTrue(utenteDisponibile.getPrestiti().contains(prestito));
        utenteDisponibile.rimuoviPrestito(prestito);
        assertFalse(utenteDisponibile.getPrestiti().contains(prestito));
        
        
    }

    /**
     * Test of toString method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        String result= utenteDisponibile.toString();
        
        assertTrue(result.contains("Mario"));
        assertTrue(result.contains("Rossi"));
        assertTrue(result.contains("0012323676"));
        assertTrue(result.contains("0"));
        assertTrue(result.contains("m.rossi12@studenti.unisa.it"));
        
    }

    /**
     * Test of compareTo method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testCompareTo() {
        System.out.println("compareTo");
        assertTrue(utenteDisponibile.compareTo(utenteNonDisponibile)<0);
        assertTrue(utenteDisponibile.compareTo(utenteDisponibile)==0);
        
        
    }

    /**
     * Test of getMatricola method, of class Utente.
     */
    @org.junit.jupiter.api.Test
    public void testGetMatricola() {
        System.out.println("getMatricola");
        
        assertEquals("0012323676", utenteDisponibile.getMatricola());
        assertEquals("0012323555", utenteNonDisponibile.getMatricola());
       
    }

    /**
     * Test of getCognome method, of class Utente.
     */
    @Test
    public void testGetCognome() {
        System.out.println("getCognome");
        
        assertEquals("Rossi", utenteDisponibile.getCognome());
        assertEquals("Verdi", utenteNonDisponibile.getCognome());
    }

    /**
     * Test of getNome method, of class Utente.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        
        assertEquals("Mario", utenteDisponibile.getNome());
        assertEquals("Maria", utenteNonDisponibile.getNome());
        
        
    }

    /**
     * Test of getPrestiti method, of class Utente.
     */
    @Test
    public void testGetPrestiti() {
        System.out.println("getPrestiti");
        
        ArrayList<Prestito> prove= new ArrayList<Prestito>();
        prove.add(prestito);
        
        utenteDisponibile.aggiungi(prestito);
        
        assertEquals(prove, utenteDisponibile.getPrestiti());
        
    }
    
}
