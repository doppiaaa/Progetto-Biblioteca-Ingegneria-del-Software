# ANALISI DELLE SPECIFICHE

- Obiettivo  
Il cliente richiede un'applicazione per gestire il catalogo, gli utenti e i prestiti di una biblioteca, in modo da rendere facili accessibili e veloci le operazioni di ricerca, pretito e restituzione dei libri. 

1. Requisiti Funzionali  
  - Accesso	
      DF. I bibliotecari sono gli unici ad aver accesso al sistema attraverso usernsame e password
  - Utenti	
      IF. Un utente può essere registrato, modificato o cancellato dal bibliotecario  
      DF: ogni utente avrà Nome, Cognome, Matricola, mail istituzionale, lista dei prstiti attivi	
      UI. Il bibliotecario può visualizzare la lista di tutti gli utenti ordinata per cognome e nome	
      IF. Gli utenti possono essere ricercati per cognome o matricola	
  - Libri	
      IF: Un libro può essere aggiunto o rimosso	
      DF: ogni Libro dovrà essere registrato attravero Titolo, Autori, Anno di pubblicazione, Codice ISBN, numero di copie	
      UI. Il bibliotecario può visualizzare la lista di tutti i libri ordinati per titolo	
      IF. I libri possono essere cercati per titolo, autore o codice ISBN	
  - Gestione prestiti	
      BF. Il bibliotecario può registrare un prestito seleionando un libro e un utente e specificando la data della restituzione	
      BF. Un libro può essere prestato solo se ci sono copie disponibili	
      BF. Un utente non può avere più du tre libri in prestito contemporaneamente	
      UI. Il bilbiotecario può visualizzare l'elenco dei prestiti attivi, ordinato per prevista data di restituzione (evidenziando i ritardi)	
      BF. Il bibliotecario può registrare la restituzione di un libro	

2. Requisiti non funzionali	
 -  Sicurezza: deve essere garantito l'accesso ai soli bibliotecari

 -  Performance: bisogna garantire l'aggiornamento in tempo reale del catalogo e della disponibilità dei libri, e la ricerca deve rimanere nel giro dei pochi secondi

 -  Usabilità: deve avere un interfaccia grafica facile da apprendere e usare

 -  Compatibilità: deve garantire utilizzo su vari sistemi operativi

 -  Scalabilità: il sistema deve essere in grado di essere modificato per gestire un ipotetico volume maggiore di libri, utenti e prestiti
      
