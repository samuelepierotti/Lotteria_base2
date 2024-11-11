<h1 align="center">Lotteria</h1>

<p align="center" style="font-family: monospace">Pierotti Samuele</p>

## Obiettivo del Progetto
Il progetto prevede lo sviluppo di un'applicazione Java multithreading che genera numeri casuali e li memorizza in una matrice di dimensione nxn. I giocatori partecipanti possono selezionare un numero e verificare se è tra quelli estratti. Solo i primi tre giocatori che indovinano un numero vinceranno un premio.

## Classi Implementate

### 1. Lotteria
Questa classe è il punto di partenza del gioco. Si occupa di:
- Richiedere all'utente la dimensione della matrice
- Creare e gestire i thread sia per l'estrazione dei numeri sia per i giocatori
- Garantire la sincronizzazione tra i diversi thread

### 2. Estrazione
Questo thread è responsabile dell'estrazione dei numeri e della verifica delle scelte dei giocatori. I suoi compiti includono:
- Creare una matrice di numeri casuali con dimensioni nxn
- Confrontare i numeri scelti dai giocatori con quelli presenti nella matrice
- Gestire un array che tiene traccia dei vincitori

#### Funzionamento del metodo `verifica()`

```java
public void verifica(int numero, int idGiocatore) {
    // Scorre la matrice alla ricerca del numero scelto
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            // Se trova il numero
            if (numero == numeri[i][j]) {
                // Aggiunge il giocatore all'array dei vincitori, se non ci sono già 3 vincitori
                if (contawin < 3) {
                    vincitori[contawin] = idGiocatore;
                    contawin++;
                }
                verifica = true;
            }
        }
    }
    // Se il numero non è presente, lo comunica al giocatore
    if (!verifica) {
        System.out.println("Che peccato giocatore " + idGiocatore + 
                          ", il numero " + numero + " non è vincente!");
    }
}
```

Questo metodo:
- Riceve in input il numero selezionato e l'ID del giocatore
- Cerca il numero all'interno della matrice
- Se il numero è trovato e ci sono meno di tre vincitori, aggiunge il giocatore all'array dei vincitori

### 3. Giocatore
Il thread del giocatore consente al giocatore di partecipare alla lotteria. Le sue responsabilità sono:
- Permettere al giocatore di scegliere un numero
- Avviare un conto alla rovescia prima di verificare il numero selezionato
- Inviare il numero scelto per la verifica nella classe `Estrazione`

## Librerie Utilizzate
Il progetto fa uso delle seguenti librerie Java:
- `java.io.BufferedReader`: per la gestione degli input da parte dell'utente
- `java.io.InputStreamReader`: per la lettura dello stream di input
- `java.util.Random`: per generare numeri casuali
- `java.util.logging`: per la gestione dei log e degli errori
- `java.util.Scanner`: per gestire input (anche se nel codice corrente non viene effettivamente utilizzata)

## Possibili Scenari di Esecuzione

### 1. Errori di Input
- **Dimensione della matrice non valida**:
  - Gestito tramite blocchi try-catch nel metodo `main` della classe `Lotteria`
  - Viene generato un log dell'errore
  - L'utente riceve un messaggio di errore

- **Numero scelto non valido**:
  - Gestito tramite blocchi try-catch nel metodo `run` della classe `Giocatore`
  - Viene generato un log dell'errore
  - L'utente viene informato dell'errore

### 2. Errori di Esecuzione
- **Interruzione dei thread**:
  - Gestita tramite try-catch quando viene chiamato il metodo `join()`
  - Viene generato un log dell'errore
  - Il gioco potrebbe terminare prematuramente

- **Errori durante il `sleep`**:
  - Gestiti nel conto alla rovescia del giocatore
  - Viene registrato un log dell'errore
  - Il gioco prosegue senza il ritardo previsto

## Fasi dell'Esecuzione

1. **Avvio del Gioco**:
   - Viene chiesto all'utente di inserire la dimensione della matrice
   - Viene avviato il thread di estrazione che genera la matrice dei numeri

2. **Svolgimento del Gioco**:
   - Vengono avviati i thread dei giocatori
   - Ogni giocatore inserisce il proprio numero
   - Parte un conto alla rovescia individuale per ciascun giocatore
   - Il numero scelto viene verificato e il risultato viene notificato al giocatore

3. **Conclusione del Gioco**:
   - Il programma attende il termine di tutti i thread
   - Viene visualizzata la lista dei vincitori
   - Il gioco termina con un messaggio di chiusura
