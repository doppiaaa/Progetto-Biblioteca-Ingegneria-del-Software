/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.util.Map;
import java.util.Objects;
import java.io.Serializable;
import java.lang.Comparable;

/**
 *
 * @author cashrules
 */
public class Libro implements Serializable, Comparable<Libro> {
    private String titolo;
    private String autore;
    private String ISBN;
    private int year;
    private int copieTotali;
    private int copieDisponibili;
    
    public Libro(String titolo, String autore, String ISBN, int year, int copieTotali){
        
    }
    
    public void modifica(Map<String, Object> attributi){
        
    }
    
    public boolean checkDisponibilità(){
        
    }
    
    public boolean checkPrestiti(){
        
    }
    
    public void upCopie(){
        
    }
    
    public void downCopie(){
        
    }
    
    @Override
    public String toString(){
        
    }
    
    @Override
    public int compareTo(Libro l){
        
    }
    
    public String getISBN(){
        
    }
    
}
