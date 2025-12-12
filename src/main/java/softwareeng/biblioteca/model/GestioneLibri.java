/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;

import javafx.collections.ObservableList;
import softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException;
import java.util.Map;
/**
 * @file GestioneLibri.java
 * @brief Interfaccia per la definizione delle operazioni di gestione dei libri.
 * @version 1.0
 */

/**
 * @brief Interfaccia che definisce il contratto per la gestione del catalogo libri.
 *
 * Estende l'interfaccia generica Gestione<T> specializzandola per la classe Libro.
 * Oltre alle operazioni CRUD standard (Create, Read, Update, Delete), definisce
 * metodi specifici per la ricerca di libri basati sui loro attributi (Titolo, Autore, ISBN).
 *
 * @see Gestione
 * @see Libro
 * @see Catalogo
 */
public interface GestioneLibri extends Gestione<Libro> {
    /**
     * @brief Restituisce l'elenco completo dei libri presenti nel catalogo.
     *
     * @return ObservableList<Libro> La lista osservabile di tutti i libri.
     */
    @Override
    public abstract ObservableList<Libro> getElenco();
    /**
     * @brief Aggiunge un nuovo libro al catalogo.
     *
     * @param[in] libro L'oggetto Libro da aggiungere.
     *
     * @pre libro != null
     * @pre checkID(libro.getISBN()) == false (Il libro con questo ISBN non deve esistere)
     * @post getElenco().contains(libro) == true
     * @post getElenco().size() == getElenco().size()++
     */
    @Override
    public abstract void aggiungi(Libro libro);
    /**
     * @brief Rimuove un libro dal catalogo.
     *
     * Prima di rimuovere il libro, verifica che non ci siano prestiti attivi associati
     * ad esso per mantenere l'integrità del database.
     *
     * @param[in] libro L'oggetto Libro da rimuovere.
     *
     * @pre libro != null
     * @pre getElenco().contains(libro) == true
     *
     * @post getElenco().contains(libro) == false
     * @post getElenco().size() == getElenco().size()--
     *
     * @throws EliminazioneNonValidaException Se il libro ha copie attualmente in prestito.
     */
    @Override
    public abstract void rimuovi(Libro libro);
   /**
     * @brief Modifica i dati di un libro esistente.
     *
     * @param[in] libro L'oggetto Libro daModificare.
     * @param[in] attributi La Mappa di coppie id-valore di dati da sovrascrivere.
     * @pre libro != null
     * @pre getElenco().contains(libro) == true
     * @post Il libro nel catalogo riflette i nuovi attributi.
     */
    @Override
    public void modifica(Libro libro, Map<String, Object> attributi);

    /**
     * @brief Verifica l'esistenza di un libro tramite il suo codice ISBN.
     *
     * Utilizzato per garantire l'unicità dei libri all'interno del catalogo.
     *
     * @param[in] id Il codice ISBN da verificare.
     * @return true se l'ISBN è già presente, false altrimenti.
     */
    @Override
    public abstract boolean checkID(String id);

    /**
     * @brief Ricerca i libri che contengono la stringa specificata nel titolo.
     *
     * @param[in] titolo Il titolo (o parte di esso) da cercare.
     * @return ObservableList<Libro> Una lista di libri che corrispondono ai criteri di ricerca.
     */
    public ObservableList<Libro> ricercaTitolo(String titolo);

    /**
     * @brief Ricerca i libri scritti da un determinato autore.
     *
     * @param[in] autore Il nome dell'autore da cercare.
     * @return ObservableList<Libro> Una lista di libri dell'autore specificato.
     */
    public ObservableList<Libro> ricercaAutore(String autore);

    /**
     * @brief Ricerca un libro tramite il suo codice ISBN.
     *
     * @param[in] isbn Il codice ISBN esatto da cercare.
     * @return ObservableList<Libro> Una lista contenente il libro trovato (o vuota se non trovato).
     */
    public ObservableList<Libro> ricercaISBN(String isbn);
    

}
