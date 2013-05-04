Bibliothekenverwaltung
======================



Beschreibung
============

-	DHBW-Projekt/2. Semester
-	Einfache Bibliothekenverwaltung mit CSV-Dateien als Speicher.
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
-	Enthält Kernkomponenten wie CSV-Handler
-	Mapper für bestimmte CSV-Dateien

/core/Exception
-	Klassen für Ausnahmebehandlung

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