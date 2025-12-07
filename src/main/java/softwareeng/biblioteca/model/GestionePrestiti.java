/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import javafx.collections.ObservableList;
import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;


/**
 * @file GestionePrestiti.java
 * @brief Interfaccia per la definizione delle operazioni di gestione dei prestiti.
 * @version 1.0
 */

/**
 * @brief Interfaccia che definisce il contratto per la gestione della collezione di prestiti.
 *
 * Estende l'interfaccia generica Gestione<T> specializzandola per la classe Prestito.
 * Gestisce il ciclo di vita dei prestiti (creazione, ricerca, chiusura/disattivazione)
 * e garantisce la consistenza tra la disponibilità dei libri e lo stato degli utenti.
 *
 * @see Gestione
 * @see Prestito
 * @see ListaPrestiti
 */
public interface GestionePrestiti extends Gestione<Prestito> {

    /**
     * @brief Restituisce l'elenco completo dei prestiti registrati nel sistema.
     *
     * @return ObservableList<Prestito> La lista osservabile di tutti i prestiti (attivi e storici).
     */
    @Override
    public ObservableList<Prestito> getElenco();

    /**
     * @brief Aggiunge un prestito già formato alla lista.
     *
     * Operazione di basso livello per l'inserimento diretto. Per la creazione
     * di nuovi prestiti con validazione, utilizzare addPrestito().
     *
     * @param[in] prestito L'oggetto Prestito da aggiungere.
     * @pre prestito != null
     * @post getElenco().contains(prestito) == true
     */
    @Override
    public void aggiungi(Prestito prestito);

    /**
     * @brief Rimuove un prestito dal sistema.
     *
     * @param[in] prestito L'oggetto Prestito da rimuovere.
     * @pre prestito != null
     * @post getElenco().contains(prestito) == false
     */
    @Override
    public void rimuovi(Prestito prestito);

   /**
     * @brief Verifica l'esistenza di un Prestito tramite id.
     *
     * @param[in] id L'id da verificare.
     * @return true se il prestito è già presente nel sistema, false altrimenti.
     */
    @Override
    public boolean checkID(String id);   
    
/**
     * @brief Crea e registra un nuovo prestito tra un utente e un libro.
     *
     * Questo metodo coordina la transazione di prestito verificando le regole di business:
     * disponibilità delle copie e idoneità dell'utente.
     *
     * @param[in] utente L'utente che richiede il prestito.
     * @param[in] libro Il libro selezionato per il prestito.
     *
     * @pre utente != null && libro != null
     *
     * @post Viene creato un nuovo oggetto Prestito.
     * @post Il prestito viene aggiunto alla lista.
     * @post Le copie disponibili del libro vengono decrementate (libro.downCopie()).
     * @post Il prestito viene aggiunto ai prestiti attivi dell'utente.
     *
     * @throws PrestitoNonValidoException Se l'utente ha raggiunto il limite o il libro non ha copie disponibili.
     */
    public void addPrestito(Utente utente, Libro libro) throws PrestitoNonValidoException;

    /**
     * @brief Ricerca i prestiti associati a uno specifico utente.
     *
     * Metodo definito nel Diagramma delle Classi.
     *
     * @param[in] u L'utente di cui cercare i prestiti.
     * @return ObservableList<Prestito> Lista dei prestiti associati all'utente.
     */
    public ObservableList<Prestito> ricerca(Utente u);

    /**
     * @brief Ricerca i prestiti associati a uno specifico libro.
     *
     * Metodo definito nel Diagramma delle Classi.
     *
     * @param[in] l Il libro di cui cercare i prestiti (attivi o passati).
     * @return ObservableList<Prestito> Lista dei prestiti associati al libro.
     */
    public ObservableList<Prestito> ricerca(Libro l);

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
    public void disattiva(Prestito p);
}
