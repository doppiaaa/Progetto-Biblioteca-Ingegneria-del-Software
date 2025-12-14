/**
 * @file HomeController.java
 * @brief Controllore per la schermata principale (Home) dell'applicazione.
 * @version 1.0
 */
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.application.MainApp;
import javafx.application.Platform;

/**
 * @brief Gestisce le interazioni della schermata Home.
 *
 * Questa classe è responsabile della gestione degli eventi (click sui pulsanti)
 * che permettono la navigazione verso le altre sezioni dell'applicazione (Libri, Utenti, Prestiti).
 */
public class HomeController {
    /** Riferimento all'istanza principale dell'applicazione per la navigazione.*/
    private MainApp mainApp;

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    public void setMainApp(MainApp app){
        this.mainApp = app;
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Libri.
     * * Gestisce il click per passare alla schermata di gestione dei Libri.
     */
    public void goToLibri(){
        if(mainApp != null) {
            
            mainApp.mostraLibri();
        }
    }

    /**
     * @brief Passa alla schermata di gestione degli Utenti.
     * * Gestisce il click per passare alla schermata di gestione degli Utenti.
     */
    public void goToUtenti(){
        if(mainApp != null) {
        
            mainApp.mostraUtenti();
        } 
    }

     /**
     * @brief Passa alla schermata di gestione dei Prestiti.
     * * Gestisce il click per passare alla schermata di gestione dei Prestiti.
     */
    public void goToPrestiti(){
        if(mainApp != null) {
            
            mainApp.mostraPrestiti();
        }
    }
    
    /**
     * @brief Gestisce l'evento di chiusura dell'applicazione.
     * * Richiama il metodo di salvataggio dei dati dalla MainApp prima di terminare
     * l'esecuzione del programma, garantendo la persistenza delle modifiche.
     * Tipicamente associato al pulsante "Esci" dell'interfaccia utente.
     * * @see MainApp#salvaDati()
     */
    public void esci(){
        if(mainApp != null) {
            // 1. Delega alla MainApp il compito di salvare lo stato corrente
            mainApp.salvaDati();
        }
        // 2. Termina la piattaforma JavaFX in modo pulito
        Platform.exit();
    }
}
