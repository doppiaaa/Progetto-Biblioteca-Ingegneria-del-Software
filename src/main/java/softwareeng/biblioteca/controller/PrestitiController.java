/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.model.*;
import softwareeng.biblioteca.application.MainApp;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory; // Serve per le nuove tabelle
import java.time.LocalDate; // Serve per il DatePicker
import softwareeng.biblioteca.model.exceptions.PrestitoNonValidoException;

/**
 *
 * @author cashrules
 */
public class PrestitiController {
    private MainApp mainApp;
    private GestionePrestiti prestiti;
    private GestioneLibri catalogo;
    private GestioneUtenti utenti;
    
    private Libro libroSelezionato;
    private Utente utenteSelezionato;
    private Prestito prestitoSelezionato;
    
    // --- FXML: Tabella Prestiti ---
    @FXML
    private TableView<Prestito> tablePrestiti;
    @FXML
    private TableColumn<Prestito, String> colID;
    @FXML
    private TableColumn<Prestito, String> colLibro;
    @FXML
    private TableColumn<Prestito, String> colUtente;
    @FXML
    private TableColumn<Prestito, String> colDataInizio;
    @FXML
    private TableColumn<Prestito, String> colDataFine;
    @FXML
    private TableColumn<Prestito, String> colStato; // Attivo o Restituito

    // --- FXML: Dettagli e Ricerca ---
    @FXML
    private TextField tfRicerca;
    @FXML
    private Label lblDettagliID;
    @FXML
    private Label lblDettagliLibro;
    @FXML
    private Label lblDettagliUtente;
    @FXML
    private Label lblDettagliInizio;
    @FXML
    private Label lblDettagliFine;
    @FXML
    private Label lblDettagliStato;

    // --- FXML: Bottoni ---
    @FXML
    private Button btnRestituisci; // Sostituisce Rimuovi/Modifica
    
    // --- FXML: PANELLI (WIZARD) ---
    @FXML private AnchorPane paneElencoPrestiti; // Schermata principale
    @FXML private AnchorPane paneSelezioneLibro; // Step 1 Nuovo Prestito
    @FXML private AnchorPane paneSelezioneUtente; // Step 2 Nuovo Prestito

    // --- FXML: TABELLA LIBRI (Step 1) ---
    @FXML private TableView<Libro> tableLibri;
    @FXML private TableColumn<Libro, String> colLibroTit;
    @FXML private TableColumn<Libro, String> colLibroAut;
    @FXML private TableColumn<Libro, Integer> colLibroDisp;
    @FXML private TextField tfRicercaLibri;

    // --- FXML: TABELLA UTENTI (Step 2) ---
    @FXML private TableView<Utente> tableUtenti;
    @FXML private TableColumn<Utente, String> colUtenteNom;
    @FXML private TableColumn<Utente, String> colUtenteCogn;
    @FXML private TableColumn<Utente, String> colUtenteMatr;
    @FXML private TextField tfRicercaUtenti;
    
    // --- FXML: Input Data ---
    @FXML private DatePicker dateScadenza;

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    public void setMainApp(MainApp app){
        this.mainApp = app;
    }
    
