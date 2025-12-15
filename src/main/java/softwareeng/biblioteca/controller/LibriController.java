/**
 * @file LibriController.java
 * @brief Controllore per la schermata di gestione CRUD dei Libri.
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
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.collections.ObservableList;

/**
 * @brief Gestisce le interazioni e la logica di business per l'entità Libro.
 *
 * Estende TController<Libro> per implementare le operazioni CRUD (Create, Read, Update, Delete)
 * specifiche per il Catalogo dei Libri.
 */
public class LibriController extends TController<Libro> {

    /** Riferimento all'istanza principale dell'applicazione per la navigazione e i dialog.*/
    private MainApp mainApp;
    /** Interfaccia di gestione del catalogo libri (GestioneLibri).*/
    private GestioneLibri catalogo;
    /** L'oggetto Libro attualmente selezionato nella TableView.*/
    private Libro libroSelezionato;
    
    // Campi FXML per la Tabella (Overview)
    @FXML
    private TableView<Libro> tableLibri;
    @FXML
    private TableColumn<Libro, String> colTitolo;
    @FXML
    private TableColumn<Libro, String> colAutore;
    @FXML
    private TableColumn<Libro, String> colISBN;
    @FXML
    private TableColumn<Libro, Integer> colAnno;
    @FXML
    private TableColumn<Libro, Integer> colCopieTotali;
    @FXML
    private TableColumn<Libro, Integer> colCopieDisponibili;

    // Campi FXML per l'Input e i Dettagli (Pane di dettaglio)
    @FXML
    private TextField tfRicerca; 
    @FXML
    private Label lblDettagliTitolo;
    @FXML
    private Label lblDettagliAutore;
    @FXML
    private Label lblDettagliISBN;
    @FXML
    private Label lblDettagliAnno;
    @FXML
    private Label lblDettagliCopieTotali;
    @FXML
    private Label lblDettagliCopieDisponibili;
    
    //bottoni oer modifica e rimuovi
    @FXML
    private Button btnModifica; // da abilitare/disabilitare
    @FXML
    private Button btnRimuovi;  // da abilitare/disabilitare

    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    @Override
    public void setMainApp(MainApp app){
        this.mainApp = app;
    }

    /**
     * @brief Inietta l'istanza del Gestore dei dati (Catalogo).
     *
     * Esegue il cast dell'interfaccia generica Gestione<Libro> al tipo specifico GestioneLibri
     * per accedere ai metodi specifici (es. ricercaTitolo).
     * @param interfaccia L'istanza del Catalogo.
     */
    @Override
    public void setDati(Gestione<Libro> interfaccia){
        this.catalogo = (GestioneLibri) interfaccia;
        gestisciRicercaLibro();
    }
    
