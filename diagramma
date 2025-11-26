```mermaid
graph TD
    %% Attori
    actor[Bibliotecario]

    %% Casi d'Uso - Utenti
    subgraph Gestione Utenti
        UC1(Registrazione Utente)
        UC2(Modifica Utente)
        UC3(Eliminazione Utente)
        UC4(Ricerca Utente)
    end

    %% Casi d'Uso - Libri
    subgraph Gestione Libri
        UC5(Aggiunta Libro)
        UC6(Modifica Libro)
        UC7(Eliminazione Libro)
        UC8(Ricerca Libro per Titolo/Autore/ISBN)
    end

    %% Casi d'Uso - Prestiti
    subgraph Gestione Prestiti
        UC9(Registrazione Prestito)
        UC10(Registrazione Restituzione)
        UC11(Visualizzazione Elenco Prestiti Attivi)
    end

    %% Accesso
    UC12(Accesso/Login Sistema)

    %% Relazioni tra Attore e Casi d'Uso
    actor --> UC12
    actor --> UC1
    actor --> UC2
    actor --> UC3
    actor --> UC4
    actor --> UC5
    actor --> UC6
    actor --> UC7
    actor --> UC8
    actor --> UC9
    actor --> UC10
    actor --> UC11

    %% Relazioni Include/Extend (Optional - per completezza, basato sui flussi)
    %% Per esempio, la Ricerca è una funzionalità che può essere inclusa nella Modifica/Eliminazione
    UC2 .> UC4 : <<include>>
    UC3 .> UC4 : <<include>>
    UC7 .> UC8 : <<include>>
'''
