/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
  
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Map;
/**
 * @file Gestione.java
 * @brief Interfaccia generica per la gestione delle collezioni di entità del sistema.
 * @version 1.0
 */

/**
 * @brief Interfaccia base che definisce le operazioni CRUD standard.
 *
 * Questa interfaccia utilizza i Generics per fornire un contratto comune a tutti i gestori
 * (Libri, Utenti, Prestiti), garantendo uniformità nelle operazioni di accesso e modifica
 * delle liste osservabili utilizzate dall'interfaccia JavaFX.
 *
 * @tparam T Il tipo di entità gestita (es. Libro, Utente, Prestito).
 */
public abstract interface Gestione<T> {
 
    /**
     * @brief Restituisce l'elenco completo degli elementi gestiti.
     *
     * @return ObservableList<T> La lista osservabile contenente tutti gli elementi.
     */
    public abstract ObservableList<T> getElenco();

    /**
     * @brief Aggiunge un nuovo elemento alla collezione.
     *
     * @param[in] elemento L'oggetto di tipo T da aggiungere.
     * @pre elemento != null
     * @post getElenco().contains(elemento) == true
     */
    public abstract void aggiungi(T elemento);

    /**
     * @brief Rimuove un elemento dalla collezione.
     *
     * Nota: Le implementazioni specifiche possono lanciare eccezioni (es. EliminazioneNonValidaException)
     * se la rimozione viola vincoli di integrità referenziale.
     *
     * @param[in] elemento L'oggetto di tipo T da rimuovere.
     * @pre elemento != null
     * @pre getElenco().contains(elemento) == true
     * @post getElenco().contains(elemento) == false
     */
    public abstract void rimuovi(T elemento);

    /**
     * @brief Verifica l'univocità di un identificativo all'interno della collezione.
     *
     * Utilizzato per prevenire duplicati al momento dell'inserimento (es. ISBN duplicato, Matricola duplicata).
     *
     * @param[in] id La stringa identificativa da verificare.
     * @return true se l'ID è già presente, false altrimenti.
     */
    public abstract boolean checkID(String id);

    /**
     * @brief Modifica un elemento esistente nella collezione.
     * * @param[in] elemento L'oggetto di tipo T a cui applicare le modifiche.
     * * @param[in] attributi Mappa contenente la coppia id-valore dei dati da modificare.
     * @pre elemento != null
     * @pre getElenco().contains(elemento) == true
     * @post L'elemento nella collezione risulta aggiornato.
     */
    public abstract void modifica(T elemento, Map<String, Object> attributi);
    

}