    /**
     * @brief Metodo di inizializzazione per il controller.
     * * Viene chiamato automaticamente dopo che il file fxml è stato caricato.
     * Inizializza le colonne della tabella e popola la tabella con tutti i libri.
     */
    @FXML
    public void initialize() {
        // Inizializzazione delle colonne della tabella (Data Binding)
        // NOTA: I nomi delle proprietà qui sotto devono corrispondere ai getter in Libro.java (es. getTitolo -> "titolo")
        colTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colAutore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn")); // Corretto typo "Factory"
        // NOTA: Assicurati che Libro.java abbia getYear() (per "year") o getAnnoPubblicazione()
        // Nel file caricato prima avevi getYear(), quindi correggo property in "year"
        colAnno.setCellValueFactory(new PropertyValueFactory<>("year")); 
        colCopieTotali.setCellValueFactory(new PropertyValueFactory<>("copieTotali"));
        colCopieDisponibili.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));
        
        // Clear dei dettagli del libro
        mostraDettagliLibro(null); 

        // Listener per la selezione nella tabella
        tableLibri.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                libroSelezionato = newValue; // Aggiorna l'attributo di classe
                mostraDettagliLibro(newValue); // Aggiorna la vista
            });
        
        
    }
    
    /**
     * @brief Aggiorna i campi di dettaglio nella vista laterale.
     * * Abilita i pulsanti Modifica e Rimuovi se viene passato un libro (non null).
     * @param libro L'oggetto Libro da visualizzare o null per cancellare.
     */
    private void mostraDettagliLibro(Libro libro) {
        if (libro != null) {
            // Visualizza i dettagli
            lblDettagliTitolo.setText(libro.getTitolo());
            lblDettagliAutore.setText(libro.getAutore());
            lblDettagliISBN.setText(libro.getISBN());
            lblDettagliAnno.setText(String.valueOf(libro.getYear())); // Uso getYear() come da Model
            lblDettagliCopieTotali.setText(String.valueOf(libro.getCopieTotali()));
            lblDettagliCopieDisponibili.setText(String.valueOf(libro.getCopieDisponibili()));

            // Abilita i pulsanti per le azioni CRUD
            btnModifica.setDisable(false);
            btnRimuovi.setDisable(false);
            
        } else {
            // Cancella i dettagli se nessun libro è selezionato
            lblDettagliTitolo.setText("");
            lblDettagliAutore.setText("");
            lblDettagliISBN.setText("");
            lblDettagliAnno.setText("");
            lblDettagliCopieTotali.setText("");
            lblDettagliCopieDisponibili.setText("");

            // Disabilita i pulsanti CRUD
            btnModifica.setDisable(true);
            btnRimuovi.setDisable(true);
        }
    }
    
    /**
     * @brief Gestisce la logica di ricerca sul catalogo.
     * * Legge il testo dal campo di ricerca e delega l'operazione al Catalogo.
     */
    @FXML
    private void gestisciRicercaLibro() {
        String termineRicerca = tfRicerca.getText();
        ObservableList<Libro> risultati;

        // 1. Recupera i dati (tutti o filtrati)
        if (termineRicerca == null || termineRicerca.trim().isEmpty()) {
            risultati = catalogo.getElenco();
        } else {
            // Nota: assicurati che questo metodo esista nel tuo GestioneLibri o usa quello corretto
            risultati = catalogo.ricercaTitolo(termineRicerca);
        }

        // 2. Avvolgi i risultati in una SortedList
        SortedList<Libro> datiOrdinati = new SortedList<>(risultati);

        // 3. Imposta il comparatore usando il metodo compareTo della classe Libro
        datiOrdinati.setComparator((libro1, libro2) -> libro1.compareTo(libro2));

        // 4. Aggiorna la tabella con la lista ordinata
        tableLibri.setItems(datiOrdinati);
        
        // Pulizia selezione
        tableLibri.getSelectionModel().clearSelection();
        mostraDettagliLibro(null);
    }
    

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    @Override
    public void goHome(){
        mainApp.mostraHome();
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
    @Override
    public void goToPrestiti(){
         if(mainApp != null) {
            mainApp.mostraPrestiti();
        }
    }

    /**
     * @brief Avvia il processo di creazione di un nuovo libro.
     *
     * Richiama showEditDialog() per aprire il form di inserimento dati.
     */
    @Override
    public void clickNuovo(){
        
    
        // Apro il dialog di modifica
        boolean okClicked = showEditDialog(null);
    
        // Se l'utente conferma (il salvataggio è gestito internamente al dialog)
        if (okClicked) {
             // Ricarica la vista se necessario, resettando i filtri
             gestisciRicercaLibro();
        }
    }

    /**
     * @brief Registra l'entità Libro selezionata per le successive operazioni.
     *
     * Imposta l'attributo libroSelezionato con l'elemento passato dalla TableView.
     * @param elemento L'oggetto Libro selezionato.
     */
    @Override
    public void seleziona(Libro elemento){
        this.libroSelezionato = elemento;
    }

    /**
     * @brief Avvia il processo di rimozione del libro selezionato.
     *
     * Richiama il metodo rimuovi del Catalogo. Gestisce le eccezioni di integrità
     * (EliminazioneNonValidaException).
     */
    @Override
    public void rimuovi(){
        if (libroSelezionato != null) {
            // Chiedo conferma all'utente 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Eliminazione");
            alert.setHeaderText("Eliminazione Libro");
            alert.setContentText("Sei sicuro di voler eliminare il libro: " + libroSelezionato.getTitolo() + "?");

            // Se l'utente preme OK
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                try {
                    // Si prova a rimuovere nel Model
                    catalogo.rimuovi(libroSelezionato); 
                    
                    // Aggiorna la vista (rimuovo dalla tabella)
                    tableLibri.getItems().remove(libroSelezionato);
                    
                    // Pulisce la selezione e i dettagli
                    tableLibri.getSelectionModel().clearSelection();
                    mostraDettagliLibro(null); 
                    
                } catch (EliminazioneNonValidaException e) {
                    // CATCH: Gestisco il caso in cui ci sono copie in prestito
                    Alert errore = new Alert(Alert.AlertType.ERROR);
                    errore.setTitle("Errore di Eliminazione");
                    errore.setHeaderText("Impossibile eliminare il libro");
                    errore.setContentText(e.getMessage());
                    errore.showAndWait();
                }
            }
        } else {
            //Se per qualche motivo il bottone era attivo senza selezione
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna Selezione");
            alert.setHeaderText("Nessun libro selezionato");
            alert.setContentText("Seleziona un libro dalla tabella per eliminarlo.");
            alert.showAndWait();
        }
    }

    /**
     * @brief Avvia il processo di modifica del libro selezionato.
     *
     * Richiama showEditDialog(Libro) per aprire il form di modifica pre-compilato.
     */
    @Override
    public void modifica(){
         if (libroSelezionato != null) {
            boolean okClicked = showEditDialog(libroSelezionato);
            if (okClicked) {
                mostraDettagliLibro(libroSelezionato); // Aggiorna i dettagli a vista
                tableLibri.refresh(); // Aggiorna la tabella
            }
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Nessuna Selezione");
             alert.setHeaderText("Nessun libro selezionato");
             alert.setContentText("Seleziona un libro dalla tabella per modificarlo.");
             alert.showAndWait();
        }
    }

    /**
     * @brief Mostra il dialog di modifica per il libro selezionato.
     *
     * @param elemento Il Libro da pre-compilare e modificare.
     * @return true se l'utente ha premuto OK, false se ha annullato.
     */
    @Override
    public boolean showEditDialog(Libro elemento){
        // Istanzia la classe interna che gestisce il popup
        LibroFormDialog dialog = new LibroFormDialog(elemento);
        
        // Mostra il popup e attende (modalmente)
        Optional<ButtonType> result = dialog.showAndWait();
        
        // Restituisce true solo se l'utente ha premuto OK
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * @brief Mostra il dialog di creazione di un nuovo libro.
     *
     * @return true se l'utente ha premuto OK, false se ha annullato.
     */
    @Override
    public boolean showEditDialog(){
        return showEditDialog(null);
    }

    /**
     * @brief Popola i campi della form con i dati del libro.
     *
     * @param elemento Il Libro con i dati da visualizzare.
     */
    @Override
    public void popolaForm(Libro elemento){
        mostraDettagliLibro(elemento);
    }

    @Override
    public void sendAttrbitui(Map<String, Object> attributi){
        // Non utilizzato direttamente qui, gestito dal Dialog interno
    }
    
    
    /**
     * @brief Classe interna per la gestione del Popup (Dialog) di inserimento/modifica.
     * Estende Dialog per creare una finestra modale sopra la schermata corrente.
     */
    private class LibroFormDialog extends Dialog<ButtonType> {
        
        private TextField tfTitolo;
        private TextField tfAutore;
        private TextField tfISBN;
        private TextField tfAnno;
        private TextField tfCopie;
        
        // Flag per sapere se stiamo modificando o creando
        private boolean isModifica;
        
        public LibroFormDialog(Libro libro) {
            this.setTitle("Dettagli Libro");
            
            // Determiniamo la modalità controllando se è stato passato null
            if (libro == null) {
                this.isModifica = false;
            } else {
                this.isModifica = true;
            }
            
            
            this.setHeaderText(isModifica ? "Modifica Libro" : "Inserisci Nuovo Libro");
            
            // Bottoni
            this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            
            // Layout
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            
            // Inizializzazione Campi
            tfTitolo = new TextField(libro == null ? "" : libro.getTitolo());
            tfAutore = new TextField(libro == null ? "" : libro.getAutore());
            tfISBN = new TextField(libro == null ? "" : libro.getISBN());
            
            // Gestione Anno e Copie (gestione stringa vuota per nuovi inserimenti)
            String annoStr = (libro==null) ? "" : String.valueOf(libro.getYear());
            tfAnno = new TextField(annoStr);
            
            String copieStr = libro==null ? "" : String.valueOf(libro.getCopieTotali());
            tfCopie = new TextField(copieStr);

            // Se è modifica, disabilitiamo l'ISBN (chiave primaria non modificabile)
            if (isModifica) {
                tfISBN.setDisable(true);
            }
            
            // Aggiunta alla griglia
            grid.add(new Label("Titolo:"), 0, 0);
            grid.add(tfTitolo, 1, 0);
            grid.add(new Label("Autore:"), 0, 1);
            grid.add(tfAutore, 1, 1);
            grid.add(new Label("ISBN:"), 0, 2);
            grid.add(tfISBN, 1, 2);
            grid.add(new Label("Anno:"), 0, 3);
            grid.add(tfAnno, 1, 3);
            grid.add(new Label("Copie Totali:"), 0, 4);
            grid.add(tfCopie, 1, 4);
            
            this.getDialogPane().setContent(grid);
            
            // Converter
            this.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    try {
                        // Chiamiamo il metodo che usa il CATALOGO
                        gestisciSalvataggio(libro);
                        return ButtonType.OK;
                    } catch (Exception e) {
                        // Mostriamo l'errore e impediamo la chiusura (ritornando null o gestendo l'evento)
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore Dati");
                        alert.setHeaderText("Impossibile salvare");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                        
                        return null; 
                    }
                }
                return null;
            });
        }
        
        /**
         * Decide se chiamare catalogo.aggiungi o catalogo.modifica
         */
        private void gestisciSalvataggio(Libro libroOriginale) {
            String titolo = tfTitolo.getText();
            String autore = tfAutore.getText();
            String isbn = tfISBN.getText();
            int anno;
            int copie;
            
            // Validazione base input numerici
            try {
                anno = Integer.parseInt(tfAnno.getText());
                copie = Integer.parseInt(tfCopie.getText());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Anno e Copie devono essere numeri interi.");
            }

            if (isModifica) {
                // --- CASO MODIFICA ---
                // Prepariamo la mappa come richiesto da GestioneLibri.modifica
                Map<String, Object> attributi = new HashMap<>();
                attributi.put("titolo", titolo);
                attributi.put("autore", autore);
                attributi.put("year", anno);           // Match con case "year" in Libro.java
                attributi.put("copietotali", copie);   // Match con case "copietotali" in Libro.java
                
                // Chiamata al CATALOGO tramite l'istanza del Controller esterno
                LibriController.this.catalogo.modifica(libroOriginale, attributi);
                
            } else {
                // --- CASO NUOVO INSERIMENTO ---
                // Verifica duplicati ISBN prima di procedere
                if (LibriController.this.catalogo.checkID(isbn)) {
                    throw new IllegalArgumentException("Esiste già un libro con questo ISBN.");
                }
                
                // Creiamo una NUOVA istanza perché dobbiamo settare l'ISBN (che non è modificabile via mappa)
                Libro nuovoLibro = new Libro(titolo, autore, isbn, anno, copie);
                
                // Chiamata al CATALOGO per aggiungere
                LibriController.this.catalogo.aggiungi(nuovoLibro);
            }
        }
    }
}