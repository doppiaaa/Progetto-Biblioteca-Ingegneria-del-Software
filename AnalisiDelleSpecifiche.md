# ANALISI DELLE SPECIFICHE

- Obiettivo  
Il cliente richiede un'applicazione per gestire il catalogo, gli utenti e i prestiti di una biblioteca, in modo da rendere facili accessibili e veloci le operazioni di ricerca, pretito e restituzione dei libri. 

1. Tabella dei Requisiti
    
| Area dei Requisiti | ID univoco |
|----------|----------|
| Funzionalità individuali (**IF**)   | [IF-1.1](#accesso) , [IF-1.2](#utenti) , [IF-1.3](#utenti) , [IF-1.4](#libri) , [IF-1.5](#libri) |
| Business Flow (**BF**)   | [BF-1.1](#prestiti) , [BF-1.2](#prestiti) , [BF-1.3](#prestiti) , [BF-1.4](#prestiti)   |
| Data Format (**DF**)|[DF-1.1](#accesso) , [DF-1.2](#utenti) , [DF-1.3](#libri) |
| Interfaccia Utente (**UI**)|[UI-1.1](#utenti) , [UI-1.2](#libri) , [UI-1.3](#prestiti)|
| Ulteriori Vincoli (**FC**)|[FC-1.1](#altri) , [FC-1.2](#altri) , [FC-1.3](#altri) , [FC-1.4](#altri) , [FC-1.5](#altri) |

***
2. Requisiti Funzionali  
  - <a name="accesso">***Accesso***</a>        
DF-1.1 : I bibliotecari possono accedere al sistema attraverso usernsame e password    
IF-1.1 : Il sistema deve assicurare un metodo per recuperare o reimpostare le credenziali di accesso
  - <a name="utenti">***Utenti***</a>	             
      IF-1.2 : Un utente può essere registrato, modificato o cancellato dal bibliotecario  
      DF-1.2 : ogni utente avrà Nome, Cognome, Matricola, mail istituzionale, lista dei prstiti attivi	
      UI-1.1 : Il bibliotecario può visualizzare la lista di tutti gli utenti ordinata per cognome e nome	
      IF-1.3 : Gli utenti possono essere ricercati per cognome o matricola	
  - <a name="libri">***Libri***</a>        
  IF-1.4 : Un libro può essere aggiunto o rimosso        
      DF-1.3 : ogni Libro dovrà essere registrato attravero Titolo, Autori, Anno di pubblicazione, Codice ISBN, numero di copie	
      UI-1.2 : Il bibliotecario può visualizzare la lista di tutti i libri ordinati per titolo	
      IF-1.5 : I libri possono essere cercati per titolo, autore o codice ISBN	
  - <a name="prestiti">***Gestione prestiti***</a>        
        BF-1.1 : Il bibliotecario può registrare un prestito selezionando un libro e un utente e specificando la data della restituzione	
      BF-1.2 : Un libro può essere prestato solo se ci sono copie disponibili	
      BF-1.3 : Un utente non può avere più di tre libri in prestito contemporaneamente	
      UI-1.3 : Il bilbiotecario può visualizzare l'elenco dei prestiti attivi, ordinato per prevista data di restituzione (evidenziando i ritardi)	
      BF-1.4 : Il bibliotecario può registrare la restituzione di un libro	

3. <a name="altri">Requisiti non funzionali</a>	
 -  FC-1.1 : Sicurezza, deve essere garantito l'accesso ai soli bibliotecari

 -  FC-1.2 : Performance, bisogna garantire l'aggiornamento in tempo reale del catalogo e della disponibilità dei libri, e la ricerca deve rimanere nel giro dei pochi secondi

 -  FC-1.3 : Usabilità, deve avere un interfaccia grafica facile da capire e utilizzare

 -  FC-1.4 : Compatibilità, deve garantire utilizzo su vari sistemi operativi

 -  FC-1.5 : Scalabilità, il sistema deve essere in grado di essere modificato per gestire un ipotetico volume maggiore di libri, utenti e prestiti
      
