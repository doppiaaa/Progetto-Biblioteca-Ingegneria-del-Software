/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;

import javafx.collections.ObservableList;

/**
 * @file GestioneUtenti.java
 * @brief Interfaccia per la definizione delle operazioni di gestione degli utenti.
 * @version 1.0
 */

/**
 * @brief Interfaccia che definisce il contratto per la gestione della collezione di utenti.
 *
 * Estende l'interfaccia generica Gestione<T> specializzandola per la classe Utente.
 * Oltre alle operazioni CRUD standard, definisce metodi di ricerca specifici per
 * gli attributi degli utenti (cognome, matricola).
 *
 * @see Gestione
 * @see Utente
 * @see ListaUtenti
 */
public interface GestioneUtenti extends Gestione<Utente> {
    /**
     * @brief Restituisce l'elenco completo degli utenti gestiti.
     *
     * @return ObservableList<Utente> La lista osservabile di tutti gli utenti.
     */
    @Override
    public ObservableList<Utente> getElenco();

    /**
     * @brief Aggiunge un nuovo utente al sistema.
     *
     * @param[in] utente L'oggetto Utente da inserire.
     *
     * @pre utente != null
     * @pre checkID(utente.getMatricola()) == false (Unicità della matricola)
     * @post getElenco().contains(utente) == true
     * @post getElenco().size() == getElenco().size()++
     */
    @Override
    public void aggiungi(Utente utente);

    /**
     * @brief Rimuove un utente dal sistema.
     *
     * Verifica l'integrità referenziale prima della rimozione: l'utente non deve
     * avere prestiti attivi.
     *
     * @param[in] utente L'oggetto Utente da rimuovere.
     *
     * @pre utente != null
     * @pre getElenco().contains(utente) == true
     *
     * @post getElenco().contains(utente) == false
     * @post getElenco().size() == getElenco().size()--
     *
     * @throws EliminazioneNonValidaException Se l'utente ha prestiti ancora attivi.
     */
    @Override
    public void rimuovi(Utente utente);

    /**
     * @brief Verifica l'esistenza di un utente tramite matricola.
     *
     * @param[in] id La matricola da verificare.
     * @return true se la matricola è già presente nel sistema, false altrimenti.
     */
    @Override
    public boolean checkID(String id);

    /**
     * @brief Modifica i dati di un utente esistente.
     *
     * Aggiorna le informazioni di un utente già presente nell'elenco.
     *
     * @param[in] utente L'oggetto Utente con i dati aggiornati.
     *
     * @pre utente != null
     * @pre getElenco().contains(utente) == true
     * @post L'utente presente in lista riflette i nuovi dati passati.
     */
    public void modifica(Utente utente);

    /**
     * @brief Ricerca gli utenti per cognome.
     *
     * Metodo specifico definito nel diagramma delle classi per filtrare l'elenco.
     *
     * @param[in] cognome Il cognome (o parte di esso) da ricercare.
     * @return ObservableList<Utente> Una sottolista contenente gli utenti che corrispondono al criterio.
     */
    public ObservableList<Utente> ricercaCognome(String cognome);

    /**
     * @brief Ricerca un utente specifico per matricola.
     *
     * Metodo specifico definito nel diagramma delle classi.
     *
     * @param[in] matricola La matricola esatta da ricercare.
     * @return ObservableList<Utente> Una lista contenente l'utente trovato (o vuota).
     */
    public ObservableList<Utente> ricercaMatricola(String matricola);

}
