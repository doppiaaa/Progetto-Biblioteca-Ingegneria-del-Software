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
 * Rappresenta un utente registrato al sistema. 
 * Contiene i dati anagrafici e i prestiti attivi. 
 * * @author Berardino Bruno
 * @version 1.0
 */
public class Utente implements Comparable<Utente>, Serializable {
    private String nome;
    private String cognome;
    private String matricola;
    private String mail;
    private ArrayList<Prestito> prestiti;

    public Utente(String nome, String cognome, String matricola, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
        this.prestiti= new ArrayList<Prestito>();
    }
    
    public void modifica(Map<String, Object> attributi){
        
    }

    /**
 * Verifica se l'utente è idoneo a prendere un nuovo prestito.
 * L'idoneità e' basata sul numero massimo di prestiti attivi (3).
 * @return true se il numero di prestiti attivi è inferiore al limite massimo, false altrimenti.
 * * @author Berardino Bruno
 */
    public boolean checkDisponibilita() {
        return true;
    }
    
    /**
     * Si accerta che l'utente non abbia prestiti in sospeso.
     * Serve a accertarsi che l'utente possa essere rimosso.
     * @retun true se non l'utente non ha alcun prestito attivo, false altrimenti.
     * * @author Berardino Bruno
     */
    public boolean checkPrestiti() {
        
    }

    public void aggiungi(Prestito p){
        
    }
    
    public void disattiva(Prestito p){
        
    }
    
    @Override
    public String toString(){
        
    }
    
    @Override
    public int compareTo(Utente u){
        
    }
    
    public String getMatricola(){
        return this.matricola;
    }
}
