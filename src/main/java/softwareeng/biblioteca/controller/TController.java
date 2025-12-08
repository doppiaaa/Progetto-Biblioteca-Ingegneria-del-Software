
package softwareeng.biblioteca.controller;
import softwareeng.biblioteca.model.Gestione;
import softwareeng.biblioteca.application.MainApp;
import java.util.Map;

/**
 * @class TController
 * @brief Classe base astratta per i Controllori delle schermate di gestione dati (CRUD).
 * * * Questa classe astratta stabilisce i metodi di contratto e implementa parzialmente
 * (se necessario) la logica comune di navigazione e le operazioni CRUD
 * standard (Aggiungi, Rimuovi, Modifica) per un'entità generica T (Libro, Utente).
 * Le classi figlie devono estenderla e implementare tutti i metodi astratti.
 *
 * @param <T> Il tipo di entità gestita dal controllore (es. Libro, Utente).
 * @author [Il tuo nome]
 * @version 1.0
 */
public abstract class TController<T> {
    
    /**
     * @brief Imposta il riferimento alla classe MainApp.
     * Imposta il riferimento alla classe principale MainApp per la gestione
     * della navigazione e l'accesso ai dati globali.
     * @param app L'istanza della MainApp dell'applicazione.
     */
    public abstract void setMainApp(MainApp app);
    
    /**
     * * @brief Inietta l'istanza del Gestore dei dati specifico.
     * Inietta l'istanza del Gestore dei dati specifico necessario al controllore.
     * @param interfaccia L'istanza del Gestore dei dati (Gestione<T>).
     */
    public abstract void setDati(Gestione<T> interfaccia);

    /**
     * @brief Torna alla schermata principale (Home).
     * * Gestisce il click per tornare alla schermata principale (Home).
     */
    public abstract void goHome();

    /**
     * @brief Passa alla schermata di gestione dei Prestiti.
     * * Gestisce il click per passare alla schermata di gestione dei Prestiti.
     */
    public abstract void goToPrestiti();

    /**
     * @brief Prepara l'interfaccia per un nuovo inserimento.
     * * Prepara il controllore ad accettare l'inserimento di una nuova entità.
     * Tipicamente, apre il dialog di modifica/creazione con campi vuoti.
     */
    public abstract void clickNuovo();

    /**
     * @brief Seleziona l'entità T sulla TableView.
     * * Seleziona e memorizza l'entità T (riga) sulla quale eseguire le operazioni CRUD.
     * Tipicamente chiamato da un Listener sulla TableView.
     * @param elemento L'entità T selezionata.
     */
    public abstract void seleziona(T elemento);

    /**
     * @brief Gestisce la richiesta di rimozione dell'entità T.
     * * Gestisce la richiesta di rimozione dell'entità T selezionata.
     * La logica interna includerà il controllo di integrità (es. prestiti attivi).
     */
    public abstract void rimuovi();

    /**
     * @brief Gestisce la richiesta di modifica dell'entità T.
     * * Gestisce la richiesta di modifica dell'entità T selezionata.
     * Tipicamente, apre il dialog di modifica con i campi pre-compilati.
     */
    public abstract void modifica();

    /**
     * @brief Mostra il dialog di modifica per l'entità selezionata.
     * * Mostra il dialog di modifica pre-compilato con i dati dell'entità selezionata.
     * @param elemento L'entità T da pre-compilare e modificare.
     * @return true se l'utente ha premuto OK, false se ha annullato.
     */
    public abstract boolean showEditDialog(T elemento);
    
    /**
     * @brief Mostra il dialog di creazione di una nuova entità.
     * * Mostra il dialog di creazione di una nuova entità T (con campi vuoti).
     * @return true se l'utente ha premuto OK, false se ha annullato.
     */
    public abstract boolean showEditDialog();

    /**
     * @brief Popola i campi della form con i dati dell'entità.
     * * Popola i campi del form UI o di un pannello di dettaglio con i dati dell'entità T.
     * @param elemento L'entità T con i dati da visualizzare.
     */
    public abstract void popolaForm(T elemento);

    /**
     * @brief Invia gli attributi modificati al gestore dati.
     * * Invia gli attributi modificati (recuperati dal form) al gestore dati.
     * Questo metodo chiama il metodo 'modifica' del Gestore<T> corrispondente.
     * @param attributi Mappa contenente i nomi dei campi (String) e i nuovi valori (Object).
     */
    public abstract void sendAttrbitui(Map<String, Object> attributi);
}
