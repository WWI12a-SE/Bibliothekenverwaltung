Bibliothekenverwaltung
======================

DHBW-Projekt/2. Semester

Einfache Bibliothekenverwaltung mit CSV-Dateien als Speicher.


== Verzeichnisstruktur ==

/core
-	Enth�lt Kernkomponenten wie CSV-Handler
-	Handler f�r bestimmte CSV-Dateien. 

/models
-	Abstrahierte Datenmodelle der einzelnen CSV-Dateien


/controller
-	Controller f�r Benutzeroberfl�che. Logik f�r Benutzerebene geh�rt hier hin.

/view
-	GUI-Bestandteile

/data
-	Datenbestand:
		-	users.csv: Benutzer
		-	stock.csv: Bestand
		-	reservations.csv: Reservationen
		-	lastid.csv: Zuletzt genutzte hochgez�hlte IDs f�r o.g. Dateien