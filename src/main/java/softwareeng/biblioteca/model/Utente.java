/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.io.Serializable;
import java.lang.Comparable;

/**
 * @file Utente.java
 * @brief File contenente la definizione della classe Utente.
 * @version 1.0
 */

/**
 * @brief Rappresenta un utente registrato al sistema bibliotecario.
 *
 * Contiene i dati anagrafici (Nome, Cognome, Matricola, Mail) e gestisce la lista
 * dei prestiti attivi associati a quell'utente. Implementa Serializable per la
 * persistenza e Comparable per l'ordinamento.
 */
public class Utente implements Comparable<Utente>, Serializable {
    /** Nome anagrafico dell'utente. */
    private String nome;
    /** Cognome anagrafico dell'utente. */
    private String cognome;
    /** Matricola univoca che funge da identificativo nel sistema. */
    private String matricola;
    /** Indirizzo email dell'utente. */
    private String mail;
    /** Lista dei prestiti attualmente attivi associati a questo utente. */
    private ArrayList<Prestito> prestiti;

    /**
     * @brief Costruttore della classe Utente.
     *
     * Inizializza un nuovo utente e crea una lista vuota per i suoi prestiti.
     *
     * @param[in] nome Il nome dell'utente.
     * @param[in] cognome Il cognome dell'utente.
     * @param[in] matricola La matricola univoca dell'utente.
     * @param[in] mail L'indirizzo email.
     *
     * @pre matricola è una stringa univoca non presente in ListaUtenti (verificato esternamente).
     * @post prestiti è una lista vuota.
     */
    public Utente(String nome, String cognome, String matricola, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
        this.prestiti= new ArrayList<Prestito>();
    }

   /**
     * @brief Modifica gli attributi dell'utente.
     *
     * Permette di aggiornare le proprietà dell'utente (es. nome, cognome, mail)
     * utilizzando una mappa di attributi.
     *
     * @param[in] attributi Mappa contenente le coppie identificatore-valore dei campi da modificare.
     * @pre attributi != null
     */
    public void modifica(Map<String, Object> attributi){
        
    }

    /**
     * @brief Verifica se l'utente è idoneo a prendere un nuovo prestito.
     *
     * L'idoneità è basata sul numero massimo di prestiti attivi (3).
     *
     * @return true se il numero di prestiti attivi è inferiore al limite massimo, false altrimenti.
     */
    public boolean checkDisponibilita() {
        
    }
    
    /**
     * @brief Si accerta che l'utente non abbia prestiti attivi in sospeso.
     *
     * Questo metodo è cruciale per la logica di eliminazione dell'utente.
     *
     * @return true se l'utente non ha alcun prestito attivo, false altrimenti.
     */
    public boolean checkPrestiti() {
        
    }

    /**
     * @brief Aggiunge un prestito attivo alla lista dell'utente.
     *
     * @param[in] p Il Prestito da associare.
     * @pre p != null
     * @post prestiti.contains(p) == true
     * @post prestiti.size() == prestiti.size()++
     */
    public void aggiungi(Prestito p){
        
    }

    /**
     * @brief Disattiva un prestito specifico.
     *
     * @param[in] p Il Prestito da disattivare (restituire).
     * @pre p != null
     * @pre prestiti.contains(p) == true
     * @post p.isAttivo() == false (Stato aggiornato dal metodo Prestito::disattiva())
     */
    public void disattiva(Prestito p){
        
    }

    /**
     * @brief Restituisce una rappresentazione in stringa dell'utente.
     *
     * @return Stringa contenente i dati anagrafici principali (es. Nome, Cognome, Matricola).
     */
    @Override
    public String toString(){
        
    }

    /**
     * @brief Implementa il confronto tra due oggetti Utente per l'ordinamento.
     *
     * @param[in] u L'oggetto Utente da confrontare.
     * @return Valore intero che indica l'ordine relativo.
     */
    @Override
    public int compareTo(Utente u){
        
    }

    /**
     * @brief Restituisce la matricola dell'utente.
     *
     * @return Stringa contenente la matricola.
     */
    public String getMatricola(){
        return this.matricola;
    }
}
