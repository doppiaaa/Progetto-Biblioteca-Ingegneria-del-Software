/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.time.*;

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
    
    private GestoreFile instance;
    private Catalogo catalogo;
    private ListaUtenti utenti;
    private ListaPrestiti prestiti;
    
    // Dati di test
    private Libro l1;
    private Utente u1;
    private Prestito p1;
    private final int CONTATORE_ATTESO = 1;
    
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
        Prestito.setContatore(0);
        instance = new GestoreFile();
        instance.reset(); // Assicurati che i file siano puliti prima di ogni test

        // Inizializzazione dei gestori in-memory
        catalogo = new Catalogo();
        utenti = new ListaUtenti();
        prestiti = new ListaPrestiti();

        // Popolamento dei dati di test (permette di controllare lo stato atteso)
        l1 = new Libro("Test Libro", "Test Autore", "ISBN-TEST-001", 2024, 2);
        u1 = new Utente("NomeTest", "CognomeTest", "MATRICOLA-001", "test@mail.it");
        
        // Aggiunta nei gestori
        catalogo.aggiungi(l1);
        utenti.aggiungi(u1);
        
        // La creazione del prestito incrementa il contatore statico
        try {
            
            prestiti.addPrestito(u1, l1, LocalDate.of(2030, Month.JANUARY, 1));
            // Qui Prestito.getContatore() dovrebbe essere CONTATORE_ATTESO (1)
            this.p1 = prestiti.getElenco().get(0);
        } catch (Exception e) {
            fail("Impossibile creare il prestito di setup: " + e.getMessage());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    public void testSalvaECaricaCorrettamente() {
        System.out.println("salva e carica");

        // 1. SALVA lo stato attuale (3 liste e contatore Prestito.cont = 1)
        instance.salva(catalogo, utenti, prestiti);

        // 2. RESETTA lo stato in memoria (Simula la chiusura e riapertura dell'app)
        catalogo.getElenco().clear();
        utenti.getElenco().clear();
        prestiti.getElenco().clear();
        Prestito.setContatore(0);
        
        // Controlli pre-caricamento
        assertEquals(0, catalogo.getElenco().size(), "Precondizione: Il catalogo in memoria deve essere vuoto.");
        assertEquals(0, Prestito.getContatore(), "Precondizione: Il contatore in memoria deve essere 0.");

        // 3. CARICA lo stato dai file
        instance.carica(catalogo, utenti, prestiti);

        // 4. VERIFICA l'integrità dei dati
        
        // Verifica dimensioni delle liste
        assertEquals(1, catalogo.getElenco().size(), "Il catalogo deve contenere 1 libro.");
        assertEquals(1, utenti.getElenco().size(), "La lista utenti deve contenere 1 utente.");
        assertEquals(1, prestiti.getElenco().size(), "La lista prestiti deve contenere 1 prestito.");

        // Verifica Contatore Statico
        assertEquals(CONTATORE_ATTESO, Prestito.getContatore(), "Il contatore ID deve essere stato ripristinato correttamente.");

        // Verifica contenuto del libro (copie totali e disponibili)
        Libro libroCaricato = catalogo.getElenco().get(0);
        assertEquals(l1.getCopieTotali(), libroCaricato.getCopieTotali(), "Copie totali libro non corrette.");
        // Le copie disponibili erano 1 dopo il prestito p1: 2 (totali) - 1 (prestito) = 1
        assertEquals(1, libroCaricato.getCopieDisponibili(), "Copie disponibili libro non corrette.");

        // Verifica che il prestito sia attivo e collegato all'utente/libro corretti
        Prestito prestitoCaricato = prestiti.getElenco().get(0);
        assertTrue(prestitoCaricato.isAttivo(), "Il prestito caricato deve risultare attivo.");
        assertEquals(u1.getMatricola(), prestitoCaricato.getUtente().getMatricola(), "Il prestito è associato all'utente sbagliato.");
    }

    /**
     * Test of reset method, of class GestoreFile.
     */
    @Test
    public void testReset() {
        System.out.println("reset");

        // Assicurati che ci siano dati da resettare (li abbiamo messi nel setUp, ma li salviamo esplicitamente per conferma)
        instance.salva(catalogo, utenti, prestiti);
        assertEquals(CONTATORE_ATTESO, Prestito.getContatore(), "Precondizione: Contatore statico > 0.");
        
        // Esecuzione del reset
        instance.reset();
        
        // 1. Verifica stato in memoria dopo il reset
        assertEquals(0, Prestito.getContatore(), "Il contatore statico deve essere azzerato dopo il reset.");
        
        // 2. Verifica dello stato dei file: proviamo a ricaricare.
        
        // Svuotiamo le liste in memoria
        catalogo.getElenco().clear();
        utenti.getElenco().clear();
        prestiti.getElenco().clear();

        // Carica (dovrebbe caricare file vuoti/troncati)
        instance.carica(catalogo, utenti, prestiti); 

        // 3. Verifica stato dopo il caricamento dal file resettato
        assertEquals(0, Prestito.getContatore(), "Dopo il reset e ricaricamento, il contatore deve essere 0.");
        assertTrue(catalogo.getElenco().isEmpty(), "Dopo il reset, il catalogo deve essere vuoto.");
        assertTrue(utenti.getElenco().isEmpty(), "Dopo il reset, la lista utenti deve essere vuota.");
        assertTrue(prestiti.getElenco().isEmpty(), "Dopo il reset, la lista prestiti deve essere vuota.");
    }
    
}
