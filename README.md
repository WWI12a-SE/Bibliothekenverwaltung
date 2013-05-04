Bibliothekenverwaltung
======================



== Beschreibung ==
-	DHBW-Projekt/2. Semester
-	Einfache Bibliothekenverwaltung mit CSV-Dateien als Speicher.
-	Singe-User


== Konventionen ==
-	Klassen-, Methoden- und Variablennamen möglichst in englischer Sprache
-	Kommentare in deutsch



== Verzeichnisstruktur ==

/core
-	Enthält Kernkomponenten wie CSV-Handler
-	Handler für bestimmte CSV-Dateien. 

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