/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.model;
import java.time.LocalDate;

/**
 *
 * @author cashrules
 */
public class Prestito {
    private Utente utente;
    private Libro libro;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private boolean attivo;
    private boolean scaduto;
    private static int cont;
    private String id;
    
    public Prestito(Utente u, Libro l, LocalDate dataFine){
        
    }
    
    public void disattiva(){
    
    }
    
    public static void setContatore(int){
        
    }
    
    public String getID(){
        
    }
    
    public String toString(){
        
    }
}
