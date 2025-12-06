/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;
import java.io.Serializable;
/**
 *
 * @author cashrules
 */
public class ListaPrestiti implements Serializable, GestionePrestiti{
    private ObservableList<Prestito> prestiti;
    
    public ListaPrestiti() {
        
    }
    
    @Override
    public ObservableList<Prestito> getElenco(){
        
    }
    
    @Override
    public void aggiungi(Prestito prestito){
        
    }
    
    @Override
    public void rimuovi(Prestito prestito){
        
    }
    
    @Override
    public boolean checkID(String id){
        
    }
    
    @Override
    public void addPrestito(Utente utente, Libro libro) throws PrestitoNonValidoException{
        
    }

}
