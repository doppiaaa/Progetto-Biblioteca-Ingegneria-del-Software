/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.application;

import javafx.application.Application;
import softwareeng.biblioteca.model.*;
import javafx.stage.Stage;

/**
 * @class MainApp
 * @brief Classe principale dell'applicazione che gestisce l'inizializzazione, lo stato e la navigazione.
 * * Questa classe funge da orcestratore centrale del progetto, detenendo le istanze
 * di tutte le classi di Gestione (Model) e gestendo la logica di navigazione (routing)
 * tra le diverse View (schermate FXML).
 * * Estende la classe 'Application' di JavaFX ed è il punto d'ingresso
 * per l'esecuzione del programma.
 *
 * @see HomeController
 * @see GestoreFile
 * @version 1.0
 */
public class MainApp extends Application {
    /**
     * Lo Stage principale (finestra) dell'applicazione JavaFX.
     * Necessario per impostare la scena e mostrare le interfacce utente.
     */
    private Stage primaryStage;
    /**
     * Gestore delle operazioni CRUD per i Libri.
     * Contiene l'elenco principale del catalogo.
     */
    private Catalogo catalogo;
    /**
     * Gestore delle operazioni CRUD per gli Utenti.
     * Contiene la lista anagrafica degli utenti registrati.
     */
    private ListaUtenti utenti; 
    /**
     * Gestore delle operazioni di creazione e chiusura dei Prestiti.
     * Contiene la lista di tutti i prestiti attivi e storici.
     */
    private ListaPrestiti prestiti;
    /**
     * Riferimento al gestore della persistenza dei dati.
     * Utilizzato per il salvataggio e caricamento dello stato dell'applicazione.
     * @see GestoreFile
     */
    private SalvataggioDati gestoreFile;

    /**
    * @brief Punto di ingresso principale dell'applicazione JavaFX.
    * * Inizializza la finestra principale (Stage), carica le dipendenze del Model
    * (Catalogo, Liste) e avvia l'interfaccia utente chiamando mostraHome().
    *
    * @param primaryStage Lo Stage principale dell'applicazione.
    */
    @Override
    public void start(Stage primaryStage){
        
    }
    /**
 * @brief Mostra la schermata principale (Home View).
 * * Carica il file FXML della Home e imposta l'HomeController.
 * Utilizzato all'avvio dell'applicazione o quando si torna alla pagina iniziale.
 */
    public void mostraHome(){
        
    }
    /**
 * @brief Mostra la schermata di gestione dei Libri (CRUD).
 * * Carica il file FXML della gestione Libri e imposta il LibriController.
 * Inoltre, inietta le dipendenze necessarie per il CRUD (Catalogo, MainApp).
 */
    public void mostraLibri(){
        
    }
    /**
 * @brief Mostra la schermata di gestione degli Utenti (CRUD).
 * * Carica il file FXML della gestione Utenti e imposta l'UtentiController.
 * Inoltre, inietta le dipendenze necessarie per il CRUD (ListaUtenti, MainApp).
 */
    public void mostraUtenti(){
        
    }
    /**
 * @brief Mostra la schermata di gestione dei Prestiti.
 * * Carica il file FXML della gestione Prestiti e imposta il PrestitiController.
 * Inietta le tre gestioni dati (Libri, Utenti, Prestiti) necessarie per la logica.
 */
    public void mostraPrestiti(){
        
    }
    /**
 * @brief Carica i dati persistenti all'avvio dell'applicazione.
 * * Chiama il GestoreFile per leggere i dati binari dai file e ripopola
 * le liste del Model (Catalogo, ListaUtenti, ListaPrestiti).
 * Gestisce inoltre l'aggiornamento del contatore statico degli ID.
 */
    public void caricaDati(){
        
    }
    /**
 * @brief Salva lo stato corrente dell'applicazione.
 * * Chiama il GestoreFile per serializzare l'intero stato del Model
 * (Catalogo, Liste) sui rispettivi file.
 * Questo metodo viene chiamato prima della chiusura dell'applicazione.
 */
    public void salvaDati(){
        
    }
}
