/**
 * @file UtentiController.java
 * @brief Controllore per la schermata di gestione CRUD degli Utenti.
 * @version 1.0
 */
package softwareeng.biblioteca.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import softwareeng.biblioteca.application.MainApp;
import softwareeng.biblioteca.model.*;
import softwareeng.biblioteca.model.exceptions.EliminazioneNonValidaException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.GridPane;
import javafx.collections.transformation.SortedList;
import javafx.collections.ObservableList;

/**
 * @brief Gestisce le interazioni e la logica di business per l'entità Utente.
 *
 * Estende TController<Utente> per implementare le operazioni CRUD (Create, Read, Update, Delete)
 * specifiche per la Lista Utenti.
 */
public class UtentiController extends TController<Utente> {
    
    private MainApp mainApp;
    private GestioneUtenti gestioneUtenti;
    private Utente utenteSelezionato;
    
    // --- FXML: Tabella ---
    @FXML
    private TableView<Utente> tableUtenti;
    @FXML
    private TableColumn<Utente, String> colNome;
    @FXML
    private TableColumn<Utente, String> colCognome;
    @FXML
    private TableColumn<Utente, String> colMatricola;
    @FXML
    private TableColumn<Utente, String> colMail;
    @FXML
    private TableColumn<Utente, Integer> colNumPrestiti; // Colonna calcolata

    // --- FXML: Dettagli e Ricerca ---
    @FXML
    private TextField tfRicerca;
    @FXML
    private Label lblDettagliNome;
    @FXML
    private Label lblDettagliCognome;
    @FXML
    private Label lblDettagliMatricola;
    @FXML
    private Label lblDettagliMail;
    @FXML
    private Label lblDettagliNumPrestiti;
    
    // --- FXML: Bottoni ---
    @FXML
    private Button btnModifica;
    @FXML
    private Button btnRimuovi;

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     */
    @Override
    public void setMainApp(MainApp app){
        this.mainApp = app;
    }
    
    /**
     * @brief Inietta l'istanza del Gestore dei dati (ListaUtenti).
     */
    @Override
    public void setDati(Gestione<Utente> interfaccia){
        this.gestioneUtenti = (GestioneUtenti) interfaccia;
        
        gestisciRicercaUtente();
    }
    
