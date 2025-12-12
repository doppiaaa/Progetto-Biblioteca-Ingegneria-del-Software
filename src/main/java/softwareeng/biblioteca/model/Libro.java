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
import softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException;


/**
 * @brief Rappresenta un libro nel catalogo della biblioteca.
 *
 * La classe Libro gestisce le informazioni anagrafiche (Titolo, Autore, ISBN)
 * e lo stato delle copie fisiche (copieTotali, copieDisponibili), permettendo
 * di tracciare la disponibilità per i prestiti.
 *
 * @see Catalogo
 */
public class Libro implements Serializable, Comparable<Libro> {
    /** Titolo del libro. */
    private String titolo;
    /** Nome dell'autore del libro. */
    private String autore;
    /** Codice identificativo univoco del libro. */
    private String isbn;
    /** Anno di pubblicazione. */
    private int year;
    /** Numero totale di copie fisiche possedute dalla biblioteca. */
    private int copieTotali;
    /** Numero di copie attualmente disponibili per il prestito. */
    private int copieDisponibili;

    /**
     * @brief Costruttore della classe Libro.
     *
     * Inizializza un nuovo libro e imposta il numero di copie disponibili
     * uguale al numero totale di copie.
     *
     * @param[in] titolo Titolo del libro.
     * @param[in] autore Autore del libro.
     * @param[in] ISBN Codice ISBN univoco.
     * @param[in] year Anno di pubblicazione.
     * @param[in] copieTotali Numero totale di copie possedute.
     *
     * @pre copieTotali >= 0
     * @post copieDisponibili == copieTotali
     */
    public Libro(String titolo, String autore, String isbn, int year, int copieTotali){
        this.titolo=titolo;
        this.autore=autore;
        this.isbn=isbn;
        this.year=year;
        this.copieTotali=copieTotali;
        this.copieDisponibili=this.copieTotali;
        
    }

    /**
     * @brief Modifica gli attributi del libro.
     *
     * Permette di aggiornare le proprietà del libro utilizzando una mappa di attributi.
     * La logica interna deve gestire la riassegnazione di copieDisponibili in caso di
     * modifica di copieTotali per mantenere la consistenza.
     *
     * @param[in] attributi Mappa contenente le coppie identificatore, valore per i campi da modificare.
     * @pre attributi != null
     */
    public void modifica(Map<String, Object> attributi){
        
        attributi.forEach((chiave, valore) -> {
            switch(chiave.toLowerCase()){
                case "titolo":
                    this.titolo=(String)valore;
                case "autore":
                    this.autore=(String)valore;
                case "year":
                    this.year=(int)valore;
                case "copietotali":
                    int nuoveCopie =(int)valore;
                    int diff= nuoveCopie - this.copieTotali;
                    if(diff<0 && Math.abs(diff)>this.copieDisponibili){
                        throw new EliminazioneNonValidaException("Stai cercando di eliminare dei libri attualmente in prestito: ci sono attualmente " + (this.copieDisponibili-this.copieTotali) + " copie in prestito.");
                    }
                                       
                    this.copieTotali= nuoveCopie;
                    this.copieDisponibili+=diff;
           } 
        });
        
    }

    /**
     * @brief Verifica la disponibilità per un nuovo prestito.
     *
     * @return true se c'è almeno una copia disponibile (copieDisponibili > 0), false altrimenti.
     * @post Il valore di ritorno è (copieDisponibili > 0).
     */
    public boolean checkDisponibilità(){
        return this.copieDisponibili>0;
    }

    /**
     * @brief Verifica se il libro ha copie attualmente prestate.
     *
     * Utile per la logica di eliminazione del libro dal catalogo.
     *
     * @return true se non ci sono prestiti attivi (copieTotali = copieDisponibili), false altrimenti.
     */
    public boolean checkPrestiti(){
        return this.copieDisponibili==this.copieTotali;
    }

    /**
     * @brief Incrementa il numero di copie disponibili.
     *
     * Questo metodo viene chiamato tipicamente alla restituzione di un libro.
     *
     * @pre copieDisponibili < copieTotali
     * @post copieDisponibili == copieDisponibili++
     */
    public void upCopie(){
        this.copieDisponibili++;
    }

    /**
     * @brief Decrementa il numero di copie disponibili.
     *
     * Questo metodo viene chiamato tipicamente all'erogazione di un prestito.
     *
     * @pre checkDisponibilità() == true
     * @post copieDisponibili == copieDisponibili)--
     */
    public void downCopie(){
        this.copieDisponibili--;
    }

    /**
     * @brief Restituisce una rappresentazione testuale del libro.
     *
     * @return Stringa contenente i dettagli principali del libro (es. Titolo, Autore).
     */
    @Override
    public String toString(){
        return "Libro :"+this.isbn+"\nTitolo: "+this.titolo+"\nAutore: "+this.autore+"\nAnno di pubblicazione: "+this.year+"\nAttualmente sono disponibili "+ this.copieDisponibili+ " delle "+ this.copieTotali+ "copie totali";
    }

    /**
     * @brief Implementa il confronto tra due oggetti Libro (ad esempio, per l'ordinamento).
     *
     * @param[in] l L'oggetto Libro da confrontare.
     * @return Valore intero che indica l'ordine relativo.
     */
    @Override
    public int compareTo(Libro l){
        int cmp= this.titolo.compareTo(l.titolo);
        if (cmp!=0)
            return cmp;
        cmp = this.autore.compareTo(l.autore);
        if (cmp!=0)
            return cmp;
        return this.isbn.compareTo(l.isbn);
    }

    /**
     * @brief Restituisce il codice ISBN del libro.
     *
     * @return Stringa contenente l'ISBN.
     */
    public String getISBN(){
        return this.isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }
    
    
    
}
