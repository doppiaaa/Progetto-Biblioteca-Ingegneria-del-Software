/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.util.*;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.regex.Pattern;

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
    private static final long serialVersionUID = 1L;
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
     * @post creazione di un nuovo oggetto Utente
     */
    public Utente(String nome, String cognome, String matricola, String mail) {
        
        if (nome == null || nome.trim().isEmpty()) 
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        if (cognome == null || cognome.trim().isEmpty()) 
            throw new IllegalArgumentException("Il cognome non può essere vuoto.");
        
        validaMatricola(matricola);
        validaEmail(mail);
        
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
        attributi.forEach((chiave, valore) -> {
            switch(chiave.toLowerCase()){
                case "nome":
                    String nuovoNome= (String)valore;
                    if (nuovoNome == null || nuovoNome.trim().isEmpty())
                        throw new IllegalArgumentException("Il nome non può essere vuoto.");
                    this.nome = nuovoNome;
                    break;
                case "cognome":
                    String nuovoCognome = (String)valore;
                    if (nuovoCognome == null || nuovoCognome.trim().isEmpty()) throw new IllegalArgumentException("Il cognome non può essere vuoto.");
                    this.cognome = nuovoCognome;
                    break;
                case "mail":
                    String nuovaMail = (String)valore;
                    validaEmail(nuovaMail); 
                    this.mail = nuovaMail;     
                    break;
           } 
        });
    }

    /**
     * @brief Verifica se l'utente è idoneo a prendere un nuovo prestito.
     *
     * L'idoneità è basata sul numero massimo di prestiti attivi (3).
     *
     * @return true se il numero di prestiti attivi è inferiore al limite massimo, false altrimenti.
     */
    public boolean checkDisponibilita() {
        
        return (prestiti.size()< 3);
        
    }
    
    /**
     * @brief Si accerta che l'utente non abbia prestiti attivi in sospeso.
     *
     * Questo metodo è cruciale per la logica di eliminazione dell'utente.
     *
     * @return true se l'utente non ha alcun prestito attivo, false altrimenti.
     */
    public boolean checkPrestiti() {
        
        return (prestiti.size()==0);
        
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
        prestiti.add(p);
    }

    // Metodi privati di validazione
    private void validaMatricola(String matricola) {
        // Regex: Esattamente 10 cifre numeriche
        if (matricola == null || !matricola.matches("\\d{10}")) {
            throw new IllegalArgumentException("La matricola deve essere composta da esattamente 10 numeri.");
        }
    }

    private void validaEmail(String mail) {
        // Regex semplice per email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (mail == null || !Pattern.matches(emailRegex, mail)) {
            throw new IllegalArgumentException("Il formato dell'email non è valido.");
        }
    }
    /**
     * @brief Rimuove un prestito dalla lista dell'utente.
     *
     * @param[in] p Il Prestito da rimuovere (restituire).
     * @pre p != null
     * @pre prestiti.contains(p) == true
     * @post p.contains(p) == false
     */
    public void rimuoviPrestito(Prestito p){
        prestiti.remove(p);
    }

    /**
     * @brief Restituisce una rappresentazione in stringa dell'utente.
     *
     * @return Stringa contenente i dati anagrafici principali (es. Nome, Cognome, Matricola).
     */
    @Override
    public String toString(){
        return "Utente: " + this.matricola + "/n Nome e Cognome: " + this.nome + this.cognome + "/n mail: " + this.mail + "/n L'utente ha al momento "+ this.prestiti.size()+ " prestiti attivi.";
    }

    /**
     * @brief Implementa il confronto tra due oggetti Utente per l'ordinamento.
     *
     * @param[in] u L'oggetto Utente da confrontare.
     * @return Valore intero che indica l'ordine relativo.
     */
    @Override
    public int compareTo(Utente u){
        int cmp= this.cognome.toLowerCase().compareTo(u.cognome.toLowerCase());
        if (cmp!=0) 
            return cmp;
        cmp=this.nome.toLowerCase().compareTo(u.nome.toLowerCase());
        if (cmp!=0) 
            return cmp;
        return this.matricola.toLowerCase().compareTo(u.matricola.toLowerCase());
        }
    

    /**
     * @brief Restituisce la matricola dell'utente.
     *
     * @return Stringa contenente la matricola.
     */
    public String getMatricola(){
        return this.matricola;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Prestito> getPrestiti() {
        return prestiti;
    }

    public String getMail() {
        return mail;
    }
    
    
    
    
}
