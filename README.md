Bibliothekenverwaltung
======================



Beschreibung
============

-	DHBW-Projekt/2. Semester
-	Einfache Bibliothekenverwaltung mit CSV-Dateien als Speicher
-	Singe-User


Konventionen
============

-	Klassen-, Methoden- und Variablennamen möglichst in englischer Sprache
-	Kommentare in deutsch
-	Am Anfang jeder Klasse steht ein Kommentar mit Beschreibung
-	Über jeder Methode steht ein Kommentar mit Beschreibung, erwarteten Parametern und Rückgabe
-	Paket- und Importangaben stehen kombiniert nach dem Einleitungskommentar ("package...", "core...")



Verzeichnisstruktur
===================

/core
-	Enthält den CSV-Handler (Kern)
-	Mapper für bestimmte CSV-Dateien

/core/Exception
-	Klassen für Ausnahmebehandlung

/core/opencsv
-	Drittanbieterklassen (Apache-Lizenz)

/models
-	Abstrahierte Datenmodelle der einzelnen CSV-Dateien

/controller
-	Controller für Benutzeroberfläche. Logik für Benutzerebene gehört hier hin.

/view
-	GUI-Bestandteile

/data
-	Datenbestand:
		-	users.csv: Benutzer
		-	stock.csv: Bestand
		-	reservations.csv: Reservationen
		-	lastid.csv: Zuletzt genutzte hochgezählte IDs für o.g. Dateien
		
/test
-	Ausführbare Tests

Programm für UML-Diagramm:
ObjectAid
GEF - Graphical Eclipse Framework //Kann sein dass das auch benötigt wird

Beide Über Eclipse automatisch installierbar (URL selbst angeben)
