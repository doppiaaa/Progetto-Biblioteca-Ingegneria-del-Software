/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;

import softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @file Catalogo.java
 * @brief File contenente l'implementazione della classe Catalogo.
 * @author cashrules
 * @version 1.0
 */

/**
 * @brief Classe concreta per la gestione del catalogo dei libri.
 *
 * Questa classe implementa l'interfaccia GestioneLibri e l'interfaccia Serializable.
 * Funge da archivio centrale per tutti i libri della biblioteca, gestendo l'inserimento,
 * la rimozione con controlli di integrità e le ricerche specifiche.
 *
 * @see GestioneLibri
 * @see Libro
 */
public class Catalogo implements GestioneLibri {
    /**
     * @brief Lista osservabile contenente i libri del catalogo.
     * Utilizzata per il data binding con la UI.
     */
    private ObservableList<Libro> libri;

    /**
     * @brief Costruttore predefinito.
     *
     * Inizializza la lista dei libri come una collezione vuota osservabile.
     *
     * @post libri != null && libri.isEmpty() == true
     */
    public Catalogo(){
        this.libri = FXCollections.observableArrayList();
    }

    /**
     * @brief Restituisce l'elenco completo dei libri.
     *
     * @return ObservableList<Libro> La lista di tutti i libri nel catalogo.
     */
    @Override
    public ObservableList<Libro> getElenco(){
        return this.libri;
    }

    /**
     * @brief Aggiunge un nuovo libro al catalogo.
     *
     * @param[in] libro L'oggetto Libro da aggiungere.
     *
     * @pre libro != null
     * @pre checkID(libro.getISBN()) == false (L'ISBN deve essere univoco)
     * @post libri.contains(libro) == true
     * @post libri.size() == old(libri.size()) + 1
     */
    @Override
    public void aggiungi(Libro libro){
        
        this.libri.add(libro);
    }

    /**
     * @brief Rimuove un libro dal catalogo.
     *
     * Esegue un controllo preventivo per assicurarsi che il libro non abbia copie
     * attualmente in prestito (checkPrestiti).
     *
     * @param[in] libro L'oggetto Libro da rimuovere.
     *
     * @pre libro != null
     * @post libri.contains(libro) == false
     *
     * @throws EliminazioneNonValidaException Se il libro ha copie in prestito (violazione integrità).
     */
    @Override
    public void rimuovi(Libro libro) throws EliminazioneNonValidaException{
        
        if (!libro.checkPrestiti()) {
            throw new EliminazioneNonValidaException("Impossibile rimuovere il libro: ci sono ancora copie in prestito.");
        }
        this.libri.remove(libro);
    }

    /**
     * @brief Modifica i dati di un libro esistente.
     *
     * @param[in] libro L'oggetto Libro da modificare.
     * @param[in] attributi La Mappa con le coppie id-valore da inserire.
     * @pre libro != null
     * @pre libri.contains(libro) == true
     */
    @Override
    public void modifica(Libro libro, Map<String, Object> attributi){
        libro.modifica(attributi);
        
        //Notifica alla ObservableList (per aggiornare la UI)
        
        int index = this.libri.indexOf(libro);
        if (index != -1) {
            // Sostituisce l'elemento con sé stesso.
            this.libri.set(index, libro); 
        }
    }

    /**
     * @brief Verifica l'esistenza di un libro tramite ISBN.
     *
     * @param[in] id Il codice ISBN da verificare.
     * @return true se l'ISBN è presente, false altrimenti.
     */
    @Override
    public boolean checkID(String id){
        for (Libro libro : libri){
            if(libro.getISBN().equals(id))
                return true;
        }
        return false;
    }

    /**
     * @brief Ricerca i libri per titolo.
     *
     * @param[in] titolo Stringa da cercare nel titolo.
     * @return ObservableList<Libro> Lista dei libri trovati.
     */
    @Override
    public ObservableList<Libro> ricercaTitolo(String titolo) {
        String ricerca= titolo.toLowerCase();
        
        List<Libro> risultati = libri.stream()
                .filter(libro -> libro.getTitolo().toLowerCase().contains(ricerca))
                .collect(Collectors.toList());
        
        return FXCollections.observableArrayList(risultati);
    }

    /**
     * @brief Ricerca i libri per autore.
     *
     * @param[in] autore Nome dell'autore da cercare.
     * @return ObservableList<Libro> Lista dei libri trovati.
     */
    @Override
    public ObservableList<Libro> ricercaAutore(String autore) {
        String ricerca= autore.toLowerCase();
        
        List<Libro> risultati = libri.stream()
                .filter(libro -> libro.getAutore().toLowerCase().contains(ricerca))
                .collect(Collectors.toList());
        
        return FXCollections.observableArrayList(risultati);
        
    }
    
    /**
     * @brief Ricerca un libro per ISBN.
     *
     * @param[in] isbn Codice ISBN esatto.
     * @return ObservableList<Libro> Lista contenente il libro trovato (o vuota).
     */
    @Override
    public ObservableList<Libro> ricercaISBN(String isbn) {
        String ricerca= isbn.toLowerCase();
        
        List<Libro> risultati = libri.stream()
                .filter(libro -> libro.getISBN().toLowerCase().contains(ricerca))
                .collect(Collectors.toList());
        
        return FXCollections.observableArrayList(risultati);
        
    }
    
    
    
}
