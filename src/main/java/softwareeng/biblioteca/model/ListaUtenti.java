/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
/**
 *
 * @author cashrules
 */
public class ListaUtenti implements Serializable, GestioneUtenti {
    private ObservableList<Utente> utenti;
    
    public ListaUtenti(){
    
    }
    
    @Override
    public ObservableList<Utente> getElenco(){
        
    }
    
    @Override
    public void aggiungi(Utente utente){
        
    }
    
    @Override
    public void rimuovi(Utente utente){
        
    }
    
    @Override
    public boolean checkID(String id){
        
    }
}
