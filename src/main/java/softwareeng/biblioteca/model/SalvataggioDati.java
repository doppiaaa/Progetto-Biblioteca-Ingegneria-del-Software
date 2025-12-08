/**
 * @file SalvataggioDati.java
 * @brief Interfaccia per la definizione del contratto di persistenza dei dati.
 * @version 1.0
 */
package softwareeng.biblioteca.model;

/**
 * @brief Contratto per il salvataggio e caricamento dei dati del Model.
 *
 * Questa interfaccia definisce le operazioni fondamentali per garantire la
 * persistenza dello stato dell'applicazione (serializzazione/deserializzazione)
 * delle liste di gestione.
 */
public interface SalvataggioDati {
    
    /**
     * @brief Salva lo stato corrente delle collezioni gestite.
     *
     * Serializza le liste di Utenti, Libri e Prestiti su file per la persistenza.
     *
     * @param[in] catalogo Il gestore dei libri.
     * @param[in] utenti Il gestore degli utenti.
     * @param[in] prestiti Il gestore dei prestiti.
     */
    public void salva(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti);

    /**
     * @brief Carica lo stato delle collezioni dai file.
     *
     * Deserializza i dati per ripopolare le liste di gestione all'avvio dell'applicazione.
     *
     * @param[in] catalogo Il gestore dei libri.
     * @param[in] utenti Il gestore degli utenti.
     * @param[in] prestiti Il gestore dei prestiti.
     */
    public void carica(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti);

    /**
     * @brief Resetta (cancella) tutti i dati persistenti.
     *
     * Elimina i file di persistenza e svuota le collezioni.
     */
    public void reset();
}
