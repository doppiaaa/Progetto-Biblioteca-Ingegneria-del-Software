/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;

import softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *
 * @author cashrules
 */

/**
 * @file ListaUtenti.java
 * @brief File contenente l'implementazione della classe ListaUtenti.
 * @version 1.0
 */

/**
 * @brief Classe concreta per la gestione dell'elenco degli utenti della biblioteca.
 * * Questa classe implementa l'interfaccia GestioneUtenti e l'interfaccia Serializable
 * per garantire la persistenza dei dati. Utilizza una ObservableList per permettere
 * il data-binding automatico con l'interfaccia grafica JavaFX.
 * * @see GestioneUtenti
 * @see Utente
 */
public class ListaUtenti implements Serializable, GestioneUtenti {
    /** * @brief Lista osservabile contenente gli utenti registrati.
     * Viene inizializzata nel costruttore come FXCollections.observableArrayList().
     */
    private ObservableList<Utente> utenti;

    /**
     * @brief Costruttore predefinito.
     * * Inizializza la lista degli utenti come una collezione vuota osservabile.
     * @post utenti != null && utenti.isEmpty() == true
     */
    public ListaUtenti(){
        this.utenti = FXCollections.observableArrayList();
        
    }

    /**
     * @brief Restituisce l'elenco completo degli utenti.
     * * @return ObservableList<Utente> La lista di tutti gli utenti gestiti.
     */
    @Override
    public ObservableList<Utente> getElenco(){
        return this.utenti;
    }

    /**
     * @brief Aggiunge un nuovo utente alla lista.
     * * @param[in] utente L'oggetto Utente da aggiungere.
     * @pre utente != null
     * @pre checkID(utente.getMatricola()) == false (L'utente non deve essere già presente)
     * @post utenti.contains(utente) == true
     * @post utenti.size() == old(utenti.size()) + 1
     */
    @Override
    public void aggiungi(Utente utente){
        this.utenti.add(utente);
    }

    /**
     * @brief Rimuove un utente dalla lista.
     * * Verifica che l'utente non abbia prestiti attivi prima di procedere con la rimozione,
     * per garantire l'integrità referenziale del sistema.
     * * @param[in] utente L'oggetto Utente da rimuovere.
     * @pre utente != null
     * @pre utenti.contains(utente) == true
     * @pre utente.checkPrestiti() == false (L'utente non deve avere prestiti in corso)
     * @post utenti.contains(utente) == false
     * @throws EliminazioneNonValidaException Se l'utente ha prestiti attivi.
     */
    @Override
    public void rimuovi(Utente utente){
        if(!utente.checkPrestiti()){
            throw new EliminazioneNonValidaException("L'utente non può essere rimosso prima di restituire tutti i prestiti.");
        }
        this.utenti.remove(utente);
    }

    /**
     * @brief Verifica l'esistenza di un utente tramite il suo identificativo (Matricola).
     * * Utilizzato per validare l'unicità della matricola durante l'inserimento di nuovi utenti.
     * * @param[in] id La matricola dell'utente da cercare.
     * @return true se esiste già un utente con quella matricola, false altrimenti.
     */
    @Override
    public boolean checkID(String id){
        for (Utente utente : utenti){
            if(utente.getMatricola().equals(id))
                return true;
        }
        return false;
    }
    
    /**
     * @brief Ricerca gli utenti per cognome.
     *
     * Metodo specifico definito nel diagramma delle classi per filtrare l'elenco.
     *
     * @param[in] cognome Il cognome (o parte di esso) da ricercare.
     * @return ObservableList<Utente> Una sottolista contenente gli utenti che corrispondono al criterio.
     */
    public ObservableList<Utente> ricercaCognome(String cognome){
        String ricerca= cognome.toLowerCase();
        
        List<Utente> risultati = utenti.stream()
                .filter(utente -> utente.getCognome().toLowerCase().contains(ricerca))
                .collect(Collectors.toList());
        
        return FXCollections.observableArrayList(risultati);
    }
    
    /**
     * @brief Ricerca un utente specifico per matricola.
     *
     * Metodo specifico definito nel diagramma delle classi.
     *
     * @param[in] matricola La matricola esatta da ricercare.
     * @return ObservableList<Utente> Una lista contenente l'utente trovato (o vuota).
     */
    public ObservableList<Utente> ricercaMatricola(String matricola){
        String ricerca= matricola.toLowerCase();
        
        List<Utente> risultati = utenti.stream()
                .filter(utente -> utente.getMatricola().toLowerCase().contains(ricerca))
                .collect(Collectors.toList());
        
        return FXCollections.observableArrayList(risultati);
    }

    /**
     * @brief Modifica i dati di un utente esistente.
     *
     * Aggiorna le informazioni di un utente già presente nell'elenco.
     *
     * @param[in] utente L'oggetto Utente con i dati aggiornati.
     *
     * @pre utente != null
     * @pre getElenco().contains(utente) == true
     * @post L'utente presente in lista riflette i nuovi dati passati.
     */
    public void modifica(Utente utente, Map<String,Object> attributi){
        utente.modifica(attributi);
    }
    
}
