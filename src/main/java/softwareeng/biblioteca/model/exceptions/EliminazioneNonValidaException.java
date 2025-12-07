/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model.exceptions;

/**
 * @file EliminazioneNonValidaException.java
 * @brief File contenente la definizione dell'eccezione EliminazioneNonValidaException.
 * @version 1.0
 */

/**
 * @brief Eccezione lanciata quando un tentativo di eliminazione viola i vincoli di integrità.
 *
 * Questa eccezione (di tipo RuntimeException) viene sollevata dai metodi di rimozione
 * nelle classi di gestione (es. GestioneLibri::rimuovi, GestioneUtenti::rimuovi) quando
 * l'entità che si sta cercando di eliminare ha dipendenze attive che non possono essere
 * ignorate.
 *
 * Esempi tipici:
 * - Tentativo di eliminare un Libro che è attualmente in prestito.
 * - Tentativo di eliminare un Utente che ha ancora libri in prestito non restituiti.
 *
 * @see softwareeng.biblioteca.model.Gestione
 */
public class EliminazioneNonValidaException extends RuntimeException {

    /**
     * @brief Costruttore predefinito.
     *
     * Crea una nuova istanza di EliminazioneNonValidaException senza un messaggio
     * di dettaglio specifico.
     */
    public EliminazioneNonValidaException() {
    }

   /**
     * @brief Costruttore con messaggio di dettaglio.
     *
     * Crea una nuova istanza di EliminazioneNonValidaException specificando la causa
     * della violazione.
     *
     * @param[in] msg Il messaggio descrittivo dell'errore (es. "Prestito Attivo", "Impossibile rimuovere utente con debiti").
     */
    public EliminazioneNonValidaException(String msg) {
        super(msg);
    }
}
