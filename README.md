Spacebots è un programma che simula dei robot nello spazio che ho fatto per l'esame di __[Metodologie Di Programmazione](http://www.didattica.cs.unicam.it/doku.php?id=didattica:ay2223:mp:main)__. In questo spazio ci sono 2 entità: i robot e delle aree.<br><br>
Le aree possono essere di forma circolare o rettangolare e possiedono una label che è semplicemente una stringa.<br><br>
I robot sono rappresentati da una forma circolare e essi possono eseguire del codice scritto in una sintassi inventata.<br><br>
La simulazione viene eseguita manualmente andando avanti o indietro di  x  step che possono essere decisi dall'utente. Ogni step di simulazione rappresenta un secondo.<br>
Per informazioni più dettagliate potete consultare la __[specifica di progetto](/docs/spacebots.pdf)__.

2. __[Sintassi](#sintassi)__
3. __[GUI](#GUI)__
4. __[Esempi](#Examples)__
   1. __[Pong](#pong)__
   2. __[Bounce](#bounce)__
   3. __[Followus](#followus)__
   1. __[Delivery](#delivery)__
5. __[Eseguire il progetto](#eseguire-il-progetto)__
6. __[Conclusioni](#conclusioni)__


## Sintassi
Ogni comando deve essere scritto su una riga.
<table width="100%" style="text-align: center;">
  <tr>
    <th width="50%"> Comando </th>
    <th width="50%"> Descrizione </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> MOVE x y s</th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot si muove nella direzione data dal vettore  (x,y)  alla velocità  s  </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> MOVE RANDOM  x1 x2 y1 y2 s  </th>
    <th  markdown="1" width="50%" style="text-align: justify;"> Il robot si muove nella direzione casuale data dal vettore (x,y) generato dal range  x1,x2  e  y1,y2  e si muove alla velocità  s  </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> SIGNAL  label  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot segnala label  label </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> UNSIGNAL  label  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot smette di segnalare la label  label </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> FOLLOW  label dist s  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot si muove muove alla velocità s in una
      direzione che è la media delle posizioni dei robot che segnalano la condizione  label  e
      che si trovano ad una distanza minore o uguale a  dist .
    </th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> CONTINUE  s  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot continua a muoversi alla direzione e velocità attuali per  s  "secondi"</th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> STOP </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il robot cessa ogni movimento</th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> REPEAT  n  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il codice dentro questo blocco viene eseguito per  n  volte</th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> UNTIL  label  </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il codice dentro questo blocco viene eseguito per fino a quando il robot non entra in contatto con una label  label </th>
  </tr>
    <tr>
    <th markdown="1" width="50%"> DO FOREVER </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Il codice dentro questo blocco viene eseguito per sempre</th>
  </tr>
  <tr>
    <th markdown="1" width="50%"> DONE </th>
    <th markdown="1" width="50%" style="text-align: justify;"> Delimita la fine di un loop</th>
  </tr>
</table>

<details markdown="1"><summary style="font-size: 1.54rem;color: var(--heading-color);"><a name="GUI">La GUI</a></summary>
Il programma presenta molte funzioni ma non tutte sono state implementate perchè non erano richieste nella specifica del progetto.<br><br><br>

![Schermata 1](/docs/schermata_1.png "schermata 1")
<br><br>
In questa tab si possono caricare il programma e le forme da file di testo. L'esplora file si aprirà automaticamente su una cartella dove ho provveduto degli esempi. Ogni esempio ha un file  readme  con spiegato come far funzionare la simulazione e il comportamento che avranno i robot.
<br><br><br>

![Schermata 2](/docs/schermata_2.png "schermata 2")
<br><br>
Qui abbiamo tre bottoni: il primo per creare uno sciame aventi il programma caricato il precedenza e creare le aree che avranno come label  prova . La scelta delle dimensioni e il numero di robot nello sciame non possono essere modificati da GUI.
<br><br><br>

![Schermata 3](/docs/schermata_3.png "schermata 3")
<br><br>
Qui ci sarebbe stata l'opzione di eliminare elementi singoli o direttamente tutta la simulazione ma non è stato implementato nulla.
<br><br><br>

![Schermata 4](/docs/schermata_4.png "schermata 4")
<br><br>
Con la freccia destra possiamo andare avanti nella simulazione in base a quanti  step  sono stati scelti e con la freccia sinistra si può tornare indietro.
<br><br><br>

![Schermata 5](/docs/schermata_5.png "schermata 5")
<br><br>
Dentro questa tab ci sarebbero stati i controlli per muovere la telecamera. Si sarebbe potuto ingrandire/rimpicciolire la simulazione e con le frecce muoversi in tutte e quattro le direzioni ma nulla è stato implementato.
<br><br><br>

</details>

# <a name="Examples">Esempi</a>

## Pong

<img src="/docs/examples/pong.gif" style="height: 500px;">
<br><br>
Questo è l'esempio più semplice di tutti. I robot rimbalzano a destra e a sinistra come nel gioco pong.
<br><br><br>

### Bounce
<img src="/docs/examples/bounce.gif" style="height: 500px;">
<br><br>
In questo esempio i robot rimbalzano sulla piattaforma sottostante come se fossero delle palline. I robot poi cadranno dall'estremità destra all'infinito.
<br><br><br>

### Followus
<img src="/docs/examples/followus.gif" style="height: 500px;">
<br><br>
In questo esempio i robot sciameranno dritti verso i robot dentro al cerchio che stanno segnalando la propria posizione.
<br><br><br>

### Delivery
<img src="/docs/examples/delivery.gif" style="height: 500px;">
<br><br>
Questo è l'esempio più complesso ma anche il più bello. I robot al centro sono "lavoratori alle poste" che segnalano la propria posizione e "consegnano pacchi" ai postini. Quest'ultimi sono i robot all'esterno che sciameranno verso le poste a prendere un pacco per poi partire in una direzione casuale per consegnarlo.
<br><br><br>


## Eseguire il progetto
Il codice è pubblico sul mio __[GitHub](https://github.com/PettingStrings/Spacebots)__.<br>
Il progetto usa __[Gradle 8.0](https://gradle.org/)__ e __[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)__.<br>
Per avviare l'applicazione basta eseguire il comando:
```
gradle run
```

## Conclusioni
Per sapere di più sul codice consultare la __[documentazione](https://docs.google.com/document/d/1W5g27gNl24ps0Wu1DAcpDyitIroVMiFuPFKGfDjepqE/edit?usp=sharing)__ o generare il javadoc con il comando<br>
```
gradle javadoc
```