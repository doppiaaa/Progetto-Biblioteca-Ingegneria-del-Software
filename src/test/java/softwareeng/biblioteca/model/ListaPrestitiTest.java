/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;


import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;
import java.time.*;
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
    
    private ListaPrestiti lista;
    
    // Oggetti per i test (Utenti e Libri)
    private Utente utenteLibero; // Utente che può prendere in prestito
    private Utente utentePieno;  // Utente con 3 prestiti attivi
    private Libro libroDisponibile; // Libro con copie > 0
    private Libro libroNonDisponibile; // Libro con copie = 0

    // Variabili per i prestiti
    private Prestito p1, p2, p3, p4;
    private LocalDate dataScadenza = LocalDate.of(2026, Month.JANUARY, 10);
    
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
        Prestito.setContatore(0); 
        lista = new ListaPrestiti();

        // Setup Utenti
        utenteLibero = new Utente("Marco", "Neri", "0000000001", "marco@mail.it");
        utentePieno = new Utente("Sara", "Gialli", "0000000002", "sara@mail.it");
        
        // Setup Libri
        libroDisponibile = new Libro("Libro OK", "Autore OK", "978-8800000001", 2000, 5); // 5 copie
        libroNonDisponibile = new Libro("Libro NO", "Autore NO", "978-8800000002", 2000, 1); // 1 copia
        
        // 3 prestiti per riempire l'utentePieno 
        
        p1 = new Prestito(utentePieno, libroDisponibile, dataScadenza.plusDays(1)); // 1/3
        p2 = new Prestito(utentePieno, libroDisponibile, dataScadenza.plusDays(2)); // 2/3
        p3 = new Prestito(utentePieno, libroNonDisponibile, dataScadenza.plusDays(3)); // 3/3
        
        // Aggiungiamo i prestiti alla lista
        lista.aggiungi(p1);
        lista.aggiungi(p2);
        lista.aggiungi(p3);
    }
    
    @AfterEach
    public void tearDown() {
        lista = null;
        utenteLibero = null;
        utentePieno = null;
        libroDisponibile = null;
        libroNonDisponibile = null;
        p1 = null;
        p2 = null;
        p3 = null;
        Prestito.setContatore(0);
    }

    /**
     * Test of getElenco method, of class ListaPrestiti.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        
        ObservableList<Prestito> result = lista.getElenco();
        assertNotNull(result, "getElenco non deve restituire null.");
        
        // Verifica che ci siano tre prestiti aggiunti in setUp
        assertEquals(3, result.size(), "La lista deve contenere 3 prestiti dopo il setup.");
        assertTrue(result.contains(p1));
        
    }

    /**
     * Test of aggiungi method, of class ListaPrestiti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        
        Prestito pStorico = new Prestito(utenteLibero, libroDisponibile, LocalDate.now().minusDays(30));
        
        assertEquals(3, lista.getElenco().size(), "Pre-condizione: dimensione iniziale 3.");
        
        
        lista.aggiungi(pStorico);
        
        
        assertEquals(4, lista.getElenco().size(), "La dimensione della lista deve essere aumentata.");
        assertTrue(lista.getElenco().contains(pStorico), "Il prestito storico deve essere nella lista.");
        
    }

    /**
     * Test of rimuovi method, of class ListaPrestiti.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        
        assertTrue(lista.getElenco().contains(p1), "p1 deve essere presente in lista.");
        
        
        lista.rimuovi(p1);

        
        assertFalse(lista.getElenco().contains(p1), "p1 deve essere stato rimosso.");
        assertEquals(2, lista.getElenco().size(), "La lista deve contenere 2 elementi.");
        
    }

    /**
     * Test of checkID method, of class ListaPrestiti.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        
        String idEsistente = "1";
        
        String idNonEsistente = "999"; 
        
        // Verifica che l'ID esistente sia rilevato (true)
        assertTrue(lista.checkID(idEsistente), "L'ID esistente deve essere trovato.");
        
        // Verifica che l'ID non esistente non sia rilevato (false)
        assertFalse(lista.checkID(idNonEsistente), "L'ID non esistente non deve essere trovato.");
        
    }

    /**
     * Test of addPrestito method, of class ListaPrestiti.
     */
    @Test
    public void testAddPrestito_Valido() throws PrestitoNonValidoException {
        System.out.println("addPrestito - Valido");
        
        int sizeIniziale = lista.getElenco().size(); // 3
        int copieIniziali = libroDisponibile.getCopieDisponibili(); // 3
        int prestitiUtenteIniziali = utenteLibero.getPrestiti().size(); // 0
        
        // Esecuzione
        lista.addPrestito(utenteLibero, libroDisponibile, dataScadenza);
        
        // Verifica esito
        assertEquals(sizeIniziale + 1, lista.getElenco().size(), "La lista prestiti deve aumentare di 1.");
        assertEquals(copieIniziali - 1, libroDisponibile.getCopieDisponibili(), "Le copie disponibili del libro devono essere decrementate.");
        assertEquals(prestitiUtenteIniziali + 1, utenteLibero.getPrestiti().size(), "L'utente libero deve avere 1 prestito attivo.");
        
        // Verifica che il nuovo prestito abbia ID 4
        assertEquals("4", lista.getElenco().get(sizeIniziale).getID(), "Il nuovo prestito deve avere ID 4.");
    }
    
    /**
     * Test of addPrestito method, of class ListaPrestiti (Caso Non Valido: Libro).
     */
    @Test
    public void testAddPrestito_LibroNonDisponibile() {
        System.out.println("addPrestito - Libro o Utente non disponibili");

        
        // Stato: utenteLibero ha 0 prestiti. libroNonDisponibile ha 0 copie.
        assertFalse(libroNonDisponibile.checkDisponibilita(), "Il libroNonDisponibile deve avere 0 copie.");
        
        // Eccezione a causa del libro
        assertThrows(PrestitoNonValidoException.class, () -> {
            lista.addPrestito(utenteLibero, libroNonDisponibile, dataScadenza.plusDays(1));
        }, "Deve fallire per mancanza di copie del libro.");
        
        // Post-condizioni (assicuriamo che lo stato non sia cambiato)
        assertEquals(0, libroNonDisponibile.getCopieDisponibili(), "Le copie disponibili non devono cambiare.");
        assertEquals(0, utenteLibero.getPrestiti().size(), "I prestiti dell'utente non devono cambiare.");
        
        
        assertFalse(utentePieno.checkDisponibilita(), "Il libroSingolo deve avere 0 copie.");
        
        assertThrows(PrestitoNonValidoException.class, () -> {
            lista.addPrestito(utentePieno, libroDisponibile, dataScadenza.plusDays(1));
        }, "Deve fallire perchè l'utente ha già 3 prestiti attivi.");
        
    }

    /**
     * Test of disattiva method, of class ListaPrestiti.
     */
    @Test
    public void testDisattiva() {
        System.out.println("disattiva");
        
        int copieIniziali = libroDisponibile.getCopieDisponibili(); // 3
        
        // Pre-condizioni:
        assertTrue(p1.isAttivo(), "Il prestito deve essere attivo.");
        assertTrue(utentePieno.getPrestiti().contains(p1), "L'utente deve avere il prestito p1 nella sua lista.");
        
        // Esecuzione
        lista.disattiva(p1);
        
        // Post-condizioni
        assertFalse(p1.isAttivo(), "Il prestito deve risultare disattivato.");
        assertEquals(copieIniziali + 1, libroDisponibile.getCopieDisponibili(), "Le copie disponibili del libro devono essere incrementate.");
        assertFalse(utentePieno.getPrestiti().contains(p1), "L'utente non deve più avere il prestito nella sua lista attiva.");
        
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Utente() {
        System.out.println("ricerca");
        
        // utentePieno ha 3 prestiti attivi (p1, p2, p3)
        ObservableList<Prestito> result1 = lista.ricerca(utentePieno);
        assertEquals(3, result1.size(), "La ricerca per utentePieno deve restituire 3 prestiti.");
        assertTrue(result1.contains(p1));
        
        // utenteLibero non ha prestiti attivi nel setup
        ObservableList<Prestito> result2 = lista.ricerca(utenteLibero);
        assertTrue(result2.isEmpty(), "La ricerca per utenteLibero deve restituire 0 prestiti.");
        
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Libro() {
        System.out.println("ricerca");
        
        ObservableList<Prestito> result1 = lista.ricerca(libroDisponibile);
        assertEquals(2, result1.size(), "La ricerca per libroDisponibile deve restituire 2 prestiti.");
        assertTrue(result1.contains(p2));

        
        ObservableList<Prestito> result2 = lista.ricerca(libroNonDisponibile);
        assertEquals(1, result2.size(), "La ricerca per libroNonDisponibile deve restituire 1 prestiti.");
        assertTrue(result2.contains(p3));
        
        Libro fasullo= new Libro("Non", "Ho", "978-0000000000", 200, 3);
        
        ObservableList<Prestito> result3 = lista.ricerca(fasullo);
        assertTrue(result3.isEmpty(), "La ricerca per fasullo deve restituire 0 prestiti.");
        
        
    }

    
    
}
