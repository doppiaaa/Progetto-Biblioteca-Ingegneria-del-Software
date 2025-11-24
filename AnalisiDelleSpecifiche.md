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

4. Casi D'uso

1)Nome: Registrazione utente    
 Attori partecipanti: Bibliotecario   
 Precondizioni: Bibliotecario loggato
 Post condizioni: il bibliotecario ha registrato un utente  
 Flusso eventi:  
   1 Bibliotecario seleziona "registra utente"  
   2 Bibliotecario inserisce nome, cognome, matricola, mail, lista dei prestiti attivi    
   3 account creato e salvato nel DB della biblioteca  

2)nome: modifica utente  
  attori partecipanti: bibliotecario  
  precondizioni:bibliotecario loggato e utente registrato    
  postcondizioni:utente modificato 
  flusso eventi:  
   1 il bibliotecario deve modificare i dati di un utente
   2 il bibliotecario cerca l'utente da modificare  
   3 il bibliotecario seleziona l'utente  
   4 il bibliotecario modifica i dati dell'utente    

3)nome: eliminazione utente  
 attori partecipanti:bibliotecario  
 precondizioni: bibliotecario loggato e utente registrato  
 postcondizioni: utente eliminato    
 flusso eventi:  
  1 il bibliotecario deve eliminare un utente dall'archivio  
  2 il bibliotecario cerca l'utente da eliminare  
  3 il bibliotecario seleziona l'utente da eliminare   
  4 il bibliotecario elimina l'utente dall'archivio  
  
4)nome: Prestito  
 attori partecipanti:Bibliotecario    
 precondizioni:bibliotecario loggato e utente registrato
 postcondizioni: l'utente ha ricevuto il libro in prestito
 flusso eventi:    
   1 il bibliotecario controlla se il libro che è stato richiesto dall'utente è presente in biblioteca  
   2 il bibliotecario controlla se l'utente ha più di tre libri già in prestito  
   3 il bibliotecario specifica la data entro il quale deve essere restituito  
   4 il libro viene marcato come in prestito e il catalogo viene aggiornato    
 flusso alternativo:  
   1a il libro non è presente  
   2a l'utente ha più di 3 libri in prestito   
   
 
5)nome: Restituzione  
  attori partecipanti:Bibliotecario   
  precondizioni: bibliotecario loggato utente registrato
  postcondizioni: l'utente ha restituito il libro in prestito  
  flusso eventi:  
   1 bibliotecrio controlla se il prestito è nell'elenco  
   2 viene confermata la restituzione e aggiornato il database  
   flusso alternativo:

6)nome: registrazione libro
 attori partecipanti:bibliotecario  
 precondizioni: bibliotecario loggato  
 postcondizioni: libro registrato    
 flusso eventi:  
  1 il bibliotecario riceve un libro nuovo  non presente in biblioteca
  2 il bibliotecario registra il titolo autore e isbn del libro  

7)nome: modifica libro  
  attori partecipanti: bibliotecario  
  precondizioni:bibliotecario loggato e libro già registrato  
  postcondizioni:libro modificato 
  flusso eventi:  
   1 il bibliotecario deve modificare i dati di un libro
   2 il bibliotecario cerca il libro da modificare  
   3 il bibliotecario seleziona il libro  
   4 il bibliotecario modifica i dati del libro  

8)nome: eliminazione libro  
 attori partecipanti:bibliotecario  
 precondizioni: bibliotecario loggato  
 postcondizioni: libro eliminato    
 flusso eventi:  
  1 il bibliotecario deve eliminare un libro dall'archivio  
  2 il bibliotecario cerca il libro da eliminare  
  3 il bibliotecario seleziona il libro da eliminare  
  4 il bibliotecario controlla se il libro è in prestito  
  5 il bibliotecario elimina il libro dall'archivio  
flussi alternativi:  
  4a il libro è attualmente in prestito  
  4a il bibliotecario non può eliminare il libro fin quando non è di nuovo disponibile  

  
 
   
 

    
    

