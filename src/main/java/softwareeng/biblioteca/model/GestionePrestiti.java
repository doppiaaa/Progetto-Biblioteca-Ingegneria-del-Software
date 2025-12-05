/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;


/**
 *
 * @author cashrules
 */
public interface GestionePrestiti extends GestioneT {
       
    /**
 * Crea e registra un nuovo prestito tra un utente e un libro.
 * Controlla i limiti dell'utente e la disponibilità del libro prima di registrare.
 * @param utente L'utente che richiede il prestito.
 * @param libro Il libro selezionato per il prestito.
 * @throws PrestitoNonValidoException Se l'utente ha raggiunto il limite o il libro non ha copie disponibili.
 * * @author Berardino Bruno
 */
    public void addPrestito(Utente utente, Libro libro) throws PrestitoNonValidoException {
    
    }
}
