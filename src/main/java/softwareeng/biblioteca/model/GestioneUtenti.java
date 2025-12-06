/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;

import javafx.collections.ObservableList;

/**
 *
 * @author cashrules
 */
public interface GestioneUtenti extends Gestione<Utente> {
    @Override
    public ObservableList<Utente> getElenco();
    
    @Override
    public void aggiungi(Utente utente);
    
    @Override
    public void rimuovi(Utente utente);
    
    @Override
    public boolean checkID(String id);
}
