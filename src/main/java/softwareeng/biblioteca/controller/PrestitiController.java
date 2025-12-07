/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.model.*;

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

    @Override
    public void setMainApp(MainApp app);
    
    @Override
    public void setDati(Gestione<Prestito> interfaccia);

    public void setDati(GestionePrestiti prestiti, GestioneLibri catalogo, GestioneUtenti utenti);

    @Override
    public void goHome();
    
    public void goToLibri();
    
    public void goToUtenti();
    
    @Override
    public void goToPrestiti();
    
    public void mostraDialogLibri();
    
    public void mostraDialogUtenti();

    @Override
    public void clickNuovo();
    
    public void clickConferma();
    
    @Override
    public void seleziona(Prestito elemento);
    
    public void seleziona(Libro libro);
    
    public void seleziona(Utente utente);

    @Override
    public void rimuovi();

    @Override
    public void modifica();
    
    public void disattiva();

    @Override
    public boolean showEditDialog(Prestito elemento);

    @Override
    public boolean showEditDialog();

    @Override
    public void popolaForm(Prestito elemento);

    @Override
    public void sendAttrbitui(Map<String, Object> attributi);
}
