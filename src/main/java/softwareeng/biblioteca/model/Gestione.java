/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
  
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author cashrules
 */
public abstract interface Gestione<T> {
 

    public abstract ObservableList<T> getElenco();
    
    public abstract void aggiungi(T elemento);
    
    public abstract void rimuovi(T elemento);
    
    public abstract boolean checkID(String id);
    

}
