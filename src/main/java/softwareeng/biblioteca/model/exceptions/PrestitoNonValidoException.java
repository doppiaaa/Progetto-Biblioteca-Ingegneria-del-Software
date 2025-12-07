/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model.exceptions;

/**
 * @file PrestitoNonValidoException.java
 * @brief File contenente la definizione dell'eccezione PrestitoNonValidoException.
 * @version 1.0
 */

/**
 * @brief Eccezione lanciata quando un tentativo di prestito viola le regole di business.
 *
 * Questa eccezione viene sollevata dai gestori dei prestiti (vedi GestionePrestiti::addPrestito)
 * quando non è possibile completare la transazione. Le cause comuni includono:
 * - Il libro richiesto non ha copie disponibili (Libro::checkDisponibilità restituisce false).
 * - L'utente ha raggiunto il limite massimo di prestiti attivi o ha sanzioni (Utente::checkDisponibilità restituisce false).
 *
 * @see softwareeng.biblioteca.model.GestionePrestiti
 */
public class PrestitoNonValidoException extends RuntimeException {

   /**
     * @brief Costruttore predefinito.
     *
     * Crea una nuova istanza di PrestitoNonValidoException senza un messaggio di dettaglio specifico.
     */
    public PrestitoNonValidoException() {
    }

  /**
     * @brief Costruttore con messaggio di dettaglio.
     *
     * Crea una nuova istanza di PrestitoNonValidoException specificando la causa dell'errore.
     *
     * @param[in] msg Il messaggio descrittivo dell'errore (es. "Copie esaurite", "Utente non abilitato").
     */
    public PrestitoNonValidoException(String msg) {
        super(msg);
    }
}