    @FXML
    public void initialize() {
        // 1. Binding Colonne Semplici
        colID.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getID()));
        colLibro.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLibro().getTitolo()));
        colUtente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUtente().getCognome()));
        
        // 2. Binding Date (CON CONTROLLO NULL PER EVITARE CRASH)
        colDataInizio.setCellValueFactory(cell -> {
            if (cell.getValue().getDataInizio() != null) {
                return new SimpleStringProperty(cell.getValue().getDataInizio().toString());
            }
            return new SimpleStringProperty("");
        });

        colDataFine.setCellValueFactory(cell -> {
            // CRUCIALE: Se il libro è restituito, questa data è NULL. 
            // Dobbiamo gestire il caso per evitare la SCHERMATA BIANCA.
            LocalDate dataFine = cell.getValue().getDataPrevistaRestituzione();
            if (dataFine != null) {
                return new SimpleStringProperty(dataFine.toString());
            } else {
                return new SimpleStringProperty("-"); // O "Restituito"
            }
        });

        // 3. Colonna Stato
        colStato.setCellValueFactory(cell -> {
            boolean attivo = cell.getValue().isAttivo();
            // Nota: Se restituito, dataFine è null, quindi isScaduto potrebbe dare errore se non gestito bene in Prestito
            // Ma basiamoci su isAttivo
            if (!attivo) return new SimpleStringProperty("Restituito");
            
            boolean scaduto = cell.getValue().isScaduto();
            return new SimpleStringProperty(scaduto ? "SCADUTO" : "In Corso");
        });

        // 4. Setup Tabelle Wizard (Libri e Utenti)
        colLibroTit.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colLibroAut.setCellValueFactory(new PropertyValueFactory<>("autore"));
        colLibroDisp.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));

        colUtenteNom.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUtenteCogn.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colUtenteMatr.setCellValueFactory(new PropertyValueFactory<>("matricola"));

        // 5. Setup Listener e Selezione
        mostraDettagliPrestito(null);
        
        tablePrestiti.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldV, newV) -> {
                seleziona(newV);
                mostraDettagliPrestito(newV);
            });
            
        tableLibri.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> seleziona(newV));
        tableUtenti.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> seleziona(newV));

        // 6. Visibilità Iniziale
        mostraPannello(paneElencoPrestiti);
    }
    
    // Mostra un solo pannello e nasconde gli altri
    private void mostraPannello(AnchorPane paneDaMostrare) {
        // Verifica null-safety per evitare errori se i pannelli non sono ancora caricati
        if (paneElencoPrestiti != null) paneElencoPrestiti.setVisible(false);
        if (paneSelezioneLibro != null) paneSelezioneLibro.setVisible(false);
        if (paneSelezioneUtente != null) paneSelezioneUtente.setVisible(false);
        
        if (paneDaMostrare != null) paneDaMostrare.setVisible(true);
    }

    /**
     * @brief Inietta le tre istanze dei Gestori dati necessarie per la transazione.
     *
     * @param prestiti L'istanza del GestorePrestiti (ListaPrestiti).
     * @param catalogo L'istanza del GestoreLibri (Catalogo).
     * @param utenti L'istanza del GestoreUtenti (ListaUtenti).
     */
    
    public void setDati(GestionePrestiti prestiti, GestioneLibri catalogo, GestioneUtenti utenti){
        this.prestiti = prestiti;
        this.catalogo = catalogo;
        this.utenti = utenti;
        
        // Chiama il nuovo metodo
        aggiornaTabellaPrestiti();
    }
    
    private void mostraDettagliPrestito(Prestito p) {
        if (p != null) {
            lblDettagliID.setText(p.getID());
            lblDettagliLibro.setText(p.getLibro().getTitolo());
            lblDettagliUtente.setText(p.getUtente().getNome() + " " + p.getUtente().getCognome());
            // Nota: sarebbe meglio avere getter diretti per le date in Prestito.java
            lblDettagliInizio.setText("Vedi tabella"); 
            lblDettagliFine.setText("Vedi tabella");   
            
            boolean attivo = p.isAttivo();
            lblDettagliStato.setText(attivo ? (p.isScaduto() ? "SCADUTO" : "Attivo") : "Restituito");
            
            // Il tasto "Restituisci" è attivo solo se il prestito è ancora attivo
            btnRestituisci.setDisable(!attivo);
        } else {
            lblDettagliID.setText("");
            lblDettagliLibro.setText("");
            lblDettagliUtente.setText("");
            lblDettagliInizio.setText("");
            lblDettagliFine.setText("");
            lblDettagliStato.setText("");
            btnRestituisci.setDisable(true);
        }
    }

    @FXML
    private void aggiornaTabellaPrestiti() {
        if (prestiti == null) return;
        
        ObservableList<Prestito> elenco = prestiti.getElenco();
        String filtro = tfRicerca != null ? tfRicerca.getText().toLowerCase() : "";

        // Filtra per cognome utente o titolo libro
        FilteredList<Prestito> filteredData = new FilteredList<>(elenco, p -> {
            if (filtro.isEmpty()) return true;
            return p.getUtente().getCognome().toLowerCase().contains(filtro) || 
                   p.getLibro().getTitolo().toLowerCase().contains(filtro);
        });

        SortedList<Prestito> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator((p1, p2) -> p1.compareTo(p2)); // Usa compareTo di Prestito
        tablePrestiti.setItems(sortedData);
        
        // Pulisce la selezione
        tablePrestiti.getSelectionModel().clearSelection();
        mostraDettagliPrestito(null);
    }
    
    @FXML
    private void aggiornaTabellaLibri() {
        if (catalogo == null) return;
        ObservableList<Libro> elenco = catalogo.getElenco();
        String filtro = tfRicercaLibri != null ? tfRicercaLibri.getText().toLowerCase() : "";
        
        FilteredList<Libro> filteredData = new FilteredList<>(elenco, l -> {
            if (filtro.isEmpty()) return true;
            return l.getTitolo().toLowerCase().contains(filtro);
        });
        
        SortedList<Libro> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator((l1, l2) -> l1.compareTo(l2)); // Usa compareTo di Libro
        tableLibri.setItems(sortedData);
    }

    @FXML
    private void aggiornaTabellaUtenti() {
        if (utenti == null) return;
        ObservableList<Utente> elenco = utenti.getElenco();
        String filtro = tfRicercaUtenti != null ? tfRicercaUtenti.getText().toLowerCase() : "";
        
        FilteredList<Utente> filteredData = new FilteredList<>(elenco, u -> {
            if (filtro.isEmpty()) return true;
            return u.getCognome().toLowerCase().contains(filtro);
        });
        
        SortedList<Utente> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator((u1, u2) -> u1.compareTo(u2)); // Usa compareTo di Utente
        tableUtenti.setItems(sortedData);
    }

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    public void goHome(){
        if(mainApp != null) {
            mainApp.mostraHome();
        }
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
     * @brief Mostra un dialog per la selezione di un Libro.
     *
     * Tipicamente apre una finestra di ricerca e selezione dal Catalogo per l'erogazione di un nuovo prestito.
     */
    @FXML // Da collegare al bottone "Indietro" nel paneUtenti
    public void mostraDialogLibri(){
        // Aggiorna la tabella per assicurarsi che i dati siano freschi
        aggiornaTabellaLibri();
        
        // Mostra il pannello Libri
        mostraPannello(paneSelezioneLibro);
    }
    
    /**
     * @brief Mostra un dialog per la selezione di un Utente.
     *
     * Tipicamente apre una finestra di ricerca e selezione dalla ListaUtenti per l'erogazione di un nuovo prestito.
     */
    @FXML // Da collegare al bottone "Avanti" nel paneLibri
    public void mostraDialogUtenti(){
        
        
        
        // Validazione Selezione Libro
        if (libroSelezionato == null) {
            mostraErrore("Nessuna selezione", "Seleziona un libro per procedere.");
            return;
        }
        
        // Validazione Disponibilità
        if (!libroSelezionato.checkDisponibilita()) {
            mostraErrore("Libro non disponibile", "Non ci sono copie disponibili per questo libro.");
            return;
        }

        // Se tutto ok, passa allo step successivo
        aggiornaTabellaUtenti(); // Popola la tabella utenti
        
        // Imposta una data di scadenza di default (es. oggi + 30 giorni)
        if(dateScadenza.getValue() == null) {
            dateScadenza.setValue(LocalDate.now().plusDays(30)); 
        }
        
        mostraPannello(paneSelezioneUtente);
    }

    /**
     * @brief Prepara l'interfaccia utente per l'inserimento di un nuovo prestito.
     *
     * Resetta i campi di selezione e abilita i pulsanti pertinenti.
     */
    @FXML
    public void clickNuovo(){
        // Resetta le selezioni temporanee
        libroSelezionato = null;
        utenteSelezionato = null;
        
        // Avvia il flusso mostrando la selezione libri
        mostraDialogLibri();
    }
    
    /**
     * @brief Esegue la transazione di creazione di un nuovo prestito.
     *
     * Chiama `GestionePrestiti::addPrestito(Utente, Libro)`. Gestisce le eccezioni
     * relative alla non validità del prestito (es. PrestitoNonValidoException).
     */
    @FXML // Da collegare al bottone "Conferma" nel paneUtenti
    public void clickConferma(){
        
        LocalDate dataFine = dateScadenza.getValue();

        // Validazioni Input
        if (utenteSelezionato == null) {
            mostraErrore("Nessuna selezione", "Seleziona un utente per completare il prestito.");
            return;
        }
        if (dataFine == null || dataFine.isBefore(LocalDate.now())) {
             mostraErrore("Data non valida", "La data di restituzione deve essere futura.");
             return;
        }

        try {
            // TENTATIVO DI SALVATAGGIO NEL MODEL
            // Nota: qui usiamo le variabili di istanza libroSelezionato e utenteSelezionato
            prestiti.addPrestito(utenteSelezionato, libroSelezionato, dataFine);
            
            // Successo: Messaggio e Ritorno alla schermata principale
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successo");
            alert.setHeaderText("Prestito Registrato");
            alert.setContentText("Prestito creato con successo!");
            alert.showAndWait();
            
            // Ricarica la tabella principale e torna alla vista elenco
            aggiornaTabellaPrestiti();
            mostraPannello(paneElencoPrestiti);

        } catch (PrestitoNonValidoException e) {
            // Gestione eccezioni di business (es. utente con troppi prestiti)
            mostraErrore("Prestito non valido", e.getMessage());
        }
    }
    
    @FXML
    public void annullaOperazione() {
        // Pulisce le selezioni e torna alla home dei prestiti
        libroSelezionato = null;
        utenteSelezionato = null;
        mostraPannello(paneElencoPrestiti);
    }

    private void mostraErrore(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
    
    /**
     * @brief Seleziona un Prestito dalla TableView principale.
     *
     * Imposta l'attributo prestitoSelezionato per le operazioni di disattivazione/rimozione.
     * @param elemento Il Prestito selezionato.
     */
    
    public void seleziona(Prestito elemento){
        this.prestitoSelezionato = elemento;
        // Aggiorna i dettagli a vista (se siamo nella schermata principale)
        mostraDettagliPrestito(elemento);
    }
    
    /**
     * @brief Seleziona un Libro dal dialog di ricerca (per il nuovo prestito).
     *
     * Imposta l'attributo libroSelezionato.
     * @param libro Il Libro selezionato.
     */
    public void seleziona(Libro libro){
        this.libroSelezionato = libro;
    }
    
    /**
     * @brief Seleziona un Utente dal dialog di ricerca (per il nuovo prestito).
     *
     * Imposta l'attributo utenteSelezionato.
     * @param utente L'Utente selezionato.
     */
    public void seleziona(Utente utente){
        this.utenteSelezionato = utente;

    }

    
    
    /**
     * @brief Implementa l'azione di restituzione di un libro.
     *
     * Chiama `GestionePrestiti::disattiva(Prestito)` per chiudere il prestito,
     * aggiornare lo stato del libro (upCopie) e dell'utente (rimuoviPrestito).
     */
    @FXML
    public void disattiva() { 
        if (prestitoSelezionato != null && prestitoSelezionato.isAttivo()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Restituzione");
            alert.setHeaderText("Chiudi Prestito");
            alert.setContentText("Confermi la restituzione del libro: " + prestitoSelezionato.getLibro().getTitolo() + "?");

            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                // 1. Modifica lo stato nel Model
                prestiti.disattiva(prestitoSelezionato);
                
                // 2. Ricarica la tabella (applica ordinamento compareTo)
                aggiornaTabellaPrestiti(); 
                
                // 3. FORZA il ridisegno delle celle (aggiorna la scritta "In Corso" -> "Restituito")
                tablePrestiti.refresh();
                
                // 4. Aggiorna i dettagli a lato
                mostraDettagliPrestito(prestitoSelezionato);
            }
        }
    }

}
