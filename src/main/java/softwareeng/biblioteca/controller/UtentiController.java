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
public class UtentiController extends TController<Utente> {
    
    private MainApp mainApp;
    private GestioneLibri interfaccia;
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
    public void setDati(Gestione<Utente> interfaccia){
        
    }
    
    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    @Override
    public void goHome(){
        
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Prestiti.
     * * Gestisce il click per passare alla schermata di gestione dei Prestiti.
     */
    @Override
    public void goToPrestiti(){
        
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Libri.
     * * Gestisce il click per passare alla schermata di gestione dei Libri.
     */
    public void goToLibri(){
    
    }
    
    @Override
    public void clickNuovo(){
        
    }
    
    @Override
    public void seleziona(Utente utente){
        
    }
    
    @Override
    public void rimuovi(){
        
    }
    
    @Override
    public void modifica(){
        
    }
    
    @Override
    public boolean showEditDialog(Utente utente){
        
    }
    
    @Override    
    public boolean showEditDialog(){
        
    }
    
    @Override
    public void popolaForm(Utente utente){
        
    }
    
    @Override
    public void sendAttrbitui(Map<String, Object> attributi){
        
    }
    
}
