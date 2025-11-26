```plantuml
@startuml Biblioteca

actor Utente

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
Utente --> Login
Utente --> GestioneUtenti
Utente --> RegistraPrestito
Utente --> RegistraRestituzione
Utente --> ModificaLibri

' --- Relazioni Include (<<include>>) ---
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
' Il caso base (RegistraPrestito) punta al caso esteso
**RegistraPrestito .right.> LibroNonDisponibile : <<extends>>**
**RegistraPrestito .right.> UteNonAbl : <<extends>>**

' --- Relazioni Login (correzione della direzione per l'include) ---
' I casi d'uso (Aggiungi/Gestione) includono Login
**AggiungiUtente .right.> Login : <<include>>**
**GestioneUtenti .right.> Login : <<include>>**

' --- Relazioni non standard (Text/Associazione) ---
UteNonAbl -- SelezionaUtente : Text

' Simbolo di inizio processo
(*) --> RegistraPrestito

@enduml

@enduml
```
