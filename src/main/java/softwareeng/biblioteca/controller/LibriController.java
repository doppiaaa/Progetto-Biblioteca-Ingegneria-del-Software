/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.controller;

import java.util.Map;
import softwareeng.biblioteca.application.MainApp;
import softwareeng.biblioteca.model.*;

/**
 *
 * @author cashrules
 */
public class LibriController extends TController<Libro> {

    private MainApp mainApp;
    private GestioneLibri catalogo; 
    private Libro libroSelezionato;

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    @Override
    public void setMainApp(MainApp app){
        
    }

    @Override
    public void setDati(Gestione<Libro> interfaccia){
        
    }

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    @Override
    public void goHome(){
        
    }
       
    /**
     * @brief Passa alla schermata di gestione degli Utenti.
     * * Gestisce il click per passare alla schermata di gestione degli Utenti.
     */
    public void goToUtenti(){
        
    }

    /**
     * @brief Passa alla schermata di gestione dei Prestiti.
     * * Gestisce il click per passare alla schermata di gestione dei Prestiti.
     */
    @Override
    public void goToPrestiti(){
        
    }

    @Override
    public void clickNuovo(){
        
    }

    @Override
    public void seleziona(Libro elemento){
        
    }

    @Override
    public void rimuovi(){
        
    }

    @Override
    public void modifica(){
        
    }

    @Override
    public boolean showEditDialog(Libro elemento){
        
    }

    @Override
    public boolean showEditDialog(){
        
    }

    @Override
    public void popolaForm(Libro elemento){
        
    }

    @Override
    public void sendAttrbitui(Map<String, Object> attributi){
        
    }
    
}
