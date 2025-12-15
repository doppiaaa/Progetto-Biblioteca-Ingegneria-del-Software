/*
* Gruppo 06 Ingegneria del software
* Biblioteca
 */
package softwareeng.biblioteca.model;

import java.util.*;
import javafx.collections.ObservableList;
import java.time.LocalDate;
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
    
    private ListaUtenti lista;
    private Utente u1, u2, u3;
    
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
        // Inizializza una lista utenti vuota
        lista = new ListaUtenti();
        
        // Creazione di utenti di test
        u1 = new Utente("Andrea", "Bianchi", "0000000001", "a.bianchi@mail.it");
        u2 = new Utente("Giulia", "Rossi", "0000000002", "g.rossi@mail.it");
        u3 = new Utente("Mario", "Verdi", "0000000003", "m.verdi@mail.it");
        
        // Aggiunge u1 e u2 alla lista per i test (2 elementi in lista)
        lista.aggiungi(u1);
        lista.aggiungi(u2);
    }
    
    @AfterEach
    public void tearDown() {
        lista = null;
        u1 = null;
        u2 = null;
        u3 = null;
    }

    /**
     * Test of getElenco method, of class ListaUtenti.
     */
    @Test
    public void testGetElenco() {
        System.out.println("getElenco");
        
        assertEquals(2, lista.getElenco().size(), "La lista deve contenere 2 utenti dopo il setup.");
        assertTrue(lista.getElenco().contains(u1));
        
    }

    /**
     * Test of aggiungi method, of class ListaUtenti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        lista.aggiungi(u3);
        assertEquals(3, lista.getElenco().size(), "La dimensione della lista deve essere 3.");
        assertTrue(lista.getElenco().contains(u3), "L'utente u3 deve essere nella lista.");
        
    }

    /**
     * Test of rimuovi method, of class ListaUtenti.
     */
    @Test
    public void testRimuovi() {
        System.out.println("rimuovi");
        
        //Rimuovi Valido
        assertTrue(u2.checkPrestiti(), "u2 non deve avere prestiti attivi.");

        
        lista.rimuovi(u2);

        
        assertFalse(lista.getElenco().contains(u2), "u2 deve essere stato rimosso.");
        assertEquals(1, lista.getElenco().size(), "La lista deve contenere un solo elemento (u1).");
        
        
        //Rimuovi con Eccezione
        Libro libroFittizio = new Libro("Test Prestito", "Fittizio", "978-0000000001", 2024, 1);
        
        
        Prestito p = new Prestito(u1, libroFittizio, LocalDate.now().plusDays(10));
        
        //u1 ha prestiti attivi (checkPrestiti() è false)
        assertFalse(u1.checkPrestiti(), "u1 deve avere prestiti attivi per questo test.");

        
        assertThrows(softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException.class, () -> {
            lista.rimuovi(u1);
        }, "La rimozione deve fallire per la presenza di prestiti attivi.");

        //Lo stato non deve essere cambiato
        assertTrue(lista.getElenco().contains(u1), "L'utente deve ancora essere presente nella lista.");
        
        
       
    }

    /**
     * Test of checkID method, of class ListaUtenti.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        
        String idEsistente = u1.getMatricola();
        String idNonEsistente = "0000000099"; 
        
        // Verifica che l'ID esistente sia rilevato (true)
        assertTrue(lista.checkID(idEsistente), "La matricola esistente deve essere trovata.");
        
        // Verifica che l'ID non esistente non sia rilevato (false)
        assertFalse(lista.checkID(idNonEsistente), "La matricola non esistente non deve essere trovata.");
        
    }

    /**
     * Test of ricercaCognome method, of class ListaUtenti.
     */
    @Test
    public void testRicercaCognome() {
        System.out.println("ricercaCognome");
        //Ricerca per cognome completo (case-insensitive)
        ObservableList<Utente> result1 = lista.ricercaCognome("rossi");
        assertEquals(1, result1.size(), "La ricerca per 'rossi' (completa) deve restituire 1 risultato.");
        assertTrue(result1.contains(u2), "Il risultato deve essere u2.");

        //Ricerca per parte di cognome (case-insensitive)
        ObservableList<Utente> result2 = lista.ricercaCognome("bia");
        assertEquals(1, result2.size(), "La ricerca per 'bia' (parziale) deve restituire 1 risultato.");
        assertTrue(result2.contains(u1), "Il risultato deve essere u1.");

        //Ricerca senza risultati
        ObservableList<Utente> result3 = lista.ricercaCognome("Inesistente");
        assertTrue(result3.isEmpty(), "La ricerca per cognome inesistente deve restituire una lista vuota.");
        
    }

    /**
     * Test of ricercaMatricola method, of class ListaUtenti.
     */
    @Test
    public void testRicercaMatricola() {
        System.out.println("ricercaMatricola");
        //Ricerca per matricola completo (case-insensitive)
        ObservableList<Utente> result1 = lista.ricercaMatricola("0000000002");
        assertEquals(1, result1.size(), "La ricerca per '0000000002' (completa) deve restituire 1 risultato.");
        assertTrue(result1.contains(u2), "Il risultato deve essere u2.");

        //Ricerca per parte di matricola (case-insensitive)
        ObservableList<Utente> result2 = lista.ricercaMatricola("01");
        assertEquals(1, result2.size(), "La ricerca per '01' (parziale) deve restituire 1 risultato.");
        assertTrue(result2.contains(u1), "Il risultato deve essere u1.");

        //Ricerca senza risultati
        ObservableList<Utente> result3 = lista.ricercaMatricola("Inesistente");
        assertTrue(result3.isEmpty(), "La ricerca per matricola inesistente deve restituire una lista vuota.");
        
    }

    /**
     * Test of modifica method, of class ListaUtenti.
     */
    @Test
    public void testModifica() {
        System.out.println("modifica");
        
        //Dati per la modifica
        String nuovoNome = "Mario Junior";
        String nuovoCognome = "Bianchi-Rossi";
        
        Map<String, Object> attributi = new HashMap<>();
        attributi.put("nome", nuovoNome);
        attributi.put("cognome", nuovoCognome);
        
        
        // Modifichiamo l'oggetto u1 che è in lista
        lista.modifica(u1, attributi);
        
        //Verifica delle modifiche (sull'utente originale)
        assertEquals(nuovoNome, u1.getNome(), "Il nome deve essere stato aggiornato correttamente.");
        assertEquals(nuovoCognome, u1.getCognome(), "Il cognome deve essere stato aggiornato correttamente.");
        
        // La lista non dovrebbe cambiare dimensione
        assertEquals(2, lista.getElenco().size(), "La dimensione della lista non deve cambiare.");
        
    }
    
}
