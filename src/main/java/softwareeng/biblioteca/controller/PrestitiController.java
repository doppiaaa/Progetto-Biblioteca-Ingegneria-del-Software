/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.model.*;
import softwareeng.biblioteca.application.MainApp;

/**
 *
 * @author cashrules
 */
public class PrestitiController {
    private MainApp mainApp;
    private GestionePrestiti prestiti;
    private GestioneLibri catalogo;
    private GestioneUtenti utenti;
    
    private Libro libroSelezionato;
    private Utente utenteSelezionato;
    private Prestito prestitoSelezionato;

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    public void setMainApp(MainApp app){
        this.mainApp = app;
    }

    /**
     * @brief Inietta le tre istanze dei Gestori dati necessarie per la transazione.
     *
     * @param prestiti L'istanza del GestorePrestiti (ListaPrestiti).
     * @param catalogo L'istanza del GestoreLibri (Catalogo).
     * @param utenti L'istanza del GestoreUtenti (ListaUtenti).
     */
    public void setDati(GestionePrestiti prestiti, GestioneLibri catalogo, GestioneUtenti utenti){
        
    }

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    public void goHome(){
        if(mainApp != null) {
            mainApp.mostraHome();
        }
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Libri.
     * * Gestisce il click per passare alla schermata di gestione dei Libri.
     */
    public void goToLibri(){
        
    }
    
    /**
     * @brief Passa alla schermata di gestione degli Utenti.
     * * Gestisce il click per passare alla schermata di gestione degli Utenti.
     */
    public void goToUtenti(){
        
    }
  
    /**
     * @brief Mostra un dialog per la selezione di un Libro.
     *
     * Tipicamente apre una finestra di ricerca e selezione dal Catalogo per l'erogazione di un nuovo prestito.
     */
    public void mostraDialogLibri(){
        
    }
    
    /**
     * @brief Mostra un dialog per la selezione di un Utente.
     *
     * Tipicamente apre una finestra di ricerca e selezione dalla ListaUtenti per l'erogazione di un nuovo prestito.
     */
    public void mostraDialogUtenti(){
        
    }

    /**
     * @brief Prepara l'interfaccia utente per l'inserimento di un nuovo prestito.
     *
     * Resetta i campi di selezione e abilita i pulsanti pertinenti.
     */
    public void clickNuovo(){
        
    }
    
    /**
     * @brief Esegue la transazione di creazione di un nuovo prestito.
     *
     * Chiama `GestionePrestiti::addPrestito(Utente, Libro)`. Gestisce le eccezioni
     * relative alla non validità del prestito (es. PrestitoNonValidoException).
     */
    public void clickConferma(){
        
    }
    
    /**
     * @brief Seleziona un Prestito dalla TableView principale.
     *
     * Imposta l'attributo prestitoSelezionato per le operazioni di disattivazione/rimozione.
     * @param elemento Il Prestito selezionato.
     */
    public void seleziona(Prestito elemento){
        
    }
    
    /**
     * @brief Seleziona un Libro dal dialog di ricerca (per il nuovo prestito).
     *
     * Imposta l'attributo libroSelezionato.
     * @param libro Il Libro selezionato.
     */
    public void seleziona(Libro libro){
        
    }
    
    /**
     * @brief Seleziona un Utente dal dialog di ricerca (per il nuovo prestito).
     *
     * Imposta l'attributo utenteSelezionato.
     * @param utente L'Utente selezionato.
     */
    public void seleziona(Utente utente){
        
    }

    /**
     * @brief Implementa l'azione di rimozione di un prestito.
     *
     * Utilizzato per cancellare un prestito (generalmente storico o creato per errore).
     * Chiama `GestionePrestiti::rimuovi(Prestito)`.
     */
    public void rimuovi(){
        
    }
    
    /**
     * @brief Implementa l'azione di restituzione di un libro.
     *
     * Chiama `GestionePrestiti::disattiva(Prestito)` per chiudere il prestito,
     * aggiornare lo stato del libro (upCopie) e dell'utente (rimuoviPrestito).
     */
    public void disattiva(){
        
    }

}
