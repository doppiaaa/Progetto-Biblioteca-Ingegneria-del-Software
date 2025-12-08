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
        
    }

    public void setDati(GestionePrestiti prestiti, GestioneLibri catalogo, GestioneUtenti utenti){
        
    }

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    public void goHome(){
        
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
  
    public void mostraDialogLibri(){
        
    }
    
    public void mostraDialogUtenti(){
        
    }

    public void clickNuovo(){
        
    }
    
    public void clickConferma(){
        
    }
    
    public void seleziona(Prestito elemento){
        
    }
    
    public void seleziona(Libro libro){
        
    }
    
    public void seleziona(Utente utente){
        
    }

    public void rimuovi(){
        
    }
    
    public void disattiva(){
        
    }

}
