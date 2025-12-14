/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import javafx.collections.ObservableList;
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


public class CatalogoTest {
    
    private Catalogo catalogo;
    private Libro l1, l2, l3;
    
    public CatalogoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        //Inizializza il toolkit JavaFX per permettere il corretto funzionamento delle ObservableList
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        catalogo = new Catalogo();
        
        l1 = new Libro("Design Pattern", "Erich Gamma", "978-0201633610", 1994, 5); // 5 disponibili
        l2 = new Libro("Clean Code", "Robert C. Martin", "978-0132350884", 2008, 1); // 1 disponibile
        l3 = new Libro("Refactoring", "Martin Fowler", "978-0131175651", 1999, 3); // 3 disponibili
        
        catalogo.aggiungi(l1);
        catalogo.aggiungi(l2);
    }
    
    @AfterEach
    public void tearDown() {
        catalogo = null;
        l1 = null;
        l2 = null;
        l3 = null;
    }

    /**
     * Test of getElenco method, of class Catalogo.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        
        ObservableList<Libro> result = catalogo.getElenco();
        
        assertNotNull(result);
        assertEquals(2, result.size(), "La lista deve contenere 2 libri dopo il setup.");
        
        assertTrue(result.contains(l1));
        assertTrue(result.contains(l2));
        
        
    }

    /**
     * Test of aggiungi method, of class Catalogo.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        
        int dimensioneIniziale = catalogo.getElenco().size();
        
        catalogo.aggiungi(l3);
        assertEquals(dimensioneIniziale + 1, catalogo.getElenco().size(), "La dimensione della lista deve essere aumentata di 1.");
        assertTrue(catalogo.getElenco().contains(l3), "Il libro l3 deve essere nella lista.");
        
        
    }

    /**
     * Test of rimuovi method, of class Catalogo.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        
        //Simula un prestito attivo su l1 (l1 aveva 5 copie, ora 4 disponibili)
        l1.downCopie();
        
        assertFalse(l1.checkPrestiti());
        
        assertThrows(softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException.class, () -> {
            catalogo.rimuovi(l1);
        });
        assertTrue(catalogo.getElenco().contains(l1));
        assertEquals(2, catalogo.getElenco().size());
        
    }

    /**
     * Test of modifica method, of class Catalogo.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        
        String nuovoTitolo = "Titolo Cambiato";
        Map<String, Object> attributi = new HashMap<>();
        attributi.put("titolo", nuovoTitolo);
        attributi.put("autore", "Gianni");
        
        catalogo.modifica(l1, attributi);
        
        assertEquals(nuovoTitolo, l1.getTitolo(), "Il titolo deve essere stato aggiornato correttamente.");
        assertEquals("Gianni", l1.getAutore(), "L'autore deve essere stato aggiornato correttamente.");
        
        //test sulla modifica delle copie
        int nuoveCopieTotali = 10;
        attributi.clear();
        attributi.put("copietotali", nuoveCopieTotali);
        
        catalogo.modifica(l1, attributi);
        assertEquals(nuoveCopieTotali, l1.getCopieTotali(), "Le copie totali devono essere 10.");
        assertEquals(nuoveCopieTotali, l1.getCopieDisponibili(), "Le copie disponibili devono essere 10.");
        
        l2.downCopie(); // l2 ora: 1 Totale, 0 Disponibili (1 prestito attivo)
        int copieTotaliAttualiL2 = l2.getCopieTotali();
        
        attributi.clear();
        attributi.put("copietotali", 0);
        
        assertThrows(softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException.class, () -> {
            catalogo.modifica(l2, attributi);
        }, "La modifica che riduce le copie al di sotto dei prestiti attivi deve fallire.");
        
        
        assertEquals(copieTotaliAttualiL2, l2.getCopieTotali(), "Le copie totali di l2 non devono essere cambiate dopo il fallimento.");
        
    }

    /**
     * Test of checkID method, of class Catalogo.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        
        String idEsistente = l1.getISBN();
        
        String idNonEsistente = "000-0000000000";
        
        assertTrue(catalogo.checkID(idEsistente), "L'ISBN esistente deve essere trovato.");
        assertFalse(catalogo.checkID(idNonEsistente), "L'ISBN non esistente non deve essere trovato.");
        
    }

    /**
     * Test of ricercaTitolo method, of class Catalogo.
     */
    @Test
    public void testRicercaTitolo() {
        System.out.println("ricercaTitolo");
        
        //Ricerca per titolo completo (case-insensitive)
        ObservableList<Libro> result1 = catalogo.ricercaTitolo("clean code");
        assertEquals(1, result1.size(), "La ricerca per 'clean code' (completa) deve restituire 1 risultato.");
        assertTrue(result1.contains(l2), "Il risultato deve essere l2.");

        //Ricerca per parte di titolo (case-insensitive)
        ObservableList<Libro> result2 = catalogo.ricercaTitolo("design");
        assertEquals(1, result2.size(), "La ricerca per 'design' (parziale) deve restituire 1 risultato.");
        assertTrue(result2.contains(l1), "Il risultato deve essere l1.");

        //Ricerca senza risultati
        ObservableList<Libro> result3 = catalogo.ricercaTitolo("Libro Inesistente");
        assertTrue(result3.isEmpty(), "La ricerca per titolo inesistente deve restituire una lista vuota.");
        
    }

    /**
     * Test of ricercaAutore method, of class Catalogo.
     */
    @Test
    public void testRicercaAutore() {
        System.out.println("ricercaAutore");
        
        //Ricerca per nome completo (case-insensitive)
        ObservableList<Libro> result1 = catalogo.ricercaAutore("robert c. martin");
        assertEquals(1, result1.size(), "La ricerca per autore completo deve restituire 1 risultato.");
        assertTrue(result1.contains(l2), "Il risultato deve essere l2.");

        //Ricerca per parte di nome/cognome (es. solo "gamma")
        ObservableList<Libro> result2 = catalogo.ricercaAutore("gamma");
        assertEquals(1, result2.size(), "La ricerca per 'gamma' (parziale) deve restituire 1 risultato.");
        assertTrue(result2.contains(l1), "Il risultato deve essere l1.");
        
        //Ricerca senza risultati
        ObservableList<Libro> result3 = catalogo.ricercaAutore("Autore Sconosciuto");
        assertTrue(result3.isEmpty(), "La ricerca per autore inesistente deve restituire una lista vuota.");
       
    }

    /**
     * Test of ricercaISBN method, of class Catalogo.
     */
    @Test
    public void testRicercaISBN() {
        System.out.println("ricercaISBN");
        
        // ISBN di l1
        String isbnEsistente = "978-0201633610";
        
        // Ricerca per ISBN esistente
        ObservableList<Libro> result1 = catalogo.ricercaISBN(isbnEsistente);
        assertEquals(1, result1.size(), "La ricerca per ISBN esistente deve restituire 1 risultato.");
        assertEquals(l1, result1.get(0), "Il risultato deve essere il libro l1.");

        // Ricerca per ISBN inesistente
        ObservableList<Libro> result2 = catalogo.ricercaISBN("999-9999999999");
        assertTrue(result2.isEmpty(), "La ricerca per ISBN inesistente deve restituire una lista vuota.");
        
        // Ricerca per parte di ISBN 
        ObservableList<Libro> result3 = catalogo.ricercaISBN("020163");
        assertEquals(1, result3.size(), "La ricerca per parte di ISBN deve restituire l1.");
        assertEquals(l1, result3.get(0));
        
    }
    
}
