/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.application;

import javafx.application.Application;
import softwareeng.biblioteca.model.*;
import softwareeng.biblioteca.controller.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*; // O il tipo di root usato nel tuo FXML, ma AnchorPane è lo standard
import java.io.IOException;

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
     * @brief Costruttore predefinito.
     * Inizializza tutte le classi del Model.
     */
    public MainApp() {
        // Inizializzazione del Model
        this.catalogo = new Catalogo();
        this.utenti = new ListaUtenti();
        this.prestiti = new ListaPrestiti();
        this.gestoreFile = new GestoreFile(); 
    }
    
    
    /**
    * @brief Punto di ingresso principale dell'applicazione JavaFX.
    * * Inizializza la finestra principale (Stage), carica le dipendenze del Model
    * (Catalogo, Liste) e avvia l'interfaccia utente chiamando mostraHome().
    *
    * @param primaryStage Lo Stage principale dell'applicazione.
    */
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage; // Memorizza lo Stage
        this.primaryStage.setTitle("Biblioteca - Gestione"); // Imposta il titolo
        
        // Carica i dati all'avvio
        caricaDati();
        
        //Esegue salvaDati() alla chiusura della finestra 
        primaryStage.setOnCloseRequest(e -> salvaDati());
        
        mostraHome();
    }
    /**
 * @brief Mostra la schermata principale (Home View).
 * * Carica il file FXML della Home e imposta l'HomeController.
 * Utilizzato all'avvio dell'applicazione o quando si torna alla pagina iniziale.
 */
    public void mostraHome(){
        try {
            //Inizializzazione e configurazione del caricatore
            FXMLLoader loader = new FXMLLoader();
            
            // Specifica la posizione del file FXML
            loader.setLocation(MainApp.class.getResource("/view/HomeView.fxml"));
            
            AnchorPane homeOverview = (AnchorPane) loader.load();
            
            Scene scene = new Scene(homeOverview);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            HomeController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile caricare la HomeView.fxml. Causa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
 * @brief Mostra la schermata di gestione dei Libri (CRUD).
 * * Carica il file FXML della gestione Libri e imposta il LibriController.
 * Inoltre, inietta le dipendenze necessarie per il CRUD (Catalogo, MainApp).
 */
    public void mostraLibri(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/LibriView.fxml"));
            
            AnchorPane libriOverview = (AnchorPane) loader.load(); 

            Scene scene = new Scene(libriOverview);
            primaryStage.setScene(scene);
            
            LibriController controller = loader.getController();
            controller.setDati(catalogo);
            controller.setMainApp(this); 

        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile caricare la LibriView.fxml. Causa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
 * @brief Mostra la schermata di gestione degli Utenti (CRUD).
 * * Carica il file FXML della gestione Utenti e imposta l'UtentiController.
 * Inoltre, inietta le dipendenze necessarie per il CRUD (ListaUtenti, MainApp).
 */
    public void mostraUtenti(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/UtentiView.fxml"));
            
            AnchorPane utentiOverview = (AnchorPane) loader.load(); 

            Scene scene = new Scene(utentiOverview);
            primaryStage.setScene(scene);
            
            UtentiController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDati(utenti);

        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile caricare la UtentiView.fxml. Causa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
 * @brief Mostra la schermata di gestione dei Prestiti.
 * * Carica il file FXML della gestione Prestiti e imposta il PrestitiController.
 * Inietta le tre gestioni dati (Libri, Utenti, Prestiti) necessarie per la logica.
 */
    public void mostraPrestiti(){
        try {
            
            FXMLLoader loader = new FXMLLoader();
            
            
            loader.setLocation(MainApp.class.getResource("/view/PrestitiView.fxml"));
            
            
            AnchorPane prestitiOverview = (AnchorPane) loader.load(); 

            
            Scene scene = new Scene(prestitiOverview);
            primaryStage.setScene(scene);
            
            PrestitiController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDati(prestiti, catalogo, utenti);

        } catch (IOException e) {
            System.err.println("ERRORE: Impossibile caricare la PrestitiView.fxml. Causa: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
 * @brief Carica i dati persistenti all'avvio dell'applicazione.
 * * Chiama il GestoreFile per leggere i dati binari dai file e ripopola
 * le liste del Model (Catalogo, ListaUtenti, ListaPrestiti).
 * Gestisce inoltre l'aggiornamento del contatore statico degli ID.
 */
    public void caricaDati(){
        this.gestoreFile.carica(catalogo, utenti, prestiti);
    }
    /**
 * @brief Salva lo stato corrente dell'applicazione.
 * * Chiama il GestoreFile per serializzare l'intero stato del Model
 * (Catalogo, Liste) sui rispettivi file.
 * Questo metodo viene chiamato prima della chiusura dell'applicazione.
 */
    public void salvaDati(){
        this.gestoreFile.salva(catalogo, utenti, prestiti);
    }
}
