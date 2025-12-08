/**
 * @file GestoreFile.java
 * @brief Implementazione concreta del gestore di salvataggio e caricamento dati.
 * @version 1.0
 */
package softwareeng.biblioteca.model;

import java.util.ArrayList;

/**
 * @brief Classe responsabile dell'I/O su file per la persistenza del Model.
 *
 * Implementa l'interfaccia SalvataggioDati e contiene la logica per la serializzazione
 * e deserializzazione delle collezioni (ObservableList).
 * * @see SalvataggioDati
 */
public class GestoreFile implements SalvataggioDati {
    /**Lista degli indirizzi (path) dei file utilizzati per la persistenza. */
    private ArrayList<String> indirizzi;
    
    /**
     * @brief Costruttore predefinito.
     * * Inizializza la lista degli indirizzi dei file.
     */
    public GestoreFile(){
        
    }
    
    /**
     * @brief Salva lo stato corrente delle collezioni gestite.
     *
     * Serializza le liste di Utenti, Libri e Prestiti su file per la persistenza.
     *
     * @param[in] catalogo Il gestore dei libri.
     * @param[in] utenti Il gestore degli utenti.
     * @param[in] prestiti Il gestore dei prestiti.
     */
    @Override
    public void salva(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti){
        
    }
    
    /**
     * @brief Carica lo stato delle collezioni dai file.
     *
     * Deserializza i dati per ripopolare le liste di gestione all'avvio dell'applicazione.
     *
     * @param[in] catalogo Il gestore dei libri.
     * @param[in] utenti Il gestore degli utenti.
     * @param[in] prestiti Il gestore dei prestiti.
     */
    @Override
    public void carica(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti){
        
    }
    
    /**
     * @brief Resetta (cancella) tutti i dati persistenti.
     *
     * Elimina i file di persistenza e svuota le collezioni.
     */
    @Override
    public void reset(){
        
    }
}