    /**
     * @brief Inizializza il controller e il binding della tabella.
     */
    @FXML
    public void initialize() {
        // Binding Colonne -> Getter di Utente
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        
        // Colonna calcolata: Numero di prestiti attivi (dimensione della lista)
        // Utile per vedere subito se un utente può essere cancellato o no
        colNumPrestiti.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getPrestiti().size()).asObject());

        // Reset dettagli
        mostraDettagliUtente(null);

        // Listener Selezione
        tableUtenti.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                utenteSelezionato = newValue;
                mostraDettagliUtente(newValue);
            });
        
    }

    /**
     * @brief Aggiorna il pannello laterale con i dettagli dell'utente.
     */
    private void mostraDettagliUtente(Utente utente) {
        if (utente != null) {
            lblDettagliNome.setText(utente.getNome());
            lblDettagliCognome.setText(utente.getCognome());
            lblDettagliMatricola.setText(utente.getMatricola());
            lblDettagliMail.setText(utente.getMail() != null ? utente.getMail() : "");
            // Mostra quanti prestiti ha in corso
            lblDettagliNumPrestiti.setText(String.valueOf(utente.getPrestiti().size()));
            
            btnModifica.setDisable(false);
            btnRimuovi.setDisable(false);
        } else {
            lblDettagliNome.setText("");
            lblDettagliCognome.setText("");
            lblDettagliMatricola.setText("");
            lblDettagliMail.setText("");
            lblDettagliNumPrestiti.setText("");
            
            btnModifica.setDisable(true);
            btnRimuovi.setDisable(true);
        }
    }
    
    /**
     * @brief Gestisce la ricerca per Cognome.
     */
    @FXML
    private void gestisciRicercaUtente() {
        String input = tfRicerca.getText();
        ObservableList<Utente> risultati;
        
        // 1. Recupera i dati (tutti o filtrati)
        if (input == null || input.trim().isEmpty()) {
            risultati = gestioneUtenti.getElenco();
        } else {
            risultati = gestioneUtenti.ricercaCognome(input);
        }
        
        // 2. Avvolgi i risultati in una SortedList
        SortedList<Utente> datiOrdinati = new SortedList<>(risultati);
        
        // 3. Imposta il comparatore usando il metodo compareTo della classe Utente
        datiOrdinati.setComparator((u1, u2) -> u1.compareTo(u2));
        
        // 4. Aggiorna la tabella con la lista ordinata
        tableUtenti.setItems(datiOrdinati);
        
        // Pulisce selezione e dettagli
        tableUtenti.getSelectionModel().clearSelection();
        mostraDettagliUtente(null);
    }
    
    /**
     * @brief Torna alla schermata principale (Home).
     */
    @Override
    public void goHome(){
         if(mainApp != null) {
            mainApp.mostraHome();
        }
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Prestiti.
     */
    @Override
    public void goToPrestiti(){
        if(mainApp != null) {
            mainApp.mostraPrestiti();
        }
    }
    
    /**
     * @brief Passa alla schermata di gestione dei Libri.
     */
    public void goToLibri(){
        if(mainApp != null) {
            mainApp.mostraLibri();
        }
    }
    
    /**
     * @brief Avvia il processo di creazione di un nuovo utente.
     */
    @Override
    public void clickNuovo(){
        
        
        // Apre il dialog 
        boolean okClicked = showEditDialog();
        
        if (okClicked) {
            // Refresh per assicurarsi che il nuovo elemento sia visibile (specie se c'erano filtri)
            gestisciRicercaUtente(); 
        }
    }
    
    /**
     * @brief Registra l'entità Utente selezionata.
     */
    @Override
    public void seleziona(Utente utente){
        this.utenteSelezionato = utente;
    }
    
    /**
     * @brief Avvia il processo di rimozione dell'utente selezionato.
     * Gestisce EliminazioneNonValidaException (se l'utente ha prestiti).
     */
    @Override
    public void rimuovi(){
        if (utenteSelezionato != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Eliminazione");
            alert.setHeaderText("Eliminazione Utente");
            alert.setContentText("Eliminare l'utente " + utenteSelezionato.getCognome() + "?");

            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                try {
                    // Tenta la rimozione nel Model
                    gestioneUtenti.rimuovi(utenteSelezionato);
                    
                    // Aggiorna la vista
                    tableUtenti.getItems().remove(utenteSelezionato);
                    tableUtenti.getSelectionModel().clearSelection();
                    mostraDettagliUtente(null);
                    
                } catch (EliminazioneNonValidaException e) {
                    Alert errore = new Alert(Alert.AlertType.ERROR);
                    errore.setTitle("Impossibile Eliminare");
                    errore.setHeaderText("Violazione Integrità");
                    // Messaggio dal model: "L'utente non può essere rimosso prima di restituire tutti i prestiti."
                    errore.setContentText(e.getMessage());
                    errore.showAndWait();
                }
            }
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Nessuna Selezione");
             alert.setContentText("Seleziona un utente per eliminarlo.");
             alert.showAndWait();
        }
    }
    
    /**
     * @brief Avvia il processo di modifica dell'utente selezionato.
     */
    @Override
    public void modifica(){
        if (utenteSelezionato != null) {
            boolean okClicked = showEditDialog(utenteSelezionato);
            if (okClicked) {
                mostraDettagliUtente(utenteSelezionato);
                tableUtenti.refresh(); // Forza l'aggiornamento grafico della tabella
            }
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Nessuna Selezione");
             alert.setContentText("Seleziona un utente per modificarlo.");
             alert.showAndWait();
        }
    }
    
    /**
     * @brief Mostra il dialog di modifica/creazione.
     */
    @Override
    public boolean showEditDialog(Utente utente){
        UtenteFormDialog dialog = new UtenteFormDialog(utente);
        Optional<ButtonType> result = dialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    /**
     * @brief Overload per nuovo inserimento.
     */
    @Override    
    public boolean showEditDialog(){
        return showEditDialog(null);
    }
    
    /**
     * @brief Popola i campi della form (Pannello Dettagli).
     */
    @Override
    public void popolaForm(Utente utente){
        mostraDettagliUtente(utente);
    }
    
    @Override
    public void sendAttrbitui(Map<String, Object> attributi){
        // Gestito internamente dal Dialog
    }
    
    // =========================================================================
    // INNER CLASS: Dialog per Utente
    // =========================================================================
    
    private class UtenteFormDialog extends Dialog<ButtonType> {
        
        private TextField tfNome;
        private TextField tfCognome;
        private TextField tfMatricola;
        private TextField tfMail;
        
        private boolean isModifica;
        
        public UtenteFormDialog(Utente utente) {
            this.setTitle("Dettagli Utente");
            
            if (utente == null) {
                this.isModifica = false;
            } else {
                this.isModifica = true;
            }
            this.setHeaderText(isModifica ? "Modifica Utente" : "Nuovo Utente");
            
            this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            
            tfNome = new TextField(utente == null ? "" : utente.getNome());
            tfCognome = new TextField(utente == null ? "" : utente.getCognome());
            tfMatricola = new TextField(utente == null ? "" : utente.getMatricola());
            tfMail = new TextField(utente == null ? "" : utente.getMail());
            
            // Matricola non modificabile se l'utente esiste già
            if (isModifica) {
                tfMatricola.setDisable(true);
            }
            
            grid.add(new Label("Nome:"), 0, 0);
            grid.add(tfNome, 1, 0);
            grid.add(new Label("Cognome:"), 0, 1);
            grid.add(tfCognome, 1, 1);
            grid.add(new Label("Matricola:"), 0, 2);
            grid.add(tfMatricola, 1, 2);
            grid.add(new Label("Email:"), 0, 3);
            grid.add(tfMail, 1, 3);
            
            this.getDialogPane().setContent(grid);
            
            this.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    try {
                        gestisciSalvataggio(utente);
                        return ButtonType.OK;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setHeaderText("Dati non validi");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                        return null;
                    }
                }
                return null;
            });
        }
        
        private void gestisciSalvataggio(Utente utenteOriginale) {
            String nome = tfNome.getText();
            String cognome = tfCognome.getText();
            String matricola = tfMatricola.getText();
            String mail = tfMail.getText();
            
            if (nome.isEmpty() || cognome.isEmpty() || matricola.isEmpty()) {
                throw new IllegalArgumentException("Nome, Cognome e Matricola sono obbligatori.");
            }

            if (isModifica) {
                // MODIFICA: Usiamo la mappa come da Utente.modifica()
                Map<String, Object> attributi = new HashMap<>();
                attributi.put("nome", nome);
                attributi.put("cognome", cognome);
                attributi.put("mail", mail);
                
                UtentiController.this.gestioneUtenti.modifica(utenteOriginale, attributi);
                
            } else {
                // NUOVO: Verifica unicità matricola e Aggiungi
                if (UtentiController.this.gestioneUtenti.checkID(matricola)) {
                    throw new IllegalArgumentException("Matricola già esistente.");
                }
                
                Utente nuovo = new Utente(nome, cognome, matricola, mail);
                UtentiController.this.gestioneUtenti.aggiungi(nuovo);
            }
        }
    }
}