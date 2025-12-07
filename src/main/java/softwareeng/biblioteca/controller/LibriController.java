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
    private GestioneLibri catalogo; // Nel diagramma è chiamato 'catalogo'
    private Libro libroSelezionato;

    @Override
    public void setMainApp(MainApp app) {
       
    }

    @Override
    public void setDati(Gestione<Libro> interfaccia) {
       
    }

    // Metodo specifico mostrato nel diagramma UML per settare i dati specifici
    public void setDati(GestioneLibri catalogo) {
       
    }

    @Override
    public void goHome() {
        // Logica per tornare alla Home
    }
    
    // Metodo di navigazione specifico verso Utenti (dal diagramma)
    public void goToUtenti() {
        
    }

    @Override
    public void goToPrestiti() {
        
    }
    
    // Metodo di navigazione specifico (dal diagramma)
    public void goToLibri() {
        
    }

    @Override
    public void clickNuovo() {
        
    }

    @Override
    public void seleziona(Libro elemento) {
        
    }

    @Override
    public void rimuovi() {
        
    }

    @Override
    public void modifica() {
        
    }

    @Override
    public boolean showEditDialog(Libro elemento) {
        
    }

    @Override
    public boolean showEditDialog() {
       
    }

    @Override
    public void popolaForm(Libro elemento) {
        
    }

    @Override
    public void sendAttrbitui(Map<String, Object> attributi) {
        
    }
}
