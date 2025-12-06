/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import javafx.collections.ObservableList;
import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;


/**
 *
 * @author cashrules
 */
public interface GestionePrestiti extends Gestione<Prestito> {
    @Override
    public ObservableList<Prestito> getElenco();
    
    @Override
    public void aggiungi(Prestito prestito);
    
    @Override
    public void rimuovi(Prestito prestito);
    
    @Override
    public boolean checkID(String id);   
    /**
 * Crea e registra un nuovo prestito tra un utente e un libro.
 * Controlla i limiti dell'utente e la disponibilità del libro prima di registrare.
 * @param utente L'utente che richiede il prestito.
 * @param libro Il libro selezionato per il prestito.
 * @throws PrestitoNonValidoException Se l'utente ha raggiunto il limite o il libro non ha copie disponibili.
 * * @author Berardino Bruno
 */
    public void addPrestito(Utente utente, Libro libro) throws PrestitoNonValidoException;
}
