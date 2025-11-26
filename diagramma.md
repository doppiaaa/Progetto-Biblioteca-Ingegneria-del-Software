```plantuml
@startuml ...
@startuml Biblioteca

' Definisci gli Attori (l'attore principale è implicito dalla freccia iniziale)
actor Utente

' Definisci i Casi d'Uso (Usando il formato 'usecase Nome' o '(Nome)')
usecase Login
usecase GestioneUtenti
usecase AggiungiUtente
usecase ModificaUtente
usecase EliminaUtente
usecase RegistraPrestito
usecase LibroNonDisponibile
usecase UtenteNonAbilitatoAPrestito as UteNonAbl
usecase SelezionaLibro
usecase SelezionaUtente
usecase PrestitoRegistrato
usecase RegistraRestituzione
usecase ModificaLibri
usecase AggiungiLibro
usecase EliminaLibro
usecase ModificaLibro

' --- Relazioni Tra Attori e Casi d'Uso ---
' L'attore principale (Utente o Amministratore, non specificato) interagisce con le funzioni principali
' Assumiamo che l'attore 'Utente' sia l'entità che compie le azioni principali.

Utente --> Login
Utente --> GestioneUtenti
Utente --> RegistraPrestito
Utente --> RegistraRestituzione
Utente --> ModificaLibri

' --- Relazioni Include (<<include>>) ---
' L'include indica che un caso d'uso base include *obbligatoriamente* il comportamento del caso d'uso incluso.

GestioneUtenti .up.> AggiungiUtente : <<include>>
GestioneUtenti .up.> ModificaUtente : <<include>>
GestioneUtenti .up.> EliminaUtente : <<include>>

ModificaLibri .up.> AggiungiLibro : <<include>>
ModificaLibri .up.> ModificaLibro : <<include>>
ModificaLibri .up.> EliminaLibro : <<include>>

RegistraPrestito .down.> SelezionaLibro : <<include>>
RegistraPrestito .down.> SelezionaUtente : <<include>>
RegistraPrestito .down.> PrestitoRegistrato : <<include>>

' --- Relazioni Extend (<<extends>>) ---
' L'extend indica che il caso d'uso esteso può *opzionalmente* estendere il comportamento del caso d'uso base (in caso di condizioni specifiche).

LibroNonDisponibile .left.< RegistraPrestito : <<extends>>
UteNonAbl .left.< RegistraPrestito : <<extends>>

' Nota: AggiungiUtente e Login non hanno una relazione esplicita da PlantUML con include/extend
' Il diagramma originale mostra:
' AggiungiUtente <<include>> Login
' GestioneUtenti <<include>> Login
' Tuttavia, nel contesto di un sistema, solitamente il Login precede l'uso.
' Lo rappresento come nel tuo diagramma, ma tieni presente che è una convenzione insolita:

Login .left.< AggiungiUtente : <<include>>
Login .left.< GestioneUtenti : <<include>>

' --- Relazioni non standard (Text/Associazione) ---
' L'associazione semplice è una linea. "Text" è una label non standard; la tratto come una semplice associazione.

UteNonAbl -- SelezionaUtente : Text

' Simbolo di inizio processo (punto nero pieno) - lo collego al caso d'uso iniziale
(*) --> RegistraPrestito

@enduml
@enduml
```
