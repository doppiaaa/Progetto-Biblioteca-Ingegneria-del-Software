/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.util.*;
import java.time.LocalDate;
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

        // 1. Setup Utenti
        utenteLibero = new Utente("Marco", "Neri", "U001", "marco@mail.it");
        utentePieno = new Utente("Sara", "Gialli", "U002", "sara@mail.it");
        
        // 2. Setup Libri
        libroDisponibile = new Libro("Libro OK", "Autore OK", "L111", 2000, 2); // 2 copie
        libroNonDisponibile = new Libro("Libro NO", "Autore NO", "L222", 2000, 1); // 1 copia

        // 3. Simula 3 prestiti per riempire l'utentePieno (BF-1.3)
        // Nota: la creazione di Prestito aggiorna automaticamente l'utente e il libro.
        p1 = new Prestito(utentePieno, libroDisponibile, dataScadenza.plusDays(1)); // 1/3
        p2 = new Prestito(utentePieno, libroDisponibile, dataScadenza.plusDays(2)); // 2/3
        p3 = new Prestito(utentePieno, libroDisponibile, dataScadenza.plusDays(3)); // 3/3
        
        // Aggiungiamo i prestiti alla lista del gestore (altrimenti addPrestito fallirà nel test)
        lista.aggiungi(p1);
        lista.aggiungi(p2);
        lista.aggiungi(p3);
    }
    
    @AfterEach
    public void tearDown() {
        
        lista = null;
        Prestito.setContatore(0);
    }

    /**
     * Test of getElenco method, of class ListaPrestiti.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        
    }

    /**
     * Test of aggiungi method, of class ListaPrestiti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        
    }

    /**
     * Test of rimuovi method, of class ListaPrestiti.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        
    }

    /**
     * Test of checkID method, of class ListaPrestiti.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        
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
        
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Utente() {
        System.out.println("ricerca");
        
    }

    /**
     * Test of ricerca method, of class ListaPrestiti.
     */
    @Test
    public void testRicerca_Libro() {
        System.out.println("ricerca");
        
    }

    /**
     * Test of modifica method, of class ListaPrestiti.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        
    }
    
}
