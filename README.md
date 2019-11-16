# Intelligente Software Systeme Seminar 
Sommersemester 2019, Technische Universität Berlin 

![](aot-logo.png) <img src="tu_logo.png"  width="200" height="150">

##### Betreuer: Dr.-Ing. Stefan Fricke 

## Implementierung
Klassen: 
- __CreateCustomFormatter.java__ für den Logger
- __Vertex.java__ definiert die Eigenschaften eines Knotens im Spielbaums (Wert, Kosten an den Kanten, Name, ID, ...)
- __Graph.java__ definiert die Methoden des Spielbaums. Enthält die __main Methode__ zum Starten der Simulation. 

## Starten der Simulation
### Parameter wählen
Es können 6 Parameter vor Beginn der Simulation gesetzt werden. Eine Anpassung während der Laufzeit ist nicht möglich. 
Bitte setzten Sie die Parameter wie gewünscht in der __Graph.java__ Klasse in Zeile 1134 - 1139 *(Suche Str + f nach "SETUP PARAMETERS")*

- DEFAULT_PROTECTION_LEVEL: Ein Interger aus der Menge {1, 2, 3}. Entspricht L1, L2 & L3 *(vgl. Seminararbeit S. 4 Def. 2)* 
- DEFAULT_DEFENDER_VALUE: Startwert der Bewertungsfunktion der Verteidigerin
- DEFAULT_ATTACKER_VALUE: Startwert der Bewertungsfunktion des Verteidigers
- nodesToProtect: Ein Integer zwischen 0 und 30 der beschreibt, wie viele Knoten die Verteidigerin pro Runde schützt. 
- smartProtect: true/false *(vgl. Seminararbeit S. 5 Def. 3)*
- conditionalNode: true/false  *(vgl. Seminararbeit S. 4 Kapitel 5.1.2)* 

### Starten der Simulation
Starten Sie die Main Methode in __Graph.java__. 
Sie können hier als 1. Paramter den Explorations-Algorithmus wählen (__ForwardPropagate__ oder __RandomChoice__). 


## Analyse der Simulation
Die Ergebnisse jeder Runde der Simulation werden in der logs.txt Datei geloggt. Der Aufbau einer Logdatei ist in der *Seminararbeit auf 
Seite 4 Kapitel 5.2.* beschrieben. 

Tabellarische Aufbereitungen der Logs finden Sie zudem in der Seminararbeit. 

