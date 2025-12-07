/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.time.LocalDate;

/**
 *
 * @author cashrules
 */

/**
 * @file Prestito.java
 * @brief File contenente la definizione della classe Prestito.
 * @author cashrules
 * @version 1.0
 */

/**
 * @brief Rappresenta un prestito di un libro a un utente all'interno del sistema bibliotecario.
 * * Questa classe gestisce le informazioni relative all'associazione tra un Utente e un Libro
 * per un determinato periodo di tempo. Gestisce inoltre lo stato del prestito (attivo/scaduto)
 * e la generazione del suo identificativo univoco.
 */
public class Prestito {
    /** Utente che ha effettuato il prestito */
    private Utente utente;
    /** Libro oggetto del prestito */
    private Libro libro;
    /** Data di inizio del prestito */
    private LocalDate dataInizio;
    /** Data di scadenza o restituzione del prestito */
    private LocalDate dataFine;
    /** Indica se il prestito è attualmente in corso */
    private boolean attivo;
    /** Indica se il prestito ha superato la data di scadenza prevista */
    private boolean scaduto;
    /** Contatore statico per la generazione univoca degli ID */
    private static int cont;
    /** Identificativo univoco del prestito */
    private String id;

    /**
     * @brief Costruttore della classe Prestito.
     * * Inizializza un nuovo prestito associando un utente a un libro.
     * Genera automaticamente l'ID univoco e imposta il prestito come attivo.
     * * @param[in] u L'utente che richiede il prestito.
     * @param[in] l Il libro da prestare.
     * @param[in] dataFine La data prevista per la fine del prestito.
     * * @pre u != null && l != null
     * @pre l.checkDisponibilita() == true (Verificato esternamente dal Controller/Gestore)
     * @post attivo == true
     * @post L'istanza è creata con un ID univoco incrementale.
     */
    public Prestito(Utente u, Libro l, LocalDate dataFine){
        
    }

    /**
     * @brief Disattiva il prestito corrente.
     * * Questo metodo viene chiamato quando il libro viene restituito.
     * Imposta lo stato del prestito su non attivo e aggiorna la data di fine
     * alla data corrente.
     * * @post attivo == false
     * @post dataFine == LocalDate.now()
     */
    public void disattiva(){
    
    }

    /**
     * @brief Imposta il valore del contatore statico.
     * * Utile in fase di caricamento dati (deserializzazione) per ripristinare
     * il conteggio degli ID ed evitare duplicati.
     * * @param[in] c Il valore intero da assegnare al contatore statico.
     */
    public static void setContatore(int){
        
    }

    /**
     * @brief Restituisce l'identificativo del prestito.
     * * @return Una stringa rappresentante l'ID univoco del prestito.
     */
    public String getID(){
        
    }

    /**
     * @brief Restituisce una rappresentazione in stringa del prestito.
     * * @return Stringa contenente i dettagli principali del prestito (ID, Utente, Libro).
     */
    public String toString(){
        
    }
}
