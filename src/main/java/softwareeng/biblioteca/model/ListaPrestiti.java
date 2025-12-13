/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
/**
 *
 * @author cashrules
 */

/**
 * @file ListaPrestiti.java
 * @brief File contenente l'implementazione della classe ListaPrestiti.
 * @version 1.0
 */

/**
 * @brief Classe concreta per la gestione dell'elenco dei prestiti della biblioteca.
 *
 * Questa classe implementa l'interfaccia GestionePrestiti e l'interfaccia Serializable.
 * Si occupa di memorizzare i prestiti correnti e gestisce la logica transazionale
 * per la creazione di nuovi prestiti, garantendo la consistenza tra disponibilità
 * dei libri e stato degli utenti.
 *
 * @see GestionePrestiti
 * @see Prestito
 */
public class ListaPrestiti implements Serializable, GestionePrestiti{
    /**
     * @brief Lista osservabile contenente tutti i prestiti (attivi e storici) gestiti dal sistema.
     */
    private ObservableList<Prestito> prestiti;

    /**
     * @brief Costruttore predefinito.
     *
     * Inizializza la lista dei prestiti come una collezione vuota osservabile tramite
     * FXCollections.observableArrayList().
     *
     * @post prestiti != null && prestiti.isEmpty() == true
     */
    public ListaPrestiti() {
        this.prestiti = FXCollections.observableArrayList();
    }

    /**
     * @brief Restituisce l'elenco completo dei prestiti.
     *
     * @return ObservableList<Prestito> La lista osservabile di tutti i prestiti.
     */
    @Override
    public ObservableList<Prestito> getElenco(){
        return this.prestiti;
    }

    /**
     * @brief Aggiunge un oggetto Prestito direttamente alla lista.
     *
     * Metodo di basso livello per inserire un prestito nella collezione (es. in fase di caricamento dati).
     *
     * @param[in] prestito L'oggetto Prestito da aggiungere.
     * @pre prestito != null
     * @post prestiti.contains(prestito) == true
     * @post prestiti.size() == old(prestiti.size()) + 1
     */
    @Override
    public void aggiungi(Prestito prestito){
        this.prestiti.add(prestito);
    }

    /**
     * @brief Rimuove un prestito dalla lista.
     *
     * @param[in] prestito L'oggetto Prestito da rimuovere.
     * @pre prestito != null
     * @post prestiti.contains(prestito) == false
     */
    @Override
    public void rimuovi(Prestito prestito){
        this.prestiti.remove(prestito);
    }

    /**
     * @brief Verifica l'esistenza di un prestito tramite il suo identificativo univoco.
     *
     * @param[in] id La stringa rappresentante l'ID del prestito.
     * @return true se esiste un prestito con quell'ID, false altrimenti.
     */
    @Override
    public boolean checkID(String id){
        for (Prestito p: prestiti){
            if(p.getID().equals(id))
                return true;
        }
        return false;
    }

    /**
     * @brief Crea e registra un nuovo prestito tra un utente e un libro.
     *
     * Questo metodo implementa la logica di business per l'erogazione di un prestito:
     * 1. Verifica che il libro abbia copie disponibili (@see Libro::checkDisponibilità).
     * 2. Verifica che l'utente sia abilitato al prestito (@see Utente::checkDisponibilità).
     * 3. Se i controlli hanno successo, crea il nuovo Prestito, decrementa le copie del libro
     * e registra il prestito nel sistema.
     *
     * @param[in] utente L'utente che richiede il prestito.
     * @param[in] libro Il libro da prestare.
     *
     * @pre utente != null && libro != null
     *
     * @post Un nuovo oggetto Prestito è creato e aggiunto alla lista prestiti.
     * @post libro.getCopieDisponibili() == libro.getCopieDisponibili()--
     * @post il prestito viene aggiunto alla lista dei prestti attivi dell'utente.
     * 
     * @throws PrestitoNonValidoException Se non ci sono copie disponibili o l'utente non può contrarre nuovi prestiti.
     */
    @Override
    public void addPrestito(Utente utente, Libro libro, LocalDate dataScadenza ) throws PrestitoNonValidoException{
        if(!libro.checkDisponibilita()){
            throw new PrestitoNonValidoException("impossibile registrare il prestito: non ci sono copie del libro disponibili.");
        }
        if(!utente.checkDisponibilita()){
            throw new PrestitoNonValidoException("impossibile registrare il prestito: l'utente ha già 3 prestiti attivi.");
        }
        Prestito p= new Prestito(utente, libro, dataScadenza);
        
        this.prestiti.add(p);
    }
    
    /**
     * @brief Disattiva (chiude) un prestito attivo.
     *
     * Gestisce la restituzione del libro.
     *
     * @param[in] p Il prestito da disattivare.
     *
     * @pre p.isAttivo() == true
     * @post p.isAttivo() == false
     * @post Il libro associato incrementa le copie disponibili (libro.upCopie()).
     * @post Il prestito viene scollegato dalla lista attiva dell'utente.
     */
    @Override 
    public void disattiva(Prestito p){
        p.disattiva();
    }
    
    /**
     * @brief Ricerca i prestiti associati a uno specifico utente.
     *
     * Metodo definito nel Diagramma delle Classi.
     *
     * @param[in] u L'utente di cui cercare i prestiti.
     * @return ObservableList<Prestito> Lista dei prestiti associati all'utente.
     */
    public ObservableList<Prestito> ricerca(Utente u){
        List<Prestito> ricerca= prestiti.stream()
                .filter(prestito -> prestito.getUtente().equals(u))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(ricerca);

    }

    /**
     * @brief Ricerca i prestiti associati a uno specifico libro.
     *
     * Metodo definito nel Diagramma delle Classi.
     *
     * @param[in] l Il libro di cui cercare i prestiti (attivi o passati).
     * @return ObservableList<Prestito> Lista dei prestiti associati al libro.
     */
    public ObservableList<Prestito> ricerca(Libro l){
        List<Prestito> ricerca= prestiti.stream()
                .filter(prestito -> prestito.getLibro().equals(l))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(ricerca);

    }
    
    /**
     * @brief NON IMPLEMENTATO. 
     * Il prestito non è un'entità modificabile (solo creato o chiuso).
     * Questo metodo è richiesto dall'interfaccia Gestione<T> ma è una No-Op.
     * * @param prestito L'oggetto Prestito da modificare.
     * @param attributi La Mappa degli attributi (ignorata).
     */
    @Override
    public void modifica(Prestito prestito, Map<String, Object> attributi) {
        // Nessuna operazione
    }

}
