/**
 * @file GestoreFile.java
 * @brief Implementazione concreta del gestore di salvataggio e caricamento dati.
 * @version 1.0
 */
package softwareeng.biblioteca.model;

import java.util.*;
import java.io.*;

/**
 * @brief Classe responsabile dell'I/O su file per la persistenza del Model.
 *
 * Implementa l'interfaccia SalvataggioDati e contiene la logica per la serializzazione
 * e deserializzazione delle collezioni (ObservableList).
 * * @see SalvataggioDati
 */
public class GestoreFile implements SalvataggioDati {
    /**Lista degli indirizzi (path) dei file utilizzati per la persistenza. */
    private final String PATH_LIBRI = "data/libri.dat";
    private final String PATH_UTENTI = "data/utenti.dat";
    private final String PATH_PRESTITI = "data/prestiti.dat";
    private final String PATH_CONTATORE = "data/contatore.txt";
    
    /**
     * @brief Costruttore predefinito.
     * * Inizializza la lista degli indirizzi dei file.
     */
    public GestoreFile(){
        File directory= new File("data");
        if(!directory.exists()){
            boolean created=directory.mkdir();
        }
    }
    
    /**
     * @brief Salva lo stato corrente delle collezioni gestite.
     *
     * Serializza le liste di Utenti, Libri e Prestiti su file per la persistenza.
     *
     * * @param[in] catalogo Il gestore dei libri.
     * * @param[in] utenti Il gestore degli utenti.
     * * @param[in] prestiti Il gestore dei prestiti.
     */
    @Override
    public void salva(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti){
        try {
        // Serializzazione delle liste di oggetti (Binario)
            scrivi(new ArrayList<>(catalogo.getElenco()), PATH_LIBRI);
            scrivi(new ArrayList<>(utenti.getElenco()), PATH_UTENTI);
            scrivi(new ArrayList<>(prestiti.getElenco()), PATH_PRESTITI);
        
            // 2. Salvataggio del Contatore ID (Testuale)
            scriviContatore(Prestito.getContatore()); 
        
            System.out.println("Salvataggio completato.");
        } catch (IOException e) {
            // Cattura solo IOException, che è l'unica lanciata dai metodi di scrittura.
            System.err.println("ERRORE SALVATAGGIO CRITICO: Impossibile scrivere su disco i dati. Causa: " + e.getMessage());
        }
    }
    
    /**
     * @brief Carica lo stato delle collezioni dai file.
     *
     * Deserializza i dati per ripopolare le liste di gestione all'avvio dell'applicazione.
     *
     * * @param[in] catalogo Il gestore dei libri.
     * * @param[in] utenti Il gestore degli utenti.
     * * @param[in] prestiti Il gestore dei prestiti.
     */
    @Override
    public void carica(GestioneLibri catalogo, GestioneUtenti utenti, GestionePrestiti prestiti){
        try {
            // 1. Caricamento Libri (Binario)
            // Il cast è necessario perché leggiOggetto ritorna Object.
            List<Libro> libriCaricati = (List<Libro>) leggi(PATH_LIBRI);
            if (libriCaricati != null) {
                // setAll(): Ripopola l'ObservableList esistente con i dati caricati.
                catalogo.getElenco().setAll(libriCaricati); 
            }

            // 2. Caricamento Utenti (Binario)
            List<Utente> utentiCaricati = (List<Utente>) leggi(PATH_UTENTI);
            if (utentiCaricati != null) {
                utenti.getElenco().setAll(utentiCaricati);
            }
        
            // 3. Caricamento Prestiti (Binario)
            List<Prestito> prestitiCaricati = (List<Prestito>) leggi(PATH_PRESTITI);
            if (prestitiCaricati != null) {
                prestiti.getElenco().setAll(prestitiCaricati);
            }
        
            // 4. Ripristino Contatore Statico (Testuale)
            // leggiContatore gestisce il caso file mancante/corrotto tornando 0.
            int idMassimo = leggiContatore(); 
            Prestito.setContatore(idMassimo); 
        
            System.out.println("Caricamento completato. Prossimo ID Prestito: " + idMassimo);
        
        } catch (IOException e) {
            // Errore I/O: disco non accessibile, permessi negati, ecc.
            System.err.println("ERRORE CARICAMENTO I/O: Impossibile leggere i file. App avviata con dati vuoti. Causa: " + e.getMessage());
        
        } catch (ClassNotFoundException e) {
            // Errore Serializzazione: i file binari sono corrotti o le classi (Libro, Utente, Prestito) sono state modificate in modo incompatibile.
            System.err.println("ERRORE CARICAMENTO SERIALIZZAZIONE: Dati non compatibili o corrotti. App avviata con dati vuoti. Causa: " + e.getMessage());
        }
    }
    
    
    /**
     * @brief Resetta (cancella) tutti i dati persistenti.
     *
     * Elimina i file di persistenza e svuota le collezioni.
     */
    @Override
    public void reset(){
        try {
            // Troncare i file Binari (svuotare il contenuto a 0 byte)
            
            new FileOutputStream(PATH_LIBRI).close();
            new FileOutputStream(PATH_UTENTI).close();
            new FileOutputStream(PATH_PRESTITI).close();
        
            // Sovrascrivere il Contatore ID a 0 (File Testuale)
            
            try (PrintWriter pw = new PrintWriter(new File(PATH_CONTATORE))) {
                pw.print(0); 
                pw.flush(); 
            }

            // Ripristinare lo stato del contatore STATIC al valore 0
            Prestito.setContatore(0); 

            System.out.println("Archivio resettato. Tutti i file sono stati svuotati e il contatore ID è stato azzerato.");
        } catch (IOException e) {
            // Gestione dell'errore (ad esempio, se i permessi impediscono di scrivere/svuotare)
            System.err.println("AVVISO: Impossibile svuotare i file di persistenza. Causa: " + e.getMessage());
        }
    }
    
    private Object leggi(String path) throws IOException, ClassNotFoundException {
    // Controllo preliminare per il primo avvio
    File f = new File(path);
    if (!f.exists()) {
        System.out.println("AVVISO: File " + path + " non trovato (Primo avvio o resettato).");
        return null; 
    }
    
    // Apertura del flusso con try-with-resources
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
        return ois.readObject();
    }
    
}
    
    private void scrivi(Object o, String path) throws IOException {
    // Inizializzazione del flusso try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {        
            // Scrittura dell'oggetto sul disco
            oos.writeObject(o);
        }
    }    
    
    
    private int leggiContatore(){
        File f = new File(PATH_CONTATORE);
        // Controllo file inesistente
        if (!f.exists()) {
            System.out.println("AVVISO: Contatore ID non trovato. Ripristino ID a 0.");
            return 0; // Torna 0 (o il valore iniziale del contatore statico)
        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_CONTATORE))) {
            // Legge la prima riga (dove si presume ci sia il numero)
            String linea = br.readLine();
        
            // Conversione e validazione
            if (linea != null) {
                return Integer.parseInt(linea.trim());
            }
        } catch (IOException | NumberFormatException e) {
            // Gestione errore di I/O o se il file contiene testo non numerico
            System.err.println("ERRORE CRITICO: Contatore ID corrotto o vuoto. Ripristino ID a 0.");
    }
    return 0;
    }
    
    private void scriviContatore(int valore) throws IOException {
    
    try (PrintWriter pw = new PrintWriter(new File(PATH_CONTATORE))) {
        
        pw.print(valore);
        pw.flush(); 
        
    } 
    // Il try-with-resources chiude automaticamente il PrintWriter.
}
    
    
}
