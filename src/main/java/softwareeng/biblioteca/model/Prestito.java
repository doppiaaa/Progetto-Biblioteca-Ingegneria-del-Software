/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.time.LocalDate;
import java.io.Serializable;

/**
 *
 * @author cashrules
 */

/**
 * @file Prestito.java
 * @brief File contenente la definizione della classe Prestito.
 * @version 1.0
 */

/**
 * @brief Rappresenta un prestito di un libro a un utente all'interno del sistema bibliotecario.
 * * Questa classe gestisce le informazioni relative all'associazione tra un Utente e un Libro
 * per un determinato periodo di tempo. Gestisce inoltre lo stato del prestito (attivo/scaduto)
 * e la generazione del suo identificativo univoco.
 */
public class Prestito implements Serializable, Comparable<Prestito> {
    /** Utente che ha effettuato il prestito */
    private Utente utente;
    /** Libro oggetto del prestito */
    private Libro libro;
    /** Data di inizio del prestito */
    private LocalDate dataInizio;
    /** Data di scadenza del prestito */
    private LocalDate dataPrevistaRestituzione;
    /** Data avvenuta restituzione del prestito*/
    private LocalDate dataRestituzioneEffettiva;
    /** Contatore statico per la generazione univoca degli ID */
    private static int cont;
    /** Identificativo univoco del prestito */
    private String id;

    /**
     * @brief Costruttore della classe Prestito.
     * * Inizializza un nuovo prestito associando un utente a un libro.
     * Genera automaticamente l'ID univoco e imposta il prestito come attivo.
     * Si assicura che il numero di copie disponibile decrementi e si aggiunga il prestito alla lista dell'utente
     * * @param[in] u L'utente che richiede il prestito.
     * @param[in] l Il libro da prestare.
     * @param[in] dataPrevistaRestituzione La data prevista per la fine del prestito.
     * @pre u != null && l != null
     * 
     * @post L'istanza è creata con un ID univoco incrementale.
     */
    public Prestito(Utente u, Libro l, LocalDate dataPrevistaRestituzione){
        this.libro=l;
        this.utente=u;
        this.dataPrevistaRestituzione=dataPrevistaRestituzione;
        this.dataInizio=LocalDate.now();
        this.cont=++cont;
        this.id= "" + cont;
        l.downCopie();
        u.aggiungi(this);
    }

    /**
     * @brief Disattiva il prestito corrente.
     * * Questo metodo viene chiamato quando il libro viene restituito.
     * Imposta la data di restituzione avvenuta.
     * * @post dataRestituzioneEffettiva== LocalDate.now()
     */
    public void disattiva(){
        this.libro.upCopie();
        this.utente.rimuoviPrestito(this);
        this.dataRestituzioneEffettiva=LocalDate.now();
        this.dataPrevistaRestituzione=null;
        
    }

    /**
     * @brief Imposta il valore del contatore statico.
     * * Utile in fase di caricamento dati per ripristinare
     * il conteggio degli ID ed evitare duplicati.
     * * @param[in] c Il valore intero da assegnare al contatore statico.
     */
    public static void setContatore(int i){
        cont = i;
    }
    
    public static int getContatore(){
        return cont;
    }

    /**
     * @brief Restituisce l'identificativo del prestito.
     * * @return Una stringa rappresentante l'ID univoco del prestito.
     */
    public String getID(){
        return this.id;
    }

    /**
     * @brief Restituisce una rappresentazione in stringa del prestito.
     * * @return Stringa contenente i dettagli principali del prestito (ID, Utente, Libro).
     */
    public String toString(){
        return "Prestito numero: " + this.id + "/n Libro :" + this.libro + "/n Preso in prestito dall'utente :" + this.utente + "/n Data inizio prestito: "+ this.dataInizio +"/n Da restituire entro: " + this.dataPrevistaRestituzione;
    }
    
    /**
     * @brief Controlla se il prestito è attivo
     * @return true se il prestito non è ancora stato restituito, false altrimenti
     */
    public boolean isAttivo(){
        return (this.dataRestituzioneEffettiva==null);
    }
    
    /**
     * @brief Controlla se il prestito è scaduto.
     * Controlla che la dataRestituzioneEffettiva sia null e che la data attuale sia successiva alla dataPrevistaRestituzione.
     * @return true se il prestito è scaduto e non stato riconsegnato, false altrimenti
     */
    public boolean isScaduto(){
        return (LocalDate.now().isAfter(this.dataPrevistaRestituzione));
    }
    
    @Override
    public int compareTo(Prestito p){
        int cmp = this.dataPrevistaRestituzione.compareTo(p.dataPrevistaRestituzione);
        if (cmp!=0)
            return cmp;
        return this.id.compareTo(p.id);
    }
}
